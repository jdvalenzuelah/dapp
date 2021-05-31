// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

contract Token {
    
    
    struct TransactionLog {
        address from;
        address to;
        uint256 amount;
        uint256 dateTs;
    }
    
    struct Account {
        address addr;
        uint256 balance;
    }
    
    string public name = "Rombit";
    string public symbol = "RMBT";
    uint256 public totalSupply;
    mapping(address => uint256) public balanceOf;
    mapping(address => mapping(address => uint256)) public allowance;

    address contractOwner;
    
    TransactionLog[] private transactionLogs;
    
    //https://medium.com/bandprotocol/solidity-102-3-maintaining-sorted-list-1edd0a228d83
    mapping(address => address) private _nextAccount;
    uint256 public accountsCount;
    address constant GUARD = address(1);

    
    event Transfer(address indexed _from, address indexed _to, uint256 _value);
    
    event Approval(address indexed _owner, address indexed _spender, uint256 _value);
    
    constructor(uint256 _initSupply) {
        _nextAccount[GUARD] = GUARD;
        addAccount(msg.sender, _initSupply);
        totalSupply = _initSupply;
        contractOwner = msg.sender;
    }

    modifier onlyIfOwner() {
        require(msg.sender == contractOwner);
        _;
    }
    
    function addSupply(uint256 number) public onlyIfOwner {
        uint256 newValue = balanceOf[contractOwner] + number;
        balanceOf[contractOwner] = newValue;
         if(_nextAccount[contractOwner] == address(0)) {
            addAccount(contractOwner, newValue);
        } else {
            updateAccount(contractOwner, newValue);
        }
    }
    
    function transfer(address _to, uint256 _value) public returns (bool success) {
        require(balanceOf[msg.sender] >= _value);
        
        uint256 fromNewValue = balanceOf[msg.sender] - _value;
        updateAccount(msg.sender, fromNewValue);
        
        uint256 toNewValue = balanceOf[_to] + _value;
        
        if(_nextAccount[_to] == address(0)) {
            addAccount(_to, toNewValue);
        } else {
            updateAccount(_to, toNewValue);
        }
        
        logTransaction(msg.sender, _to, _value); 
        
        emit Transfer(msg.sender, _to, _value);
        return true;
    }
    
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
        require(balanceOf[_from] >= _value);
        require(allowance[_from][msg.sender] >= _value);
        
        uint256 fromNewValue = balanceOf[_from] - _value;
        updateAccount(_from, fromNewValue);
        
        uint256 toNewValue = balanceOf[_to] + _value;
        if(_nextAccount[_to] == address(0)) {
            addAccount(_to, toNewValue);
        } else {
            updateAccount(_to, toNewValue);
        }
        
        allowance[_from][msg.sender] -= _value;
        
        logTransaction(msg.sender, _to, _value); 

        emit Transfer(_from, _to, _value);
        
        return true;
    }
    
    function approve(address _spender, uint256 _value) public returns (bool success) {
        allowance[msg.sender][_spender] = _value;
        
        emit Approval(msg.sender, _spender, _value);
        
        return true;
    }
    
    function logTransaction(address from, address to, uint256 amount) private returns (bool success) {
        
        TransactionLog memory transaction = TransactionLog(from, to, amount, block.timestamp); 
        transactionLogs.push(transaction);
        
        return true;
    }
    
    function getTransactionLog(uint n) public view returns (address from, address to, uint256 amount, uint ts) {
        uint i = n % 19;
        uint index = i >= transactionLogs.length ? transactionLogs.length - 1 : i;
        
        TransactionLog memory transaction = transactionLogs[index];
        return (transaction.from, transaction.to, transaction.amount, transaction.dateTs);
    } 
    
    function getTopNAccount(uint256 n) public view returns(address account, uint256 balance, uint256 indexx) {
        uint256 index = n >= accountsCount ? transactionLogs.length - 1 : n;
        return getTopAccounts(index);
    }
    
    function getTopAccounts(uint256 n) private view returns(address account, uint256 balance, uint256 index) {
        require(n <= accountsCount);
        address curr = _nextAccount[GUARD];
        uint256 i = 0;
        for(i = 0; i < n; i++) {
            curr = _nextAccount[curr];
        }
        return (curr, balanceOf[curr], i);
    }
    
    function _verifyIndex(address prevAcc, uint256 newValue, address nextAcc) internal view returns(bool) {
        return (prevAcc == GUARD || balanceOf[prevAcc] >= newValue) &&
                (nextAcc == GUARD || newValue > balanceOf[nextAcc]);
    }
    
    function _findIndex(uint256 newValue) internal view returns(address newIndex) {
        address candidate = GUARD;
        while(true) {
            if(_verifyIndex(candidate, newValue, _nextAccount[candidate])) {
                return candidate;
            }
            candidate = _nextAccount[candidate];
        }
    }
    
    function _isPrevAcc(address acc, address prevAcc) internal view returns (bool) {
        return _nextAccount[prevAcc] == acc;
    }
    
    function _findPrevAccount(address account) internal view returns (address prev) {
        address curr = GUARD;
        while(_nextAccount[curr] != GUARD) {
            if(_isPrevAcc(account, curr)) {
                return curr;
            }
            curr = _nextAccount[curr];
        }
    }
    
    function addAccount(address account, uint256 initVal) internal {
        require(_nextAccount[account] == address(0));
        address index = _findIndex(initVal);
        balanceOf[account] = initVal;
        _nextAccount[account] = _nextAccount[index];
        _nextAccount[index] = account;
        accountsCount++;
    }
    
    function removeAccount(address account) private {
        require(_nextAccount[account] != address(0));
        address prevAcc = _findPrevAccount(account);
        _nextAccount[prevAcc] = _nextAccount[account];
        _nextAccount[account] = address(0);
        balanceOf[account] = 0;
        accountsCount--;
    }
    
    function updateAccount(address account, uint256 newValue) internal {
        require(_nextAccount[account] != address(0));
        address prevAcc = _findPrevAccount(account);
        address nextAcc = _nextAccount[account];
        if(_verifyIndex(prevAcc, newValue, nextAcc)) {
            balanceOf[account] = newValue;
        } else {
            removeAccount(account);
            addAccount(account, newValue);
        }
    }

    
}

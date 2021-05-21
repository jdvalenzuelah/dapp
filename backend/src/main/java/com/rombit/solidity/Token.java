package com.rombit.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Token extends Contract {
    public static final String BINARY = "60c06040526006608081905265149bdb589a5d60d21b60a09081526200002991600091906200025a565b50604080518082019091526004808252631493509560e21b602090920191825262000057916001916200025a565b503480156200006557600080fd5b506040516200115538038062001155833981016040819052620000889162000300565b6001600081905260066020527f3e5fec24aa4dc4e5aee2e025e51e1392c72a2500577559fae9665c6d52bd6a3180546001600160a01b0319169091179055620000d23382620000db565b60025562000378565b6001600160a01b0382811660009081526006602052604090205416156200010157600080fd5b60006200010e8262000182565b6001600160a01b038481166000818152600360209081526040808320889055858516808452600690925280832080548585529184208054929096166001600160a01b0319928316179095559082528354169091179091556007805492935090620001788362000350565b9190505550505050565b600060015b6001600160a01b03808216600090815260066020526040902054620001b1918391869116620001e7565b15620001bf579050620001e2565b6001600160a01b039081166000908152600660205260409020541662000187565b505b919050565b60006001600160a01b038416600114806200021a57506001600160a01b0384166000908152600360205260409020548311155b80156200025257506001600160a01b038216600114806200025257506001600160a01b03821660009081526003602052604090205483115b949350505050565b828054620002689062000319565b90600052602060002090601f0160209004810192826200028c5760008555620002d7565b82601f10620002a757805160ff1916838001178555620002d7565b82800160010185558215620002d7579182015b82811115620002d7578251825591602001919060010190620002ba565b50620002e5929150620002e9565b5090565b5b80821115620002e55760008155600101620002ea565b60006020828403121562000312578081fd5b5051919050565b6002810460018216806200032e57607f821691505b60208210811415620001e057634e487b7160e01b600052602260045260246000fd5b60006000198214156200037157634e487b7160e01b81526011600452602481fd5b5060010190565b610dcd80620003886000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806370a082311161007157806370a082311461011c57806395d89b411461012f578063a9059cbb14610137578063b1e1e67a1461014a578063c5a9308d1461016c578063dd62ed3e1461018f576100a9565b806306fdde03146100ae578063095ea7b3146100cc57806318160ddd146100ec57806323b872dd1461010157806327538b3014610114575b600080fd5b6100b66101a2565b6040516100c39190610c6f565b60405180910390f35b6100df6100da366004610bd9565b610230565b6040516100c39190610c64565b6100f461029a565b6040516100c39190610cc2565b6100df61010f366004610b9e565b6102a0565b6100f4610420565b6100f461012a366004610b4b565b610426565b6100b6610438565b6100df610145366004610bd9565b610445565b61015d610158366004610c02565b610549565b6040516100c393929190610c43565b61017f61017a366004610c02565b610587565b6040516100c39493929190610c1a565b6100f461019d366004610b6c565b610647565b600080546101af90610d11565b80601f01602080910402602001604051908101604052809291908181526020018280546101db90610d11565b80156102285780601f106101fd57610100808354040283529160200191610228565b820191906000526020600020905b81548152906001019060200180831161020b57829003601f168201915b505050505081565b3360008181526004602090815260408083206001600160a01b038716808552925280832085905551919290917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92590610289908690610cc2565b60405180910390a350600192915050565b60025481565b6001600160a01b0383166000908152600360205260408120548211156102c557600080fd5b6001600160a01b03841660009081526004602090815260408083203384529091529020548211156102f557600080fd5b6001600160a01b038416600090815260036020526040812054610319908490610ce3565b90506103258582610664565b6001600160a01b038416600090815260036020526040812054610349908590610ccb565b6001600160a01b038681166000908152600660205260409020549192501661037a5761037585826106fa565b610384565b6103848582610664565b6001600160a01b0386166000908152600460209081526040808320338452909152812080548692906103b7908490610ce3565b909155506103c8905033868661079c565b50846001600160a01b0316866001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8660405161040c9190610cc2565b60405180910390a350600195945050505050565b60075481565b60036020526000908152604090205481565b600180546101af90610d11565b3360009081526003602052604081205482111561046157600080fd5b3360009081526003602052604081205461047c908490610ce3565b90506104883382610664565b6001600160a01b0384166000908152600360205260408120546104ac908590610ccb565b6001600160a01b03868116600090815260066020526040902054919250166104dd576104d885826106fa565b6104e7565b6104e78582610664565b6104f233868661079c565b50846001600160a01b0316336001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040516105369190610cc2565b60405180910390a3506001949350505050565b60008060008060075485101561055f578461056e565b60055461056e90600190610ce3565b9050610579816108a0565b935093509350509193909250565b600080808080610598601387610d61565b6005549091506000908210156105ae57816105bd565b6005546105bd90600190610ce3565b90506000600582815481106105e257634e487b7160e01b600052603260045260246000fd5b60009182526020918290206040805160808101825260049390930290910180546001600160a01b039081168085526001830154909116948401859052600282015492840183905260039091015460609093018390529b929a5098509650945050505050565b600460209081526000928352604080842090915290825290205481565b6001600160a01b038281166000908152600660205260409020541661068857600080fd5b60006106938361094a565b6001600160a01b03808516600090815260066020526040902054919250166106bc8284836109ae565b156106e1576001600160a01b03841660009081526003602052604090208390556106f4565b6106ea84610a1e565b6106f484846106fa565b50505050565b6001600160a01b03828116600090815260066020526040902054161561071f57600080fd5b600061072a82610ab9565b6001600160a01b038481166000818152600360209081526040808320889055858516808452600690925280832080548585529184208054929096166001600160a01b031992831617909555908252835416909117909155600780549293509061079283610d46565b9190505550505050565b604080516080810182526001600160a01b039485168152928416602084019081529083019182524260608401908152600580546001808201835560009290925294517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0600490960295860180546001600160a01b031990811692891692909217905592517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db18601805490941696169590951790915590517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db2830155517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db39091015590565b60008060006007548411156108b457600080fd5b6001600090815260066020527f3e5fec24aa4dc4e5aee2e025e51e1392c72a2500577559fae9665c6d52bd6a31546001600160a01b0316905b85811015610925576001600160a01b03918216600090815260066020526040902054909116908061091d81610d46565b9150506108ed565b6001600160a01b03821660009081526003602052604090205491969195509350915050565b600060015b6001600160a01b03818116600090815260066020526040902054166001146109a75761097b8382610b12565b156109875790506109a9565b6001600160a01b039081166000908152600660205260409020541661094f565b505b919050565b60006001600160a01b038416600114806109e057506001600160a01b0384166000908152600360205260409020548311155b8015610a1657506001600160a01b03821660011480610a1657506001600160a01b03821660009081526003602052604090205483115b949350505050565b6001600160a01b0381811660009081526006602052604090205416610a4257600080fd5b6000610a4d8261094a565b6001600160a01b038381166000818152600660209081526040808320805487871685528285208054919097166001600160a01b031991821617909655938352835490941690925560039091529081208190556007805492935090610ab083610cfa565b91905055505050565b600060015b6001600160a01b03808216600090815260066020526040902054610ae69183918691166109ae565b15610af25790506109a9565b6001600160a01b0390811660009081526006602052604090205416610abe565b6001600160a01b03908116600090815260066020526040902054811691161490565b80356001600160a01b03811681146109a957600080fd5b600060208284031215610b5c578081fd5b610b6582610b34565b9392505050565b60008060408385031215610b7e578081fd5b610b8783610b34565b9150610b9560208401610b34565b90509250929050565b600080600060608486031215610bb2578081fd5b610bbb84610b34565b9250610bc960208501610b34565b9150604084013590509250925092565b60008060408385031215610beb578182fd5b610bf483610b34565b946020939093013593505050565b600060208284031215610c13578081fd5b5035919050565b6001600160a01b0394851681529290931660208301526040820152606081019190915260800190565b6001600160a01b039390931683526020830191909152604082015260600190565b901515815260200190565b6000602080835283518082850152825b81811015610c9b57858101830151858201604001528201610c7f565b81811115610cac5783604083870101525b50601f01601f1916929092016040019392505050565b90815260200190565b60008219821115610cde57610cde610d81565b500190565b600082821015610cf557610cf5610d81565b500390565b600081610d0957610d09610d81565b506000190190565b600281046001821680610d2557607f821691505b602082108114156109a757634e487b7160e01b600052602260045260246000fd5b6000600019821415610d5a57610d5a610d81565b5060010190565b600082610d7c57634e487b7160e01b81526012600452602481fd5b500690565b634e487b7160e01b600052601160045260246000fdfea26469706673582212200567abadad9cce50c57d4b6e372e6f2863563985190deff5874f6259067444f064736f6c63430008010033";

    public static final String FUNC_ACCOUNTSCOUNT = "accountsCount";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETTOPNACCOUNT = "getTopNAccount";

    public static final String FUNC_GETTRANSACTIONLOG = "getTransactionLog";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Token(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Token(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> accountsCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ACCOUNTSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> allowance(String param0, String param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> getTopNAccount(BigInteger n) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOPNACCOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(n)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple4<String, String, BigInteger, BigInteger>> getTransactionLog(BigInteger n) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRANSACTIONLOG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(n)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Token load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Token load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Token(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Token(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Token> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _initSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initSupply)));
        return deployRemoteCall(Token.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Token> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initSupply)));
        return deployRemoteCall(Token.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initSupply)));
        return deployRemoteCall(Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initSupply)));
        return deployRemoteCall(Token.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String _owner;

        public String _spender;

        public BigInteger _value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;

        public BigInteger _value;
    }
}

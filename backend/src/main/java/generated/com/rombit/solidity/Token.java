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
import org.web3j.abi.datatypes.DynamicArray;
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
    public static final String BINARY = "60c06040526006608081905265149bdb589a5d60d21b60a09081526200002991600091906200025a565b50604080518082019091526004808252631493509560e21b602090920191825262000057916001916200025a565b503480156200006557600080fd5b506040516200126c3803806200126c833981016040819052620000889162000300565b6001600081905260066020527f3e5fec24aa4dc4e5aee2e025e51e1392c72a2500577559fae9665c6d52bd6a3180546001600160a01b0319169091179055620000d23382620000db565b60025562000378565b6001600160a01b0382811660009081526006602052604090205416156200010157600080fd5b60006200010e8262000182565b6001600160a01b038481166000818152600360209081526040808320889055858516808452600690925280832080548585529184208054929096166001600160a01b0319928316179095559082528354169091179091556007805492935090620001788362000350565b9190505550505050565b600060015b6001600160a01b03808216600090815260066020526040902054620001b1918391869116620001e7565b15620001bf579050620001e2565b6001600160a01b039081166000908152600660205260409020541662000187565b505b919050565b60006001600160a01b038416600114806200021a57506001600160a01b0384166000908152600360205260409020548311155b80156200025257506001600160a01b038216600114806200025257506001600160a01b03821660009081526003602052604090205483115b949350505050565b828054620002689062000319565b90600052602060002090601f0160209004810192826200028c5760008555620002d7565b82601f10620002a757805160ff1916838001178555620002d7565b82800160010185558215620002d7579182015b82811115620002d7578251825591602001919060010190620002ba565b50620002e5929150620002e9565b5090565b5b80821115620002e55760008155600101620002ea565b60006020828403121562000312578081fd5b5051919050565b6002810460018216806200032e57607f821691505b60208210811415620001e057634e487b7160e01b600052602260045260246000fd5b60006000198214156200037157634e487b7160e01b81526011600452602481fd5b5060010190565b610ee480620003886000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c806370a082311161006657806370a082311461011e57806395d89b4114610131578063a3d608f014610139578063a9059cbb1461014e578063dd62ed3e146101615761009e565b806306fdde03146100a3578063095ea7b3146100c157806318160ddd146100e157806323b872dd146100f657806335617fca14610109575b600080fd5b6100ab610174565b6040516100b89190610da6565b60405180910390f35b6100d46100cf366004610cc2565b610202565b6040516100b89190610d9b565b6100e961026c565b6040516100b89190610df9565b6100d4610104366004610c87565b610272565b6101116103f2565b6040516100b89190610d43565b6100e961012c366004610c34565b610532565b6100ab610544565b610141610551565b6040516100b89190610ceb565b6100d461015c366004610cc2565b61057c565b6100e961016f366004610c55565b610680565b6000805461018190610e48565b80601f01602080910402602001604051908101604052809291908181526020018280546101ad90610e48565b80156101fa5780601f106101cf576101008083540402835291602001916101fa565b820191906000526020600020905b8154815290600101906020018083116101dd57829003601f168201915b505050505081565b3360008181526004602090815260408083206001600160a01b038716808552925280832085905551919290917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259061025b908690610df9565b60405180910390a350600192915050565b60025481565b6001600160a01b03831660009081526003602052604081205482111561029757600080fd5b6001600160a01b03841660009081526004602090815260408083203384529091529020548211156102c757600080fd5b6001600160a01b0384166000908152600360205260408120546102eb908490610e1a565b90506102f7858261069d565b6001600160a01b03841660009081526003602052604081205461031b908590610e02565b6001600160a01b038681166000908152600660205260409020549192501661034c576103478582610733565b610356565b610356858261069d565b6001600160a01b038616600090815260046020908152604080832033845290915281208054869290610389908490610e1a565b9091555061039a90503386866107d5565b50846001600160a01b0316866001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040516103de9190610df9565b60405180910390a350600195945050505050565b6040805160148082526102a0820190925260609160009190816020015b610417610be6565b81526020019060019003908161040f57505060055490915060009060141161044d5760055461044890601490610e1a565b610450565b60005b9050600060146005805490501061046857601461046c565b6005545b905060005b818110156105295760056104858285610e02565b815481106104a357634e487b7160e01b600052603260045260246000fd5b600091825260209182902060408051606081018252600390930290910180546001600160a01b0390811684526001820154169383019390935260029092015491810191909152845185908390811061050b57634e487b7160e01b600052603260045260246000fd5b6020026020010181905250808061052190610e7d565b915050610471565b50919250505090565b60036020526000908152604090205481565b6001805461018190610e48565b60606000600a60075410156105685760075461056b565b600a5b9050610576816108aa565b91505090565b3360009081526003602052604081205482111561059857600080fd5b336000908152600360205260408120546105b3908490610e1a565b90506105bf338261069d565b6001600160a01b0384166000908152600360205260408120546105e3908590610e02565b6001600160a01b03868116600090815260066020526040902054919250166106145761060f8582610733565b61061e565b61061e858261069d565b6106293386866107d5565b50846001600160a01b0316336001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8660405161066d9190610df9565b60405180910390a3506001949350505050565b600460209081526000928352604080842090915290825290205481565b6001600160a01b03828116600090815260066020526040902054166106c157600080fd5b60006106cc836109fd565b6001600160a01b03808516600090815260066020526040902054919250166106f5828483610a60565b1561071a576001600160a01b038416600090815260036020526040902083905561072d565b61072384610ad0565b61072d8484610733565b50505050565b6001600160a01b03828116600090815260066020526040902054161561075857600080fd5b600061076382610b6b565b6001600160a01b038481166000818152600360209081526040808320889055858516808452600690925280832080548585529184208054929096166001600160a01b03199283161790955590825283541690911790915560078054929350906107cb83610e7d565b9190505550505050565b604080516060810182526001600160a01b03948516815292841660208401908152908301918252600580546001808201835560009290925293517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0600390950294850180546001600160a01b031990811692881692909217905591517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db1850180549093169516949094179055517f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db29091015590565b60606007548211156108bb57600080fd5b60008267ffffffffffffffff8111156108e457634e487b7160e01b600052604160045260246000fd5b60405190808252806020026020018201604052801561091d57816020015b61090a610c06565b8152602001906001900390816109025790505b506001600090815260066020527f3e5fec24aa4dc4e5aee2e025e51e1392c72a2500577559fae9665c6d52bd6a31549192506001600160a01b03909116905b848110156109f2576040805180820182526001600160a01b038416808252600090815260036020908152929020549181019190915283518490839081106109b357634e487b7160e01b600052603260045260246000fd5b6020908102919091018101919091526001600160a01b0392831660009081526006909152604090205490911690806109ea81610e7d565b91505061095c565b50909150505b919050565b600060015b6001600160a01b0381811660009081526006602052604090205416600114610a5a57610a2e8382610bc4565b15610a3a5790506109f8565b6001600160a01b0390811660009081526006602052604090205416610a02565b50919050565b60006001600160a01b03841660011480610a9257506001600160a01b0384166000908152600360205260409020548311155b8015610ac857506001600160a01b03821660011480610ac857506001600160a01b03821660009081526003602052604090205483115b949350505050565b6001600160a01b0381811660009081526006602052604090205416610af457600080fd5b6000610aff826109fd565b6001600160a01b038381166000818152600660209081526040808320805487871685528285208054919097166001600160a01b031991821617909655938352835490941690925560039091529081208190556007805492935090610b6283610e31565b91905055505050565b600060015b6001600160a01b03808216600090815260066020526040902054610b98918391869116610a60565b15610ba45790506109f8565b6001600160a01b0390811660009081526006602052604090205416610b70565b6001600160a01b03908116600090815260066020526040902054811691161490565b604080516060810182526000808252602082018190529181019190915290565b604080518082019091526000808252602082015290565b80356001600160a01b03811681146109f857600080fd5b600060208284031215610c45578081fd5b610c4e82610c1d565b9392505050565b60008060408385031215610c67578081fd5b610c7083610c1d565b9150610c7e60208401610c1d565b90509250929050565b600080600060608486031215610c9b578081fd5b610ca484610c1d565b9250610cb260208501610c1d565b9150604084013590509250925092565b60008060408385031215610cd4578182fd5b610cdd83610c1d565b946020939093013593505050565b602080825282518282018190526000919060409081850190868401855b82811015610d3657815180516001600160a01b03168552860151868501529284019290850190600101610d08565b5091979650505050505050565b602080825282518282018190526000919060409081850190868401855b82811015610d3657815180516001600160a01b0390811686528782015116878601528501518585015260609093019290850190600101610d60565b901515815260200190565b6000602080835283518082850152825b81811015610dd257858101830151858201604001528201610db6565b81811115610de35783604083870101525b50601f01601f1916929092016040019392505050565b90815260200190565b60008219821115610e1557610e15610e98565b500190565b600082821015610e2c57610e2c610e98565b500390565b600081610e4057610e40610e98565b506000190190565b600281046001821680610e5c57607f821691505b60208210811415610a5a57634e487b7160e01b600052602260045260246000fd5b6000600019821415610e9157610e91610e98565b5060010190565b634e487b7160e01b600052601160045260246000fdfea2646970667358221220c8a16c5501a0c471275a7385f2b33bdbd3c0789358fc10f523b0478362c1f0f264736f6c63430008010033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETLATESTTRANSACTIONS = "getLatestTransactions";

    public static final String FUNC_GETTOPTENACCOUNTS = "getTopTenAccounts";

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

    public RemoteFunctionCall<List> getLatestTransactions() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLATESTTRANSACTIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<TransactionLog>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getTopTenAccounts() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOPTENACCOUNTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Account>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
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

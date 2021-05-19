# dapp

## Backend

### Requirements

#### Dependencies

- [web3j](https://docs.web3j.io/latest/quickstart/)
- [solc](https://docs.soliditylang.org/en/develop/installing-solidity.html)

#### Generate java wrapper for smart contract

It is necessary to compile smart contracts first

```bash
$ ./scripts/build_contracts.sh
```

Generate java class passsing contract name
```bash
$ ./scripts/generate_java_classes.sh Token
```
Generated java class will be created under `backend/src/main/java/generated/com/rombit/solidity`

### [Backend docs](/backend/README.md)
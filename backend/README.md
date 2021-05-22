# Rombit token Transactions Portal


## Requirements
### Install Java SDK 11
- Use [sdkman](http://sdkman.io/)
```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
$ sdk install java
```

### Install Gradle 5.3 or higher
```sh
$ sdk update
$ sdk install gradle
```

## Usage
### Clone and compile dist
```sh
$ git clone git@github.com:jdvalenzuelah/dapp.git ${YOUR_PROJECT_NAME}
$ cd ${YOUR_PROJECT_NAME}/app
$ ./gradlew clean build
```

Extract generated dist
```sh
$ tar -xf build/distributions/rombit-token-backend-1.0.0.tar
```

### Deploy rombit token:

Pass initial supply and web3 service url
```sh
$ ./rombit-token-backend-1.0.0/bin/rombit-token-backend deploy --initial-supply 100000 --url http://localhost:8721/
Please enter private key to use for deploy:
Contract deployed at: 0x3ed2d857ec5a0ec758cb81674b4ca66ad3ee9408
```

### Use transaction portal:

Pass token contract address, web3 service url and port to start web server
```sh
./rombit-token-backend-1.0.0/bin/rombit-token-backend app --address 0x6d504e2b8f2f637e86bddc7bf56c8a40bf69fed9 --url http://localhost:8721/ --port 8080
Please enter private key to use for initial connection:
INFO: KWeb is listening on port 8080
```

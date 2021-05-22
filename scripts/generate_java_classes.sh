function env() {
    SCRIPT_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
    SOURCE_PATH="$(dirname "${SCRIPT_PATH}")"
    echo SCRIPT_PATH: ${SCRIPT_PATH}
    echo SOURCE_PATH: ${SOURCE_PATH}
}

function check_web3j() {
    if ! command -v web3j &> /dev/null
    then
        echo "web3j is required to generate contract java class"
        exit
    fi
}

function prepare() {
    BUILD_DEST_PATH="$SOURCE_PATH/build"
    GENERATED_DEST_PATH="$SOURCE_PATH/app/src/main/java"
    GENERATED_CLASS="com.rombit.solidity"
    echo GENERATED_CLASS: ${GENERATED_CLASS}
    echo BUILD_DEST_PATH: ${BUILD_DEST_PATH}
    echo GENERATED_DEST_PATH: ${GENERATED_DEST_PATH}
}

function generate() {
    web3j generate solidity -b "$BUILD_DEST_PATH/$1.bin" -a "$BUILD_DEST_PATH/$1.abi" -o "$GENERATED_DEST_PATH" -p "$GENERATED_CLASS"
}

function main() {
    check_web3j && env && prepare && generate "$@"
}

main "$@"

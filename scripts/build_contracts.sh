#!/bin/bash

function env() {
    SCRIPT_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
    SOURCE_PATH="$(dirname "${SCRIPT_PATH}")"
    echo SCRIPT_PATH: ${SCRIPT_PATH}
    echo SOURCE_PATH: ${SOURCE_PATH}
}

function check_solc() {
    if ! command -v solc &> /dev/null
    then
        echo "solc is required to build smart contract"
        exit
    fi
}

function prepare() {
    BUILD_DEST_PATH="$SOURCE_PATH/build"
    CONTRACTS_PATH="$SOURCE_PATH/contracts"
    mkdir -p $BUILD_DEST_PATH
    echo BUILD_DEST_PATH: ${BUILD_DEST_PATH}
    echo CONTRACTS_PATH: ${CONTRACTS_PATH}
}

function build() {
    solc $CONTRACTS_PATH/*.sol  --bin --abi --optimize -o "$BUILD_DEST_PATH" $@
}

function main() {
    check_solc && env && prepare && build "$@"
}

main "$@"

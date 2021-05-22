package com.rombit

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.int
import com.rombit.app.TokenApp
import com.rombit.token.TokenRepo
import com.rombit.solidity.Token
import com.rombit.token.privateKeyToCredentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.StaticGasProvider
import kotlin.system.exitProcess


class RombitTokenApp : CliktCommand() {

    enum class Mode {
        Deploy,
        App,
    }

    private val mode by argument("-m", help = "Mose use to execute app").enum<Mode>(ignoreCase = true)
        .default(Mode.App)

    private val web3Url: String by option("-u", "--url", help = "URL of web3 service")
        .default("http://localhost:8721/")

    private val initialSupply by option("-s", "--initial-supply", help = "Initial supply use to deploy").default("100")

    private val port by option("-p", "--port", help = "Port where webserver will be listening").int().default(8080)

    private val tokenAddress by option("-a", "--address", help = "Address of deployed contract")

    private val web3j by lazy {
        Web3j.build(HttpService(web3Url))
    }

    private val defaultGasProvider by lazy {
        StaticGasProvider(20000000000.toBigInteger(), 6721975.toBigInteger())
    }

    private fun deploy() {
        val pk = prompt("Please enter private key to use for deploy", hideInput = true)

        if(pk.isNullOrEmpty())
            exitProcess(-1)

        val deployed = Token.deploy(web3j, pk.privateKeyToCredentials(), defaultGasProvider, initialSupply.toBigInteger())
            .send()

        echo("Contract deployed at: ${deployed.contractAddress}")
    }

    private fun app() {
        val pk = prompt("Please enter private key to use for initial connection", hideInput = true)

        if(pk.isNullOrEmpty())
            exitProcess(-1)

        val repo = TokenRepo(web3j, tokenAddress!!, defaultGasProvider)

        TokenApp(port = port, tokenRepo = repo)
            .start()
    }

    override fun run() {
        when(mode) {
            Mode.App -> app()
            Mode.Deploy -> deploy()
        }
    }

}

fun main(args: Array<String>) = RombitTokenApp().main(args)

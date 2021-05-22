package com.rombit.token

import com.rombit.solidity.Token
import org.tinylog.kotlin.Logger
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigInteger

class TokenRepo(
    private val web3j: Web3j,
    private val contractAddress: String,
    private val gasProvider: StaticGasProvider,
) {

    private fun <T> tryOrEmptyCollection(block: () -> Collection<T>): Collection<T> {
        return try {
            block()
        } catch (e: Exception) {
            Logger.warn("Error: $e")
            emptyList()
        }
    }

    private fun getTokenImpl(credentials: Credentials): Token {
        return Token.load(contractAddress, web3j, RawTransactionManager(web3j,credentials), gasProvider)
    }

    fun balanceOf(address: String, credentials: Credentials): BigInteger =
        getTokenImpl(credentials).balanceOf(address).send()

    fun transferFunds(credentials: Credentials, toAddress: String, amount: BigInteger) {
        getTokenImpl(credentials).transfer(toAddress, amount).send()
    }

    fun getTopTenAccounts(credentials: Credentials): Collection<Account> {
        val token = getTokenImpl(credentials)
        val accountCount = token.accountsCount().send()

        return tryOrEmptyCollection {
            val accounts = mutableListOf<Account>()
            repeat(accountCount.toInt().coerceAtMost(10)) {
                val account = token.getTopNAccount((it).toBigInteger()).send()
                accounts.add(Account(account.component1(), account.component2()))
            }

            accounts.distinct()
        }
    }

    fun getLastTwentyTransactions(credentials: Credentials): Collection<TransactionLog> {
        val token = getTokenImpl(credentials)

        return tryOrEmptyCollection {
            val logs = mutableListOf<TransactionLog>()
            for(i in 0..20) {
                val log = token.getTransactionLog(i.toBigInteger()).send()
                logs.add(TransactionLog(log.component1(), log.component2(), log.component3(), log.component4().toLong()))
            }
            logs.distinct()
        }
    }

}

fun String.privateKeyToCredentials(): Credentials = Credentials.create(this)

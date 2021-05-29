package com.rombit.app

import com.rombit.app.components.*
import kweb.*
import com.rombit.app.style.*
import com.rombit.token.Account
import com.rombit.token.TokenRepo
import com.rombit.token.TransactionLog
import com.rombit.token.privateKeyToCredentials
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer
import kweb.plugins.jqueryCore.executeOnSelf
import kweb.shoebox.Shoebox
import kweb.shoebox.stores.DirectoryStore
import kweb.state.KVal
import kweb.state.KVar
import kweb.state.render
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class TokenApp(
    private val port: Int = 8080,
    private val debug: Boolean = false,
    private val tokenRepo: TokenRepo,
) {

    private val keys by lazy {
        val dir = Paths.get("data")
        if(Files.notExists(dir))
            Files.createDirectory(dir)

        Shoebox(DirectoryStore(dir.resolve("keys"), String.serializer()))
    }

    private val topAccounts = KVar<Collection<Account>>(emptyList())
    private val transactionLog = KVar<Collection<TransactionLog>>(emptyList())

    fun start() {
        Kweb(port = port, debug = debug, plugins = listOf(fomanticPlugin), buildPage = { buildApp() })
    }

    private fun WebBrowser.buildApp() {
        doc.head {
            meta(name = "Description", content = "Simple UI for eth transactions")
        }

        doc.body {

            div(fomantic.content).new {

                route {
                    path("/") { startPage(url) }
                    path("/{id}") { params ->

                        render(params.getValue("id")) {
                            val pk = keys[it]
                            if(pk.isNullOrEmpty())
                                url.value = "/"
                            else
                                home(pk)
                        }

                        execute("""
                            ${'$'}('.message .close')
                              .on('click', function() {
                                ${'$'}(this)
                                  .closest('.message')
                                  .transition('fade')
                                ;
                              })
                            ;
                        """.trimIndent())

                    }

                    notFound {
                        mainView("Rombit Transfer Portal") {
                            div(fomantic.ui.negative.message).new {
                                div(fomantic.header).text("Not Found :(")
                                p().text(url.map { "Unable to find path $it" })
                            }
                        }
                    }
                }

            }

        }
    }

    private fun ElementCreator<*>.startPage(url: KVar<String>) {
        mainView("Rombit Transfer Portal") {

            loginForm(
                handleContinue = {
                    GlobalScope.launch {
                        val pk = it.getValue().await()

                        if(pk.isNotEmpty()) {
                            val id = storePrivateKey(pk)
                            url.value = "/$id"
                        }
                    }
                }
            ).executeOnSelf(".modal('show')")

        }
    }

    private fun ElementCreator<*>.home(pk: String) {
        require(pk.isNotEmpty())
        val privateKey = pk.privateKeyToCredentials()

        val latestNotification = KVar<TransactionLog?>(null)

        tokenRepo.onTransfer(privateKey) {
            latestNotification.value = it
        }

        mainView("Rombit Transfer Portal") {

            topAccounts.value = tokenRepo.getTopTenAccounts(privateKey)
            transactionLog.value = tokenRepo.getLastTwentyTransactions(privateKey)

            div(fomantic.ui.segment) {
                val transferForm = transferForm { to, amount ->
                    GlobalScope.launch {
                        val toAddr = to.getValue().await()
                        val amountBI = amount.getValue().await().toBigInteger()
                        tokenRepo.transferFunds(privateKey, toAddr, amountBI)
                        topAccounts.value = tokenRepo.getTopTenAccounts(privateKey)
                        transactionLog.value = tokenRepo.getLastTwentyTransactions(privateKey)
                    }
                }
                val btn = button(fomantic.ui.button).text("Transfer funds!")
                btn.onImmediate.click {
                    transferForm.executeOnSelf(".modal('show')")
                }
            }

            div(fomantic.ui.two.column.grid) {
                div(fomantic.column) {
                    div(fomantic.top.attached.ui.segment) {
                        val balance = KVar("" to BigInteger.ZERO)

                        checkBalanceForm {
                            GlobalScope.launch {
                                val addr = it.getValue().await()
                                balance.value = addr to tokenRepo.balanceOf(addr, privateKey)
                            }
                        }

                        render(balance) {
                            val (addr, balance) = it
                            if(addr.isNotEmpty()) {
                                div(fomantic.ui.basic.segment) {
                                    div(fomantic.ui.message.success) {
                                        p().text("Balance $addr: $balance")
                                    }
                                }
                            } else { div() }
                        }
                    }
                    div(fomantic.ui.segment) {
                        h4().text("Accounts with most balance:")
                        render(topAccounts) { accountList(it) }
                    }
                }
                div(fomantic.ui.column) {
                    div(fomantic.top.attached.ui.segment) {
                        h4().text("Latest transactions:")
                        render(transactionLog) { transactionList(it) }
                    }
                }
            }

            render(latestNotification) { transaction ->
                if(transaction != null) {
                    div(fomantic.ui.teal.message) {
                        i(fomantic.close.icon).on.click { latestNotification.value = null }
                        div(fomantic.header) { text("New transfer!") }
                        p().text("New transfer from ${transaction.from} to ${transaction.to} for ${transaction.amount}")
                    }.executeOnSelf("""
                    .on('click', function() {
                        $(this)
                          .closest('.message')
                          .transition('fade');
                      });
                """.trimIndent())
                }
            }
        }
    }

    private fun storePrivateKey(pk: String): String {
        val id = UUID.randomUUID().toString()
        keys[id] = pk
        return id
    }


}

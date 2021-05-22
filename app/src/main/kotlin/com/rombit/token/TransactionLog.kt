package com.rombit.token

import java.math.BigInteger
import java.time.Instant

data class TransactionLog(
    val from: String,
    val to: String,
    val amount: BigInteger,
    val dateEpoch: Long,
) {
    val date by lazy {
        Instant.ofEpochSecond(dateEpoch)
    }
}

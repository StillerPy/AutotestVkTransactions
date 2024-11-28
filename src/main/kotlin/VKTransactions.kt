package ru.netology

object VKTransactions {
    const val ERR_UNKNOWN_CARD = -1
    const val ERR_DAY_LIMIT = -2
    const val ERR_MONTH_LIMIT = -3
    const val ERR_INVALID_ARGS = -4
    fun calculateCommission(cardFrom: String,
                            monthSum: Int,
                            transaction: Int): Int {
        if (monthSum < 0 || transaction <= 0) {
            return ERR_INVALID_ARGS
        }
        if (monthSum + transaction > 600_000) {
            return ERR_MONTH_LIMIT
        }
        if (transaction > 150_000) {
            return ERR_DAY_LIMIT
        }
        when (cardFrom) {
            "VK Pay" -> {
                if (transaction > 15_000) {
                    return ERR_DAY_LIMIT
                } else if (monthSum + transaction > 40_000) {
                    return ERR_MONTH_LIMIT
                } else {
                    return 0
                }
            }
            "Visa", "Мир" -> {
                return 35.coerceAtLeast((transaction * 0.0075).toInt())
            }
            "Maestro", "Mastercard" -> {
                if (transaction >= 300 && (monthSum + transaction) <= 75_000) {
                    return 0
                } else {
                    return (transaction * 0.006).toInt() + 20
                }
            }
            else -> return ERR_UNKNOWN_CARD
        }
    }
}
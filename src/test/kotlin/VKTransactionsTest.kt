import org.junit.Assert.*
import org.junit.Test
import ru.netology.VKTransactions

class VKTransactionsTest {
    @Test
    fun unknownCard() {
        val card = "Unknown"
        val monthSum = 0
        val transaction = 500
        val expected = VKTransactions.ERR_UNKNOWN_CARD
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun invalidArgs1() {
        val card = "Maestro"
        val monthSum = -10
        val transaction = 500
        val expected = VKTransactions.ERR_INVALID_ARGS
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun invalidArgs2() {
        val card = "Maestro"
        val monthSum = 0
        val transaction = 0
        val expected = VKTransactions.ERR_INVALID_ARGS
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun monthLimit() {
        val card = "Visa"
        val monthSum = 590_000
        val transaction = 10_010
        val expected = VKTransactions.ERR_MONTH_LIMIT
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun dayLimit() {
        val card = "Visa"
        val monthSum = 0
        val transaction = 200_000
        val expected = VKTransactions.ERR_DAY_LIMIT
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun vkPayDayLimit() {
        val card = "VK Pay"
        val monthSum = 0
        val transaction = 20_000
        val expected = VKTransactions.ERR_DAY_LIMIT
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun vkPayMonthLimit() {
        val card = "VK Pay"
        val monthSum = 35_000
        val transaction = 10_000
        val expected = VKTransactions.ERR_MONTH_LIMIT
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun visaMirSmallSum() {
        val card = "Visa"
        val monthSum = 0
        val transaction = 100
        val expected = 35
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun maestroMasterCardSmallSum() {
        val card = "Maestro"
        val monthSum = 0
        val transaction = 400
        val expected = 0
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }
    @Test
    fun maestroMasterCardBigSum() {
        val card = "Maestro"
        val monthSum = 75_000
        val transaction = 400
        val expected = 22
        val result = VKTransactions.calculateCommission(card, monthSum, transaction)
        assertEquals(expected, result)
    }

}
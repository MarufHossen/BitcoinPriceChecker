import bitcoin.BitCoin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitCoinTest {

    @Test
    void getCurrentBitCoinPrice() {
        BitCoin bitCoin = new BitCoin("eur");
        bitCoin.getCurrentBitCoinPrice();
        assertNotEquals(-1, bitCoin.getCurrentPrice(),"Case 1: Testing current price with correct currency code");

        BitCoin bitCoin2 = new BitCoin("abcd");
        bitCoin2.getCurrentBitCoinPrice();
        assertEquals(-1, bitCoin2.getCurrentPrice(), "Case 2: Testing current price with incorrect currency code");
    }

    @Test
    void calculateHighAndLowPrice() {
        BitCoin bitCoin = new BitCoin("eur");

        bitCoin.calculateHighAndLowPrice("2021-07-01", "2021-07-10");

        double expectedHighestPrice = 29732.4682;
        double expectedLowestPrice = 27749.4105;

        assertEquals(expectedHighestPrice,bitCoin.getHighestPrice(),"Case 1: Testing highest price in EUR");
        assertEquals(expectedLowestPrice,bitCoin.getLowestPrice(),"Case 2: Testing lowest price in EUR");

        BitCoin bitCoin2 = new BitCoin("abcd");
        bitCoin2.calculateHighAndLowPrice("2021-07-01", "2021-07-10");
        assertEquals(-1, bitCoin2.getHighestPrice(), "Case 3: Testing highest price with incorrect currency code");
        assertEquals(-1, bitCoin2.getLowestPrice(), "Case 4: Testing lowest price with incorrect currency code");

    }
}
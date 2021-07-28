import bitcoin.BitCoin;
import helpers.DateHelper;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter currency code (eg. EUR, USD, AUD, CAD etc): ");
        String currencyCode = scanner.nextLine();

        BitCoin bitCoin = new BitCoin(currencyCode);
        bitCoin.getCurrentBitCoinPrice();

        String todayDate = DateHelper.getCurrentDate();
        String thirtyDaysAgoDate = DateHelper.getDate(30);
        bitCoin.calculateHighAndLowPrice(thirtyDaysAgoDate, todayDate);

        if (bitCoin.getCurrentPrice() == -1) {
            System.out.println("Currency is not supported by the API");
        }else {
            System.out.println("Current Bitcoin Price: " + bitCoin.getCurrentPrice() + " " + currencyCode.toUpperCase());
            System.out.println("Lowest Bitcoin Price: " + bitCoin.getLowestPrice() + " " + currencyCode.toUpperCase());
            System.out.println("Highest Bitcoin Price: " + bitCoin.getHighestPrice() + " " + currencyCode.toUpperCase());
        }
    }
}

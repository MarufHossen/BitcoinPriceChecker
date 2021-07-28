package bitcoin;

import api.API;
import helpers.Results;
import org.json.JSONObject;

import java.util.ArrayList;

public class BitCoin {
    private String currency;
    private double currentPrice;
    private double lowestPrice;
    private double highestPrice;

    public BitCoin(String currency) {
        this.currency = currency;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void getCurrentBitCoinPrice() {

        String url = "https://api.coindesk.com/v1/bpi/currentprice/" +
                this.currency.toLowerCase() +
                ".json";
        Results currentApiResult = API.fetchData(url);

        if (currentApiResult.getError() == null) {
            try{
                JSONObject currentApiResultObject = new JSONObject(currentApiResult.getData());
                double currentPrice = currentApiResultObject
                        .getJSONObject("bpi")
                        .getJSONObject(currency.toUpperCase())
                        .getDouble("rate_float");

                this.currentPrice = currentPrice;
            }
            catch (Exception e){
                this.currentPrice = -1;
            }
        }
        else{
            this.currentPrice = -1;
        }

    }

    public void calculateHighAndLowPrice(String fromDate, String todayDate) {

        ArrayList<Double> priceList = this.getPriceList(fromDate, todayDate);

        if (priceList != null) {
            double maxPrice = priceList.get(0);
            double minPrice = priceList.get(0);

            for (double price : priceList) {

                if (price < minPrice) {
                    minPrice = price;
                }

                if (price > maxPrice) {
                    maxPrice = price;
                }
            }
            this.highestPrice = maxPrice;
            this.lowestPrice = minPrice;
        }
        else{
            this.highestPrice = -1;
            this.lowestPrice = -1;
        }
    }

    private ArrayList<Double> getPriceList(String fromDate, String toDate) {
        ArrayList<Double> priceList = new ArrayList<Double>();

        String url = "https://api.coindesk.com/v1/bpi/historical/close.json?start=" + fromDate + "&end=" + toDate + "&currency=" +
                this.currency.toLowerCase();
        Results apiResult = API.fetchData(url);

        if (apiResult.getError() == null) {
            try {
                JSONObject apiResultObject = new JSONObject(apiResult.getData());
                JSONObject pricesObj = apiResultObject.getJSONObject("bpi");

                pricesObj.keySet().forEach(key -> {
                    double value = pricesObj.getDouble(key);
                    priceList.add(value);
                });
            }
            catch (Exception e){
                return null;
            }

        }
        else {
            return null;
        }

        return priceList;
    }

    @Override
    public String toString() {
        return "BitCoin{" +
                "currency='" + currency + '\'' +
                ", currentPrice=" + currentPrice +
                ", lowestPrice=" + lowestPrice +
                ", highestPrice=" + highestPrice +
                "}";
    }
}

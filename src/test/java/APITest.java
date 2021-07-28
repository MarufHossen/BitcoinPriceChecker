import api.API;
import helpers.Results;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APITest {

    @Test
    void fetchData() {

        Results results = API.fetchData("https://api.coindesk.com/v1/bpi/currentprice/eur.json");
        assertNotNull(results.getData(), "Case 1: Testing coin desk api");

        Results wrongResults = API.fetchData("https://api.coindesk.com/v1/bpi/currentprice/acdscds.json");
        assertNotNull(wrongResults.getError(), "Case 2: Testing coin desk api with incorrect url");
    }
}
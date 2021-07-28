package api;

import helpers.Results;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {

    public static Results fetchData(String url){
        Results results = new Results();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                results.setData(response.body());
            }
            else{
                results.setError("Status Error Code "+ response.statusCode());
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            results.setError(e.getMessage());

        }

        return results;
    }
}

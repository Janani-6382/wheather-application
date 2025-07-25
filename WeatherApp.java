import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherApp {

    // Replace with your actual OpenWeatherMap API key
    private static final String API_KEY = "e66a69a483cf26c42e05fae9eff4cf2c";

    public static void main(String[] args) {
        // Use city from arguments or default to London
        String city = (args.length > 0) ? args[0] : "London";
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +
                        encodedCity + "&appid=" + API_KEY + "&units=metric";

        // Create the HTTP client and make request
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(API_KEY))) {

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                System.out.println("Failed to retrieve weather data. HTTP Status Code: " + statusCode);
                return;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                System.out.println("Empty response from weather service.");
                return;
            }

            String responseString = EntityUtils.toString(entity);
            JsonObject jsonResponse = JsonParser.parseString(responseString).getAsJsonObject();

            // Safely extract and display weather data
            JsonObject main = jsonResponse.getAsJsonObject("main");
            JsonObject wind = jsonResponse.getAsJsonObject("wind");
            JsonObject weather = jsonResponse.getAsJsonArray("weather").get(0).getAsJsonObject();

            double temperature = main.has("temp") ? main.get("temp").getAsDouble() : Double.NaN;
            int humidity = main.has("humidity") ? main.get("humidity").getAsInt() : -1;
            int pressure = main.has("pressure") ? main.get("pressure").getAsInt() : -1;
            double windSpeed = wind.has("speed") ? wind.get("speed").getAsDouble() : Double.NaN;
            String description = weather.has("description") ? weather.get("description").getAsString() : "N/A";

            System.out.println("Weather Information for " + city + ":");
            System.out.println("----------------------------------------");
            System.out.printf("Temperature: %.1f °C%n", temperature);
            System.out.println("Humidity: " + humidity + " %");
            System.out.println("Pressure: " + pressure + " hPa");
            System.out.println("Weather: " + description);
            System.out.println("Wind Speed: " + windSpeed + " m/s");

        } catch (IOException e) {
            System.err.println("Error occurred while fetching weather data:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error:");
            e.printStackTrace();
        }
    }
}

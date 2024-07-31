import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CURRENT_TEMPERATURE_FORCAST {
    private static final String API_URL = "https://api.open-meteo.com/v1/forecast";
    private static final int PAST_DAYS = 3;
    private static final int FUTURE_DAYS = 4;

    public String[] dates;
    public double[] maxTemps;
    public double[] minTemps;
    public double[] precipitation;
    public double[] rainSums;
    public double[] precipitationHours;

    public CURRENT_TEMPERATURE_FORCAST(double latitude, double longitude) {
        int totalDays = PAST_DAYS + FUTURE_DAYS + 1;
        dates = new String[totalDays];
        maxTemps = new double[totalDays];
        minTemps = new double[totalDays];
        precipitation = new double[totalDays];
        rainSums = new double[totalDays];
        precipitationHours = new double[totalDays];

        try {
            int arrayIndex = 0;

            // Weather for past days
            for (int i = PAST_DAYS; i >= 1; i--, arrayIndex++) {
                LocalDate date = LocalDate.now().minusDays(i);
                dates[arrayIndex] = date.toString();
                JSONObject weatherData = getWeatherData(API_URL, latitude, longitude, dates[arrayIndex], dates[arrayIndex]);
                fillDataArrays(weatherData, arrayIndex);
            }

            // Current weather
            LocalDate today = LocalDate.now();
            dates[arrayIndex] = today.toString();
            JSONObject currentWeather = getWeatherData(API_URL, latitude, longitude, dates[arrayIndex], dates[arrayIndex]);
            fillDataArrays(currentWeather, arrayIndex++);

            // Weather for future days
            for (int i = 1; i <= FUTURE_DAYS; i++, arrayIndex++) {
                LocalDate date = LocalDate.now().plusDays(i);
                dates[arrayIndex] = date.toString();
                JSONObject weatherData = getWeatherData(API_URL, latitude, longitude, dates[arrayIndex], dates[arrayIndex]);
                fillDataArrays(weatherData, arrayIndex);
            }
        } catch (IOException | URISyntaxException | ParseException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private void fillDataArrays(JSONObject weatherData, int index) {
        JSONObject daily = (JSONObject) weatherData.get("daily");
        JSONArray maxTempArray = (JSONArray) daily.get("temperature_2m_max");
        JSONArray minTempArray = (JSONArray) daily.get("temperature_2m_min");
        JSONArray precipitationArray = (JSONArray) daily.get("precipitation_sum"); // Change here
        JSONArray rainSumArray = (JSONArray) daily.get("rain_sum");
        JSONArray precipitationHoursArray = (JSONArray) daily.get("precipitation_hours");

        // Assuming each JSON array contains only one element per day
        maxTemps[index] = (double) maxTempArray.get(0);
        minTemps[index] = (double) minTempArray.get(0);
        precipitation[index] = (double) precipitationArray.get(0); // Change here
        rainSums[index] = (double) rainSumArray.get(0);
        precipitationHours[index] = (double) precipitationHoursArray.get(0);
    }

    private static JSONObject getWeatherData(String apiUrl, double latitude, double longitude, String startDate, String endDate)
            throws IOException, URISyntaxException, ParseException {
        String urlString = String.format("%s?latitude=%.4f&longitude=%.4f&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,precipitation_hours&start_date=%s&end_date=%s",
                apiUrl, latitude, longitude, startDate, endDate);
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        if (response.length() == 0) {
            throw new IOException("Empty response from API");
        }

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response.toString());
    }

    public static void main(String[] args) {
        double latitude = 20.25;
        double longitude = 85.875;

        CURRENT_TEMPERATURE_FORCAST forecast = new CURRENT_TEMPERATURE_FORCAST(latitude, longitude);

        // Print weather data
        for (int i = 0; i < forecast.dates.length; i++) {
            System.out.printf("Date: %s, Max Temp: %.2f°C, Min Temp: %.2f°C, Precipitation: %.2f mm, Rain Sum: %.2f mm, Precipitation Hours: %.2f hours%n",
                    forecast.dates[i], forecast.maxTemps[i], forecast.minTemps[i], forecast.precipitation[i], forecast.rainSums[i], forecast.precipitationHours[i]);
        }
    }
}
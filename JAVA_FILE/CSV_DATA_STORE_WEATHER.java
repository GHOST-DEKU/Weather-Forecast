import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class WeatherData {
    private LocalDate date;
    private double maxTemperature;
    private double minTemperature;
    private double precipitation;
    private double rainSum;
    private double precipitationHours;

    WeatherData(LocalDate date, double maxTemp, double minTemp, double precipitation, double rainSum, double precipHours) {
        this.date = date;
        this.maxTemperature = maxTemp;
        this.minTemperature = minTemp;
        this.precipitation = precipitation;
        this.rainSum = rainSum;
        this.precipitationHours = precipHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getRainSum() {
        return rainSum;
    }

    public double getPrecipitationHours() {
        return precipitationHours;
    }
}

public class CSV_DATA_STORE_WEATHER {
    public static void main(String[] args) {
        double latitude = 20.25;
        double longitude = 85.875;

        // Instantiate CURRENT_TEMPERATURE_FORCAST to access its data
        CURRENT_TEMPERATURE_FORCAST forecast = new CURRENT_TEMPERATURE_FORCAST(latitude, longitude);

        List<WeatherData> weatherDataList = new ArrayList<>();

        // Populate weatherDataList using data from forecast arrays
        for (int i = 0; i < forecast.dates.length; i++) {
            LocalDate date = LocalDate.parse(forecast.dates[i]);
            double maxTemp = forecast.maxTemps[i];
            double minTemp = forecast.minTemps[i];
            double precipitation = forecast.precipitation[i];
            double rainSum = forecast.rainSums[i];
            double precipHours = forecast.precipitationHours[i];

            weatherDataList.add(new WeatherData(date, maxTemp, minTemp, precipitation, rainSum, precipHours));
        }

        writeWeatherDataToCsv(weatherDataList);
    }

    public static void writeWeatherDataToCsv(List<WeatherData> weatherDataList) {
        try (FileWriter writer = new FileWriter("../HTML_FILE/weather_data.csv")) {
            writer.append("Date, Max Temperature(°C), Min Temperature(°C), Precipitation(mm), Rain Sum(mm), Precipitation Hours\n");
            for (WeatherData weatherData : weatherDataList) {
                writer.append(String.format("%s, %.1f, %.1f, %.1f, %.1f, %.1f\n",
                        weatherData.getDate(), weatherData.getMaxTemperature(), weatherData.getMinTemperature(),
                        weatherData.getPrecipitation(), weatherData.getRainSum(),
                        weatherData.getPrecipitationHours()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
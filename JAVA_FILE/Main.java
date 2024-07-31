import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        runJavaWeatherProject();
        runCurrentTemperatureForecast();
        runCsvDataStoreWeather();
    }

    private static void runJavaWeatherProject() {
        try {
            Process process = new ProcessBuilder("java", "JAVA_WEATHER_PROJECT").start();
            // Optional: Wait for the process to complete
            // process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runCurrentTemperatureForecast() {
        try {
            Process process = new ProcessBuilder("java", "CURRENT_TEMPERATURE_FORCAST").start();
            // Optional: Wait for the process to complete
            // process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runCsvDataStoreWeather() {
        try {
            Process process = new ProcessBuilder("java", "CSV_DATA_STORE_WEATHER").start();
            // Optional: Wait for the process to complete
            // process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
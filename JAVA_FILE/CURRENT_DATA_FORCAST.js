async function fetchWeather(cityName, apiKey) {
    const apiUrl = `http://api.openweathermap.org/data/2.5/forecast?q=${cityName}&appid=${apiKey}`;
    
    try {
        const response = await fetch(apiUrl);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching weather data:', error);
        return null;
    }
}

// Function to display weather information
function displayWeather(weatherData) {
    if (!weatherData) {
        console.error('No weather data to display');
        return;
    }
    
    console.log('Weather forecast for', weatherData.city.name);
    
    weatherData.list.forEach(item => {
        const dateTime = item.dt_txt;
        const temperature = item.main.temp;
        const description = item.weather[0].description;
        console.log(`${dateTime} - Temperature: ${temperature}°C, Description: ${description}`);
    });
}

// Main function
async function main() {
    const cityName = 'Bhubaneswar'; // Replace with the city name you want to get weather data for
    const apiKey = 'Bismaya@4168'; // Replace with your OpenWeatherMap API key
    
    const weatherData = await fetchWeather(cityName, apiKey);
    displayWeather(weatherData);
}

// Call the main function
main();
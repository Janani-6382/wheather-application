# wheather-application
Command-line weather app with Java implementations
# Weather Application

A command-line weather application that fetches and displays current weather data from OpenWeatherMap API. Available in  Java implementations.

## Features

- Real-time weather data from OpenWeatherMap API
- Support for any city worldwide
- Displays temperature, humidity, pressure, weather conditions, and wind speed
- Comprehensive error handling
- Cross-platform compatibility
- language implementations (Java)

## Quick Start

### Java Version

1. Compile and run:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="WeatherApp" -Dexec.args="[city_name]"
```

## Sample Output

```
Fetching weather data for London...

Weather Information for London:
========================================
Temperature: 18.8 °C
Humidity: 77%
Pressure: 1019 hPa
Weather: Few Clouds
Wind Speed: 0.51 m/s
```

## API Setup

1. Get a free API key from [OpenWeatherMap](https://openweathermap.org/api)
2. Set environment variable (optional):
```bash
export OPENWEATHER_API_KEY=your_api_key_here
```

Note: A default API key is included for testing purposes.

## Requirements

### Java Version
- Java 21+
- Maven 3.6+
- Apache HTTP Client 4.5.13
- Gson 2.10.1

## Project Structure

```
├── src/main/java/
│   └── WeatherApp.java     # Java implementation
├── pom.xml                 # Maven configuration
├── README.md               # This file
└── replit.md              # Project documentation
```

## Error Handling

The application handles various error scenarios:
- Invalid API keys
- City not found
- Network connection issues
- API rate limits
- Invalid response formats

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test both Python and Java versions
5. Submit a pull request

## License

This project is open source and available under the MIT License.

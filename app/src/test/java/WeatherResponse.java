package com.example.previsaotempo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("results")
    private Results results;

    public Results getResults() {
        return results;
    }

    public static class Results {
        @SerializedName("city")
        private String city;

        @SerializedName("temperature")
        private String temperature;

        @SerializedName("description")
        private String description;

        @SerializedName("forecast")
        private List<Forecast> forecast;

        public String getCity() {
            return city;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getDescription() {
            return description;
        }

        public List<Forecast> getForecast() {
            return forecast;
        }

        public static class Forecast {
            @SerializedName("date")
            private String date;

            @SerializedName("temperature")
            private String temperature;

            @SerializedName("description")
            private String description;

            public String getDate() {
                return date;
            }

            public String getTemperature() {
                return temperature;
            }

            public String getDescription() {
                return description;
            }
        }
    }
}
public class WeatherResponse {
    public Main main;
    public String name;

    public class Main {
        public float temp;
        public int humidity;
    }
}

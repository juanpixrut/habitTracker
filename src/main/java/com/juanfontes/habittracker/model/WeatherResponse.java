package com.juanfontes.habittracker.model;

public class WeatherResponse {
    private Main main;

    public Main getMain() { return main; }

    public static class Main {
        private double temp;
        private double humidity;

        public double getTemp() { return temp; }
        public double getHumidity() { return humidity; }
    }
}


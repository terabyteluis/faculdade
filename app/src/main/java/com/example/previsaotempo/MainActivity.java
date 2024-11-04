package com.example.previsaotempo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.CaptureActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView cityTextView;
    private String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.cityTextView);
        currentCity = "Cidade Inicial";
        cityTextView.setText(currentCity);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {

            String scannedData = data.getStringExtra("SCAN_RESULT");
            if (scannedData != null) {

                updateCity(scannedData);
            } else {
                Toast.makeText(this, "Nenhum dado escaneado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateCity(String city) {
        currentCity = city;
        cityTextView.setText(currentCity);
        Toast.makeText(this, "Cidade trocada para: " + currentCity, Toast.LENGTH_SHORT).show();


        getWeather(currentCity);
    }

    private void getWeather(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService apiService = retrofit.create(WeatherApiService.class);
        Call<WeatherResponse> call = apiService.getWeatherByCity(city, "https://api.hgbrasil.com/weather?woeid=56123154"); // Substitua "YOUR_API_KEY" pela sua chave

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();

                    String weatherInfo = "Temperatura: " + weather.getTemperatureInCelsius() + "°C\n" +
                            "Umidade: " + weather.main.humidity + "%\n" +
                            "Cidade: " + weather.name;
                    Toast.makeText(MainActivity.this, weatherInfo, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
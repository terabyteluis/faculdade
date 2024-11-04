package com.example.previsaotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<WeatherResponse.Results.Forecast> forecasts;


    public WeatherAdapter(List<WeatherResponse.Results.Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WeatherResponse.Results.Forecast forecast = forecasts.get(position);
        holder.dateTextView.setText(forecast.getDate());
        holder.temperatureTextView.setText(forecast.getTemperature());
        holder.descriptionTextView.setText(forecast.getDescription());
    }

    @Override
    public int getItemCount() {

        return forecasts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView temperatureTextView;
        public TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.date_text_view);
            temperatureTextView = itemView.findViewById(R.id.temperature_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
        }
    }
}
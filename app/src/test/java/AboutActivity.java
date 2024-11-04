package com.example.previsaotempo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView raTextView;
    private TextView courseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        nameTextView = findViewById(R.id.name);
        raTextView = findViewById(R.id.ra);
        courseTextView = findViewById(R.id.course);


        String name = "Luis Gustavo";
        String ra = "09067563";
        String course = "Análise e Desenvolvimento de Sistemas";


        nameTextView.setText(name);
        raTextView.setText("RA: " + ra);
        courseTextView.setText("Curso: " + course);
    }
}
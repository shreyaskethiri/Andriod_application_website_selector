package com.example.myapplication;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerWebsites;
    ProgressBar progressBar;
    TextView tvPercentage;

    String selectedUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerWebsites = findViewById(R.id.spinnerWebsites);
        progressBar = findViewById(R.id.progressBar);
        tvPercentage = findViewById(R.id.tvPercentage);

        String[] options = {
                "Click here to access ACN website",
                "ACN Website",
                "Amrita Website"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                options
        );

        spinnerWebsites.setAdapter(adapter);

        spinnerWebsites.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    selectedUrl = "https://www.amritacybernation.com";
                    startProgress();
                } else if (position == 2) {
                    selectedUrl = "https://www.amrita.edu";
                    startProgress();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tvPercentage.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        tvPercentage.setText("0%");

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                progress += 10;
                progressBar.setProgress(progress);
                tvPercentage.setText(progress + "%");

                if (progress < 100) {
                    handler.postDelayed(this, 1000); // 10 seconds total
                } else {
                    progressBar.setVisibility(View.GONE);
                    tvPercentage.setVisibility(View.GONE);
                    openWebsite();
                }
            }
        };

        handler.post(runnable);
    }

    private void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedUrl));
        startActivity(intent);
    }
}

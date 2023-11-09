package com.majedalmoqbeli.html_to_pdf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;

import com.majedalmoqbeli.html_to_pdf.databinding.ActivityMainBinding;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnConvertToPdf.setOnClickListener(view -> {
            startConverting();
        });
    }


    private void startConverting() {
        PdfConverter converter = new PdfConverter();
        String htmlString = "<html><body><p>Hello World</p></body></html>";
        converter.convert(this, htmlString);
    }

}
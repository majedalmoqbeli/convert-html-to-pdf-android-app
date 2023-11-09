package com.majedalmoqbeli.html_to_pdf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;

import com.majedalmoqbeli.html_to_pdf.databinding.ActivityMainBinding;
import com.majedalmoqbeli.html_to_pdf.helpers.ValidationHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editTextHtmlCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();
                if (!input.isEmpty()) {
                    if (ValidationHelper.isValidHtml(input)) {
                        binding.btnConvertToPdf.setEnabled(true);
                    } else {
                        binding.editTextHtmlCode.setError(getString(R.string.message_error_not_valid_html));
                        binding.btnConvertToPdf.setEnabled(false);
                    }
                } else {
                    binding.btnConvertToPdf.setEnabled(false);
                }
            }
        });

        binding.btnConvertToPdf.setOnClickListener(view -> {
            startConverting(binding.editTextHtmlCode.getText().toString().trim());
        });
    }


    private void startConverting(String htmlCode) {
        PdfConverter converter = new PdfConverter();
        converter.convert(this, htmlCode);
    }

}
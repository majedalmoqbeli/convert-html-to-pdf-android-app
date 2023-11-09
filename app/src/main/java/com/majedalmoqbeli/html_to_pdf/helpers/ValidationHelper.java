package com.majedalmoqbeli.html_to_pdf.helpers;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static boolean isValidHtml(String input) {
        if (input != null) {
            Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
            return htmlPattern.matcher(input).matches();
        } else {
            return false;
        }
    }
}

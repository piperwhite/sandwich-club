package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        sandwich.setImage(getValue(json, "image"));
        sandwich.setDescription(getValue(json, "description"));
        sandwich.setMainName(getValue(json, "mainName"));
        sandwich.setPlaceOfOrigin(getValue(json, "placeOfOrigin"));
        sandwich.setAlsoKnownAs(getListValue(json, "alsoKnownAs"));
        sandwich.setIngredients(getListValue(json, "ingredients"));
        return sandwich;
    }

    private static List<String> getListValue(String json, String name) {
        int index = json.indexOf(name);
        String substring = json.substring(index+ name.length());
        String[] list = substring.split("[\\[||\\]]");
        String replacement = list[1].replace("\"", "");
        return new ArrayList<String>(Arrays.asList(replacement.split(",(?![^()]*\\))\\s*")));
    }

    private static String getValue(String json, String name) {
        int index = json.indexOf(name);
        String substring = json.substring(index+ name.length());
        String[] tokens = substring.split("\"");
        return tokens[2];
    }
}

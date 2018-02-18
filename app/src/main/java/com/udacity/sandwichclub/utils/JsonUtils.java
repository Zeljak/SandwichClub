package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_ALSO_KNOWN = "alsoKnownAs";
    private static final String SANDWICH_PLACE_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichJsonObject;
        String mainName = null;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();

        try {
            //all sandwich data
            sandwichJsonObject = new JSONObject(json);

            //sandwich name
            JSONObject sandwichName = sandwichJsonObject.getJSONObject(SANDWICH_NAME);

            //sandwich main name
            mainName = sandwichName.getString(SANDWICH_MAIN_NAME);

            //sandwich also known as
            alsoKnownAs = jsonArrayList(sandwichJsonObject.getJSONArray(SANDWICH_ALSO_KNOWN));

            //place of origin of sandwich
            placeOfOrigin = sandwichJsonObject.getString(SANDWICH_PLACE_ORIGIN);

            //description of sandwich
            description = sandwichJsonObject.getString(SANDWICH_DESCRIPTION);

            //image of sandwich
            image = sandwichJsonObject.getString(SANDWICH_IMAGE);

            //ingredients of sandwich
            ingredients = jsonArrayList(sandwichJsonObject.getJSONArray(SANDWICH_INGREDIENTS));

            //is there an error?
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error on parsing", e);
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static List<String> jsonArrayList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>(0);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error on array list", e);
                }
            }
        }
        return list;
    }
}
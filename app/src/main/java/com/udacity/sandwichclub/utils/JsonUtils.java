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


        String mainName = null;
        List<String> alsoKnownAs = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = null;

        try {
            JSONObject sandwichesJSON = new JSONObject(json);

            //sandwich name
            JSONObject sandwichName = sandwichesJSON.optJSONObject(SANDWICH_NAME);

            //sandwich main name
            mainName = sandwichName.optString(SANDWICH_MAIN_NAME);

            //nickname of sandwich
            JSONArray alsoKnownAsArray = sandwichName.optJSONArray(SANDWICH_ALSO_KNOWN);
            alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(i, alsoKnownAsArray.optString(i));
            }


            //place of origin of sandwich
            placeOfOrigin = sandwichesJSON.optString(SANDWICH_PLACE_ORIGIN);

            //description of sandwich
            description = sandwichesJSON.optString(SANDWICH_DESCRIPTION);


            //ingredients of sandwich
            JSONArray ingredientsArray = sandwichesJSON.optJSONArray(SANDWICH_INGREDIENTS);
            ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(i, ingredientsArray.optString(i));
            }
            //image of sandwich
            image = sandwichesJSON.optString(SANDWICH_IMAGE);

            //is there an error?
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error on parsing", e);
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }


}

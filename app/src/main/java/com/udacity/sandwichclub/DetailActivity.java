package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;
    private TextView tv_also_know;
    private TextView tv_origin;
    private TextView tv_description;
    private TextView tv_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        tv_also_know = findViewById(R.id.also_known_tv);
        tv_origin =findViewById(R.id.origin_tv);
        tv_description = findViewById(R.id.description_tv);
        tv_ingredients = findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (sandwich.getPlaceOfOrigin().isEmpty()){
            tv_origin.setText(R.string.detail_error_message);
        } else {
            tv_origin.setText(sandwich.getPlaceOfOrigin());
        }
        if(sandwich.getAlsoKnownAs().isEmpty()){
            tv_also_know.setText(R.string.detail_error_message);
        } else {
            tv_also_know.setText((CharSequence) sandwich.getAlsoKnownAs());
        }
        if(sandwich.getDescription().isEmpty()){
            tv_description.setText(R.string.detail_description_label);
        } else {
            tv_description.setText(R.string.detail_error_message);
        }
        if(sandwich.getIngredients().isEmpty()){
            tv_ingredients.setText(R.string.detail_error_message);
        } else {
            tv_ingredients.setText(R.string.detail_error_message);
        }


    }
}

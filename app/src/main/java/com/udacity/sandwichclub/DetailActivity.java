package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView ingredientsIv;

    private TextView origin;

    private TextView alsoKnown;
    private TextView alsoKnownLabel;
    private TextView placeOrigin;
    private TextView ingredients;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
     placeOrigin=findViewById(R.id.textView2);
        origin = findViewById(R.id.origin_tv);

        alsoKnown = findViewById(R.id.also_known_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        description = findViewById(R.id.description_tv);
alsoKnownLabel=findViewById(R.id.textView);
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
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        setTitle(sandwich.getMainName());
        populateUI(sandwich);



    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        StringBuilder altNames = new StringBuilder();
        if(alsoKnownAsList != null){
            for(int i = 0; i < alsoKnownAsList.size(); i++){
                altNames.append("* ");
                altNames.append(alsoKnownAsList.get(i));

                if(i != (alsoKnownAsList.size() - 1)){
                    altNames.append("\n");
                }
            }
        }
        if(altNames.toString()!="")
alsoKnown.setText(altNames.toString());
        else{
            alsoKnown.setVisibility(View.GONE);
            alsoKnownLabel.setVisibility(View.GONE);
        }
      if(!sandwich.getPlaceOfOrigin().isEmpty())
      origin.setText(sandwich.getPlaceOfOrigin());
      else{
          placeOrigin.setVisibility(View.GONE);
          origin.setVisibility(View.GONE);

      }


        List<String> ingredientsList = sandwich.getIngredients();
        StringBuilder ingredientsName = new StringBuilder();
        for(int i = 0; i < ingredientsList.size(); i++){

            ingredientsName.append(i+1);
            ingredientsName.append(" ");
            ingredientsName.append(ingredientsList.get(i));
            if(i != (ingredientsList.size() - 1)){
                ingredientsName.append("\n");
            }
        }

      ingredients.setText(ingredientsName.toString());
        description.setText(sandwich.getDescription());


    }

}

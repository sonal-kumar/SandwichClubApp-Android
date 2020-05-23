package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich dataParsed = null;
        try{
          
            JSONObject jsonData = new JSONObject(json);
      
            JSONObject names = jsonData.getJSONObject("name");
            String mainName = names.getString("mainName");
            JSONArray alsoKnownAsJsonArray = names.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAs = new ArrayList<>();
            for(int i = 0; i < alsoKnownAsJsonArray.length(); i++){
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }
            String placeOfOrigin = jsonData.getString("placeOfOrigin");
            String description = jsonData.getString("description");
            String imageUrl = jsonData.getString("image");
            JSONArray ingredientsJsonArray = jsonData.getJSONArray("ingredients");
            ArrayList<String> ingredients = new ArrayList<>();
            for(int i = 0; i < ingredientsJsonArray.length(); i++){
                ingredients.add(ingredientsJsonArray.getString(i));
            }
           dataParsed=new Sandwich(mainName, alsoKnownAs,
                   placeOfOrigin,
                   description,
                   imageUrl,
                   ingredients
           );
            

        }catch(JSONException e){
            e.printStackTrace();
        }




        return dataParsed;

    }
}

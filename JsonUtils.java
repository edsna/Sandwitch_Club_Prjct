package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    //Constructor
    public JsonUtils() throws JSONException {
    }

    /*Method to parse objects of the sandwich_details.json file
    *returns a Sandwich object with parsed sandwich elements
    */
    public static Sandwich parseSandwichJson(String sandwich_details) {
        //Non-Array fields
        String image;
        String placeOfOrigin;
        String description;
        if(TextUtils.isEmpty(sandwich_details)){
            return null;            //Exit out if JSON String is empty
        }
        Sandwich sandwich = null;    //Initialize an empty sandwich object
        /*
        Parses the sandwichArray throwing and catching any possible exception due to the array
         */
        try{
            JSONObject sandwichObject = new JSONObject(sandwich_details);   //Creates a JSONObject
            image = sandwichObject.getString("image");  //Gets image's key value
            JSONObject name = sandwichObject.getJSONObject("name"); //Gets a specific key from the object
            String mainName = name.getString("mainName");   //gets the value of specific key

            placeOfOrigin = sandwichObject.getString("placeOfOrigin");  //Gets place of origin's key value
            description = sandwichObject.getString("description");  //Gets description's key value
            JSONArray alsoKnownAsJsonArray = name.getJSONArray("name"); //Gets the JsonArray with key name
            List<String> alsoKnownAs = jsonArrayToStringList(alsoKnownAsJsonArray); //Gets and converts JsonArray to a list of Strings with key value  alsoKnownAs
            JSONArray ingredientsArray = new getJSONArray("ingredients");   //Stores all strings from ingredients' key
            List<String> ingredients = jsonArrayToStringList(ingredientsArray);    //Stores previous array values into a List

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return sandwich;
    }
    private static List<String> jsonArrayToStringList(JSONArray jsonArray) throws JSONException{
        List<String> allDetails = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            allDetails.add(jsonArray.getString(i));
        }
        return allDetails;
    }
    //Inner getJSONArray class
    private static class getJSONArray extends JSONArray {
        public getJSONArray(String ingredients) {
        }
    }
}

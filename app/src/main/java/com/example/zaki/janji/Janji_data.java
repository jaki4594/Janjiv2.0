package com.example.zaki.janji;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Janji_data {

    public JSONObject utilisateur;
    public String tempat;
    public String masa;
    public String p1;
    public String p2;
    public String v1;
    public String v2;
    public String tajuk;
    public String tarikh;
    public String id;

    final ArrayList<Janji_data> janjiList = new ArrayList<>();


    public ArrayList<Janji_data> getJanjiFromFile(){
        return janjiList;
    }


    public void setJsonData(JSONObject utilisateur){
        this.utilisateur = utilisateur;
        try {

            // Get Recipe objects from data

            Janji_data recipe = new Janji_data();

            recipe.id = utilisateur.getString("Idjanji");
            recipe.p1 = utilisateur.getString("P1");
            recipe.v1 = utilisateur.getString("V1");
            recipe.p2 = utilisateur.getString("P2");
            recipe.v2 = utilisateur.getString("V2");
            recipe.tajuk = utilisateur.getString("tajuk");
            recipe.tarikh = utilisateur.getString("tarikh");
            recipe.masa = utilisateur.getString("masa");
            recipe.tempat = utilisateur.getString("tempat");


            janjiList.add(recipe);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

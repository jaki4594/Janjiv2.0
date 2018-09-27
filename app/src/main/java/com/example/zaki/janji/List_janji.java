package com.example.zaki.janji;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class List_janji extends ListFragment{

    public TextView titre;
    HashMap<String, String> params2 = new HashMap<String, String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Home_page user = (Home_page)getActivity();
        params2.put("email", user.email);
        Singleton.getInstance().getJanji(params2, getActivity(), "GetJanji.php");

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.,
                numbers_text);*/
        //JanjiAdapter adapter = new JanjiAdapter(this, )
        //setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

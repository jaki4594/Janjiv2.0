package com.example.zaki.janji;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Singleton {

    private ProgressDialog progressDialog;
    private Home_page mActivity;
    private Context appContext;
    private static Singleton instance;
    private TextView textv;
    HashMap<String, String> paramsTickets = new HashMap<String, String>();

    public Singleton() {}

    public void init(Context context){
        if(appContext == null){
            appContext = context;
        }
    }

    private Context getContext(){
        return appContext;
    }

    public static Context getCon(){
        return getInstance().getContext();
    }

    public static Singleton getInstance(){
        instance = (instance == null) ? new Singleton(): instance;
        return instance;
    }


    public void mailtosupport (final HashMap<String, String> params,final Activity act, String php){
        Connecter cnt = new Connecter(params,php);
        cnt.execute();
    }



    public void getJanji (final HashMap<String, String> params, final Activity context, String php){

        Connecter cnt = new Connecter(params,php){
            @Override
            protected void onPreExecute(){
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Envoi de votre requete");
                progressDialog.setIndeterminate(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();

            }

            @Override
            protected void onPostExecute(final String result) {

                TextView txtView = (TextView) ((Activity)context).findViewById(R.id.textView);
                txtView.setText(result);

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }

        };
        cnt.execute();

    }



}


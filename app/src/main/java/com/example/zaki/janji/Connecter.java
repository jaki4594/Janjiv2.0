package com.example.zaki.janji;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Connecter extends AsyncTask<String, Integer, String> {

    private HashMap<String, String> parameters;
    private String phpToCall;
    public String result;

    public Connecter(HashMap<String, String> params, String phpTC){
        phpToCall = phpTC;
        parameters = params;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String dataParsed = "";
        //String type = "";
        HttpURLConnection conn = null;
        try {

            String u = "http://51.15.205.212/php/".concat(phpToCall);
            URL url = new URL(u);

            JSONObject postDataParams = new JSONObject();
            //type = parameters.get("type");
            //parameters.remove("type");
            Iterator<HashMap.Entry<String, String>> entries = parameters.entrySet().iterator();
            while (entries.hasNext()) {
                HashMap.Entry<String, String> entry = entries.next();
                postDataParams.put(entry.getKey(), entry.getValue());

            }

            Log.e("params", postDataParams.toString());
            conn = (HttpURLConnection) url.openConnection();
           /* if (!type.equals("listeIncident")) { // si ce n'est pas la liste d'incident

                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
            } else {*/
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //}

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                dataParsed = sb.toString();
                return dataParsed;

            } else {
                return new String("false : " + responseCode);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        //setRes(dataParsed);
        return dataParsed;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(final String result) {
        //delegate.onTaskCompleted(result);

    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}

package com.ephraimhammer.jct.customercarrental.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by binyamin on 01/12/2017.
 */

public class PHPtools {

    public static String GET(String url) throws Exception {
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        // check if teh response is valid code == 200 status == ok.
        // if yes so the INPUT is Valid and we can use it .
        Log.e("PHPtools", Integer.toString(connection.getResponseCode()));
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            // creating a file reader from the input given by the  HTTP connection
            // and opening it.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // creating a string wich will be filled by each line of the in (the input given
            // by the http connection : In short we go through all the Input by the loop
            // while and put it in the
            // 'response' variable

            String line;
            StringBuffer response = new StringBuffer();

            while  ((line = in.readLine() )!= null)
            {
                response.append(line);
            }

            // close the file Reader.
            in.close();

            return response.toString();


        }
        else {
            // if the input was not sent correctly ...
            return "";
        }
    }

    public static String POST (String url , ContentValues params) throws IOException {

        // string builder is more efficient than string for the append method.
        StringBuilder postData = new StringBuilder();

        // from map <String , Object> to &key=value ;
        for (String param : params.keySet())
        {
            if (postData.length() != 0) postData.append('&');

            postData.append(URLEncoder.encode(param, "UTF-8"));
            postData.append("=");
            postData.append(URLEncoder.encode(String.valueOf(params.get(param)), "UTF-8"));
        }

        Log.d("URL", postData.toString());
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(postData.toString().getBytes("UTF-8"));
            Log.d("postData: ", String.valueOf(postData));
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){ // succes
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line ;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null)
                    response.append(line);

                in.close();
                return  response.toString();
            }
            else return "";
        }
        catch (IOException e)
        {
            return e.toString();
        }


    }

    public static ContentValues JsonToContentValues(JSONObject jsonObject) throws JSONException {
        ContentValues result = new ContentValues();
        Iterator<?> keys = jsonObject.keys();

        while (keys.hasNext())
        {
            // a Json line is build like this : key : value
            // so we take the key into variable key and to access to the
            // value we use the key
            // finnaly we add a key value object to the contentValue.
            String key = (String) keys.next();
            Object value = jsonObject.get(key);
            result.put(key,value.toString());
        }
        return result;
    }
}

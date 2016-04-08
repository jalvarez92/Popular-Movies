package com.jag0292.popularmovies.helpers;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jalvarez on 4/7/16.
 */
public class TMDbAPIHelper {

    public static final String API_KEY = "YOUR_API_KEY";
    private static final String LOG_TAG = TMDbAPIHelper.class.getSimpleName();
    public static final String SCHEME = "http";
    public static final String AUTHORITY = "api.themoviedb.org";
    public static final String VERSION = "3";
    public static final String API_KEY_TAG = "api_key";
    public static final String HTTP_GET_METHOD = "GET";
    public static final String ERROR = "Error ";


    public static JSONObject executeRequest(String... pResources){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String response = null;

        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(VERSION)
                    .appendPath(pResources[0])
                    .appendPath(pResources[1])
                    .appendQueryParameter(API_KEY_TAG, API_KEY);

            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(builder.build().toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(HTTP_GET_METHOD);
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            response = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, ERROR, e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, ERROR, e);
                }
            }
        }
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            return null;
        }
    }
}

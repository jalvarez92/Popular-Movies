package com.jag0292.popularmovies.models;

import android.util.Log;

import com.jag0292.popularmovies.helpers.TMDbAPIHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jalvarez on 4/7/16.
 */
public class Movie {


    public static final String TMDb_IMAGE_PATH = "http://image.tmdb.org/t/p/w185";
    public String mTitle;
    public String mOverview;
    public String mPosterURL;

    public Movie(String pTitle, String pOverview, String pPosterURL){
        mTitle = pTitle;
        mOverview = pOverview;
        mPosterURL = pPosterURL;
    }

    public static ArrayList<Movie> getMovies(String pSort){
        JSONObject response = TMDbAPIHelper.executeRequest(MOVIE_RESOURCE_ADDRESS, pSort);
        return getMoviesFromJSON(response);
    }

    public static ArrayList<Movie> getMoviesFromJSON(JSONObject pResponse){
        ArrayList<Movie> result = new ArrayList<>();
        try {
            JSONArray moviesJSON = pResponse.getJSONArray("results");
            int numMovies = moviesJSON.length();
            for (int numMovie = 0; numMovie < numMovies; numMovie++){
                JSONObject movieJSON = moviesJSON.getJSONObject(numMovie);
                String title = movieJSON.getString("title");
                String overview = movieJSON.getString("overview");
                String posterPath = TMDb_IMAGE_PATH +  movieJSON.getString("poster_path");
                result.add(new Movie(title, overview, posterPath));
            }
        } catch (JSONException ignored) { }
        return result;
    }




    public static final String MOVIE_RESOURCE_ADDRESS = "movie";
    public static final String POPULAR_SORT_CRITERIA = "popular";
    public static final String TOP_RATED_SORT_CRITERIA = "top_rated";
}

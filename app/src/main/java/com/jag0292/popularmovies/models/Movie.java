package com.jag0292.popularmovies.models;

import com.jag0292.popularmovies.helpers.TMDbAPIHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jalvarez on 4/7/16.
 */
public class Movie {


    public static final String TMDb_IMAGE_PATH = "http://image.tmdb.org/t/p/w185";
    public String mTitle;
    public String mSynopsis;
    public String mPosterURL;
    public float mUserRating;
    public String mReleaseDate;


    public Movie(String pTitle, String pSynopsis, String pPosterURL, float pUserRating, String pReleaseDate){
        mTitle = pTitle;
        mSynopsis = pSynopsis;
        mPosterURL = pPosterURL;
        mUserRating = pUserRating;
        mReleaseDate = pReleaseDate;
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
                String synopsis = movieJSON.getString("overview");
                String posterPath = TMDb_IMAGE_PATH +  movieJSON.getString("poster_path");
                float userRating = (float) movieJSON.getDouble("vote_average");
                String releaseDate = movieJSON.getString("release_date");
                result.add(new Movie(title, synopsis, posterPath, userRating, releaseDate));
            }
        } catch (JSONException ignored) { }
        return result;
    }




    public static final String MOVIE_RESOURCE_ADDRESS = "movie";

}

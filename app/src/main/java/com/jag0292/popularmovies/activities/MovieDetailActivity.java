package com.jag0292.popularmovies.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jag0292.popularmovies.R;
import com.jag0292.popularmovies.fragments.MovieDetailFragment;
import com.jag0292.popularmovies.fragments.MoviesFragment;
import com.jag0292.popularmovies.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String ARG_PARAM_MOVIE_TITLE = "movie_title";
    public static final String ARG_PARAM_POSTER_URL = "poster_url";
    public static final String ARG_PARAM_SYNOPSIS = "synopsis";
    public static final String ARG_PARAM_USER_RATING = "user_rating";
    public static final String ARG_PARAM_RELEASE_DATE = "release_date";

    private FragmentManager mFragmentManager;
    private Movie mMovie;
    private String mParamMovieTitle;
    private String mParamPosterUrl;
    private String mParamSynopsis;
    private Float mParamUserRating;
    private String mParamReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        mParamMovieTitle = intent.getStringExtra(ARG_PARAM_MOVIE_TITLE);
        mParamPosterUrl = intent.getStringExtra(ARG_PARAM_POSTER_URL);
        mParamSynopsis = intent.getStringExtra(ARG_PARAM_SYNOPSIS);
        mParamUserRating = intent.getFloatExtra(ARG_PARAM_USER_RATING, 0.0f);
        mParamReleaseDate = intent.getStringExtra(ARG_PARAM_RELEASE_DATE);

        mMovie = new Movie(mParamMovieTitle, mParamSynopsis, mParamPosterUrl, mParamUserRating, mParamReleaseDate);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, MovieDetailFragment.newInstance(mMovie));
        transaction.commit();
    }
}

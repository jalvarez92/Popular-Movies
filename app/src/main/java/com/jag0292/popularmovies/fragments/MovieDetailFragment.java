package com.jag0292.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jag0292.popularmovies.R;
import com.jag0292.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {



    private static final String ARG_PARAM_MOVIE_TITLE = "movie_title";
    private static final String ARG_PARAM_POSTER_URL = "poster_url";
    private static final String ARG_PARAM_SYNOPSIS = "synopsis";
    private static final String ARG_PARAM_USER_RATING = "user_rating";
    private static final String ARG_PARAM_RELEASE_DATE = "release_date";


    private ImageView mImageViewMoviePoster;
    private TextView mTextViewMovieTitle;
    private TextView mTextViewUserRating;
    private TextView mTextViewReleaseDate;
    private TextView mTextViewSynopsis;
    private String mParamMovieTitle;
    private String mParamPosterUrl;
    private String mParamSynopsis;
    private Float mParamUserRating;
    private String mParamReleaseDate;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(Movie pMovie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_MOVIE_TITLE, pMovie.mTitle);
        args.putString(ARG_PARAM_POSTER_URL, pMovie.mPosterURL);
        args.putString(ARG_PARAM_SYNOPSIS, pMovie.mSynopsis);
        args.putFloat(ARG_PARAM_USER_RATING, pMovie.mUserRating);
        args.putString(ARG_PARAM_RELEASE_DATE, pMovie.mReleaseDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamMovieTitle = getArguments().getString(ARG_PARAM_MOVIE_TITLE);
            mParamPosterUrl = getArguments().getString(ARG_PARAM_POSTER_URL);
            mParamSynopsis = getArguments().getString(ARG_PARAM_SYNOPSIS);
            mParamUserRating = getArguments().getFloat(ARG_PARAM_USER_RATING);
            mParamReleaseDate = getArguments().getString(ARG_PARAM_RELEASE_DATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mTextViewMovieTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        mTextViewMovieTitle.setText(mParamMovieTitle);
        mTextViewUserRating = (TextView) view.findViewById(R.id.textViewUserRating);
        mTextViewUserRating.setText(mParamUserRating+"");
        mTextViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        mTextViewReleaseDate.setText(mParamReleaseDate);
        mTextViewSynopsis = (TextView) view.findViewById(R.id.textViewSynopsis);
        mTextViewSynopsis.setText(mParamSynopsis);
        mImageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        Picasso.with(getActivity()).load(mParamPosterUrl).into(mImageViewMoviePoster);
        return view;
    }

}

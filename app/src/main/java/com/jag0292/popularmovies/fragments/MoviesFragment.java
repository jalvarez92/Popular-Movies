package com.jag0292.popularmovies.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;

import com.jag0292.popularmovies.R;
import com.jag0292.popularmovies.adapters.MoviesRecyclerViewAdapter;
import com.jag0292.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {

    private MoviesRecyclerViewAdapter mAdapter;
    private ArrayList<Movie> mMovies;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences mSharedPreferences;


    public MoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView gridView = (RecyclerView) view.findViewById(R.id.gridViewMovies);
        mMovies = new ArrayList<Movie>();
        mAdapter = new MoviesRecyclerViewAdapter(getActivity(), mMovies );
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridView.setLayoutManager(mLayoutManager);
        gridView.setAdapter(mAdapter);
        new FetchMoviesTask().execute(mSharedPreferences.getString("sort_by_preference", "popular"));
        return view;
    }



    public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {

            return Movie.getMovies(params[0]);
        }


        public void onPostExecute(ArrayList<Movie> result){
            mMovies.clear();
            mMovies.addAll(result);
            mAdapter.notifyDataSetChanged();

        }


    }
}

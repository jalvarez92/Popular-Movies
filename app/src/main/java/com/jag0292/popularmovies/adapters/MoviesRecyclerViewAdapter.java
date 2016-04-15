package com.jag0292.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jag0292.popularmovies.R;
import com.jag0292.popularmovies.activities.MovieDetailActivity;
import com.jag0292.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jalvarez on 4/7/16.
 */
public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {

    // region Attributes
    private final List<Movie> mMovies;
    private final Context mContext;
    // endregion

    // region Constructor
    public MoviesRecyclerViewAdapter(Context pContext, List<Movie> items) {
        mContext = pContext;
        mMovies = items;
    }
    // endregion

    // region Adapter Events
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        int width = parent.getWidth();
        viewHolder.mItemBackground = (RelativeLayout) view.findViewById(R.id.itemBackground);
        viewHolder.mImageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        viewHolder.mImageViewMoviePoster.setLayoutParams(new RelativeLayout.LayoutParams(width/2, (int) (width/2*1.50)));
        viewHolder.mTextViewMovieTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mMovie = mMovies.get(position);
        Movie movie = holder.mMovie;

        holder.mItemBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.ARG_PARAM_MOVIE_TITLE, holder.mMovie.mTitle);
                intent.putExtra(MovieDetailActivity.ARG_PARAM_SYNOPSIS, holder.mMovie.mSynopsis);
                intent.putExtra(MovieDetailActivity.ARG_PARAM_POSTER_URL, holder.mMovie.mPosterURL);
                intent.putExtra(MovieDetailActivity.ARG_PARAM_USER_RATING, holder.mMovie.mUserRating);
                intent.putExtra(MovieDetailActivity.ARG_PARAM_RELEASE_DATE, holder.mMovie.mReleaseDate);
                mContext.startActivity(intent);
            }
        });
        Picasso.with(mContext).load(movie.mPosterURL).into(holder.mImageViewMoviePoster);
        holder.mTextViewMovieTitle.setText(movie.mTitle);

    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    // endregion

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Movie mMovie;
        private RelativeLayout mItemBackground;
        private ImageView mImageViewMoviePoster;
        private TextView mTextViewMovieTitle;


        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}

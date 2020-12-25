package com.project.recyclerviewmvvm.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.recyclerviewmvvm.R;
import com.project.recyclerviewmvvm.model.MovieModel;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private Context context;
    private List<MovieModel> movieList;

    public MovieListAdapter(Context context,List<MovieModel> movieList){
        this.context=context;
        this.movieList=movieList;

    }
    public void setMovieList(List<MovieModel> movieList){
        this.movieList=movieList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.title.setText(this.movieList.get(position).getTitle().toString());
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (this.movieList!=null){
            return this.movieList.size();
        }
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.tvtitle);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}

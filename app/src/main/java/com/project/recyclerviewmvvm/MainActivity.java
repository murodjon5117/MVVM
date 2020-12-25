package com.project.recyclerviewmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.recyclerviewmvvm.adapter.MovieListAdapter;
import com.project.recyclerviewmvvm.model.MovieModel;
import com.project.recyclerviewmvvm.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        final TextView noresult=findViewById(R.id.noResult);
        LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MovieListAdapter(this,movieModelList);

        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMovieListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null){
                    movieModelList=movieModels;
                    adapter.setMovieList(movieModels);
                    noresult.setVisibility(View.GONE);

                }else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}
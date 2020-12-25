package com.project.recyclerviewmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.recyclerviewmvvm.model.MovieModel;
import com.project.recyclerviewmvvm.network.APIService;
import com.project.recyclerviewmvvm.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movielist;

    public MovieListViewModel(){
        movielist=new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMovieListObserver() {
        return movielist;
    }

    public void makeApiCall(){
        APIService apiService= RetroInstance.getRetrofitClient().create(APIService.class);
        Call<List<MovieModel>> call=apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movielist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movielist.postValue(null);

            }
        });

    }
}

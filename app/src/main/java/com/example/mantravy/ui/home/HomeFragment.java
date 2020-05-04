package com.example.mantravy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.mantravy.Adapters.RestaurantAdapter;
import com.example.mantravy.Models.CurrentCity;
import com.example.mantravy.Models.NearbyRestaurant;
import com.example.mantravy.Models.Restaurant;
import com.example.mantravy.Network.ApiClient;
import com.example.mantravy.R;
import com.example.mantravy.Room.MyappDatabse;
import com.example.mantravy.Room.RoomRestaurant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    public static MyappDatabse myappDatabse;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myappDatabse = Room.databaseBuilder(getActivity(), MyappDatabse.class, "favfooddb").allowMainThreadQueries().build();

        List<RoomRestaurant> roomRestaurantList = myappDatabse.mydao().getFavFoods();
        Call<CurrentCity> currentCityCall = ApiClient.getInstance().getApi().getRestaurant(18.521428,73.8544541);

        currentCityCall.enqueue(new Callback<CurrentCity>() {
            @Override
            public void onResponse(Call<CurrentCity> call, Response<CurrentCity> response) {
                List<NearbyRestaurant> nearbyRestaurantList = new ArrayList<>();
                nearbyRestaurantList = response.body().getNearbyRestaurants();

                recyclerView.setAdapter(new RestaurantAdapter(nearbyRestaurantList,getActivity()));
            }

            @Override
            public void onFailure(Call<CurrentCity> call, Throwable t) {

            }
        });
        return root;
    }
}

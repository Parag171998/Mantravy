package com.example.mantravy.ui.dashboard;

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

import com.example.mantravy.Adapters.RoomAdapter;
import com.example.mantravy.R;
import com.example.mantravy.Room.RoomRestaurant;
import com.example.mantravy.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        getActivity().setTitle("Cart");
        recyclerView = root.findViewById(R.id.custom_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<RoomRestaurant> roomRestaurantList = new ArrayList<>();
        roomRestaurantList = HomeFragment.myappDatabse.mydao().getFavFoods();
        recyclerView.setAdapter(new RoomAdapter(getContext(),roomRestaurantList));
        return root;
    }
}

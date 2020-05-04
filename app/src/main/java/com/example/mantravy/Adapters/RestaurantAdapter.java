package com.example.mantravy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mantravy.Models.NearbyRestaurant;
import com.example.mantravy.Models.Restaurant;
import com.example.mantravy.R;
import com.example.mantravy.Room.RoomRestaurant;
import com.example.mantravy.ui.home.HomeFragment;


import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantAdapterHolder> {

    List<NearbyRestaurant> nearbyRestaurantList;
    Context context;

    public RestaurantAdapter(List<NearbyRestaurant> nearbyRestaurantList, Context context) {
        this.nearbyRestaurantList = nearbyRestaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantAdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_recycler_layout
                        ,parent
                        ,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapterHolder holder, int position) {

        Glide.with(context).load(nearbyRestaurantList.get(position).getRestaurant().getFeaturedImage()).into(holder.imageView);
        holder.name.setText(nearbyRestaurantList.get(position).getRestaurant().getName());
        holder.rating.setText(String.valueOf(nearbyRestaurantList.get(position).getRestaurant().getAverageCostForTwo().intValue()));
    }

    @Override
    public int getItemCount() {
        return nearbyRestaurantList.size();
    }

    class RestaurantAdapterHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView rating;
        Button cartBtn;
        public RestaurantAdapterHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.custom_img);
            name = itemView.findViewById(R.id.custom_title);
            rating = itemView.findViewById(R.id.custom_rating);
            cartBtn = itemView.findViewById(R.id.custom_cart);

            cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Restaurant restaurant = nearbyRestaurantList.get(getAdapterPosition()).getRestaurant();
                    if(HomeFragment.myappDatabse.mydao().chekIfPresent(restaurant.getId()) == null) {
                        RoomRestaurant roomRestaurant = new RoomRestaurant(restaurant.getId(), restaurant.getName(), restaurant.getUrl(), restaurant.getAverageCostForTwo(), restaurant.getFeaturedImage());
                        HomeFragment.myappDatabse.mydao().addFavFood(roomRestaurant);
                        Toast.makeText(context, "Added in cart", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Item already present in  cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

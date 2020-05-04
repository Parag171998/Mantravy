package com.example.mantravy.Adapters;

import android.content.Context;
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
import com.example.mantravy.R;
import com.example.mantravy.Room.RoomRestaurant;
import com.example.mantravy.ui.home.HomeFragment;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomAdapterHolder> {

    Context context;
    List<RoomRestaurant> roomRestaurantList;

    public RoomAdapter(Context context, List<RoomRestaurant> roomRestaurantList) {
        this.context = context;
        this.roomRestaurantList = roomRestaurantList;
    }

    @NonNull
    @Override
    public RoomAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomAdapter.RoomAdapterHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_room_layout
                        ,parent
                        ,false
                )
                );
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapterHolder holder, int position) {
        Glide.with(context).load(roomRestaurantList.get(position).getFeaturedImage()).into(holder.imageView);
        holder.name.setText(roomRestaurantList.get(position).getName());
        holder.rating.setText(String.valueOf(roomRestaurantList.get(position).getAverageCostForTwo().intValue()));
    }

    @Override
    public int getItemCount() {
        return roomRestaurantList.size();
    }

    class RoomAdapterHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView rating;
        Button deletebtn;

        public RoomAdapterHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.custom_room_img);
            name = itemView.findViewById(R.id.custom_room_title);
            rating = itemView.findViewById(R.id.custom_room_rating);
            deletebtn = itemView.findViewById(R.id.custom_room_delete);

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeFragment.myappDatabse.mydao().deleteFood(roomRestaurantList.get(getAdapterPosition()));
                    roomRestaurantList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

package com.tammamkhalaf.myuaeguide.HelperClasses.HomeAdapter.MostViewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tammamkhalaf.myuaeguide.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {

    ArrayList<MostViewedHelperClass> mostViewedLocations;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewd_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewedHelperClass helperClass = mostViewedLocations.get(position);

        holder.ivImageOfHotel.setImageResource(helperClass.getImageView());
        holder.tvNameOfHotel.setText(helperClass.getTextView());
        holder.tvDescriptionOfHotel.setText(helperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImageOfHotel;
        TextView tvNameOfHotel,tvDescriptionOfHotel;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImageOfHotel = itemView.findViewById(R.id.mv_image);
            tvNameOfHotel = itemView.findViewById(R.id.mv_title);
            tvDescriptionOfHotel = itemView.findViewById(R.id.mv_desc);
        }
    }
}
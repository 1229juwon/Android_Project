package com.example.myproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.Domain.CategoryDomain;
import com.example.myproject.Domain.PopularDomain;
import com.example.myproject.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<PopularDomain>popularDomains;

    public PopularAdapter(ArrayList<PopularDomain> popularDomains) {
        this.popularDomains = popularDomains;
    }

    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.popularTitle.setText(popularDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl = "game_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 1:{
                picUrl = "exercise_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 2:{
                picUrl = "drinking_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 3:{
                picUrl = "exhibition_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4:{
                picUrl = "bowling_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 5:{
                picUrl = "cafe_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }

        }
        // holder에 이미지 저장
        // drawable에서 coin.png 사진을 가져옴
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.popularPic);
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView popularTitle;
        TextView popularContents;
        TextView coin;
        TextView popularName;
        ImageView popularPic;
        LinearLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popularTitle = itemView.findViewById(R.id.popular_title);
            //popularName = itemView.findViewById(R.id.popular_Name);
            popularContents = itemView.findViewById(R.id.popular_contents);
            coin = itemView.findViewById(R.id.coin);
            popularPic = itemView.findViewById(R.id.popular_Pic);
            mainLayout = itemView.findViewById(R.id.popularLayout);
        }
    }
}

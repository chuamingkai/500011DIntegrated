package com.example.a500011d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

//this is for the lost page
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomeViewHolder> {
    //add context
    //create new  variables to hold values that is going to pass in mainActivity
    ArrayList<ItemEntry> data;;
    Context context;
    StorageReference storageRef;


    //constructors
    public HomePageAdapter(Context ct, StorageReference storageRef, ArrayList<ItemEntry> data){
        this.context = ct;
        this.data = data;
        this.storageRef=storageRef;
    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View homeView =inflater.inflate(R.layout.activity_home_row,parent,false);
        return new HomeViewHolder(homeView);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ItemEntry entry = data.get(position);
        holder.ItemName.setText(entry.getItem());
        holder.ItemTime.setText(entry.getDate_created());
        holder.ItemVenue.setText(entry.getLocation());
        holder.ItemDescription.setText(entry.getDescription());
        holder.ItemStatus.setText(entry.getStatus().toString());
        if (entry.isImageExist()){
            StorageReference imageRef = storageRef.child(entry.getImagePath());
            FireBaseUtils.downloadToImageView(context, imageRef, holder.myImage);
        }
    }



    @Override
    //need to pass the  number of items we have
    //??? if not in array how to get
    public int getItemCount() { return data.size(); }

    public class HomeViewHolder extends  RecyclerView.ViewHolder{
        TextView  ItemName,ItemTime,ItemVenue,ItemDescription,ItemStatus;
        //do we need another one for found item?
        ImageView myImage;
        ConstraintLayout HomeLayout;

        //communicating with onBindViewHolder
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName=itemView.findViewById(R.id.ItemName);
            ItemTime=itemView.findViewById(R.id.ItemTime);
            ItemVenue=itemView.findViewById(R.id.ItemVenue);
            ItemDescription=itemView.findViewById(R.id.ItemDescription);
            ItemStatus=itemView.findViewById(R.id.ItemStatus);
            myImage= itemView.findViewById(R.id.imageUploaded);
            HomeLayout =itemView.findViewById(R.id.HomeLayout);

        }
    }
}

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
public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewHolder> {
    //add context
    //create new  variables to hold values that is going to pass in mainActivity
    ArrayList<ItemEntry> data;;
    Context context;
    StorageReference storageRef;


    //constructors
    public MainPageAdapter(Context ct, StorageReference storageRef, ArrayList<ItemEntry> data){
        this.context = ct;
        this.data = data;
        this.storageRef=storageRef;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_mainpage_row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // how to change this
        ItemEntry entry = data.get(position);
        holder.ItemName.setText(entry.getItem());
        holder.ItemTime.setText(entry.getDate_created());
        holder.ItemVenue.setText(entry.getLocation());
        holder.ItemDescription.setText(entry.getDescription());
        if (entry.isImageExist()){
            StorageReference imageRef = storageRef.child(entry.getImagePath());
            FireBaseUtils.downloadToImageView(context, imageRef, holder.myImage);
        }
    }



    @Override
    //need to pass the  number of items we have
    //??? if not in array how to get
    public int getItemCount() { return data.size(); }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView  ItemName,ItemTime,ItemVenue,ItemDescription;
        //do we need another one for found item?
        ImageView myImage;
        ConstraintLayout mainLayout;

        //communicating with onBindViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName=itemView.findViewById(R.id.ItemName);
            ItemTime=itemView.findViewById(R.id.ItemTime);
            ItemVenue=itemView.findViewById(R.id.ItemVenue);
            ItemDescription=itemView.findViewById(R.id.ItemDescription);
            myImage= itemView.findViewById(R.id.imageUploaded);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}

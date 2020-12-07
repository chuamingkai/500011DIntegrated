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
public class ProfilePageAdapter extends RecyclerView.Adapter<ProfilePageAdapter.ProfileViewHolder> {
    //add context
    //create new  variables to hold values that is going to pass in mainActivity
    ArrayList<ItemEntry> data;;
    Context context;
    StorageReference storageRef;


    //constructors
    public ProfilePageAdapter(Context ct, StorageReference storageRef, ArrayList<ItemEntry> data){
        this.context = ct;
        this.data = data;
        this.storageRef=storageRef;
    }



    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View profileView =inflater.inflate(R.layout.activity_profile_row,parent,false);
        return new ProfileViewHolder(profileView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
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

    public class ProfileViewHolder extends  RecyclerView.ViewHolder{
        TextView  ItemName,ItemTime,ItemVenue,ItemDescription,ItemStatus;
        //do we need another one for found item?
        ImageView myImage;
        ConstraintLayout ProfileLayout;

        //communicating with onBindViewHolder
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName=itemView.findViewById(R.id.ItemName);
            ItemTime=itemView.findViewById(R.id.ItemTime);
            ItemVenue=itemView.findViewById(R.id.ItemVenue);
            ItemDescription=itemView.findViewById(R.id.ItemDescription);
            ItemStatus=itemView.findViewById(R.id.ItemStatus);
            myImage= itemView.findViewById(R.id.imageUploaded);
            ProfileLayout=itemView.findViewById(R.id.ProfileLayout);

        }
    }
}

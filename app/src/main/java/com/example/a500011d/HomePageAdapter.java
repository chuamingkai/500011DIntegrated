package com.example.a500011d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    HomeAdapterListener clickListener;


    //constructors
    public HomePageAdapter(Context ct, StorageReference storageRef, ArrayList<ItemEntry> data, HomeAdapterListener listener){
        this.context = ct;
        this.data = data;
        this.storageRef = storageRef;
        this.clickListener = listener;
    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View homeView =inflater.inflate(R.layout.activity_home_row, parent,false);
        return new HomeViewHolder(homeView);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ItemEntry entry = data.get(position);
        holder.itemName.setText(entry.getItem());
        holder.itemTime.setText(entry.getDate_created());
        holder.itemVenue.setText(entry.getLocation());
        holder.itemDescription.setText(entry.getDescription());
        holder.itemStatus.setText(entry.getStatus().toString());
        if (entry.isImageExist()){
            StorageReference imageRef = storageRef.child(entry.getImagePath());
            FireBaseUtils.downloadToImageView(context, imageRef, holder.myImage);
        }
    }

    public interface HomeAdapterListener {
        void navigateToChatOnClick(View v);
    }

    @Override
    public int getItemCount() { return data.size(); }

    public class HomeViewHolder extends  RecyclerView.ViewHolder{
        TextView itemName, itemTime, itemVenue, itemDescription, itemStatus;
        ImageView myImage;
        Button buttonChat;

        //communicating with onBindViewHolder
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemName);
            itemTime = itemView.findViewById(R.id.ItemTime);
            itemVenue = itemView.findViewById(R.id.ItemVenue);
            itemDescription = itemView.findViewById(R.id.ItemDescription);
            itemStatus = itemView.findViewById(R.id.ItemStatus);
            myImage = itemView.findViewById(R.id.imageUploaded);
            buttonChat = itemView.findViewById(R.id.ChatHomeButton);
            buttonChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.navigateToChatOnClick(view);
                }
            });
        }
    }
}

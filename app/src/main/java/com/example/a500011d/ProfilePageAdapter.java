package com.example.a500011d;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    ProfileAdapterListener clickListeners;


    //constructors
    public ProfilePageAdapter(Context ct, StorageReference storageRef, ArrayList<ItemEntry> data, ProfileAdapterListener clickListeners){
        this.context = ct;
        this.data = data;
        this.storageRef = storageRef;
        this.clickListeners = clickListeners;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View profileView = inflater.inflate(R.layout.activity_profile_row, parent,false);
        return new ProfileViewHolder(profileView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        ItemEntry entry = data.get(position);
        holder.itemName.setText(entry.getItem());
        holder.itemTime.setText(entry.getDate_created());
        holder.itemVenue.setText(entry.getLocation());
        holder.itemDescription.setText(entry.getDescription());
        holder.buttonStatus.setText(entry.getStatus().toString());
        if (entry.isImageExist()){
            StorageReference imageRef = storageRef.child(entry.getImagePath());
            FireBaseUtils.downloadToImageView(context, imageRef, holder.myImage);
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public interface ProfileAdapterListener {
        void resolveItemOnClick(View v, int position);
        void navigateToChatOnClick(View v);
    }

    public class ProfileViewHolder extends  RecyclerView.ViewHolder {
        TextView itemName, itemTime, itemVenue, itemDescription;
        ImageView myImage;
        Button buttonStatus;
        Button buttonChat;

        //communicating with onBindViewHolder
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemName);
            itemTime = itemView.findViewById(R.id.ItemTime);
            itemVenue = itemView.findViewById(R.id.ItemVenue);
            itemDescription = itemView.findViewById(R.id.ItemDescription);
            buttonStatus = itemView.findViewById(R.id.ItemStatus);
            buttonStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListeners.resolveItemOnClick(view, getAdapterPosition());
                }
            });
            buttonChat = itemView.findViewById(R.id.ChatProfileButton);
            buttonChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListeners.navigateToChatOnClick(view);
                }
            });
            myImage = itemView.findViewById(R.id.imageUploaded);
        }
    }
}

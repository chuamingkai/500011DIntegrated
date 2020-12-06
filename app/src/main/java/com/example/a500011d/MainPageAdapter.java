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

import java.util.ArrayList;

//this is for the lost page
public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewHolder> {
    //add context
    //create new  variables to hold values that is going to pass in mainActivity
    ArrayList<ItemEntry> data;;
    Context context;


    //constructors
    public MainPageAdapter(Context ct, ArrayList<ItemEntry> data){
        this.context = ct;
        this.data = data;

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
        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.text3.setText(data3[position]);
        holder.text4.setText(data4[position]);
        holder.myImage.setImageResource(imagePath[position]);


    }



    @Override
    //need to pass the  number of items we have
    //???
    public int getItemCount() {
        return lost_images.length;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView  text1,text2,text3,text4;
        //do we need another one for found item?
        ImageView myImage;
        ConstraintLayout mainLayout;

        //communicating with onBindViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.ItemName);
            text2=itemView.findViewById(R.id.ItemTime);
            text3=itemView.findViewById(R.id.ItemVenue);
            text4=itemView.findViewById(R.id.ItemDescription);
            myImage= itemView.findViewById(R.id.LostImage);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}

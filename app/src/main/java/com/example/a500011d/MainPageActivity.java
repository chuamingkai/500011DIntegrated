package com.example.a500011d;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

//for the homepage(lost page)
public class MainPageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainPageAdapter mainPageAdapter;
    ItemEntry itemEntry;
    ImageView imageViewSelected;
    DatabaseReference mRootDatabaseRef;
    DatabaseReference mNodeRefItem;


    final StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    StorageReference item_image = storageRef.child(ItemEntry.imagePath);

   // String lost_item[], lost_d[],lost_time[],lost_venue[];
    //string xml file
    //drawable
    //int lost_image[]={R.drawable.book1, R.drawable.book2,R.drawable.book3,R.drawable.book4,R.drawable.book5,R.drawable.book6,R.drawable.pen1,R.drawable.pen2,R.drawable.pen3,R.drawable.ruler1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_row);
        recyclerView=findViewById(R.id.recycleView) ;
        imageViewSelected=findViewById(R.id.imageViewSelected);

        mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mNodeRefItem = mRootDatabaseRef.child(getString(R.string.item_node_key));

        //lost_item=getResources().
        //lost_d=getResources().getStringArray(R.array.lost_item_description);
        //lost_time =getResources().getStringArray(R.array.lost_item_time);
        //lost_venue=getResources().getStringArray(R.array.lost_item_venue);



        //pass the value from main activity to RecycleViewAdapter
        MainPageAdapter myAdapter= new MainPageAdapter(ItemEntry);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //this,lost_item,lost_time,lost_venue,lost_d,lost_image


    }
}
package com.example.a500011d;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    Button chatHome;
    Button post;
    TextView text;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef = db.collection("Items").document("LostItem");
    public Map<String,Object> doc;

    DatabaseReference mRootDatabaseRef;
    DatabaseReference mNodeRefItem;
    RecyclerView HomeRecycleView;
    HomePageAdapter homeAdapter;
    ArrayList<ItemEntry> data;

    final StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeRecycleView = findViewById(R.id.HomeRecycleView);
        mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mNodeRefItem = mRootDatabaseRef.child(getString(R.string.item_node_key));
        mNodeRefItem.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
                if (count > 0) {
                    data = new ArrayList<ItemEntry>((int) count);
                    for (Iterator<DataSnapshot> item = snapshot.getChildren().iterator(); item.hasNext(); ) {
                        DataSnapshot ds = item.next();
                        ItemEntry entry = ds.getValue(ItemEntry.class);
                        if (entry.getStatus() == ItemEntry.Status.RESOLVED) continue;
                        entry.databaseId = ds.getKey();
                        data.add(entry);
                    }

                }
                homeAdapter = new HomePageAdapter(HomeActivity.this, storageRef, data, new HomePageAdapter.HomeAdapterListener() {
                    @Override
                    public void navigateToChatOnClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                });
                HomeRecycleView.setAdapter(homeAdapter);
                HomeRecycleView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*
        chatHome = findViewById(R.id.ChatHomeButton);
        //post = findViewById(R.id.PostButton);
        chatHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        ChatActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
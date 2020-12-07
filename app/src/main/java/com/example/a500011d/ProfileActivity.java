package com.example.a500011d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProfileActivity extends AppCompatActivity {
    String username;

    DatabaseReference mRootDatabaseRef;
    DatabaseReference mNodeRefItem;
    RecyclerView ProfileRecyclerView;
    ProfilePageAdapter profileAdapter;
    ArrayList<ItemEntry> data;

    final StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = SharedPrefs.getUsernamePref(ProfileActivity.this);

        ProfileRecyclerView = findViewById(R.id.ProfileRecycleView);
        mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mNodeRefItem = mRootDatabaseRef.child(getString(R.string.item_node_key));
        mNodeRefItem.orderByChild(getString(R.string.item_username)).equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
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
                profileAdapter = new ProfilePageAdapter(ProfileActivity.this, storageRef, data, new ProfilePageAdapter.ProfileAdapterListener() {
                    @Override
                    public void resolveItemOnClick(View v, int position) {
                        ItemEntry entry = data.get(position);
                        entry.resolve();
                        mNodeRefItem.child(entry.databaseId).setValue(entry);
                        Toast.makeText(ProfileActivity.this, "Resolved", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void navigateToChatOnClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                });
                ProfileRecyclerView.setAdapter(profileAdapter);
                ProfileRecyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
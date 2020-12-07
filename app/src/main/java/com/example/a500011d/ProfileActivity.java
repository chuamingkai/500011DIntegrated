package com.example.a500011d;

import android.content.Intent;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity {


    TextView text;
    String username;
    Button chatProfile;
    Button changeStatus;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef = db.collection("Items").document("LostItem");
    public Map<String,Object> doc;

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
        Intent intent = getIntent();
        username = intent.getStringExtra(getString(R.string.username_intent));
        ProfileRecyclerView = findViewById(R.id.ProfileRecycleView);
        mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mNodeRefItem.orderByChild(getString(R.string.item_username)).equalTo(username).addListenerForSingleValueEvent;
        mNodeRefItem.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
                if (count > 0) {
                    data = new ArrayList<ItemEntry>((int) count);
                    for (Iterator<DataSnapshot> item = snapshot.getChildren().iterator(); item.hasNext(); ) {
                        DataSnapshot ds = item.next();
                        ItemEntry entry = ds.getValue(ItemEntry.class);
                        entry.databaseId = ds.getKey();
                        data.add(entry);
                    }

                }

                profileAdapter = new ProfilePageAdapter(ProfileActivity.this, storageRef, data);
                ProfileRecyclerView.setAdapter(profileAdapter);
                ProfileRecyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        chatProfile= findViewById(R.id.ChatProfileButton);
        changeStatus=findViewById(R.id.ItemStatus);
        chatProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,
                        ChatActivity.class);
                startActivity(intent);
            }
        });

        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change status, what to put in intend?
                Intent intent = new Intent(ProfileActivity.this,
                        ChatActivity.class);
                startActivity(intent);
            }
        });
        /*
        text = findViewById(R.id.ITEMLIST);
        text.setMovementMethod(new ScrollingMovementMethod());
        // Here update text with the values;
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                                StringBuilder itemInfo = new StringBuilder();
                                List<Map<String,String>> items = new ArrayList<Map<String,String>>();
                                doc = documentSnapshot.getData();
                                for(String itemName : doc.keySet()){
                                    items.add((Map<String, String>) doc.get(itemName));

                                }
                                for(Map<String,String> item : items){
                                        itemInfo.append("item : ").append(item.get("item")).append("\n")
                                                .append("date : ").append(item.get("date")).append("\n")
                                                .append("information : ").append(item.get("information")).append("\n").append("\n");
                                }
                                text.setText(itemInfo);
                        }else{
                            Toast.makeText(HomeActivity.this,"No items updated!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d("TAG",e.toString());
                    }
                });

            }
        });
        */



    }
}
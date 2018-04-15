package com.example.maceo.babylog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private ListAdapter adapter;

    private List<ListItem> listItems;

    private DatabaseReference mDbRef;
    private FirebaseAuth mAuth;
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        adapter = new ListAdapter(NotificationActivity.this, listItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getUid();
        mDbRef = FirebaseDatabase.getInstance().getReference("Users/" + user_id + "/Notifications");

        valueEventListener = mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItems.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ListItem listItem = postSnapshot.getValue(ListItem.class);
                    if (listItem != null) {
                        listItem.setKey(postSnapshot.getKey());
                    }
                    listItems.add(listItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NotificationActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void showDialog(){
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment addNotification = new  AddNotification();
        addNotification.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        addNotification.show(fragmentManager, "AddNotification");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_notification, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }else if(id == R.id.add_notification){
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Tap and hold for menu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClick(int position) {
        ListItem selectedItem = listItems.get(position);
        String selectedKey = selectedItem.getKey();

    }

    @Override
    public void onDeleteClick(int position){
        ListItem selectedItem = listItems.get(position);
        String selectedKey = selectedItem.getKey();

        mDbRef.child(selectedKey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(NotificationActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbRef.removeEventListener(valueEventListener);
    }
}

package com.tropicthunder.firehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<PostDetails> postsList;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTeach = (Button)findViewById(R.id.btn_Teach);

        btnTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeachActivity.class));
                finish();
            }
        });

        postsList = new ArrayList<PostDetails>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_classesList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (and pass questions list data to adapter)
        mAdapter = new PostListAdapter(this.postsList, getApplicationContext());
        adapter  = mAdapter;
        mRecyclerView.setAdapter(mAdapter);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com/data/posts");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PostDetails post = dataSnapshot.getValue(PostDetails.class);
                System.out.println(post.getName());
                postsList.add(post);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }




}

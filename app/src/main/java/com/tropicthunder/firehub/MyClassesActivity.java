package com.tropicthunder.firehub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.List;

public class MyClassesActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<PostDetails> postsList;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);

        Button btnBack = (Button) findViewById(R.id.btn_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        postsList = new ArrayList<PostDetails>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_myClassesList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (and pass questions list data to adapter)
        mAdapter = new MyClassListAdapter(this.postsList, getApplicationContext());
        adapter  = mAdapter;
        mRecyclerView.setAdapter(mAdapter);

        final SessionManager sessionManager = new SessionManager(this);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com/data/posts");
        //Query query = ref.orderByChild("uid").equalTo(sessionManager.getUid());

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               if (!dataSnapshot.getKey().equals("count")){
                    PostDetails post = dataSnapshot.getValue(PostDetails.class);

                    String[] participants = post.getParticipants();
                    if (participants!= null && participants.length>0) {
                        for (int i = 0; i < participants.length; i++) {
                            System.out.println(participants[i]);
                            if (participants[i].equals(sessionManager.getUid())) {
                                postsList.add(post);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                }

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

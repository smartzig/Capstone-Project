package com.smartz.conexaodescontos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscountListActivity extends AppCompatActivity {




    private static final String LOG_TAG = DiscountListActivity.class.toString();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rvNoteList)
    RecyclerView mRecyclerView;

    private PromotionRecyclerViewAdapter mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

  
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_list);



        ButterKnife.bind(this);

        //getSupportActionBar().hide();




        firestoreDB = FirebaseFirestore.getInstance();

        firestoreListener = firestoreDB.collection("promotion")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(LOG_TAG, "Listen failed!", e);
                            return;
                        }

                        List<Promotion> promotionList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            Promotion promotion = doc.toObject(Promotion.class);
                            promotion.setId(Long.parseLong(doc.getId()));


                            promotionList.add(promotion);
                        }

                        mAdapter = new PromotionRecyclerViewAdapter(promotionList, getApplicationContext(), firestoreDB);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            if (item.getItemId() == R.id.addNote) {
                Intent intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }*/
}

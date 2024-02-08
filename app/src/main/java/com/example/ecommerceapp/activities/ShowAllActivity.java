package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapters.ShowAllAdapter;
import com.example.ecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;

    Toolbar toolbar;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, showAllModelList);
        recyclerView.setAdapter(showAllAdapter);

        firestore.collection("ShowAll")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                showAllModelList.add(showAllModel);


                            }
                            showAllAdapter.notifyDataSetChanged();
                        }

                    }
                });

        if (type == null || type.isEmpty()) {
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);


                                }
                                showAllAdapter.notifyDataSetChanged();
                            }

                        }
                    });
            if (type != null && type.equalsIgnoreCase("men")) {
                firestore.collection("ShowAll").whereEqualTo("type", "men")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                        ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                        showAllModelList.add(showAllModel);


                                    }
                                    showAllAdapter.notifyDataSetChanged();
                                }

                            }
                        });
                if (type != null && type.equalsIgnoreCase("women")) {
                    firestore.collection("ShowAll").whereEqualTo("type", "women")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                            showAllModelList.add(showAllModel);


                                        }
                                        showAllAdapter.notifyDataSetChanged();
                                    }

                                }
                            });
                    if (type != null && type.equalsIgnoreCase("pant")) {
                        firestore.collection("ShowAll").whereEqualTo("type", "pant")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                showAllModelList.add(showAllModel);


                                            }
                                            showAllAdapter.notifyDataSetChanged();
                                        }

                                    }
                                });
                        if (type != null && type.equalsIgnoreCase("womenpant")) {
                            firestore.collection("ShowAll").whereEqualTo("type", "womenpant")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                    showAllModelList.add(showAllModel);


                                                }
                                                showAllAdapter.notifyDataSetChanged();
                                            }

                                        }
                                    });
                            if (type != null && type.equalsIgnoreCase("polo")) {
                                firestore.collection("ShowAll").whereEqualTo("type", "polo")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                        ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                        showAllModelList.add(showAllModel);


                                                    }
                                                    showAllAdapter.notifyDataSetChanged();
                                                }

                                            }
                                        });
                                if (type != null && type.equalsIgnoreCase("jogger")) {
                                    firestore.collection("ShowAll").whereEqualTo("type", "jogger")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                            showAllModelList.add(showAllModel);


                                                        }
                                                        showAllAdapter.notifyDataSetChanged();
                                                    }

                                                }
                                            });
                                    if (type != null && type.equalsIgnoreCase("kurta")) {
                                        firestore.collection("ShowAll").whereEqualTo("type", "kurta")
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                                ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                                showAllModelList.add(showAllModel);


                                                            }
                                                            showAllAdapter.notifyDataSetChanged();
                                                        }

                                                    }
                                                });
                                        if (type != null && type.equalsIgnoreCase("womenjogger")) {
                                            firestore.collection("ShowAll").whereEqualTo("type", "womenjogger")
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                                                    showAllModelList.add(showAllModel);


                                                                }
                                                                showAllAdapter.notifyDataSetChanged();
                                                            }

                                                        }
                                                    });
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
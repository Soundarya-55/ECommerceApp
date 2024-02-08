package com.example.ecommerceapp.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activities.ShowAllActivity;
import com.example.ecommerceapp.adapters.CategoriesAdapter;
import com.example.ecommerceapp.adapters.NewProductAdapter;
import com.example.ecommerceapp.adapters.PopularProductAdapter;
import com.example.ecommerceapp.adapters.PopularProductAdapter1;
import com.example.ecommerceapp.models.CategoryModel;
import com.example.ecommerceapp.models.NewProductModel;
import com.example.ecommerceapp.models.PopularProductModel;
import com.example.ecommerceapp.models.PopularProductModel1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    TextView catShowAll , newProductShowAll, popularProductShowAll;

    LinearLayout linearLayout;
     RecyclerView catRecyclerview,newProductRecyclerview,popularRecyclerview,popularRecyclerview1,searchRecyclerview;

    //Category recyclerview
    CategoriesAdapter categoriesAdapter;
    List<CategoryModel> categoryModelList;

    //New product recyclerview
    NewProductAdapter newProductAdapter;
    List<NewProductModel> newProductModelList;

    //Popular product
    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;

    ProgressDialog progressDialog;

    //Popular product
    PopularProductAdapter1 popularProductAdapter1;
    List<PopularProductModel1> popularProductModel1List;


    //FireStore
    FirebaseFirestore db ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();

        progressDialog =new ProgressDialog(getActivity());
        catRecyclerview = root.findViewById(R.id.rec_category);
        newProductRecyclerview = root.findViewById(R.id.new_product_rec);
        popularRecyclerview = root.findViewById(R.id.popular_rec);
        popularRecyclerview1 = root.findViewById(R.id.popular_rec1);

        catShowAll = root.findViewById(R.id.category_see_all);
        newProductShowAll = root.findViewById(R.id.newProducts_see_all);
        popularProductShowAll = root.findViewById(R.id.popular_see_all);




        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popularProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);
        //image  slider
         ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome to toj");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //Category
        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoriesAdapter =new CategoriesAdapter(getActivity(),categoryModelList);
        catRecyclerview.setAdapter(categoriesAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoriesAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }



                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        //New products
        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductModelList = new ArrayList<>();
        newProductAdapter =new NewProductAdapter(getActivity(),newProductModelList);
        newProductRecyclerview.setAdapter(newProductAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewProductModel newProductModel = document.toObject(NewProductModel.class);
                                newProductModelList.add(newProductModel);
                            }
                            // Notify the adapter of the dataset changes after all data is added.
                            newProductAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


// Assuming you are using 'popularRecyclerview' for the RecyclerView widget
        popularRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter =new PopularProductAdapter(getActivity(),popularProductModelList);
        popularRecyclerview.setAdapter(popularProductAdapter);

// Replace 'db' with the reference to your Firestore database
        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Make sure 'PopularProductModel' is the correct class corresponding to your Firestore documents
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                            }
                            // Notify the adapter of the dataset changes after all data is added.
                            popularProductAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //popular product
        popularRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        List<PopularProductModel1> popularProductModel1List = new ArrayList<>();
        PopularProductAdapter1 popularProductAdapter1;
        popularProductAdapter1 = new PopularProductAdapter1(getActivity(), popularProductModel1List);
        popularRecyclerview1.setAdapter(popularProductAdapter1);

        db.collection("PopularProduct1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularProductModel1 popularProductModel1 = document.toObject(PopularProductModel1.class);
                                popularProductModel1List.add(popularProductModel1);
                                popularProductAdapter1.notifyDataSetChanged();
                            }



                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });





        return root;


    }
}

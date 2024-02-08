package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.NewProductModel;
import com.example.ecommerceapp.models.PopularProductModel;
import com.example.ecommerceapp.models.PopularProductModel1;
import com.example.ecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, description, price;
    TextView rating;
    Button addToCart, buyNow;
    ImageView addItem, removeItem;

    Toolbar toolbar;
    int totalQuantity = 1;
    int totalPrice = 0;

    //New products
    NewProductModel newProductModel = null;

    //Popular product
    PopularProductModel popularProductModel = null;

    //Popular product1
    PopularProductModel1 popularProductModel1 = null;
    //Show all
    ShowAllModel showAllModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductModel) {
            newProductModel = (NewProductModel) obj;
        } else if (obj instanceof PopularProductModel) {
            popularProductModel = (PopularProductModel) obj;

        } else if (obj instanceof PopularProductModel1) {
            popularProductModel1 = (PopularProductModel1) obj;
        } else if (obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;

    }


        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.my_rating1);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        //New Products
        if (newProductModel != null) {
            Glide.with(getApplicationContext()).load(newProductModel.getImg_url()).into(detailedImg);
            name.setText(newProductModel.getName());
            rating.setText(String.valueOf(newProductModel.getRating()));
            description.setText(newProductModel.getDescription());
            price.setText(String.valueOf(newProductModel.getPrice()));
            name.setText(newProductModel.getName());

        }
        //Popular  Products
        if (popularProductModel != null) {
            Glide.with(getApplicationContext()).load(popularProductModel.getImg_url()).into(detailedImg);
            name.setText(popularProductModel.getName());
            rating.setText(String.valueOf(popularProductModel.getRating()));
            price.setText(String.valueOf(popularProductModel.getPrice()));
            name.setText(popularProductModel.getName());

        }
        //Popular  Products 1
        if (popularProductModel1 != null) {
        Glide.with(getApplicationContext()).load(popularProductModel1.getImg_url()).into(detailedImg);
        name.setText(popularProductModel1.getName());
        rating.setText(String.valueOf(popularProductModel1.getRating()));
        price.setText(String.valueOf(popularProductModel1.getPrice()));
        name.setText(popularProductModel1.getName());

    }
    //Show All Products
        if ( showAllModel != null) {
        Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
        name.setText(showAllModel.getName());
        rating.setText(String.valueOf(showAllModel.getRating()));
        price.setText(String.valueOf(showAllModel.getPrice()));
        description.setText(showAllModel.getDescription());
        name.setText(showAllModel.getName());

         }
        //Buy Now
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);
               if (newProductModel != null){
                   intent.putExtra("item",newProductModel);
                }
               if (popularProductModel !=null){
                   intent.putExtra("item",popularProductModel);
               }
                if (showAllModel !=null){
                    intent.putExtra("item",showAllModel);
                }

               startActivity(intent);

            }
        });
        //Add To cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCart();
            }
        });
    }

    private void addtoCart() {

        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap  = new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Added To a Cart",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }


}

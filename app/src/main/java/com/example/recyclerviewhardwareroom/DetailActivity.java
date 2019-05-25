package com.example.recyclerviewhardwareroom;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    TextView tVDetailName, tVDetailDescription, tVDetailPrice, tVDetailType, tVDetailExpire;
    ImageView iVDetailImage;
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_DATE = "extra_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        int photo = getIntent().getIntExtra(EXTRA_PHOTO, 0);
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        String date = getIntent().getStringExtra(EXTRA_DATE);
        Float price = getIntent().getFloatExtra(EXTRA_PRICE,0);

        tVDetailName = findViewById(R.id.tV_item_detail_name);
        tVDetailDescription = findViewById(R.id.tV_item_detail_description);
        tVDetailPrice = findViewById(R.id.tV_item_detail_price);
        tVDetailExpire = findViewById(R.id.tV_item_detail_expire);
        iVDetailImage = findViewById(R.id.img_item_photo_detail);

        tVDetailName.setText(name);
        tVDetailDescription.setText(description);
        tVDetailPrice.setText("Rp. "+String.valueOf(price));
        tVDetailExpire.setText(date);
//        tVDetailType.setText(String.valueOf(photo));

//        iVDetailImage.setImageResource(photo);





    }

    public int getImageFromDrawableByName(String imageName){
        int drawableResId = getApplicationContext().getResources().getIdentifier(imageName, "drawable", getApplicationContext().getPackageName());
        return drawableResId;
    }

}

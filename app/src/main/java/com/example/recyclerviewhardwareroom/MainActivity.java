package com.example.recyclerviewhardwareroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddHardwareDialogFragment.NoticeDialogListener{
    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
    private HardwareViewModel hardwareViewModel;
    private List<Hardware> listHardware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get data dari array gambar di resource
        TypedArray img = getResources().obtainTypedArray(R.array.images);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        listHardware = new ArrayList<>();
        showRecyclerView(img);

        //floating action
        FloatingActionButton add = findViewById(R.id.FAB);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //memanggil fragment
                AddHardwareDialogFragment addDialogFragment = AddHardwareDialogFragment.newInstance("AddHardwareDialogFragment");
                addDialogFragment.show(getSupportFragmentManager(), "AddHardwareDialogFragment");
            }
        });
    }

    private void showRecyclerView(TypedArray img){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final ListHardwareAdapter listHardwareAdapter = new ListHardwareAdapter(getApplicationContext(), img);
        recyclerView.setAdapter(listHardwareAdapter);

        //view model
        hardwareViewModel = ViewModelProviders.of(this).get(HardwareViewModel.class);
        hardwareViewModel.getmAllHardware().observe(this, new Observer<List<Hardware>>() {
            @Override
            public void onChanged(@Nullable List<Hardware> listHardware) {
                //update recyclerview
                listHardwareAdapter.setListHardware(listHardware);
            }
        });

        listHardwareAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Hardware hardware) {
                showSelectedHardware(hardware);
            }
        });

//        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
////                showSelectedHardware(listHardware.get(position));
////                Toast.makeText(getApplicationContext(), listHardware.get(position).getDescription(),Toast.LENGTH_SHORT).show();
//                Log.d("ININIH", getListHardware().toString());
//            }
//        });

    }

    private void showRecyclerCardView(TypedArray img){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final CardViewHardwareAdapter cardViewHardwareAdapter = new CardViewHardwareAdapter(getApplicationContext(), img);
        recyclerView.setAdapter(cardViewHardwareAdapter);

        //view model
        hardwareViewModel = ViewModelProviders.of(this).get(HardwareViewModel.class);
        hardwareViewModel.getmAllHardware().observe(this, new Observer<List<Hardware>>() {
            @Override
            public void onChanged(@Nullable List<Hardware> listHardware) {
                //update recyclerview
                cardViewHardwareAdapter.setListHardware(listHardware);
            }
        });

        cardViewHardwareAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Hardware hardware) {
                showSelectedHardware(hardware);
            }
        });

//        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                showSelectedHardware(listHardware.get(position));
//            }
//        });
    }

    private void showSelectedHardware(Hardware hardware){
        Intent moveWithDataIntent = new Intent(MainActivity.this, DetailActivity.class);
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_NAME, hardware.getName());
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_DESCRIPTION, hardware.getDescription());
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_PHOTO, hardware.getImg());
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_TYPE, hardware.getType());
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_DATE, hardware.getManufacturedAt().toString());
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_PRICE, hardware.getPrice());
        startActivity(moveWithDataIntent);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = null;
        TypedArray img = getResources().obtainTypedArray(R.array.images);
        switch (item.getItemId()){
            case R.id.action_list:
                title = "ListView";
                showRecyclerView(img);
                break;
            case R.id.action_cardview:
                title = "CardView";
                showRecyclerCardView(img);
                break;
        }
        setActionBarTitle(title);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment fragment, Hardware hardware) {
        hardwareViewModel.insert(hardware);
        Toast.makeText(this, "Added New Hardware", Toast.LENGTH_SHORT).show();
    }
    public List<Hardware> getListHardware() {
        return listHardware;
    }
}

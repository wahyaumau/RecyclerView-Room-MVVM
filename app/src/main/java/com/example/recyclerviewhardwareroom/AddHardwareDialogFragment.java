package com.example.recyclerviewhardwareroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddHardwareDialogFragment extends DialogFragment {
    private static final String TAG = "AddHardwareDialogFragment";

    private static  Drawable[] drawables = new Drawable[12];
    private ImageView imageView;
    private EditText editTextDescription, editTextPrice, editTextExpire, editTextType;
    private Spinner spinnerName;
    private int currentPosition;
    String[] hardwareNames;

    NoticeDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NoticeDialogListener) context;
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment fragment, Hardware hardware);
    }

    static AddHardwareDialogFragment newInstance(String title){
        AddHardwareDialogFragment add = new AddHardwareDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        add.setArguments(args);
        return add;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_hardware_dialog_fragment, null);
        builder.setView(view);

        spinnerName = view.findViewById(R.id.spinner_item_name);
        editTextDescription = view.findViewById(R.id.edit_text_item_description);
        editTextExpire = view.findViewById(R.id.edit_text_item_expire_date);
        editTextPrice = view.findViewById(R.id.edit_text_item_price);
        imageView = view.findViewById(R.id.iv_item_image);

        hardwareNames = getResources().getStringArray(R.array.names);

        ArrayAdapter<String> adapterHardwareName = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, hardwareNames);
        adapterHardwareName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerName.setAdapter(adapterHardwareName);

        spinnerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Drawable drawable = AddHardwareDialogFragment.getHardwareDrawable(getResources(), position);
                imageView.setImageDrawable(drawable);
                currentPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = hardwareNames[currentPosition];
//                String type = editTextType.getText().toString();
                String desc = editTextDescription.getText().toString();
                String price = editTextPrice.getText().toString();
//
                if (TextUtils.isEmpty(desc) || TextUtils.isEmpty(price)){
                    Toast.makeText(getActivity(), "Data cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    String dateExpire = editTextExpire.getText().toString();
                    try {
                        Date parsedDateExpire = dateFormat.parse(dateExpire);
                        Hardware newHardware = new Hardware(name,desc, "type", Float.parseFloat(price), currentPosition, parsedDateExpire);
                        listener.onDialogPositiveClick(AddHardwareDialogFragment.this, newHardware);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

    public static Drawable getHardwareDrawable(Resources resources, int index) {
        if (drawables[index]==null){
            TypedArray images = resources.obtainTypedArray(R.array.images);
            Drawable drawable = images.getDrawable(index);
            drawables[index]=drawable;
        }
        return  drawables[index];
    }
}

package com.example.recyclerviewhardwareroom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CardViewHardwareAdapter extends RecyclerView.Adapter<CardViewHardwareAdapter.CardViewViewHolder>{
    private Context context;
    private List<Hardware> listHardware;
    private TypedArray img;



    private OnItemClickListener onItemClickListener;

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tVName, tVDescription;
        ImageView imgPhoto;
        Button btnFavorite, btnShare;
        View layout;

        CardViewViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tVName = itemView.findViewById(R.id.tv_item_name);
            tVDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(listHardware.get(position));
                    }
                }
            });
        }
    }

    public CardViewHardwareAdapter(Context context, TypedArray img) {
        this.context = context;
        this.img = img;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_hardware, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.tVName.setText(getListHardware().get(i).getName());
        cardViewViewHolder.tVDescription.setText(getListHardware().get(i).getDescription());
        cardViewViewHolder.imgPhoto.setImageDrawable(img.getDrawable(getListHardware().get(i).getImg()));


        cardViewViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Liked "+getListHardware().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        cardViewViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Share "+getListHardware().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public List<Hardware> getListHardware() {
        return listHardware;
    }

    public void setListHardware(List<Hardware> listHardware) {
        this.listHardware = listHardware;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listHardware==null) return 0;
        return listHardware.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}

package com.example.recyclerviewhardwareroom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ListHardwareAdapter extends RecyclerView.Adapter<ListHardwareAdapter.CategoryViewHolder> {
    private Context context;
    private List<Hardware> listHardware;
    private TypedArray img;
    private OnItemClickListener onItemClickListener;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tVName, tVDescription;
        ImageView imgPhoto;
        public View layout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            tVName = itemView.findViewById(R.id.tv_item_name);
            tVDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);

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

    public ListHardwareAdapter(Context context, TypedArray img) {
        this.context = context;
        this.img = img;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hardware,viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tVName.setText(listHardware.get(i).getName());
        categoryViewHolder.tVDescription.setText(listHardware.get(i).getDescription());
        categoryViewHolder.imgPhoto.setImageDrawable(img.getDrawable(listHardware.get(i).getImg()));

    }

    @Override
    public int getItemCount() {
        if (listHardware==null) return 0;
        return listHardware.size();
    }

    public void setListHardware(List<Hardware> hardwares){
        this.listHardware = hardwares;
        notifyDataSetChanged();
    }
    public Hardware getHardware(int position){
        return listHardware.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

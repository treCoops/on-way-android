package com.trecoops.onway.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.trecoops.onway.Model.Vehicle;
import com.trecoops.onway.R;

import java.util.ArrayList;

public class VehicleCategoryAdapter extends RecyclerView.Adapter<VehicleCategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vehicle> vehicleArrayList;
    private int checkedPosition = 0;
    private OnItemClickListener onItemClickListener;

    public VehicleCategoryAdapter(Context context, ArrayList<Vehicle> vehicleArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.vehicleArrayList = vehicleArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_vehicle_category_item, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(vehicleArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleArrayList.size();
    }

    public Vehicle getSelected(){
        if(checkedPosition != -1)
            return vehicleArrayList.get(checkedPosition);

        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSelected;
        ImageView imgCategory;
        TextView txtCategory;
        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            imgSelected = itemView.findViewById(R.id.imgSelected);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            this.onItemClickListener = onItemClickListener;
        }

        public void bind(final Vehicle vehicle) {
            txtCategory.setText(vehicle.getCat_name());
            imgSelected.setVisibility(vehicle.isChecked() ? View.VISIBLE : View.GONE);
            imgCategory.setImageDrawable(ContextCompat.getDrawable(context, vehicle.getResID()));

            if (checkedPosition == -1)
                imgSelected.setVisibility(View.GONE);
            else {
                if (checkedPosition == getAdapterPosition())
                    imgSelected.setVisibility(View.VISIBLE);
                else
                    imgSelected.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgSelected.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                        onItemClickListener.onItemClick(checkedPosition);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

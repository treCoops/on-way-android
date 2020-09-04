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

import com.trecoops.onway.Model.Service;
import com.trecoops.onway.Model.Vehicle;
import com.trecoops.onway.R;

import java.util.ArrayList;

public class ServiceCategoryAdapter extends RecyclerView.Adapter<ServiceCategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Service> serviceArrayList;

    public ServiceCategoryAdapter(Context context, ArrayList<Service> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_service_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(serviceArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }


    public ArrayList<Service> getSelected() {
        ArrayList<Service> selectedServices = new ArrayList<>();
        for (int i = 0; i < serviceArrayList.size(); i++) {
            if (serviceArrayList.get(i).isChecked()) {
                selectedServices.add(serviceArrayList.get(i));
            }
        }
        return selectedServices;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgService;
        ImageView imgSelected;
        TextView txtServiceName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgService = itemView.findViewById(R.id.imgService);
            imgSelected = itemView.findViewById(R.id.imgSelected);
            txtServiceName = itemView.findViewById(R.id.txtServiceName);
        }

        public void bind(final Service service) {
            txtServiceName.setText(service.getServiceName());
            imgSelected.setVisibility(service.isChecked() ? View.VISIBLE : View.GONE);
            imgService.setImageDrawable(ContextCompat.getDrawable(context, service.getResID()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    service.setChecked(!service.isChecked());
                    imgSelected.setVisibility(service.isChecked() ? View.VISIBLE : View.GONE);
                }
            });

        }
    }
}

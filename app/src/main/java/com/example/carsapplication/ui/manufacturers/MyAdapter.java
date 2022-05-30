package com.example.carsapplication.ui.manufacturers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<ManufacturerExample> manufacturersList;
    List<ManufacturerExample> manufacturerExampleListFull;

    public MyAdapter(Context context, List<ManufacturerExample> manufacturersList) {
        this.context = context;
        this.manufacturersList = manufacturersList;
        this.manufacturerExampleListFull = new ArrayList<>(manufacturersList);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.manufacturerName.setText(manufacturersList.get(position).getTitle());
        holder.country.setText(manufacturersList.get(position).getCountry());
        holder.manufacturerLogo.setImageResource(manufacturersList.get(position).getImageLogo());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("data1", manufacturersList.get(position).getTitle());
                intent.putExtra("data2", manufacturersList.get(position).getCountry());
                intent.putExtra("myImage", manufacturersList.get(position).getImageLogo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return manufacturersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView manufacturerName;
        TextView country;
        ImageView manufacturerLogo;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.manufacturerName = itemView.findViewById(R.id.manufacturerText);
            this.country = itemView.findViewById(R.id.countryText);
            this.manufacturerLogo = itemView.findViewById(R.id.myLogoImage);
            this.mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ManufacturerExample> filteredList = new ArrayList<>();
            if (constraint != null && constraint.length() > 0) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                manufacturerExampleListFull.stream()
                        .filter(el -> el.getTitle().toLowerCase().startsWith(filterPattern))
                        .forEach(filteredList::add);
            } else {
                filteredList.addAll(manufacturerExampleListFull);
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            manufacturersList.clear();
            manufacturersList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}

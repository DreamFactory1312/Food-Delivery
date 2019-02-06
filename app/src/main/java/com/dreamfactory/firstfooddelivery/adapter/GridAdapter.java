package com.dreamfactory.firstfooddelivery.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.firstfooddelivery.model.GridPojo;
import com.dreamfactory.firstfooddelivery.R;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

    private Context context;
    private List<GridPojo> gridPojoList;

    public GridAdapter(Context context, List<GridPojo> gridPojoList) {
        this.context = context;
        this.gridPojoList = gridPojoList;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //Defining landscape and portrait layout
        if (this.context.getApplicationContext().getResources().getConfiguration().orientation == 1) {
            View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_grid, viewGroup, false);
            return new GridViewHolder(convertView);
        } else if (this.context.getApplicationContext().getResources().getConfiguration().orientation == 2) {
            View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_grid_land, viewGroup, false);
            return new GridViewHolder(convertView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final GridViewHolder gridViewHolder, int postion) {
        gridViewHolder.gridImage.setImageResource(gridPojoList.get(postion).getGridImage());
        gridViewHolder.gridText.setText(gridPojoList.get(postion).getGridName());

        gridViewHolder.gridCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + gridPojoList.get(gridViewHolder.getAdapterPosition()).getGridName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gridPojoList.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView gridImage;
        TextView gridText;
        CardView gridCardView;

        public GridViewHolder(View itemView) {
            super(itemView);

            gridImage = (ImageView) itemView.findViewById(R.id.gridImage);
            gridText = (TextView) itemView.findViewById(R.id.gridText);
            gridCardView = itemView.findViewById(R.id.gridCardView);
        }
    }
}

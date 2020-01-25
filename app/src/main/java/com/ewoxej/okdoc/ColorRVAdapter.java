package com.ewoxej.okdoc;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ColorRVAdapter extends RecyclerView.Adapter<ColorRVAdapter.ItemViewHolder>
{
    List<Color> colors;
    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        ImageButton colorBtn;
        ItemViewHolder(View itemView)
        {
            super(itemView);
            colorBtn = itemView.findViewById(R.id.colorBtn);
        }
    }

    ColorRVAdapter(List<Color> colors)
    {
        this.colors = colors;
    }

    @NonNull
    @Override
    public ColorRVAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_colors, parent, false);
        //ColorRVAdapter.ItemViewHolder pvh = new ColorRVAdapter.ItemViewHolder(v);
        return null;// pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorRVAdapter.ItemViewHolder holder, int position)
    {
        holder.colorBtn.setBackgroundColor(colors.get(position).hashCode());
        holder.colorBtn.setOnClickListener(new View.OnClickListener()
        { public void onClick(View v)
        {        }
        });

    }

    @Override
    public int getItemCount()
    {
        return colors.size();
    }


}

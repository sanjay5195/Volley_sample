package com.devsan.seenitassignment.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.devsan.seenitassignment.R;
import com.devsan.seenitassignment.databinding.ItemBookBinding;
import com.devsan.seenitassignment.model.BookVO;
import com.devsan.seenitassignment.util.CustomClickListener;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<BookVO> dataModelList;
    private CustomClickListener clickListener;

    public BooksAdapter(ArrayList<BookVO> dataModelList, CustomClickListener clickListener) {
        this.dataModelList = dataModelList;
        this.clickListener = clickListener;
    }

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        ItemBookBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_book, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookVO dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemBookBinding.setItem(dataModel);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemBookBinding itemBookBinding;

        public ViewHolder(ItemBookBinding itemBookBinding) {
            super(itemBookBinding.getRoot());
            this.itemBookBinding = itemBookBinding;
        }

        public void bind(BookVO obj) {
            itemBookBinding.setItem(obj);
            itemBookBinding.executePendingBindings();
            itemBookBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.cardClicked(obj);
                }
            });
        }
    }
}

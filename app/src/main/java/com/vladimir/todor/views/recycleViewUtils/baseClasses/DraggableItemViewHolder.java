package com.vladimir.todor.views.recycleViewUtils.baseClasses;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DraggableItemViewHolder<I> extends RecyclerView.ViewHolder {
    public View touchedView;
    public DraggableItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void updateView(int position, I item) {}

    public void onRowSelected() {}

    public void onRowClear() {}
}

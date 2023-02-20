package com.vladimir.todor.views.stepRecyclerView;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vladimir.todor.models.Step;
import com.vladimir.todor.views.recycleViewUtils.ItemMoveCallback;

import java.util.List;

public class StepRecyclerView {
    private final RecyclerView recyclerView;
    private StepRecyclerViewAdapter mAdapter;
    private ItemTouchHelper touchHelper;

    public StepRecyclerView(RecyclerView recyclerView, List<Step> dataArray, int itemViewId) {
        this.recyclerView = recyclerView;

        init(itemViewId, dataArray);
    }

    private void init(int itemViewId, List<Step> dataArray) {
        mAdapter = new StepRecyclerViewAdapter(
                viewHolder -> touchHelper.startDrag(viewHolder),
                itemViewId,
                dataArray
        );

        ItemTouchHelper.Callback callback = new ItemMoveCallback<>(mAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(Step item) {
        mAdapter.data.add(0, item);

        mAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteItem(Step item) {
        mAdapter.data.remove(item);

        mAdapter.notifyDataSetChanged();
    }
}

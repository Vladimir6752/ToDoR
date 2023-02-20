package com.vladimir.todor.views.todoRecyclerView;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vladimir.todor.models.ToDo;
import com.vladimir.todor.views.recycleViewUtils.ItemMoveCallback;

import java.util.List;

public class ToDoRecyclerView {
    private final RecyclerView recyclerView;
    private ToDoRecyclerViewAdapter mAdapter;
    private ItemTouchHelper touchHelper;

    public ToDoRecyclerView(RecyclerView recyclerView, List<ToDo> dataArray, int itemViewId) {
        this.recyclerView = recyclerView;

        init(itemViewId, dataArray);
    }

    private void init(int itemViewId, List<ToDo> dataArray) {
        mAdapter = new ToDoRecyclerViewAdapter(
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
    public void addItem(ToDo item) {
        mAdapter.data.add(0, item);

        mAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteItem(ToDo item) {
        mAdapter.data.remove(item);

        mAdapter.notifyDataSetChanged();
    }
}

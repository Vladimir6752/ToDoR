package com.vladimir.todor.views.todoRecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.models.ToDo;
import com.vladimir.todor.views.recycleViewUtils.ItemTouchHelper;
import com.vladimir.todor.views.recycleViewUtils.StartDragListener;

import java.util.Collections;
import java.util.List;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoCardViewHolder> implements ItemTouchHelper<ToDoCardViewHolder> {
    private final StartDragListener mStartDragListener;
    private final int idViewHolder;
    public final List<ToDo> data;

    public ToDoRecyclerViewAdapter(StartDragListener mStartDragListener, int idViewHolder, List<ToDo> data) {
        this.mStartDragListener = mStartDragListener;
        this.idViewHolder = idViewHolder;
        this.data = data;
    }

    @NonNull
    @Override
    public ToDoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(idViewHolder, parent, false);

        return new ToDoCardViewHolder(itemView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ToDoCardViewHolder holder, int position) {
        ToDo item = data.get(position);

        item.setIndexInViewSequence(position);
        ToDoService.todoDao.update(item);

        holder.updateView(position, item);

        if(holder.touchedView == null) return;

        holder.touchedView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mStartDragListener.requestDrag(holder);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIndexInViewSequence(i);
        }

        ToDoService.todoDao.updateAll(data);

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        ToDo toDo = data.get(position);

        ToDoService.todoDao.delete(toDo);

        data.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public boolean canSwiped() {
        return false;
    }

    @Override
    public void onItemSelected(ToDoCardViewHolder myViewHolder) {

    }

    @Override
    public void onItemClear(ToDoCardViewHolder myViewHolder) {

    }
}

package com.vladimir.todor.views.stepRecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.models.Step;
import com.vladimir.todor.views.recycleViewUtils.ItemTouchHelper;
import com.vladimir.todor.views.recycleViewUtils.StartDragListener;

import java.util.Collections;
import java.util.List;

public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepCardviewHolder> implements ItemTouchHelper<StepCardviewHolder> {
    private final StartDragListener mStartDragListener;
    private final int idViewHolder;
    public final List<Step> data;

    public StepRecyclerViewAdapter(StartDragListener mStartDragListener, int idViewHolder, List<Step> data) {
        this.mStartDragListener = mStartDragListener;
        this.idViewHolder = idViewHolder;
        this.data = data;
    }

    @NonNull
    @Override
    public StepCardviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(idViewHolder, parent, false);

        return new StepCardviewHolder(itemView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull StepCardviewHolder holder, int position) {
        Step item = data.get(position);

        item.setIndexInViewSequence(position);
        ToDoService.stepDao.update(item);

        holder.updateView(position, data.get(position));

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
                data.get(i).setIndexInViewSequence(i + 1);

                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                data.get(i).setIndexInViewSequence(i - 1);

                Collections.swap(data, i, i - 1);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIndexInViewSequence(i);
        }

        ToDoService.stepDao.updateAll(data);

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        Step step = data.get(position);

        ToDoService.stepDao.delete(step);

        data.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public boolean canSwiped() {
        return true;
    }

    @Override
    public void onItemSelected(StepCardviewHolder myViewHolder) {}

    @Override
    public void onItemClear(StepCardviewHolder myViewHolder) {}
}

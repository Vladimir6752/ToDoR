package com.vladimir.todor.views.stepRecyclerView;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;

import com.vladimir.todor.databinding.StepCardviewBinding;
import com.vladimir.todor.listeners.OnClickStepCheckBoxListener;
import com.vladimir.todor.models.Step;
import com.vladimir.todor.views.recycleViewUtils.baseClasses.DraggableItemViewHolder;

public class StepCardviewHolder extends DraggableItemViewHolder<Step> {

    private final StepCardviewBinding binding;

    public StepCardviewHolder(@NonNull View itemView) {
        super(itemView);

        binding = StepCardviewBinding.bind(itemView);

        touchedView = binding.stepCheckBox;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void updateView(int position, Step item) {
        binding.stepTitle.setText(item.getContent());

        binding.stepCheckBox.setChecked(item.isDone());

        /*binding.getRoot().setOnLongClickListener(
                new OnLongClickStepListener(
                        item
                )
        );*/

        binding.stepCheckBox.setOnCheckedChangeListener(
                new OnClickStepCheckBoxListener(
                        item
                )
        );
    }

    @Override
    public void onRowSelected() {}

    @Override
    public void onRowClear() {}
}

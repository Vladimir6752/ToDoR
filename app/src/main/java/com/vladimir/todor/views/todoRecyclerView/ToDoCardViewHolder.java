package com.vladimir.todor.views.todoRecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.vladimir.todor.R;
import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.databinding.TodoCardviewBinding;
import com.vladimir.todor.listeners.OnClickContextMenuListener;
import com.vladimir.todor.listeners.OnClickDeleteToDoButtonListener;
import com.vladimir.todor.listeners.OnClickToDoCardViewListener;
import com.vladimir.todor.listeners.OnTouchAddStepInToDoListener;
import com.vladimir.todor.models.Step;
import com.vladimir.todor.models.ToDo;
import com.vladimir.todor.views.patterns.PatternStepView;
import com.vladimir.todor.views.recycleViewUtils.baseClasses.DraggableItemViewHolder;
import com.vladimir.todor.views.stepRecyclerView.StepRecyclerView;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ToDoCardViewHolder extends DraggableItemViewHolder<ToDo> {
    private PatternStepView patternStepView;
    private TodoCardviewBinding binding;
    private StepRecyclerView stepRecyclerView;
    private CardOpenType cardOpenType = CardOpenType.FULL_CLOSE;

    private ToDo toDo;
    public ToDoCardViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = TodoCardviewBinding.bind(itemView);
        touchedView = binding.draggableButton;
        patternStepView = binding.stepPatternView;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        binding.getRoot().setOnTouchListener(
                new OnClickToDoCardViewListener(
                        this
                )
        );

        binding.addStepInTodoButton.setOnTouchListener(
                new OnTouchAddStepInToDoListener(
                        patternStepView,
                        toDo.getId(),
                        this,
                        stepRecyclerView
                )
        );

        binding.openContextTodoButton.setOnTouchListener(
                new OnClickContextMenuListener(
                        this
                )
        );

        binding.deleteTodoButton.setOnTouchListener(
                new OnClickDeleteToDoButtonListener(
                        toDo
                )
        );
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void updateView(int position, ToDo item) {
        this.toDo = item;
        binding.todoTitle.setText(item.getTitle());
        binding.createdDate.setText(item.getCreatedDate());

        setOpenTypeFromToDo();

        stepRecyclerView = new StepRecyclerView(
                binding.stepsRecyclerView,
                ToDoService.stepDao.getStepsByTodoId(item.getId())
                        .stream()
                        .sorted(Comparator.comparing(Step::getIndexInViewSequence))
                        .collect(Collectors.toList()),
                R.layout.step_cardview
        );

        setListeners();
    }

    private void setOpenTypeFromToDo() {
        CardOpenType openType = CardOpenType.valueOf(toDo.getCardOpenType());

        switch (openType) {
            case FULL_CLOSE:
                closeFull();
                break;
            case PARTIALLY_OPEN:
                openPartially();
            case FULL_OPEN:
                openFull();
                break;
        }
    }

    @Override
    public void onRowSelected() {
        binding.getRoot().setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onRowClear() {
        binding.getRoot().setBackgroundColor(Color.WHITE);
    }

    private void setOpenTypeInToDo(CardOpenType partiallyOpen) {
        cardOpenType = partiallyOpen;

        toDo.setCardOpenType(String.valueOf(cardOpenType));

        ToDoService.updateToDo(toDo);
    }

    public void openPartially() {
        binding.openContextTodoButton.setVisibility(View.VISIBLE);
        binding.createdDate.setVisibility(View.VISIBLE);
        binding.stepsRecyclerView.setVisibility(View.VISIBLE);

        binding.draggableButton.setVisibility(View.GONE);

        setOpenTypeInToDo(CardOpenType.PARTIALLY_OPEN);
    }

    public void closeFull() {
        binding.openContextTodoButton.setVisibility(View.GONE);
        binding.createdDate.setVisibility(View.GONE);
        binding.stepsRecyclerView.setVisibility(View.GONE);
        binding.stepPatternView.setVisibility(View.GONE);

        binding.deleteTodoButton.setVisibility(View.GONE);
        binding.addStepInTodoButton.setVisibility(View.GONE);

        binding.draggableButton.setVisibility(View.VISIBLE);

        binding.openContextTodoButton.setRotation(0);

        MainActivity.inputMethodManager.hideSoftInputFromWindow(itemView.getWindowToken(), 0);

        setOpenTypeInToDo(CardOpenType.FULL_CLOSE);
    }

    public void openFull() {
        binding.openContextTodoButton.setVisibility(View.VISIBLE);
        binding.createdDate.setVisibility(View.VISIBLE);
        binding.stepsRecyclerView.setVisibility(View.VISIBLE);

        binding.deleteTodoButton.setVisibility(View.VISIBLE);
        binding.addStepInTodoButton.setVisibility(View.VISIBLE);

        binding.draggableButton.setVisibility(View.GONE);

        binding.openContextTodoButton.setRotation(180);

        setOpenTypeInToDo(CardOpenType.FULL_OPEN);
    }

    public CardOpenType getCardOpenType() {
        return cardOpenType;
    }

    public enum CardOpenType {
        FULL_CLOSE,
        PARTIALLY_OPEN,
        FULL_OPEN;
    }
}

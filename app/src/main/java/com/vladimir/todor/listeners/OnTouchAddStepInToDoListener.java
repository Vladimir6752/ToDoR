package com.vladimir.todor.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.views.patterns.PatternStepView;
import com.vladimir.todor.views.stepRecyclerView.StepRecyclerView;
import com.vladimir.todor.views.todoRecyclerView.ToDoCardViewHolder;

public class OnTouchAddStepInToDoListener implements View.OnTouchListener {
    private final PatternStepView patternStepView;
    private final int todoId;
    private final ToDoCardViewHolder toDoCardView;
    private final StepRecyclerView stepsRecyclerView;

    public OnTouchAddStepInToDoListener(PatternStepView patternStepView, int todoId, ToDoCardViewHolder toDoCardView, StepRecyclerView stepsRecyclerView) {
        this.patternStepView = patternStepView;
        this.todoId = todoId;
        this.toDoCardView = toDoCardView;
        this.stepsRecyclerView = stepsRecyclerView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;

        int visibility = patternStepView.getVisibility();

        if(visibility == View.GONE) {
            MainActivity.inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            patternStepView.setVisibility(View.VISIBLE);
        }
        else {
            MainActivity.inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            patternStepView.setVisibility(View.GONE);
            ToDoService.createNewStep(patternStepView.getText(), todoId, stepsRecyclerView);

            toDoCardView.openPartially();
        }

        patternStepView.resetText();
        patternStepView.updateFocus();

        return true;
    }
}

package com.vladimir.todor.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.vladimir.todor.views.todoRecyclerView.ToDoCardViewHolder;

public class OnClickToDoCardViewListener implements View.OnTouchListener {
    private final ToDoCardViewHolder toDoCardView;

    public OnClickToDoCardViewListener(ToDoCardViewHolder toDoCardView) {
        this.toDoCardView = toDoCardView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) return true;

        switch (toDoCardView.getCardOpenType()) {
            case FULL_CLOSE:
                toDoCardView.openPartially();
                break;
            case PARTIALLY_OPEN:
            case FULL_OPEN:
                toDoCardView.closeFull();
                break;
        }

        return true;
    }
}

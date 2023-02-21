package com.vladimir.todor.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.vladimir.todor.views.todoRecyclerView.ToDoCardViewHolder;

public class OnClickContextMenuListener implements View.OnTouchListener {
    private final ToDoCardViewHolder toDoCardView;

    public OnClickContextMenuListener(ToDoCardViewHolder toDoCardView) {
        this.toDoCardView = toDoCardView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;

        switch (toDoCardView.getCardOpenType()) {
            case PARTIALLY_OPEN:
                toDoCardView.openFull();
                break;
            case FULL_OPEN:
                toDoCardView.closeFull();
                toDoCardView.openPartially();
                break;
        }
        return true;
    }
}

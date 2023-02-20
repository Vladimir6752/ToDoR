package com.vladimir.todor.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.models.ToDo;

public class OnClickDeleteToDoButtonListener implements View.OnTouchListener {

    private final ToDo toDo;

    public OnClickDeleteToDoButtonListener(ToDo toDo) {
        this.toDo = toDo;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;

        ToDoService.todoDao.delete(toDo);

        MainActivity.toDoRecyclerView.deleteItem(toDo);

        return true;
    }
}

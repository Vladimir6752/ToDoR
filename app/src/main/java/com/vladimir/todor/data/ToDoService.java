package com.vladimir.todor.data;

import android.content.Context;

import androidx.room.Room;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.models.Step;
import com.vladimir.todor.models.ToDo;
import com.vladimir.todor.views.stepRecyclerView.StepRecyclerView;

import java.util.Collections;
import java.util.List;

public class ToDoService {
    public static ToDoDao todoDao;
    public static StepDao stepDao;

    public static void init(Context applicationContext) {
        ToDoDatabase database = Room
                .databaseBuilder(applicationContext, ToDoDatabase.class, "todoData")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        todoDao = database.getToDoDao();
        stepDao = database.getStepDao();
    }

    public static void createNewToDo(String title) {
        ToDo todo = new ToDo(title);
        todoDao.add(todo);

        List<ToDo> all = todoDao.getAll();

        Collections.reverse(all);

        MainActivity.toDoRecyclerView.addItem(all.get(all.size() - 1));
    }

    public static void createNewStep(String stepTitle, int todoId, StepRecyclerView stepsRecyclerView) {
        if(stepTitle.equals("")) {
            return;
        }

        Step step = new Step();
        step.setContent(stepTitle);
        step.setParentTodoId(todoId);

        ToDoService.stepDao.add(step);

        stepsRecyclerView.addItem(step);
    }
}

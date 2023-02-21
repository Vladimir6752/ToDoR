package com.vladimir.todor.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vladimir.todor.models.Step;
import com.vladimir.todor.models.ToDo;

@Database(
        entities = {ToDo.class, Step.class},
        version = 15,
        exportSchema = false
)
public abstract class ToDoDatabase extends RoomDatabase {
    public abstract ToDoDao getToDoDao();
    public abstract StepDao getStepDao();
}

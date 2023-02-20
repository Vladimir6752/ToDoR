package com.vladimir.todor.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vladimir.todor.models.Step;

import java.util.List;

@Dao
public interface StepDao {
    @Query("SELECT * FROM steps ORDER BY id")
    List<Step> getAll();

    @Insert(onConflict = REPLACE)
    void add(Step step);

    @Delete
    void delete(Step step);

    @Query("DELETE FROM steps")
    void deleteAll();

    @Query("SELECT * FROM steps WHERE id = :id")
    Step getById(int id);

    @Update
    void update(Step step);

    @Update
    void updateAll(List<Step> step);

    @Query("SELECT * FROM steps WHERE parentTodoId = :todoId")
    List<Step> getStepsByTodoId(int todoId);
}

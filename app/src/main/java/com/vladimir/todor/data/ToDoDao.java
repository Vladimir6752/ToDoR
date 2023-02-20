package com.vladimir.todor.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vladimir.todor.models.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM todos ORDER BY id DESC")
    List<ToDo> getAll();

    @Insert(onConflict = REPLACE)
    void add(ToDo todo);

    @Delete
    void delete(ToDo todo);

    @Query("DELETE FROM todos")
    void deleteAll();

    @Query("SELECT * FROM todos WHERE id = :id")
    ToDo getById(int id);

    @Update
    void update(ToDo todo);

    @Update
    int updateAll(List<ToDo> step);
}

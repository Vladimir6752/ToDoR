package com.vladimir.todor.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "steps",
        foreignKeys = @ForeignKey(
                entity = ToDo.class,
                parentColumns = "id",
                childColumns = "parentTodoId",
                onDelete = 5
        ),
        indices = @Index("parentTodoId")
)
public class Step implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private boolean isDone;
    private int parentTodoId;
    private int indexInViewSequence;

    public Step() {}

    public Step(String content) {
        this.content = content;
    }

    public int getIndexInViewSequence() {
        return indexInViewSequence;
    }

    public void setIndexInViewSequence(int indexInViewSequence) {
        this.indexInViewSequence = indexInViewSequence;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentTodoId() {
        return parentTodoId;
    }

    public void setParentTodoId(int parentTodoId) {
        this.parentTodoId = parentTodoId;
    }
}

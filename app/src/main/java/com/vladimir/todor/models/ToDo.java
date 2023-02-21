package com.vladimir.todor.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vladimir.todor.views.todoRecyclerView.ToDoCardViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "todos")
public class ToDo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private boolean isPinned;
    private String createdDate = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm").format(new Date());
    private int indexInViewSequence;
    private String cardOpenType = String.valueOf(ToDoCardViewHolder.CardOpenType.FULL_CLOSE);

    public ToDo() {}

    public ToDo(String title) {
        this.title = title;
    }

    public int getIndexInViewSequence() {
        return indexInViewSequence;
    }

    public void setIndexInViewSequence(int indexInViewSequence) {
        this.indexInViewSequence = indexInViewSequence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                ", title='" + title + '\'' +
                ", indexInViewSequence=" + indexInViewSequence +
                '}';
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCardOpenType() {
        return cardOpenType;
    }

    public void setCardOpenType(String cardOpenType) {
        this.cardOpenType = cardOpenType;
    }
}
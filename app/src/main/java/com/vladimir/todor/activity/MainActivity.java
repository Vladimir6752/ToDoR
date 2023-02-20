package com.vladimir.todor.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vladimir.todor.MenuButtonType;
import com.vladimir.todor.R;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.databinding.ActivityMainBinding;
import com.vladimir.todor.listeners.OnClickAddToDoButton;
import com.vladimir.todor.models.ToDo;
import com.vladimir.todor.views.todoRecyclerView.ToDoRecyclerView;

import java.util.Comparator;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    public static ActivityMainBinding binding;
    public static ToDoRecyclerView toDoRecyclerView;
    public static InputMethodManager inputMethodManager;
    public static OnClickAddToDoButton addToDoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ToDoService.init(getApplicationContext());

        addToDoButton = new OnClickAddToDoButton(binding.todoPatternView);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        toDoRecyclerView = new ToDoRecyclerView(
                binding.todoRecyclerView,
                ToDoService.todoDao.getAll()
                        .stream()
                        .sorted(Comparator.comparingInt(ToDo::getIndexInViewSequence))
                        .collect(Collectors.toList()),
                R.layout.todo_cardview
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MenuButtonType.of(
                item.getItemId()
        ).onClick();

        return super.onOptionsItemSelected(item);
    }
}

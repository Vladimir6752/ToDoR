package com.vladimir.todor.views.patterns;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vladimir.todor.R;

public class PatternToDoView extends ConstraintLayout {
    public static com.vladimir.todor.databinding.PatternTodoViewBinding binding;

    public PatternToDoView(@NonNull Context context) {
        this(context, null);
    }

    public PatternToDoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public PatternToDoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PatternToDoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        inflate(getContext(), R.layout.pattern_todo_view, this);
        binding = com.vladimir.todor.databinding.PatternTodoViewBinding.bind(this);
    }

    public String getText() {
        return String.valueOf(binding.editTextPattern.getText());
    }

    public void resetText() {
        binding.editTextPattern.setText("");
    }

    public void updateFocus() {
        binding.editTextPattern.requestFocus();
    }
}

package com.vladimir.todor.views.patterns;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vladimir.todor.R;

public class PatternStepView extends ConstraintLayout {
    public com.vladimir.todor.databinding.PatternStepViewBinding binding;

    public PatternStepView(@NonNull Context context) {
        this(context, null);
    }

    public PatternStepView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public PatternStepView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PatternStepView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        inflate(getContext(), R.layout.pattern_step_view, this);
        binding = com.vladimir.todor.databinding.PatternStepViewBinding.bind(this);
    }

    public String getText() {
        return binding.editTextPattern.getText().toString();
    }

    public void resetText() {
        binding.editTextPattern.setText("");
    }

    public void updateFocus() {
        binding.editTextPattern.requestFocus();
    }
}

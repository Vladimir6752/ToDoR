package com.vladimir.todor.listeners;

import android.util.Log;
import android.widget.CompoundButton;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.models.Step;

import java.util.Collections;
import java.util.List;

public class OnClickStepCheckBoxListener implements CompoundButton.OnCheckedChangeListener {
    private final Step step;

    public OnClickStepCheckBoxListener(Step step) {
        this.step = step;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("TAG", "onCheckedChanged: " + step.getId() + " " + step.isDone());

        step.setDone(isChecked);

        ToDoService.stepDao.update(step);


        List<Step> all = ToDoService.stepDao.getAll();
        Collections.reverse(all);
        Log.d("TAG", "updated Step: " + all.get(all.size() - 1));
    }
}

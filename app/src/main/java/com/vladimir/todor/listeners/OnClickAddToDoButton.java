package com.vladimir.todor.listeners;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vladimir.todor.activity.MainActivity;
import com.vladimir.todor.data.ToDoService;
import com.vladimir.todor.views.patterns.PatternToDoView;

public class OnClickAddToDoButton {
    private final PatternToDoView patternToDoView;

    public OnClickAddToDoButton(PatternToDoView patternToDoView) {
        this.patternToDoView = patternToDoView;
    }

    public void onClick() {
        int visibility = patternToDoView.getVisibility();

        if(visibility == View.GONE) {
            MainActivity.inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            patternToDoView.setVisibility(View.VISIBLE);

            int visibleHeightWithKeyboard = (int) (MainActivity.binding.getRoot().getHeight() * 0.75);
            MainActivity.binding.todoRecyclerView
                    .getLayoutParams()
                    .height = visibleHeightWithKeyboard;
        }
        else {
            MainActivity.inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            patternToDoView.setVisibility(View.GONE);
            resetPatternToDo();

            MainActivity.binding.todoRecyclerView
                    .getLayoutParams()
                    .height = MATCH_PARENT;
        }

        patternToDoView.updateFocus();
    }

    private void resetPatternToDo() {
        String newToDoTitle = patternToDoView.getText();

        if(newToDoTitle.equals("")) return;

        ToDoService.createNewToDo(newToDoTitle);

        patternToDoView.resetText();
    }
}

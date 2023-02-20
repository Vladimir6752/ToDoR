package com.vladimir.todor;

import com.vladimir.todor.activity.MainActivity;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum MenuButtonType {
    ADD_TODO_BUTTON(R.id.add_todo_button) {
        @Override
        public void onClick() {
            MainActivity.addToDoButton.onClick();
        }
    };

    private final int menuItemId;

    MenuButtonType(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public abstract void onClick();

    public static MenuButtonType of(int menuItemId) {
        return Arrays.stream(MenuButtonType.values())
                .filter(menuButtonType -> menuButtonType.menuItemId == menuItemId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
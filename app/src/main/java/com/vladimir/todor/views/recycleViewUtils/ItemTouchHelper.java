package com.vladimir.todor.views.recycleViewUtils;

public interface ItemTouchHelper<V> {
    void onItemMoved(int fromPosition, int toPosition);
    void onItemSelected(V myViewHolder);
    void onItemClear(V myViewHolder);
    void onItemDismiss(int position);
    boolean canSwiped();
}
package com.vladimir.todor.views.recycleViewUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemMoveCallback<V> extends androidx.recyclerview.widget.ItemTouchHelper.Callback {
    private final ItemTouchHelper<V> mAdapter;

    public ItemMoveCallback(ItemTouchHelper<V> adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mAdapter.canSwiped();
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mAdapter.onItemDismiss(viewHolder.getBindingAdapterPosition());
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = androidx.recyclerview.widget.ItemTouchHelper.UP | androidx.recyclerview.widget.ItemTouchHelper.DOWN;
        int swipeFlags = androidx.recyclerview.widget.ItemTouchHelper.START | androidx.recyclerview.widget.ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMoved(
                viewHolder.getBindingAdapterPosition(),
                target.getBindingAdapterPosition()
        );
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE) {
            try {
                mAdapter.onItemSelected((V) viewHolder);
            } catch (Exception ignored) {}
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        try {
            mAdapter.onItemSelected((V) viewHolder);
        } catch (Exception ignored) {}
    }
}

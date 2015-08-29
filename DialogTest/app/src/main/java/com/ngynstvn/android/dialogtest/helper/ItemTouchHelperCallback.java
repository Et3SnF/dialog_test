package com.ngynstvn.android.dialogtest.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

// This class allows the integration of a custom feedback action when swiping on a RecyclerView item
// Must extend ItemTouchHelper.Callback to do this

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private static final String TAG = "Test (" + ItemTouchHelperCallback.class.getSimpleName() + "): ";

    private final ItemTouchHelperAdapter itemTouchHelperAdapter;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter, Context context) {
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.v(TAG, "onSelectedChanged() called");

        if(actionState != ItemTouchHelper.ANIMATION_TYPE_SWIPE_CANCEL) {
            if(viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder itemTouchHelperViewHolder =
                        (ItemTouchHelperViewHolder) viewHolder;
                itemTouchHelperViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        Log.v(TAG, "clearView() called");

        super.clearView(recyclerView, viewHolder);

        if(viewHolder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder itemTouchHelperViewHolder =
                    (ItemTouchHelperViewHolder) viewHolder;
            itemTouchHelperViewHolder.onItemClear();
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {

        Log.v(TAG, "onMove() called");

        itemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        Log.v(TAG, "getMovementFlags() called");

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = 0;

        Log.v(TAG, "Current values: (" + dragFlags + "," + swipeFlags + ")");

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        Log.v(TAG, "onSwiped() called");

        itemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    //  With this callback class ready, attach it.
}

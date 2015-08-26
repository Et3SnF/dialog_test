package com.ngynstvn.android.dialogtest.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.ngynstvn.android.dialogtest.fragment.CustomDialog;

// This class is integrate a custom feedback action whenever we swipe to dismiss or drag an item.
// Must extend ItemTouchHelper.Callback to do this

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final CustomDialog.ItemTouchHelperAdapter itemTouchHelperAdapter;

    public ItemTouchHelperCallback(CustomDialog.ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder instanceof CustomDialog.ItemTouchHelperViewHolder) {
                CustomDialog.ItemTouchHelperViewHolder itemTouchHelperViewHolder =
                        (CustomDialog.ItemTouchHelperViewHolder) viewHolder;
                itemTouchHelperViewHolder.onItemSelected();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if(viewHolder instanceof CustomDialog.ItemTouchHelperViewHolder) {
            CustomDialog.ItemTouchHelperViewHolder itemTouchHelperViewHolder =
                    (CustomDialog.ItemTouchHelperViewHolder) viewHolder;
            itemTouchHelperViewHolder.onItemClear();
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        itemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.LEFT;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    //  With this callback class ready, attach it.
}
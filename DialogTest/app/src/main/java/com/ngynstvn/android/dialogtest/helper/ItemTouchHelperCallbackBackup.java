package com.ngynstvn.android.dialogtest.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ngynstvn.android.dialogtest.R;

// This class allows the integration of a custom feedback action when swiping on a RecyclerView item
// Must extend ItemTouchHelper.Callback to do this

public class ItemTouchHelperCallbackBackup extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;
    private final Context context;

    public ItemTouchHelperCallbackBackup(ItemTouchHelperAdapter adapter, Context context) {
        mAdapter = adapter;
        this.context = context;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = 0;
        final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition(), direction);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Get RecyclerView item from the ViewHolder
            View itemView = viewHolder.itemView;
            Paint paint = new Paint();

            Bitmap like_bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_thumb_up_outline);
            Bitmap dislike_bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_thumb_down_outline);
            float width = like_bitmap.getWidth();

            if (dX > 0) { // swiping right
                paint.setColor(Color.RED);
                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX + dX, (float) itemView.getBottom(), paint);

                if (dX > 96f + width) { // when sliding more than the icon's width, show the icon itself
                    float height = (itemView.getHeight() / 2) - (dislike_bitmap.getHeight() / 2);
                    c.drawBitmap(dislike_bitmap, 96f, (float) itemView.getTop() + height, null);
                }

            } else { // swiping left
                paint.setColor(Color.GREEN);
                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom(), paint);

                if (dX < -1 * (96 + width)) {//when slided are is more than icon width show icon
                    float height = (itemView.getHeight() / 2) - (dislike_bitmap.getHeight() / 2);
                    c.drawBitmap(like_bitmap, ((float) itemView.getRight() - like_bitmap.getWidth()) - 96f, (float) itemView.getTop() + height, null);
                }

            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
    }

    //  With this callback class ready, attach it.
}

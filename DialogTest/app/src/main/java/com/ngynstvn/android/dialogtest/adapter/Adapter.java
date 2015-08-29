package com.ngynstvn.android.dialogtest.adapter;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.ngynstvn.android.dialogtest.R;
import com.ngynstvn.android.dialogtest.UIUtils;
import com.ngynstvn.android.dialogtest.application.DialogApplication;
import com.ngynstvn.android.dialogtest.data.FakeData;
import com.ngynstvn.android.dialogtest.helper.ItemTouchHelperAdapter;
import com.ngynstvn.android.dialogtest.helper.ItemTouchHelperViewHolder;
import com.ngynstvn.android.dialogtest.model.Category;

import java.util.ArrayList;
import java.util.Collections;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> implements ItemTouchHelperAdapter {

    // ----- Class Variables ----- //

    private static final String TAG = "Test (" + Adapter.class.getSimpleName() + "): ";

    // ----- Member Variables ----- //

    private FakeData fakeData = DialogApplication.getSharedFakeData();

    // ----- Constructor ----- //

    public Adapter() {
        Log.v(TAG, "CategoryAdapter object instantiated");
    }

    // ----- CategoryAdapter Methods ----- //

    @Override
    public int getItemCount() {
        // This is really important to place otherwise nothing will display! RecyclerView goes
        // through this method to count and then add the items!

        return fakeData.getCategoryArrayList().size();
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        holder.updateViewHolder(fakeData.getCategoryArrayList().get(position));
    }

    // Generate fake categories for now

    public void generateCategories(ArrayList<Category> arrayList) {

        for(int i = 0; i < 10; i++) {
            arrayList.add(new Category("Category " + String.valueOf(i+1),
                    UIUtils.generateRandomColor(android.R.color.white)));
        }

    }

    /**
     *
     * CustomDialog.ItemTouchHelperAdapter methods
     *
     */

    // For swiping items to dismiss

    @Override
    public void onItemDismiss(int position) {
        fakeData.getCategoryArrayList().remove(position);
        notifyItemRemoved(position); // this is important for adapter to be aware
    }

    // For dragging items

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if(fromPosition < toPosition) {
            for(int i = fromPosition; i < toPosition; i++) {
                Collections.swap(fakeData.getCategoryArrayList(), i, i+1); // allows item to move down
            }
        }
        else {
            for(int i = fromPosition; i > toPosition; i--) {
                Collections.swap(fakeData.getCategoryArrayList(), i, i -1); // allows item to move up
            }
        }

        notifyItemMoved(fromPosition, toPosition); // important for adapter to be aware of this
        return true;
    }

    // CategoryAdapterViewHolder inner class

    class AdapterViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        // ----- Member variables ----- //

        private TextView categoryColor;
        private TextView categoryName;
        private CheckBox filterCategory;

        private SwipeLayout swipeLayout;
        private Button deleteButton;
        private Button editButton;

        // Constructor

        public AdapterViewHolder(final View itemView) {

            // Constructor is for inflation and listeners, that's it! No updating info!!..idiot

            super(itemView);

            categoryColor = (TextView) itemView.findViewById(R.id.tv_category_color);
            categoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
            filterCategory = (CheckBox) itemView.findViewById(R.id.cb_filter_category);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.sl_category_item);
            deleteButton = (Button) itemView.findViewById(R.id.btn_delete_category);
            editButton = (Button) itemView.findViewById(R.id.btn_edit_category);

            // Need this check to check API version otherwise a RunTimeException occurs

            if(Build.VERSION.SDK_INT >= 21) {

                categoryColor.setText("");

                categoryColor.setOutlineProvider(new ViewOutlineProvider() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setOval(0,0, view.getWidth(), view.getHeight());
                    }
                });

                categoryColor.setClipToOutline(true);
            }

            filterCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Delegate this somehow to a controller that accesses the database
                }
            });

            // SwipeLayout Material Below

            swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout swipeLayout) {

                }

                @Override
                public void onOpen(SwipeLayout swipeLayout) {

                }

                @Override
                public void onStartClose(SwipeLayout swipeLayout) {

                }

                @Override
                public void onClose(SwipeLayout swipeLayout) {

                }

                @Override
                public void onUpdate(SwipeLayout swipeLayout, int i, int i1) {

                }

                @Override
                public void onHandRelease(SwipeLayout swipeLayout, float v, float v1) {

                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Delegate this to a controller at some point
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Delegate this to another controller at some point
                }
            });

        }

        // ----- Separate Methods ----- //

        void updateViewHolder(Category category) {
            categoryName.setText(category.getCategoryName());
            categoryColor.setBackgroundColor(category.getCategoryColor());
        }

        /**
         *
         * CustomDialog.ItemTouchHelperViewHolder implemented methods
         *
         */

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(0x1E000000);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0x00000000);
        }

    }

}

package com.ngynstvn.android.dialogtest.data;

import android.os.Handler;

import com.ngynstvn.android.dialogtest.UIUtils;
import com.ngynstvn.android.dialogtest.model.Category;

import java.util.ArrayList;

public class FakeData {

    private static final String TAG = "Test (" + FakeData.class.getSimpleName() + "): ";

    private ArrayList<Category> categoryArrayList;

    public FakeData() {
        categoryArrayList = new ArrayList<>();
        generateCategories();
    }

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    // Generate fake categories for now

    public void generateCategories() {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    categoryArrayList.add(new Category("Category " + String.valueOf(i+1),
                            UIUtils.generateRandomColor(android.R.color.white)));
                }
            }
        });
    }
}

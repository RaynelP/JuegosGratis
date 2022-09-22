package com.example.juegosgratis.view.adapters.genericAdapter;

import android.view.View;

public interface OnclickItemListener<T> {
    void onClickItem(View v, T item);
}

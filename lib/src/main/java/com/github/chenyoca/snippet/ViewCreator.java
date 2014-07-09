package com.github.chenyoca.snippet;

import android.view.LayoutInflater;
import android.view.View;

/**
 *
 * @param <T>
 */
public interface ViewCreator<T>{
    View createView(LayoutInflater inflater, T data);
    void bind(View view, int position, T data);
}
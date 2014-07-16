package com.github.chenyoca.snippet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class FastCellAdapter<T> extends BaseAdapter{

    protected List<T> dataSetReference;
    protected LayoutInflater layoutInflater;
    protected ViewCreator2<T> viewCreator;

    public FastCellAdapter(LayoutInflater inflater, ViewCreator2<T> delegate) {
        this.layoutInflater = inflater;
        this.viewCreator = delegate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T data = dataSetReference.get(position);
        ViewCreator2.ViewHolder holder;
        if (convertView == null) {
            holder = viewCreator.createHolder(layoutInflater, data);
            convertView = holder.parent;
            convertView.setTag(holder);
        } else {
            holder = (ViewCreator2.ViewHolder) convertView.getTag();
        }
        viewCreator.bind(holder, position, data);
        return convertView;
    }

    /**
     * 更新Adapter的数据集。
     * @param data 数据集
     */
    public void update(List<T> data){
        dataSetReference = data;
    }

    /**
     * 清空Adapter的数据集
     */
    public void clear(){
        if(dataSetReference != null){
            dataSetReference.clear();
        }
    }

    @Override
    public int getCount() {
        return null == dataSetReference ? 0 : dataSetReference.size();
    }

    @Override
    public T getItem(int position) {
        return null == dataSetReference ? null : dataSetReference.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

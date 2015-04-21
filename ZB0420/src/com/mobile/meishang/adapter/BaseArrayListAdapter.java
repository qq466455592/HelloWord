package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * 
 * @Title:
 * @Description:
 * @Author:11076982
 * @Since:2013-4-24
 * @Version:
 * @param:<T>
 */
public abstract class BaseArrayListAdapter<T> extends ArrayAdapter<T>
{
    protected final List<T> list;

    /**
     * 
     * @param context
     * @param id
     */
    public BaseArrayListAdapter(Context context, int id)
    {
        super(context, id);
        this.list = new ArrayList<T>();
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-8
     */
    public boolean addAll(List<? extends T> list)
    {
        return this.list.addAll(list);
    }

    public void clear()
    {
        if (list != null)
        {
            this.list.clear();
        }
    }

    public int getCount()
    {
        return list == null ? 0 : list.size();
    }

    public T getItem(int i)
    {
        return this.list.get(i);
    }

    public long getItemId(int id)
    {
        return id;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public boolean isEmpty()
    {
        return list == null ? true : list.isEmpty();
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-8
     */
    public T remove(int i)
    {
        return list.remove(i);
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-8
     */
    public void addItem(T t)
    {
        this.list.add(t);
    }
}

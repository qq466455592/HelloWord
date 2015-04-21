package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.BaseAdapter;

public abstract class BaseListFourAdapter<T> extends BaseAdapter {
	protected final List<T> list;
	private final static int FOURTH = 4;

	public BaseListFourAdapter() {
		super();
		this.list = new ArrayList<T>();
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public boolean addAll(List<? extends T> list) {
		return this.list.addAll(list);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public void clear() {
		if (list != null) {
			this.list.clear();
		}
	}

	public int getCount() {
		if (null != list && list.size() > 0) {
			return (int) Math.ceil((float) list.size() / FOURTH);
		} else {
			return 0;
		}
	}

	public T getItem(int i) {
		return this.list.get(i);
	}

	public long getItemId(int id) {
		return id;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isEmpty() {
		return list == null ? true : list.isEmpty();
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public T remove(int i) {
		return list.remove(i);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-8
	 */
	public void addItem(T t) {
		this.list.add(t);
	}
}

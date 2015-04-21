package com.mobile.meishang.database;

import android.database.Cursor;
import android.os.Bundle;

import com.mobile.meishang.model.ObjectReference;

public interface IDataBaseObserver {
	public boolean action(String what, Bundle data,
			ObjectReference<Cursor> cursorRef);
}

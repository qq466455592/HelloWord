package com.mobile.meishang.core.local;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.database.MyDBHelper;

public class LocalDataManager {
	private static LocalDataManager mThis;
	private MyDBHelper DBhelp;
	private SQLiteDatabase mDb;

	private LocalDataManager(Context context) {
		if (DBhelp == null) {
			DBhelp = new MyDBHelper(context);
		}
		if (mDb == null) {
			mDb = DBhelp.getWritableDatabase();
		}
	}

	public static LocalDataManager getInstance() {
		if (null == mThis) {
			synchronized (LocalDataManager.class) {
				if (null == mThis) {
					mThis = new LocalDataManager(MApplication.mApplication);
				}
			}
		}
		return mThis;
	}

	public long doInsert(String tableName, ContentValues values) {
		long rowId = 0;
		mDb.beginTransaction();
		try {
			rowId = mDb.insert(tableName, null, values);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return rowId;
	}

	public long doInsert(String tableName, List<ContentValues> valuesList) {
		long rowId = 0;
		int size = valuesList.size();
		mDb.beginTransaction();
		try {
			for (int i = 0; i < size; i++) {
				rowId = mDb.insert(tableName, null, valuesList.get(i));
			}
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return rowId;
	}

	public int doDelete(String tableName, String[] whereArgs,
			String[] whereArgsValues) {
		int count = 0;

		mDb.beginTransaction();
		try {
			count = mDb.delete(tableName, buildQuerySQLParm(whereArgs),
					whereArgsValues);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return count;
	}

	public int doUpdate(String tableName, ContentValues values,
			String[] whereArgs, String[] whereArgsValues) {
		int count = 0;
		mDb.beginTransaction();
		try {
			count = mDb.update(tableName, values, buildQuerySQLParm(whereArgs),
					whereArgsValues);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return count;
	}

	public Cursor doQuery(String table, String[] columns, String[] selections,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		Cursor cursor = null;

		mDb.beginTransaction();
		try {
			cursor = mDb.query(table, columns, buildQuerySQLParm(selections),
					selectionArgs, groupBy, having, orderBy);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return cursor;
	}

	public Cursor doQuery(String sql) {
		Cursor cursor = null;
		mDb.beginTransaction();
		try {
			cursor = mDb.rawQuery(sql, null);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			mDb.endTransaction();
		}
		return cursor;
	}

	/**
	 * 
	 * 
	 * @see doQuery("select * form " + " some_table " + " where " + " id = 123 "
	 *      + " AND " + " name = ? ", new String[] {name} );
	 */
	public Cursor doQuery(String sql, String[] selectionArgs) {
		return mDb.rawQuery(sql, selectionArgs);
	}

	public String executeSQL(String sql) {
		mDb.beginTransaction();
		try {
			mDb.execSQL(sql);
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			return "0";
		} finally {
			mDb.endTransaction();
		}
		return "1";
	}

	private String buildQuerySQLParm(String[] whereArgs) {
		if (whereArgs == null || whereArgs.length <= 0) {
			return null;
		}

		StringBuffer sqlStr = new StringBuffer("");
		final int length = whereArgs.length;
		for (int i = 0; i < length; i++) {
			if (i > 0) {
				sqlStr.append(" AND ");
			}
			sqlStr.append(whereArgs[i]);
			sqlStr.append("=?");
		}

		return sqlStr.toString();
	}

	public void close() {
		try {
			if (DBhelp != null) {
				DBhelp.close();
				DBhelp = null;
				mThis = null;
			}
			return;
		} catch (SQLException localSQLException) {
		} catch (Exception localException) {
		}
	}

	public SQLiteDatabase getSqliteDB() {
		return mDb;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

}

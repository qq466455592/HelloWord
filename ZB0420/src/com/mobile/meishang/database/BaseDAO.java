
package com.mobile.meishang.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * @Title:
 * @Description:基本数据库操作接口
 * @Author:12091216
 * @Since:2012-10-12
 * @Version:
 */
public interface BaseDAO
{

    /**
     * 插入数据
     */
    public long doInsert(String tableName, ContentValues values);

    /**
     * 对数据库中数据的删除操作
     * 
     * @param tableName
     *            删除操作中要操作的数据表的名称
     * @param whereArgs
     *            要删除的数据中提供的条件参数的名称
     * @param whereArgsValues
     *            要删除的数据中提供的条件参数的值
     */
    public int doDelete(String tableName, String[] whereArgs,
            String[] whereArgsValues);

    /**
     * 对数据的更新操作
     * 
     * @param tableName
     *            是所对应的操作表
     * @param values
     *            更新的数据名称和值组成的键值对
     * @param whereArgs
     *            要更新的数据集的条件参数
     * @param whereArgsValues
     *            要更新的数据集的条件参数的值
     */
    public int doUpdate(String tableName, ContentValues values,
            String[] whereArgs, String[] whereArgsValues);

    /**
     * 对数据进行的查询操作
     * 
     * @param tableName
     *            表名
     * @param selections
     *            要查询的数据中提供的条件参数的名称
     * @param selectionArgs
     *            要查询的数据中提供的条件参数的值
     * @param column
     *            控制哪些字段返回结果（传null是返回所有字段的结果集）
     * @param orderBy
     *            是否对某字段进行排序（传null不进行排序）
     * @return 查找的数据集的游标
     */
    public Cursor doQuery(String table, String[] columns, String[] selections,
            String[] selectionArgs, String groupBy, String having,
            String orderBy);

    /**
     * 直接用SQL语句查询
     */
    public Cursor doQuery(String sql);

    /**
     * 直接用SQL语句查询
     * 
     * @see doQuery("select * form " + " some_table " + " where " + " id = 123 "
     *      + " AND " + " name = ? ", new String[] {name} );
     */
    public Cursor doQuery(String sql, String[] selectionArgs);

    /**
     * 直接执行SQL语句
     */
    public String executeSQL(String sql);

    /**
     * 将用户提供的参数拼接成一条完整的删除数据库的SQL语句
     */

}

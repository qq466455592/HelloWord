/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobile.meishang.imagecache;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 * 
 * @Title:
 * @Description:Class containing some static utility methods.
 * @Author:Administrator
 * @Since:2013-5-15
 * @Version:
 */
public class Utils
{
    public static final int IO_BUFFER_SIZE = 8 * 1024;

    /**
     * Workaround for bug pre-Froyo, see here for more info:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     */
    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static void disableConnectionReuseIfNecessary()
    {
        // HTTP connection reuse which was buggy pre-froyo
        if (hasHttpConnectionBug())
        {
            System.setProperty("http.keepAlive", "false");
        }
    }

    /**
     * Get the size in bytes of a bitmap.
     * 
     * @param bitmap
     * @return size in bytes
     */
    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static int getBitmapSize(Bitmap bitmap)
    {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
        // return bitmap.getByteCount();
        // }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * Check if external storage is built-in or removable.
     * 
     * @return True if external storage is removable (like an SD card), false
     *         otherwise.
     */
    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static boolean isExternalStorageRemovable()
    {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
        // return Environment.isExternalStorageRemovable();
        // }
        return true;
    }

    /**
     * 
     * @Description:Get the external app cache directory.
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static File getExternalCacheDir(Context context)
    {
        final String cacheDir = "/leshihui/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath()
                + cacheDir);
    }

    /**
     * 
     * @Description:getHttpCacheDir
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static File getHttpCacheDir(Context context)
    {
        if (hasExternalCacheDir())
        {
            if (context.getExternalCacheDir() != null)
            {
                return context.getExternalCacheDir();
            }
            // else
            // {
            // Toast.makeText(context, "��Ǹ���豸SD������", Toast.LENGTH_SHORT)
            // .show();
            // android.os.Process.killProcess(android.os.Process.myPid());
            // }
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName()
                + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath()
                + cacheDir);
    }

    /**
     * Check how much usable space is available at a given path.
     * 
     * @param path
     *            The path to check
     * @return The space available in bytes
     */
    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static long getUsableSpace(File path)
    {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
        // return path.getUsableSpace();
        // }
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }

    /**
     * 
     * @Description:Get the memory class of this device
     * @Author Administrator
     * @Date 2013-5-15
     */
    public static int getMemoryClass(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR)
        {
            return ((ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
        }
        return 16;
    }

    /**
     * Check if OS version has a http URLConnection bug. See here for more
     * information:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     * 
     * @return
     */
    public static boolean hasHttpConnectionBug()
    {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO;
    }

    /**
     * Check if OS version has built-in external cache dir method.
     * 
     * @return
     */
    public static boolean hasExternalCacheDir()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * Check if ActionBar is available.
     * 
     * @return
     */
    public static boolean hasActionBar()
    {
        return false;
        // return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}

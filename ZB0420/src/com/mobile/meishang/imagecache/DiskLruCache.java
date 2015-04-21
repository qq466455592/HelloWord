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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.mobile.meishang.config.Config;
import com.mobile.meishang.logger.MyLog;

/**
 * A simple disk LRU bitmap cache to illustrate how a disk cache would be used
 * for bitmap caching. A much more robust and efficient disk LRU cache solution
 * can be found in the ICS source code
 * (libcore/luni/src/main/java/libcore/io/DiskLruCache.java) and is preferable
 * to this simple implementation.
 */
public class DiskLruCache
{
    private static final String TAG = "DiskLruCache";
    private static final String CACHE_FILENAME_PREFIX = "cache_";
    private final File mCacheDir;
    private CompressFormat mCompressFormat = CompressFormat.PNG;
    private int mCompressQuality = 70;

    private static final FilenameFilter cacheFileFilter = new FilenameFilter()
    {
        @Override
        public boolean accept(File dir, String filename)
        {
            return filename.startsWith(CACHE_FILENAME_PREFIX);
        }
    };

    /**
     * Used to fetch an instance of DiskLruCache.
     * 
     * @param context
     * @param cacheDir
     * @param maxByteSize
     * @return
     */
    public static DiskLruCache openCache(Context context, File cacheDir)
    {
        if (!cacheDir.exists())
        {
            try
            {
                cacheDir.mkdirs();
            }
            catch (Exception e)
            {
            }
        }

        if (cacheDir.isDirectory() && cacheDir.canWrite())
        {
            return new DiskLruCache(cacheDir);
        }
        return null;
    }

    /**
     * Constructor that should not be called directly, instead use
     * {@link DiskLruCache#openCache(Context, File, long)} which runs some extra
     * checks before creating a DiskLruCache instance.
     * 
     * @param cacheDir
     * @param maxByteSize
     */
    private DiskLruCache(File cacheDir)
    {
        mCacheDir = cacheDir;
    }

    /**
     * Add a bitmap to the disk cache.
     * 
     * @param key
     *            A unique identifier for the bitmap.
     * @param data
     *            The bitmap to store.
     */
    public void put(String key, Bitmap data)
    {
        synchronized (key)
        {
            final String existingFile = createFilePath(mCacheDir, key);
            final File imageFile = new File(existingFile);
            if (!new File(existingFile).exists())
            {
                try
                {
                    imageFile.createNewFile();
                    writeBitmapToFile(data, existingFile);

                }
                catch (final FileNotFoundException e)
                {
                    MyLog.e(TAG, "Error in put: " + e.getMessage());
                }
                catch (final IOException e)
                {
                    MyLog.e(TAG, "Error in put: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Get an image from the disk cache.
     * 
     * @param key
     *            The unique identifier for the bitmap
     * @return The bitmap or null if not found
     */
    public Bitmap get(String key)
    {
        synchronized (key)
        {
            final String existingFile = createFilePath(mCacheDir, key);
            final File imageFile = new File(existingFile);
            if (imageFile.exists())
            {
                if (Config.DEBUG)
                {
                    MyLog.d(TAG, "Disk cache hit (existing file)");
                }
                return BitmapFactory.decodeFile(existingFile);
            }
            return null;
        }
    }

    /**
     * Checks if a specific key exist in the cache.
     * 
     * @param key
     *            The unique identifier for the bitmap
     * @return true if found, false otherwise
     */

    /**
     * Removes all disk cache entries from this instance cache dir
     */
    public void clearCache()
    {
        DiskLruCache.clearCache(mCacheDir);
    }

    /**
     * Removes all disk cache entries from the application cache directory in
     * the uniqueName sub-directory.
     * 
     * @param context
     *            The context to use
     * @param uniqueName
     *            A unique cache directory name to append to the app cache
     *            directory
     */
    public static void clearCache(Context context, String uniqueName)
    {
        File cacheDir = getDiskCacheDir(context, uniqueName);
        clearCache(cacheDir);
    }

    /**
     * Removes all disk cache entries from the given directory. This should not
     * be called directly, call {@link DiskLruCache#clearCache(Context, String)}
     * or {@link DiskLruCache#clearCache()} instead.
     * 
     * @param cacheDir
     *            The directory to remove the cache files from
     */
    public static void clearCache(File cacheDir)
    {
        final File[] files = cacheDir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            try
            {
                files[i].delete();
            }
            catch (Exception e)
            {
                MyLog.e("图片清除异常");
            }
        }
    }

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     * 
     * @param context
     *            The context to use
     * @param uniqueName
     *            A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String uniqueName)
    {

        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        // return new File(Environment.getExternalStorageDirectory(),
        // File.separator + uniqueName);
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Utils.isExternalStorageRemovable() ? Utils
                .getExternalCacheDir(context).getPath() : context.getCacheDir()
                .getPath();
        return new File(cachePath + File.separator + uniqueName);

    }

    /**
     * 当外部存储创建cache有问题，且没有自动使用内部存储，手动使用内部存储
     */
    public static File getInternalDir(Context context, String uniqueName)
    {
        final String cachePath = context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    public static File gethttpCacheDir(Context context, String uniqueName)
    {

        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        // return new File(Environment.getExternalStorageDirectory(),
        // File.separator + uniqueName);
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Utils.isExternalStorageRemovable() ? Utils.getHttpCacheDir(
                context).getPath() : context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * Creates a constant cache file path given a target cache directory and an
     * image key.
     * 
     * @param cacheDir
     * @param key
     * @return
     */
    public static String createFilePath(File cacheDir, String key)
    {
        return cacheDir.getAbsolutePath() + File.separator + key.hashCode();
    }

    /**
     * Create a constant cache file path using the current cache directory and
     * an image key.
     * 
     * @param key
     * @return
     */
    public String createFilePath(String key)
    {
        return createFilePath(mCacheDir, key);
    }

    /**
     * Sets the target compression format and quality for images written to the
     * disk cache.
     * 
     * @param compressFormat
     * @param quality
     */
    public void setCompressParams(CompressFormat compressFormat, int quality)
    {
        mCompressFormat = compressFormat;
        mCompressQuality = quality;
    }

    /**
     * Writes a bitmap to a file. Call
     * {@link DiskLruCache#setCompressParams(CompressFormat, int)} first to set
     * the target bitmap compression and format.
     * 
     * @param bitmap
     * @param file
     * @return
     */
    private boolean writeBitmapToFile(Bitmap bitmap, String file)
            throws IOException, FileNotFoundException
    {

        OutputStream out = null;
        try
        {
            out = new BufferedOutputStream(new FileOutputStream(file),
                    Utils.IO_BUFFER_SIZE);
            return bitmap.compress(mCompressFormat, mCompressQuality, out);
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
}

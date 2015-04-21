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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Config;
import com.mobile.meishang.logger.MyLog;

/**
 * This class holds our bitmap caches (memory and disk).
 */
public class ImageLruCache {
	private static final String TAG = "ImageCache";

	// Default memory cache size
	private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 1024 * 5; // 5MB

	// Compression settings when writing images to disk cache
	private static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.PNG;
	private static final int DEFAULT_COMPRESS_QUALITY = 70;

	// Constants to easily toggle various caches
	private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
	private static final boolean DEFAULT_DISK_CACHE_ENABLED = true;
	private static final boolean DEFAULT_CLEAR_DISK_CACHE_ON_START = false;

	private DiskLruCache mDiskCache;
	private LruCache<String, Bitmap> mMemoryCache;

	/**
	 * Creating a new ImageCache object using the specified parameters.
	 * 
	 * @param context
	 *            The context to use
	 * @param cacheParams
	 *            The cache parameters to use to initialize the cache
	 */
	public ImageLruCache(Context context, ImageCacheParams cacheParams) {
		init(context, cacheParams);
	}

	/**
	 * Creating a new ImageCache object using the default parameters.
	 * 
	 * @param context
	 *            The context to use
	 * @param uniqueName
	 *            A unique name that will be appended to the cache directory
	 */
	// public ImageLruCache(Context context, String uniqueName) {
	// init(context, new ImageCacheParams(uniqueName));
	// }

	/**
	 * Find and return an existing ImageCache stored in a {@link RetainFragment}
	 * , if not found a new one is created with defaults and saved to a
	 * {@link RetainFragment}.
	 * 
	 * @param activity
	 *            The calling {@link FragmentActivity}
	 * @param uniqueName
	 *            A unique name to append to the cache directory
	 * @return An existing retained ImageCache object or a new one if one did
	 *         not exist.
	 */
	public static ImageLruCache findOrCreateCache(final MApplication app,
			final String uniqueName, final boolean deteleDiskLruCache) {
		return findOrCreateCache(app, new ImageCacheParams(uniqueName),
				deteleDiskLruCache);
	}

	/**
	 * Find and return an existing ImageCache stored in a {@link RetainFragment}
	 * , if not found a new one is created using the supplied params and saved
	 * to a {@link RetainFragment}.
	 * 
	 * @param activity
	 *            The calling {@link FragmentActivity}
	 * @param cacheParams
	 *            The cache parameters to use if creating the ImageCache
	 * @return An existing retained ImageCache object or a new one if one did
	 *         not exist
	 */
	public static ImageLruCache findOrCreateCache(final MApplication app,
			ImageCacheParams cacheParams, boolean deteleDiskLruCache) {

		// Search for, or create an instance of the non-UI RetainFragment
		// final RetainFragment mRetainFragment =
		// RetainFragment.findOrCreateRetainFragment(
		// activity.getSupportFragmentManager());
		// final SubookApplication app = (SubookApplication)
		// activity.getApplication();

		// See if we already have an ImageCache stored in RetainFragment
		// ImageCache imageCache = (ImageCache) mRetainFragment.getObject();
		ImageLruCache imageCache = MApplication.getImageLruCache();

		// No existing ImageCache, create one and store it in RetainFragment
		if (imageCache == null) {
			cacheParams.memCacheSize = 1024 * 1024 * Utils
					.getMemoryClass(MApplication.mApplication) / 3;
			cacheParams.clearDiskCacheOnStart = deteleDiskLruCache;
			imageCache = new ImageLruCache(app, cacheParams);
			MApplication.setImageLruCache(imageCache);
			// mRetainFragment.setObject(imageCache);
		}
		return imageCache;
	}

	/**
	 * Initialize the cache, providing all parameters.
	 * 
	 * @param context
	 *            The context to use
	 * @param cacheParams
	 *            The cache parameters to initialize the cache
	 */
	private void init(Context context, ImageCacheParams cacheParams) {
		File diskCacheDir = DiskLruCache.getDiskCacheDir(context,
				cacheParams.uniqueName);

		// Set up disk cache
		if (cacheParams.diskCacheEnabled) {
			mDiskCache = DiskLruCache.openCache(context, diskCacheDir);
			if (mDiskCache == null) {
				diskCacheDir = DiskLruCache.getInternalDir(context,
						cacheParams.uniqueName);
				mDiskCache = DiskLruCache.openCache(context, diskCacheDir);
			}
			mDiskCache.setCompressParams(cacheParams.compressFormat,
					cacheParams.compressQuality);
			if (cacheParams.clearDiskCacheOnStart) {
				new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... paramArrayOfParams) {
						mDiskCache.clearCache();
						return null;
					}
				}.execute();
			}
		}

		// Set up memory cache
		if (cacheParams.memoryCacheEnabled) {
			mMemoryCache = new LruCache<String, Bitmap>(
					cacheParams.memCacheSize) {
				/**
				 * Measure item size in bytes rather than units which is more
				 * practical for a bitmap cache
				 */
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return Utils.getBitmapSize(bitmap);
				}

				@Override
				protected void entryRemoved(boolean evicted, String key,
						Bitmap oldValue, Bitmap newValue) {
					// 硬引用缓存区满，将一个最不经常使用的oldvalue图片清空
					if (oldValue.isRecycled()) {
						oldValue.recycle();
						oldValue = null;
					}
				}

			};
		}
	}

	public void addBitmapToCache(String data, Bitmap bitmap) {
		if (data == null || bitmap == null) {
			return;
		}

		// Add to memory cache
		if (mMemoryCache != null && mMemoryCache.get(data) == null) {
			mMemoryCache.put(data, bitmap);
		}

		// Add to disk cache
		if (mDiskCache != null) {
			mDiskCache.put(data, bitmap);
		}
	}

	/**
	 * Get from memory cache.
	 * 
	 * @param data
	 *            Unique identifier for which item to get
	 * @return The bitmap if found in cache, null otherwise
	 */
	public Bitmap getBitmapFromMemCache(String data) {
		if (mMemoryCache != null) {
			final Bitmap memBitmap = mMemoryCache.get(data);
			if (memBitmap != null) {
				if (Config.DEBUG) {
					MyLog.d(TAG, "Memory cache hit");
				}
				return memBitmap;
			}
		}
		return null;
	}

	/**
	 * Get from disk cache.
	 * 
	 * @param data
	 *            Unique identifier for which item to get
	 * @return The bitmap if found in cache, null otherwise
	 */
	public Bitmap getBitmapFromDiskCache(String data) {
		if (mDiskCache != null) {
			return mDiskCache.get(data);
		}
		return null;
	}

	public void clearCaches() {
		// mDiskCache.clearCache();
		mMemoryCache.evictAll();
	}

	public void deleteFileInHttpCache(String path) {
		File cacheDir = DiskLruCache.gethttpCacheDir(MApplication.mApplication,
				ImageFetcher.HTTP_CACHE_DIR);
		File f = new File(cacheDir.getAbsolutePath() + File.separator + path);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * A holder class that contains cache parameters.
	 */
	public static class ImageCacheParams {
		public String uniqueName;
		public int memCacheSize = DEFAULT_MEM_CACHE_SIZE;
		public CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
		public int compressQuality = DEFAULT_COMPRESS_QUALITY;
		public boolean memoryCacheEnabled = DEFAULT_MEM_CACHE_ENABLED;
		public boolean diskCacheEnabled = DEFAULT_DISK_CACHE_ENABLED;
		public boolean clearDiskCacheOnStart = DEFAULT_CLEAR_DISK_CACHE_ON_START;

		public ImageCacheParams(String uniqueName) {
			this.uniqueName = uniqueName;
		}
	}
}

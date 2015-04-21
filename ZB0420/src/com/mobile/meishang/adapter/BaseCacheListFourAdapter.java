package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.imagecache.ImageFetcher;
import com.mobile.meishang.imagecache.ImageWorker;


/**
 * @Title:
 * @Description:listView�б�һ���ĸ�item���������ϲ��в����
 * @Author:12120332
 * @Since:2012-12-18
 * @Version:
 * @param:<T>
 */
public abstract class BaseCacheListFourAdapter<T> extends
        BaseListFourAdapter<T>
{

    protected LayoutInflater mInflater;
    protected ImageWorker mImageWorker;
    protected Context mContext;

    /**
     * 
     * @param context
     */
    public BaseCacheListFourAdapter(Context context)
    {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);

        // The ImageWorker takes care of loading images into our ImageView
        // children asynchronously
        mImageWorker = new ImageFetcher(context,
                MApplication.getLongest());
        mImageWorker.setImageCache(MApplication
                .getImageLruCache());
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-8
     */
    public void setCacheImage(final ImageView imageView, String imageUrl,
            final int resId)
    {
        mImageWorker.setLoadingImage(resId);
        mImageWorker.loadImage(imageUrl, imageView);
    }

    /**
     * should be called when Activity is destoried
     * 
     * @Description:
     * @Author 11112760
     * @Date 2012-8-17
     */
    public void destory()
    {
        super.clear();
        if (mImageWorker != null)
        {
            mImageWorker.getImageCache().clearCaches();
            mImageWorker.setImageCache(null);
            mImageWorker = null;
        }
        mInflater = null;
    }

}

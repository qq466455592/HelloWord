package com.mobile.meishang.utils.view;
//package com.ivpoints.mobile.utils.view;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.LinearGradient;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Shader.TileMode;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//
//public class ImageAdapter extends BaseAdapter
//{
//    int mGalleryItemBackground;
//    private Context mContext;
//    private Integer[] mImageIds;
//    private ImageView[] mImages;
//
//    public ImageAdapter(Context c, Integer[] ImageIds)
//    {
//        mContext = c;
//        mImageIds = ImageIds;
//        mImages = new ImageView[mImageIds.length];
//    }
//
//    /**
//     * ������ӰЧ��
//     * 
//     * @return
//     */
//    public boolean createReflectedImages()
//    {
//        // ��Ӱͼ��ԭͼ֮��ľ���
//        final int reflectionGap = 4;
//        int index = 0;
//        for (int imageId : mImageIds)
//        {
//            // ����ԭͼ����֮���bitmap����
//            Bitmap originalImage = BitmapFactory.decodeResource(
//                    mContext.getResources(), imageId);
//            int width = originalImage.getWidth();
//            int height = originalImage.getHeight();
//            // �����������
//            Matrix matrix = new Matrix();
//
//            // ָ��һ��Ƕ���0,0Ϊ��������ת
//            // matrix.setRotate(30);
//
//            // ָ������(x�᲻�䣬y���෴)
//            matrix.preScale(1, -1);
//
//            // ������Ӧ�õ���ԭͼ֮�У�����һ���Ȳ��䣬�߶�Ϊԭͼ1/2�ĵ�Ӱλͼ
//            Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
//                    height / 2, width, height / 2, matrix, false);
//
//            // ����һ���Ȳ��䣬�߶�Ϊԭͼ+��Ӱͼ�߶ȵ�λͼ
//            Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
//                    (height + height / 2), Config.ARGB_8888);
//
//            // �����洴����λͼ��ʼ��������
//            Canvas canvas = new Canvas(bitmapWithReflection);
//            canvas.drawBitmap(originalImage, 0, 0, null);
//
//            Paint deafaultPaint = new Paint();
//            deafaultPaint.setAntiAlias(false);
//            // canvas.drawRect(0, height, width, height +
//            // reflectionGap,deafaultPaint);
//            canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
//            Paint paint = new Paint();
//            paint.setAntiAlias(false);
//
//            /**
//             * ����һ:Ϊ�����������xλ�ã� �����:Ϊy��λ�ã� ���������:�ֱ��Ӧ�����յ㣬 ������Ϊƽ�̷�ʽ��
//             * ��������Ϊ����Gradient�ǻ���Shader�࣬��������ͨ��Paint��setShader����4��������
//             */
//            LinearGradient shader = new LinearGradient(0,
//                    originalImage.getHeight(), 0,
//                    bitmapWithReflection.getHeight() + reflectionGap,
//                    0x70ffffff, 0x00ffffff, TileMode.MIRROR);
//            // ������Ӱ
//            paint.setShader(shader);
//            paint.setXfermode(new PorterDuffXfermode(
//                    android.graphics.PorterDuff.Mode.DST_IN));
//            // ���Ѿ�����õĻ��ʹ���һ�������Ӱ����Ч��
//            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
//                    + reflectionGap, paint);
//
//            // ����һ��ImageView��4��ʾ�Ѿ����õ�bitmapWithReflection
//            ImageView imageView = new ImageView(mContext);
//            imageView.setImageBitmap(bitmapWithReflection);
//            // ����imageView��С ��Ҳ����������ʾ��ͼƬ��С
//            imageView.setLayoutParams(new GalleryFlow.LayoutParams(350, 400));
//            // imageView.setScaleType(ScaleType.CENTER_INSIDE);
//            mImages[index++] = imageView;
//        }
//        return true;
//    }
//
//    @SuppressWarnings("unused")
//    private Resources getResources()
//    {
//        return null;
//    }
//
//    public int getCount()
//    {
//        return mImageIds.length;
//    }
//
//    public Object getItem(int position)
//    {
//        return position;
//    }
//
//    public long getItemId(int position)
//    {
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//        return mImages[position];
//    }
//
//    public float getScale(boolean focused, int offset)
//    {
//        return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
//    }
//}

package com.mobile.meishang.utils;
///**
// *@Copyright:Copyright (c) 2012 - 2100
// *@Company:suning.com
// */
//package com.ivpoints.mobile.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.widget.TextView;
//
///**
// * 
// * @Title:
// * @Description:
// * @Author:Administrator
// * @Since:2013-4-27
// * @Version:
// */
//public class AutoLineTextView extends TextView
//{
//    private final String namespace = "http://www.suningEbuy.com/";
//    private String text;
//    private float textSize;
//    private float paddingLeft;
//    private float paddingRight;
//    private float marginLeft;
//    private float marginRight;
//    private int textColor;
//
//    private Paint mPaint = new Paint();
//    private float textShowWidth;
//
//    public AutoLineTextView(Context context, AttributeSet attrs)
//    {
//        super(context, attrs);
//        textSize = attrs.getAttributeIntValue(namespace, "textSize", 14);
//        textColor = attrs.getAttributeIntValue(namespace, "textColor",
//                Color.WHITE);
//        paddingLeft = attrs.getAttributeIntValue(namespace, "paddingLeft", 0);
//        paddingRight = attrs.getAttributeIntValue(namespace, "paddingRight", 0);
//        marginLeft = attrs.getAttributeIntValue(namespace, "marginLeft", 0);
//        marginRight = attrs.getAttributeIntValue(namespace, "marginRight", 0);
//        textSize = getRawSize(textSize);
//        paddingLeft = getRawSize(paddingLeft);
//        paddingRight = getRawSize(paddingRight);
//        marginLeft = getRawSize(marginLeft);
//        marginRight = getRawSize(marginRight);
//        mPaint.setTextSize(textSize);
//        mPaint.setColor(textColor);
//        mPaint.setAntiAlias(true);
//        textShowWidth = ((Activity) context).getWindowManager()
//                .getDefaultDisplay().getWidth()
//                - paddingLeft - paddingRight - marginLeft - marginRight;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        int lineCount = 0;
//        char[] textCharArray;
//        text = this.getText().toString();
//        if (text != null)
//        {
//            textCharArray = text.toCharArray();
//        }
//        else
//        {
//            return;
//        }
//        // �ѻ�Ŀ��
//        float drawedWidth = 0;
//        float charWidth;
//        for (int i = 0; i < textCharArray.length; i++)
//        {
//            charWidth = mPaint.measureText(textCharArray, i, 1);
//
//            if (textCharArray[i] == '\n')
//            {
//                lineCount++;
//                drawedWidth = 0;
//                continue;
//            }
//            if (textShowWidth - drawedWidth < charWidth)
//            {
//                lineCount++;
//                drawedWidth = 0;
//            }
//            canvas.drawText(textCharArray, i, 1, paddingLeft + drawedWidth,
//                    (lineCount + 1) * textSize, mPaint);
//            drawedWidth += charWidth;
//        }
//        setHeight((lineCount + 1) * (int) textSize + 5);
//    }
//
//    /**
//     * ���������С
//     * 
//     * @param unit
//     * @param size
//     * @return
//     */
//    private float getRawSize(float size)
//    {
//        Context c = getContext();
//        Resources r;
//
//        if (c == null)
//        {
//            r = Resources.getSystem();
//        }
//        else
//        {
//            r = c.getResources();
//        }
//
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size,
//                r.getDisplayMetrics());
//    }
//}

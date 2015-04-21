package com.mobile.meishang.utils.view;
//package com.ivpoints.mobile.utils.view;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.TypedValue;
//import android.widget.TextView;
//
//public class AutoLineTextView extends TextView
//{
//    private final String namespace = "http://www..com/";
//    private String text;// Ҫ��ʾ����
//    private float textSize;// Ҫ��ʾ���ݵĴ�С
//    private float paddingLeft;// ���ݾ���ؼ���ߵĴ�С
//    private float paddingRight;// ���ݾ���ؼ��ұߵĴ�С
//    private float marginLeft;// �ؼ��������ڲ��ֵ���߾���
//    private float marginRight;// �ؼ��������ڲ��ֵ��ұ߾���
//    private int textColor;// ��ʾ�ֵ���ɫ
//
//    private Paint mPaint = new Paint();// ����
//    private float textShowWidth;// ��ʾ���ݵĿ��
//    private int maxLines;// �����ʾ����
//
//    private float mRate;// ��ʾ�ƶ���Ƶ��
//    private float widthRate;
//
//    /**
//     * @param context
//     *            �����Ļ���
//     * @param attrs
//     *            ����xml�ļ�����
//     */
//    public AutoLineTextView(Context context, AttributeSet attrs)
//    {
//        super(context, attrs);
//        // ��ȡxml�ļ����������õĴ�С�����û������Ĭ��Ϊ14sp
//        textSize = attrs.getAttributeIntValue(namespace, "textSize", 14);
//        // ��ȡxml�ļ����������õ���ɫ�����û������Ĭ��Ϊ��ɫ
//        textColor = attrs.getAttributeIntValue(namespace, "textColor",
//                Color.WHITE);
//        // ��ȡxml�ļ�����ʾ���ݾ���ؼ���ߵĴ�С�����û������Ĭ��Ϊ0
//        paddingLeft = attrs.getAttributeIntValue(namespace, "paddingLeft", 0);
//        // ��ȡxml�ļ�����ʾ���ݾ���ؼ��ұߵĴ�С�����û������Ĭ��Ϊ0
//        paddingRight = attrs.getAttributeIntValue(namespace, "paddingRight", 0);
//        // ��ȡxml�ļ��пؼ��������ڲ��ֵ���߾��룬���û������Ĭ��Ϊ0
//        marginLeft = attrs.getAttributeIntValue(namespace, "marginLeft", 0);
//        // ��ȡxml�ļ��пؼ��������ڲ��ֵ��ұ߾��룬���û������Ĭ��Ϊ0
//        marginRight = attrs.getAttributeIntValue(namespace, "marginRight", 0);
//        // ��ȡxml�ļ�����ʾ���ݵ������ʾ�������û������Ĭ��Ϊ100
//        maxLines = attrs.getAttributeIntValue(namespace, "maxLines", 100);
//        widthRate = attrs.getAttributeFloatValue(namespace, "widthRate", 1f);
//        // ��ȡ��ǰ�ֱ�����ָ����λ��Ӧ�����ش�С
//        textSize = getRawSize(textSize);
//        // ��ȡ��ǰ�ֱ�����ָ����λ��Ӧ�����س���
//        paddingLeft = getRawLength(paddingLeft);
//        paddingRight = getRawLength(paddingRight);
//        marginLeft = getRawLength(marginLeft);
//        marginRight = getRawLength(marginRight);
//        // ���û��ʵ�����
//        mPaint.setTextSize(textSize);
//        // ���û�����ɫ
//        mPaint.setColor(textColor);
//        mPaint.setAntiAlias(true);
//        textShowWidth = ((Activity) context).getWindowManager()
//                .getDefaultDisplay().getWidth()
//                * widthRate
//                - paddingLeft
//                - paddingRight
//                - marginLeft
//                - marginRight;
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        int lineCount = 0;
//        text = this.getText().toString();
//        if (text == null)
//        {
//            return;
//        }
//        char[] textCharArray = text.toCharArray();
//        char[] baseChar = new char[] { '.', '.', '.' };
//        // �ѻ�Ŀ��
//        float drawedWidth = 0;
//        float charWidth;
//        float baseCharWidth;
//        baseCharWidth = mPaint.measureText(".");
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
//
//            if (lineCount < maxLines)
//            {
//                if (lineCount == maxLines - 1)
//                {
//                    if (textShowWidth - drawedWidth <= 7 * baseCharWidth)
//                    {
//                        canvas.drawText(baseChar, 0, 3, paddingLeft
//                                + drawedWidth, (lineCount + 1) * mRate, mPaint);
//                        break;
//                    }
//                }
//                canvas.drawText(textCharArray, i, 1, paddingLeft + drawedWidth,
//                        (lineCount + 1) * mRate, mPaint);
//            }
//            drawedWidth += charWidth;
//        }
//        setHeight((lineCount + 1) * (int) mRate + 5);// ���ò������ˢ��
//    }
//
//    /**
//     * @Description:���������С
//     * @Author 13050527
//     * @Date 2013-6-13
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
//
//    /**
//     * @Description:������Ļ���
//     * @Author 13050527
//     * @Date 2013-6-13
//     */
//    private float getRawLength(float size)
//    {
//        Context c = getContext();
//        DisplayMetrics dm = c.getResources().getDisplayMetrics();
//        float mDensity = (float) dm.density;
//
//        if (mDensity <= 1)
//        {
//            mRate = textSize + 1;
//        }
//        else if (mDensity < 2)
//        {
//            mRate = textSize + 4;
//        }
//        else
//        {
//            mDensity += 0.2;
//            mRate = textSize + 5;
//        }
//        return size * mDensity;
//    }
//    
//    @Override
//    public void setTextColor(int color)
//    {
//        super.setTextColor(color);
//        mPaint.setColor(color);
//        invalidate();
//    }
//
//    /**
//     * @param maxLines
//     *            the maxLines to set
//     */
//    public void setMaxLines(int maxLines)
//    {
//        this.maxLines = maxLines;
//        invalidate();
//    }
//
//}

package com.mobile.meishang.utils;
//package com.ivpoints.mobile.utils;
//
//
//import com.ivpoints.mobile.R;
//
//import android.app.Activity;
//import android.content.Intent;
//
//public class UITool
//{
//    public static void opneWindowDefault(Activity mContext, Intent intent)
//    {
//        mContext.startActivity(intent);
//        mContext.overridePendingTransition(R.anim.activity_fade_in,
//                R.anim.activity_fade_out);
//    }
//
//    public static void openWindow(Activity mContext, Intent intent,
//            int enterAnim, int exitAnim)
//    {
//        mContext.startActivity(intent);
//        if (enterAnim != -1 && exitAnim != -1)
//            mContext.overridePendingTransition(enterAnim, exitAnim);
//    }
//
//    public static void openWindowForResult(Activity mContext, Intent intent,
//            int requestCode)
//    {
//        mContext.startActivityForResult(intent, requestCode);
//        mContext.overridePendingTransition(R.anim.activity_fade_in,
//                R.anim.activity_fade_out);
//    }
//
//    public static void closeWindow(Activity mContext, int enterAnim,
//            int exitAnim)
//    {
//        mContext.finish();
//        if (enterAnim != -1 && exitAnim != -1)
//            mContext.overridePendingTransition(enterAnim, exitAnim);
//    }
//}

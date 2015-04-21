package com.mobile.meishang.utils;
//package com.ivpoints.mobile.utils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.regex.PatternSyntaxException;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.text.SpannableStringBuilder;
//
//public class ShareUtil
//{
//    public static void share(Context context, String text, Bitmap bmp)
//    {
//        String productUrl = saveBitmap(bmp, "share.png");
//
//        startShare(context, text, productUrl);
//    }
//
//    public static void shareApk(Context context, String filePath)
//    {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        File file = new File(filePath);
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//        context.startActivity(Intent.createChooser(intent, "分享"));
//    }
//
//    // 保存到本地
//    private static String saveBitmap(Bitmap bmp, String bmppName)
//    {
//        if (bmp == null)
//        {
//            return "";
//        }
//
//        if (bmppName == null || bmppName.length() == 0)
//        {
//            bmppName = "share.png";
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(bmp);
//        // 存储路径
//        File file = new File("/sdcard/suning.ebuy/image/share/");
//        if (!file.exists())
//            file.mkdirs();
//        try
//        {
//            FileOutputStream fileOutputStream = new FileOutputStream(
//                    file.getPath() + "/" + bmppName);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//            fileOutputStream.close();
//        }
//        catch (Exception e)
//        {
//            return "";
//        }
//        return "file:////sdcard//suning.ebuy//image//share//" + bmppName;
//    }
//
//    private static void startShare(Context context, String text, String bmpUrl)
//    {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TITLE, "分享");
//        // 主题
//        intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
//                "我在苏宁易购发现个好东东，快来看看哦");
//        intent.putExtra(Intent.EXTRA_TEXT, stringFilter(text));
//        if (bmpUrl.length() > 0)
//        {
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(bmpUrl));
//        }
//        else
//        {
//            intent.setType("text/plain");
//        }
//        context.startActivity(Intent.createChooser(intent, "分享方式"));
//    }
//
////    public static String getContent(ProductBean bean)
////    {
////
////        String mStrBooks;
////        if (bean.isABook.equals("true"))
////        {
////            mStrBooks = "22001";
////        }
////        else
////        {
////            mStrBooks = "10051";
////        }
////        String detialUrl = "http://www.suning.com/emall/prd_10052_" + mStrBooks
////                + "_" + "-7" + "_" + bean.productId + "_.html";
////
////        String content = "苏宁易购卖的\"" + bean.productName + "\"不错哦，" + detialUrl;
////
////        return htmlfrom(content).toString();
////    }
//
//    /**
//     * Replacement, filter the special characters
//     * 
//     * @param str
//     * @return
//     * @throws PatternSyntaxException
//     */
//    public static String stringFilter(String str) throws PatternSyntaxException
//    {
//        str = str.replaceAll("【", "[").replaceAll("】", "]")
//                .replaceAll("！", "!");// 替换中文标号
//        String regEx = "[『』]"; // 清除掉特殊字符
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(str);
//        return m.replaceAll("").trim();
//    }
//
//    public static CharSequence htmlfrom(CharSequence text)
//    {
//        Pattern htmlflag1 = Pattern.compile("<(.*?)>");
//        SpannableStringBuilder builder = new SpannableStringBuilder(text);
//        Matcher matcher = htmlflag1.matcher(text);
//        while (matcher.find())
//        {
//            builder.delete(matcher.start(), matcher.end());
//            text = builder;
//            matcher = htmlflag1.matcher(text);
//        }
//        Pattern htmlflag2 = Pattern.compile("&(.*?);");
//        matcher = htmlflag2.matcher(text);
//        while (matcher.find())
//        {
//            builder.delete(matcher.start(), matcher.end());
//            text = builder;
//            matcher = htmlflag2.matcher(text);
//        }
//
//        return builder;
//
//    }
//}
package com.mobile.meishang.utils;
//package com.cebbank.yktx.mobile.utils;
//
//import java.util.Calendar;
//
//import com.cebbank.yktx.mobile.R;
//
//import kankan.wheel.widget.OnWheelChangedListener;
//import kankan.wheel.widget.WheelView;
//import kankan.wheel.widget.adapters.NumericWheelAdapter;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.DatePickerDialog.OnDateSetListener;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//
//
///**
// * 
// * @Title:
// * @Description:
// * @Author:Administrator
// * @Since:2013-5-2
// * @Version:
// */
//public class ActivityUtil
//{
//	private static final int YEAR_START = 1901;
//	private static final int YEAR_END = 2049;
//	/**
//	 * 显示选择日期对话框
//	 */
//	public static Dialog showDateDialog(final Context context, final OnDateSetListener listener, Calendar calendar) {
//		if (calendar == null)
//			calendar = Calendar.getInstance();
//
//		View view = LayoutInflater.from(context).inflate(R.layout.dialog_selectdate, null);
//
//		final WheelView yearwWheelView = (WheelView) view.findViewById(R.id.dialog_selectdate_wheel_year);
//		final WheelView monthWheelView = (WheelView) view.findViewById(R.id.dialog_selectdate_wheel_month);
//		final WheelView dayWheelView = (WheelView) view.findViewById(R.id.dialog_selectdate_wheel_day);
//
//		OnWheelChangedListener changingListener = new OnWheelChangedListener() {
//			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				updateDays(context, yearwWheelView, monthWheelView, dayWheelView);
//			}
//		};
//
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//
//		yearwWheelView.setViewAdapter(new NumericWheelAdapter(context, YEAR_START, YEAR_END));
//		yearwWheelView.setCurrentItem(year - YEAR_START);
//		yearwWheelView.addChangingListener(changingListener);
//		yearwWheelView.setCyclic(true);
//
//		monthWheelView.setViewAdapter(new NumericWheelAdapter(context, 1, 12, "%02d"));
//		monthWheelView.setCurrentItem(month);
//		monthWheelView.addChangingListener(changingListener);
//		monthWheelView.setCyclic(true);
//
//		updateDays(context, yearwWheelView, monthWheelView, dayWheelView);
//
//		dayWheelView.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
//		dayWheelView.setCyclic(true);
//
//		final Dialog dialog = new AlertDialog.Builder(context).create();
//
//		view.findViewById(R.id.dialog_selectdate_textview_setting).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				int year, month, day;
//
//				year = yearwWheelView.getCurrentItem() + YEAR_START;
//				month = monthWheelView.getCurrentItem() + 1;
//				day = dayWheelView.getCurrentItem() + 1;
//
//				listener.onDateSet(null, year, month, day);
////				System.out.println(year + "年" + month + "月" + day + "日");
//				dialog.dismiss();
//			}
//		});
//		view.findViewById(R.id.dialog_selectdate_textview_cancel).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//		dialog.show();
//		dialog.setContentView(view);
//		WindowManager.LayoutParams  lp= dialog.getWindow().getAttributes();  
//        lp.width=WindowManager.LayoutParams.MATCH_PARENT;//定义宽度  
//        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;//定义高度  
//        dialog.getWindow().setAttributes(lp);  
//		return dialog;
//	}
//
//	private static void updateDays(Context context, WheelView year, WheelView month, WheelView day) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
//		calendar.set(Calendar.MONTH, month.getCurrentItem());
//
//		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		day.setViewAdapter(new NumericWheelAdapter(context, 1, maxDays, "%02d"));
//		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
//		day.setCurrentItem(curDay - 1, true);
//	}
//
//	/**
//	 * 显示对话框
//	 */
//	public static Dialog showDialog(Context context, int layot) {
//		Dialog mDialog = new AlertDialog.Builder(context).create();
//		mDialog.show();
//		// 注意此处要放在show之后 否则会报异常
//		mDialog.setContentView(layot);
//
//		return mDialog;
//	}
//
//	/**
//	 * 显示对话框
//	 */
//	public static Dialog showDialog(Context context, View view) {
//		Dialog mDialog = new AlertDialog.Builder(context).create();
//		mDialog.show();
//		// 注意此处要放在show之后 否则会报异常
//		mDialog.setContentView(view);
//
//		return mDialog;
//	}
//
////	/**
////	 * 显示已完成对话框
////	 */
////	public static Dialog showAccomplishDialog(Context context) {
////		Dialog mDialog = new AlertDialog.Builder(context).create();
////		mDialog.show();
////		// 注意此处要放在show之后 否则会报异常
////		mDialog.setContentView(R.layout.dialog_accomplish);
////
////		return mDialog;
////	}
//
//	/**
//	 * 关闭对话框
//	 */
//	public static void dimissDialog(Dialog dialog) {
//		if (dialog != null && dialog.isShowing())
//			dialog.dismiss();
//	}
//
//	/**
//	 * 关闭软键盘
//	 */
//	public static void closeInputMethod(Context context, View view) {
//		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//	}
//}

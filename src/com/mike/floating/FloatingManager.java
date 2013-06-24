package com.mike.floating;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

public class FloatingManager {
	
	private static final String TAG = "FloatingManager";
	
	private Context mContext;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mLayoutParams;
	
	private FloatingView mFloatingView;
	
	private boolean mIsFloatingViewRemoved = true;
	
	public FloatingManager (Context context, FloatingView view) {
		mContext = context;
		mWindowManager = (WindowManager) mContext.getApplicationContext()
				.getSystemService("window");
		mFloatingView = view;	
	}
	
	public void show() {
		if (null != mWindowManager && null != mFloatingView
				&& mIsFloatingViewRemoved) {
			Log.d(TAG, "show()");
			mLayoutParams = ((FloatingApplication) mContext.getApplicationContext())
					.getMywmParams();
			mWindowManager.addView(mFloatingView, mLayoutParams);
			mIsFloatingViewRemoved = false;
		}
	}
	
	public void dismiss() {
		Log.d(TAG, "dismiss()");
		if (null != mWindowManager && null != mFloatingView)
		mWindowManager.removeView(mFloatingView);
		mIsFloatingViewRemoved = true;
	}
	

}

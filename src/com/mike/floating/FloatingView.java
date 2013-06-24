package com.mike.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class FloatingView extends LinearLayout {
	private static final String TAG = "FloatingView";
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;

	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mLayoutParams;
	private boolean mIsFloatingViewRemoved = true;
	private View mContentView = null;
	
	public FloatingView(Context context) {
		super(context);
		init();
	}
	
	public FloatingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FloatingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mWindowManager = (WindowManager) getContext().getApplicationContext()
				.getSystemService("window");

		mLayoutParams = ((FloatingApplication) getContext()
				.getApplicationContext()).getMywmParams();
		LayoutInflater inflater = LayoutInflater.from(getContext());
		mContentView = inflater.inflate(R.layout.test, null);
		addView(mContentView);
		initLayoutParams();
	}
	
	private void initLayoutParams() {
		mLayoutParams = ((FloatingApplication) mContext.getApplicationContext())
				.getMywmParams();

		//int WindowManager LayoutParams
		mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE; // window type
		mLayoutParams.format = PixelFormat.RGBA_8888;  

		//int WindowManager Flags
		mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */

		mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP; // ������������Ͻ�
	
		mLayoutParams.x = 0;
		mLayoutParams.y = 0;

		mLayoutParams.width = 400;
		mLayoutParams.height = 80;
	}
	
	public void show() {
		if (null != mWindowManager && null != mContentView
				&& mIsFloatingViewRemoved) {
			Log.d(TAG, "show()");
			mLayoutParams = ((FloatingApplication) mContext.getApplicationContext())
					.getMywmParams();
			mWindowManager.addView(this, mLayoutParams);
			mIsFloatingViewRemoved = false;
		}
	}
	
	public void dismiss() {
		Log.d(TAG, "dismiss()");
		if (null != mWindowManager && null != mContentView)
		mWindowManager.removeView(this);
		mIsFloatingViewRemoved = true;
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// default position
		x = event.getRawX();
		y = event.getRawY() - 25; // remove the height of notification bar
		Log.i(TAG, "currX" + x + "====currY" + y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// catch the touch point
			mTouchStartX = event.getX();
			mTouchStartY = event.getY();

			Log.i("a", "startX" + mTouchStartX + "====startY" + mTouchStartY);

			break;
		case MotionEvent.ACTION_MOVE:
			updateViewPosition();
			break;

		case MotionEvent.ACTION_UP:
			updateViewPosition();
			mTouchStartX = mTouchStartY = 0;
			break;
		}
		return true;
	}

	private void updateViewPosition() {
		mLayoutParams.x = (int) (x - mTouchStartX);
		mLayoutParams.y = (int) (y - mTouchStartY);
		mWindowManager.updateViewLayout(this, mLayoutParams);
	}

}

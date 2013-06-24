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

/*
 * @author wangxin
 * 
 * @date June 24, 2013
 */

public class FloatingView extends LinearLayout {
	private static final String TAG = "FloatingView";
	
	private static final int MUSIC_BAR_MODE_STARDARD = 0;
	private static final int MUSIC_BAR_MODE_MINI = 0;
	private static final int MUSIC_BAR_MODE_FULLSCREEN = 0;
	
	private float mTouchStartX;
	private float mTouchStartY;
	private float mRawX;
	private float mRawY;

	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mLayoutParams;
	private View mContentView = null;
	private boolean mIsFloatingViewRemoved = true;
	
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
				.getApplicationContext()).getWindowManagerLayoutParams();
		LayoutInflater inflater = LayoutInflater.from(getContext());
		mContentView = inflater.inflate(R.layout.test, null);
		addView(mContentView);
		initLayoutParams();
	}

	private void initLayoutParams() {
		// int WindowManager LayoutParams
		mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE; // window
																	// type
		mLayoutParams.format = PixelFormat.RGBA_8888;

		// int WindowManager Flags
		mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */

		mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;

		mLayoutParams.x = 0;
		mLayoutParams.y = 0;

		mLayoutParams.width = 400;
		mLayoutParams.height = 80;
	}

	public void show() {
		if (null != mWindowManager && null != mContentView
				&& mIsFloatingViewRemoved) {
			Log.d(TAG, "show()");
			mWindowManager.addView(this, mLayoutParams);
			mIsFloatingViewRemoved = false;
		}
	}

	public void dismiss() {
		if (null != mWindowManager && null != mContentView) {
			Log.d(TAG, "dismiss()");
			mWindowManager.removeView(this);
			mIsFloatingViewRemoved = true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// default position
		mRawX = event.getRawX();
		mRawY = event.getRawY() - 25; // remove the height of notification bar
		Log.i(TAG, "RawX = " + mRawX + " RawY = " + mRawY);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// catch the touch point
			mTouchStartX = event.getX();
			mTouchStartY = event.getY();

			Log.i(TAG, "startX = " + mTouchStartX + " startY = " + mTouchStartY);

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
		mLayoutParams.x = (int) (mRawX - mTouchStartX);
		mLayoutParams.y = (int) (mRawY - mTouchStartY);
		mWindowManager.updateViewLayout(this, mLayoutParams);
	}

}

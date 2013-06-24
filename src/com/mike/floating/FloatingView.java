package com.mike.floating;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class FloatingView extends ImageView {
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;

	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mLayoutParams;

	public FloatingView(Context context) {
		super(context);
		mWindowManager = (WindowManager) getContext().getApplicationContext()
				.getSystemService("window");

		mLayoutParams = ((FloatingApplication) getContext()
				.getApplicationContext()).getMywmParams();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// default position
		x = event.getRawX();
		y = event.getRawY() - 25; // remove the height of notification bar
		Log.i("a", "currX" + x + "====currY" + y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// catch the touch point
			mTouchStartX = event.getX();
			mTouchStartY = event.getY();

			Log.i("a", "startX" + mTouchStartX + "====startY"
					+ mTouchStartY);

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

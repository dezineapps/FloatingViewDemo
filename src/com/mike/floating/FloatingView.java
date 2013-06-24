package com.mike.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingView extends LinearLayout {
	private Context mContext;
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;

	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mLayoutParams;
	
//	private FloatingView mFloatingView = null;
	

	public FloatingView(Context context) {
		super(context);
		mContext = context;
		mWindowManager = (WindowManager) getContext().getApplicationContext()
				.getSystemService("window");

		mLayoutParams = ((FloatingApplication) getContext()
				.getApplicationContext()).getMywmParams();
		TextView textview = new TextView(getContext());
		textview.setSingleLine(true);
		textview.setText("floating test");
		addView(textview);
		createView();
	}
	
	
	private void createView() {
		mLayoutParams = ((FloatingApplication) mContext.getApplicationContext())
				.getMywmParams();

		//int WindowManager LayoutParams
		mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE; // ����window type
		mLayoutParams.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��

		//int WindowManager Flags
		mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * �����flags���Ե�Ч����ͬ������
		 * ����ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ��
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */

		mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP; // ������������Ͻ�
		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
		mLayoutParams.x = 0;
		mLayoutParams.y = 0;

		// ������ڳ������
		mLayoutParams.width = 40;
		mLayoutParams.height = 40;
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

package com.mike.floating;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FloatingActivity extends Activity {
	/** Called when the activity is first created. */

//	private WindowManager mWindowManager = null;
//	private WindowManager.LayoutParams mLayoutParams = null;
//
	private FloatingView mFloatingView = null;
//	private boolean mIsFloatingViewRemoved = false;
	private FloatingManager mFloatingManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnClick = (Button) findViewById(R.id.click);
		btnClick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Click done!", Toast.LENGTH_SHORT).show();
			}
		});
//		createView();
		mFloatingView = new FloatingView(getApplicationContext());
		mFloatingManager = new FloatingManager(this, mFloatingView);
		mFloatingManager.show();

	}

//	private void createView() {
//		mFloatingView = new FloatingView(getApplicationContext());
//		mWindowManager = (WindowManager) getApplicationContext()
//				.getSystemService("window");
//		mLayoutParams = ((FloatingApplication) getApplication())
//				.getMywmParams();
//
//		/**
//		 * ���¶���WindowManager.LayoutParams��������� ������;�ɲο�SDK�ĵ�
//		 */
//		mLayoutParams.type = LayoutParams.TYPE_PHONE; // ����window type
//		mLayoutParams.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��
//
//		// ����Window flag
//		mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
//				| LayoutParams.FLAG_NOT_FOCUSABLE;
//		/*
//		 * �����flags���Ե�Ч����ͬ������
//		 * ����ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ��
//		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
//		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
//		 */
//
//		mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP; // ������������Ͻ�
//		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
//		mLayoutParams.x = 0;
//		mLayoutParams.y = 0;
//
//		// ������ڳ������
//		mLayoutParams.width = 40;
//		mLayoutParams.height = 40;
//
//		// ��ʾmyFloatViewͼ��
//		mWindowManager.addView(mFloatingView, mLayoutParams);
//	}

	@Override
	protected void onStop() {
		super.onStop();
		if (null != mFloatingManager) {
			mFloatingManager.dismiss();
		}
//		mWindowManager.removeView(mFloatingView);
//		mIsFloatingViewRemoved = true;
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != mFloatingManager) {
			mFloatingManager.show();
		}
//		if (null != mWindowManager && null != mFloatingView
//				&& mIsFloatingViewRemoved) {
//			mWindowManager.addView(mFloatingView, mLayoutParams);
//			mIsFloatingViewRemoved = false;
//		}
	}

}
package com.mike.floating;

import android.app.Application;
import android.view.WindowManager;

public class FloatingApplication extends Application {
	
	/**
	 * ����ȫ�ֱ���
	 * ȫ�ֱ���һ�㶼�Ƚ������ڴ���һ��������������ļ�����ʹ��static��̬����
	 * 
	 * ����ʹ������Application�������ݵķ���ʵ��ȫ�ֱ���
	 * ע����AndroidManifest.xml�е�Application�ڵ����android:name=".MyApplication"����
	 * 
	 */
	private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();

	public WindowManager.LayoutParams getMywmParams(){
		return wmParams;
	}
}

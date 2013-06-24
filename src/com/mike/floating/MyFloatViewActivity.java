package com.mike.floating;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class MyFloatViewActivity extends Activity {
    /** Called when the activity is first created. */
	
	private WindowManager wm=null;
	private WindowManager.LayoutParams wmParams=null;
	
	private MyFloatView myFV=null;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //�������
        createView();
    
    }
    
  
    
    private void createView(){
    	myFV=new MyFloatView(getApplicationContext());
    	myFV.setImageResource(R.drawable.icon);
    	//��ȡWindowManager
    	wm=(WindowManager)getApplicationContext().getSystemService("window");
        //����LayoutParams(ȫ�ֱ�������ز���
    	wmParams = ((MyApplication)getApplication()).getMywmParams();

         /**
         *���¶���WindowManager.LayoutParams���������
         * ������;�ɲο�SDK�ĵ�
         */
        wmParams.type=LayoutParams.TYPE_PHONE;   //����window type
        wmParams.format=PixelFormat.RGBA_8888;   //����ͼƬ��ʽ��Ч��Ϊ����͸��

        //����Window flag
        wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                              | LayoutParams.FLAG_NOT_FOCUSABLE;
        /*
         * �����flags���Ե�Ч����ͬ������
         * ����ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ��
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
        */
        
        
        wmParams.gravity=Gravity.LEFT|Gravity.TOP;   //������������Ͻ�
        //����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
        wmParams.x=0;
        wmParams.y=0;
        
        //������ڳ������
        wmParams.width=40;
        wmParams.height=40;
    
        //��ʾmyFloatViewͼ��
        wm.addView(myFV, wmParams);
    	
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	//�ڳ����˳�(Activity��٣�ʱ������
    	wm.removeView(myFV);
    }
    
   
    
}
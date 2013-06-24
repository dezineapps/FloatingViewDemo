package com.mike.floating;

import android.app.Application;
import android.view.WindowManager;

public class FloatingApplication extends Application {

	private WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams();

	public WindowManager.LayoutParams getMywmParams() {
		return windowManagerLayoutParams;
	}
}

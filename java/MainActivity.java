package com.velato;

import android.app.Activity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public final class MainActivity extends Activity implements SurfaceHolder.Callback {
	private SurfaceView view;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		view = new SurfaceView(this);
		view.getHolder().addCallback(this);
		setContentView(view);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Glue.surfaceCreated(holder.getSurface(), Math.max(1, view.getWidth()), Math.max(1, view.getHeight()));
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Glue.surfaceChanged(holder.getSurface(), Math.max(1, width), Math.max(1, height));
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Glue.surfaceDestroyed(holder.getSurface());
	}

	@Override
	protected void onPause() {
		super.onPause();
		Glue.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Glue.resume();
	}
}

final class Glue {
	static { System.loadLibrary("main"); }

	static native void surfaceCreated(Surface surface, int width, int height);
	static native void surfaceChanged(Surface surface, int width, int height);
	static native void surfaceDestroyed(Surface surface);
	static native void pause();
	static native void resume();
}

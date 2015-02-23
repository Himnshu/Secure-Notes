package com.smochhtech.Securenotes;



import com.smochhtech.Securenotes.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity{
	ImageView splash;
	TextView tx;
	CountDownTimer cmd;
	int SPLASH_TIME_OUT=3000;
	Animation animFadein;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.splash);
	animFadein=AnimationUtils.loadAnimation(this, R.anim.fade);
tx=(TextView)findViewById(R.id.fnt);
	Typeface tf=Typeface.createFromAsset(getAssets(), "Dreamwish.ttf");
	tx.setText("SECURE NOTES");
	tx.setTypeface(tf);
	tx.setAnimation(animFadein);
	cmd=new CountDownTimer(6000,1000) {
		
		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			Intent intr=new Intent(Splash.this,SecurenotesActivity.class);
			startActivity(intr);
			finish();
		}
	}.start();
	/*//splash=(ImageView)findViewById(R.id.img_splash);
	new Handler().postDelayed(new Runnable() {

		/*
		 * Showing splash screen with a timer. This will be useful when you
		 * want to show case your app logo / company
		 

		public void run() {
			// This method will be executed once the timer is over
			// Start your app main activity
			
			Intent i = new Intent(getApplicationContext(), SecurenotesActivity.class);
			startActivity(i);

			// close this activity
			finish();
		}
	}, SPLASH_TIME_OUT);
}*/
}
}

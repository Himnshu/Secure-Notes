package com.smochhtech.Securenotes;


import java.util.ArrayList;

import com.smochhtech.Securenotes.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Profile extends Activity{
	TextView txt_username;
	TextView txt_paassword;
	Typeface face_others ;
	TextView txt_forget;
	EditText edt_username;
	EditText edt_paassword;
	String mobNumber;
	Button login,cancel;
	String username,password;
	ArrayList<String> id;
	 public com.smoketech.DataBase.DataBaseSampleActivity1 db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//overridePendingTransition(R.anim.together, 0);
		
		db = new com.smoketech.DataBase.DataBaseSampleActivity1(getApplicationContext());
		txt_forget=(TextView)findViewById(R.id.txt_forget);
		txt_username=(TextView)findViewById(R.id.txt_user);
		txt_paassword=(TextView)findViewById(R.id.txt_pass);
		edt_username=(EditText)findViewById(R.id.edt_user);
		edt_paassword=(EditText)findViewById(R.id.edt_pass);
		face_others = Typeface.createFromAsset(getAssets(),	"fonts/ArchitectsDaughter.ttf");
		txt_forget.setTypeface(face_others);
		txt_username.setTypeface(face_others);
		txt_paassword.setTypeface(face_others);
		login=(Button)findViewById(R.id.btn_login);
		cancel=(Button)findViewById(R.id.btn_can);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		txt_forget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					// TODO Auto-generated method stub
					//showCustomDialog(view);
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login_Profile.this);
					 
			        // Setting Dialog Title
			        alertDialog.setTitle("Forget Password");

			        // Setting Dialog Message
			        alertDialog.setMessage("Do You Want To Send Password?You Will be charged for it.");

			        // Setting Icon to Dialog
			        alertDialog.setIcon(R.drawable.qu);

			        // Setting Positive "Yes" Button
			        alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            // User pressed YES button. Write Logic Here
			            //Toast.makeText(getApplicationContext(), "You clicked on View" Toast.LENGTH_SHORT).show();
			            fun_GetValue();
						String message = "Your UserName is"+" "+username+".    "+"Your password is"+password;
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(mobNumber, null, message, null, null);
						Toast.makeText(getApplicationContext(), "Message has been sent", 3000).show();
					
			            
		            }
			        });
			        alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            // User pressed YES button. Write Logic Here
			          dialog.dismiss();  }
			        });
			 		        

			        // Showing Alert Message
			        alertDialog.show();
				}
		});
//		txt_forget.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				fun_GetValue();
//				String message = "Your UserName"+username+"\n"+"Your password is"+password;
//				SmsManager smsManager = SmsManager.getDefault();
//				smsManager.sendTextMessage(mobNumber, null, message, null, null);
//				Toast.makeText(getApplicationContext(), "Message has been sent", 3000).show();
////				ContentValues values = new ContentValues(); 
//				values.put("address", "<9779781130>"); 
//				values.put("body", "Test Message"); 
//				getContentResolver().insert(Uri.parse("content://sms/sent"), values);
//
//				
//			}
//		});
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fun_GetValue();
			
			if(edt_username.getText().toString().equals(username)&&edt_paassword.getText().toString().equals(password)){
			Intent intent=new Intent(getApplicationContext(),Notes.class);
			startActivity(intent);
			finish();
			}
			else{
			if(edt_username.getText().toString()!=username&&edt_paassword.getText().toString()!=password){
				Toast.makeText(getApplicationContext(),"check it",2000).show();
				
				}}	}
		});
	}
	private void fun_GetValue() {
		db.open();
		Cursor cur = db.getEmpValues();
		
		cur.moveToFirst();
		
		while (!cur.isAfterLast()) {
		mobNumber=cur.getString(cur.getColumnIndex(db.ColNum));
			username=cur.getString(cur.getColumnIndex(db.ColEmpName));
			password=cur.getString(cur.getColumnIndex(db.ColEmpAge));
			cur.moveToNext();
		}
	//Toast.makeText(getApplicationContext(), ""+" "+username+" "+password+" "+mobNumber, 2000).show();
		db.close();
	}
	
}

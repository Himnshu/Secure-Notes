package com.smochhtech.Securenotes;

import java.io.File;
import java.util.ArrayList;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Notes extends Activity{
	Button add,show,trash;
	TextView text_user;
	ImageView img_pro;
	String userpro,img;
	DataBaseSampleActivity1 db;
	  ArrayList<String>notename=new ArrayList<String>();
	  ArrayList<String>content=new ArrayList<String>();
	  ArrayList<String>time=new ArrayList<String>();
	  ArrayList<String>id=new ArrayList<String>();
	  File img_Scuss;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);
		db=new DataBaseSampleActivity1(getApplicationContext());		
		add=(Button)findViewById(R.id.btn_add);
		show=(Button)findViewById(R.id.btn_show);
		trash=(Button)findViewById(R.id.btn_trash);
		img_pro=(ImageView)findViewById(R.id.imgg_pro);
		text_user=(TextView)findViewById(R.id.txt_loginuser);
		
		fun_GetValue();
		String pro="Welcome"+"  "+userpro;
		text_user.setText(pro);
		//int img2=Integer.parseInt(img);
		img_Scuss = new File(img);
		if (img_Scuss.exists()) {
			 
			Bitmap bitmapnew = BitmapFactory.decodeFile(img_Scuss.getAbsolutePath());
			img_pro.setImageBitmap(bitmapnew);
			//decodeFileDescriptor(img_Scuss.getAbsolutePath());
		}
		//img_pro.setBackgroundResource(img2);
		//overridePendingTransition(R.anim.blink, 0);
		trash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.open();
		    	Cursor cur = db.getNotesValues("1");
		    	if (cur.getCount()==0) {
		    	Toast.makeText(getApplicationContext(), "Empty", 2000).show();	
				}else{
				Intent intent=new Intent(Notes.this,Trash.class);
				startActivity(intent);
				}
				
			}
		});
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),Content.class);
				startActivity(intent);
				
			}
		});
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				fun_GetValue();
				db.open();
		    	Cursor cur = db.getNotesValues("0");
		    	if (cur.getCount()==0) {
		    	Toast.makeText(getApplicationContext(), "Empty", 2000).show();	
				}else {    	
				Intent intent=new Intent(Notes.this,List_Notes.class);
//				intent.putStringArrayListExtra("key", notename);
//				intent.putStringArrayListExtra("key1", content);;
//				intent.putStringArrayListExtra("key2", time);
//				intent.putExtra("key3", id);
				startActivity(intent);
				db.close();
			}}
		});
	}

	private void fun_GetValue() {
		db.open();
		Cursor cur = db.getEmpValues();
		
		cur.moveToFirst();
		
		while (!cur.isAfterLast()) {
		//mobNumber=cur.getString(cur.getColumnIndex(db.ColNum));
			userpro=cur.getString(cur.getColumnIndex(db.ColEmpName));
			img=cur.getString(cur.getColumnIndex(db.ColDept));
			
			cur.moveToNext();
			
			
		}
		//Toast.makeText(getApplicationContext(), ""+img, 5000).show();
		//Toast.makeText(getApplicationContext(), ""+" "+username+" "+password+" "+mobNumber, 2000).show();
		db.close();
	}

}

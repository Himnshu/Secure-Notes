package com.smochhtech.Securenotes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Content extends Activity {
	TextView txt_note, txt_content;
	EditText edt_note, edt_content;
	Button btn_save,btn_cancel;
	Typeface face_others ;
	String c, n;
	public DataBaseSampleActivity1 db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		db = new DataBaseSampleActivity1(getApplicationContext());
		txt_content = (TextView) findViewById(R.id.txt_content);
		edt_content = (EditText) findViewById(R.id.edt_content);
		txt_note = (TextView) findViewById(R.id.txt_note);
		edt_note = (EditText) findViewById(R.id.edt_note);
		btn_cancel=(Button)findViewById(R.id.btn_cancel);
		face_others = Typeface.createFromAsset(getAssets(),	"fonts/ArchitectsDaughter.ttf");
		txt_note.setTypeface(face_others);
		txt_content.setTypeface(face_others);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = edt_note.getText().toString();
				String content = edt_content.getText().toString();
				if (name.equals("") || content.equals("")) {
					Toast.makeText(getApplicationContext(),
							"fill the required fields", 3000).show();
				} else {
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
							.format(Calendar.getInstance().getTime());
					db.open();
					db.insert(name, content, "0", timeStamp);
					db.close();
					Intent intent=new Intent(getApplicationContext(),List_Notes.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

}

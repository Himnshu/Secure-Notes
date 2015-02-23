package com.smochhtech.Securenotes;

import java.util.ArrayList;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Update extends Activity implements OnClickListener{
	TextView txt_note,txt_content;
	EditText edt_note,edt_content;
	Button btn_save,btn_cancel;
	DataBaseSampleActivity1 db;	
	String c,n;
	private String content;
	private String name;
	private int position;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.content);
	db = new DataBaseSampleActivity1(getApplicationContext());
	txt_content=(TextView)findViewById(R.id.txt_content);
	edt_content=(EditText)findViewById(R.id.edt_content);
	txt_note=(TextView)findViewById(R.id.txt_note);
	edt_note=(EditText)findViewById(R.id.edt_note);
	btn_save=(Button)findViewById(R.id.btn_save);
	btn_cancel=(Button)findViewById(R.id.btn_cancel);
	content=getIntent().getStringExtra("key1");
	name=getIntent().getStringExtra("key");
	position=getIntent().getIntExtra("key3", 0);
	edt_note.setText(name);
	edt_content.setText(content);
	btn_cancel.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		finish();	
		}
	});
	btn_save.setOnClickListener(this);

}

public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.btn_save:
		db.open();
		db.updatenotelist( edt_note.getText().toString(),edt_content.getText().toString(), position);
		db.close();
		finish();
		break;

	default:
		break;
	}
}
}

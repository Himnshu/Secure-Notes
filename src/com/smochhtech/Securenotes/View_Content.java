package com.smochhtech.Securenotes;

import java.util.ArrayList;

import com.smochhtech.Securenotes.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class View_Content extends Activity{
	TextView txt_note,txt_content;
	ArrayList<String> name=new ArrayList<String>();
	ArrayList<String> view=new ArrayList<String>();
	public Typeface face,face1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		txt_note=(TextView)findViewById(R.id.txt_view1);
		txt_content=(TextView)findViewById(R.id.txt_nameview);
		view=getIntent().getStringArrayListExtra("key");
		name=getIntent().getStringArrayListExtra("key1");
		int position=getIntent().getIntExtra("key3", 0);
		txt_note.setText(name.get(position));
		txt_content.setText(view.get(position));
		 face1 = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
		 face = Typeface.createFromAsset(getAssets(),"fonts/Dreamwish.ttf");
		 txt_note.setTypeface(face1);
		 txt_content.setTypeface(face);
	}

}

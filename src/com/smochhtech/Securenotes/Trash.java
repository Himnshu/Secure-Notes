package com.smochhtech.Securenotes;

import java.util.ArrayList;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Trash extends Activity{
	ListView notes_list;
	Button view,update,delete;
		ArrayList<String> name=new ArrayList<String>();
		ArrayList<String> content=new ArrayList<String>();
		ArrayList<String> time=new ArrayList<String>();
		ArrayList<String> id=new ArrayList<String>();
		DataBaseSampleActivity1 db;
		
		public void fun_GetValue() {
			db.open();
			Cursor cur = db.getNotesValues("1");
			name.clear();
			content.clear();
			time.clear();
			id.clear();
			cur.moveToFirst();
			
			
			
			while (!cur.isAfterLast()) {
				id.add(cur.getString(cur.getColumnIndex(db.Colsecurenotesid)));
				name.add(cur.getString(cur.getColumnIndex(db.Colsecurenotestname)));
			 content.add(cur.getString(cur.getColumnIndex(db.Colsecurenotescontents)));
			 time.add(cur.getString(cur.getColumnIndex(db.Colsecurenotesdateandtime)));
				cur.moveToNext();
			}
			
			notes_list.setAdapter(new Test(getApplicationContext()));
			db.close();
		}
		
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			fun_GetValue();
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		db = new DataBaseSampleActivity1(getApplicationContext());
		notes_list=(ListView)findViewById(R.id.list);
//		name=getIntent().getStringArrayListExtra("key");
//		content=getIntent().getStringArrayListExtra("key1");
//		time=getIntent().getStringArrayListExtra("key2");
//		id=getIntent().getStringArrayListExtra("key3");
		
		view=(Button)findViewById(R.id.view);
		update=(Button)findViewById(R.id.update);
		delete=(Button)findViewById(R.id.delete);
		
		db=new DataBaseSampleActivity1(getApplicationContext());
				notes_list.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							final int arg2, long arg3) {
						// TODO Auto-generated method stub
						//showCustomDialog(view);
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(Trash.this);
						 
				        // Setting Dialog Title
				        alertDialog.setTitle("Notes...");

				        // Setting Dialog Message
				        alertDialog.setMessage("What You Want To Do?");

				        // Setting Icon to Dialog
				        alertDialog.setIcon(R.drawable.arrow);

				        // Setting Positive "Yes" Button
				      // etural "Cancel" Button
				        alertDialog.setNeutralButton("delete permanently", new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				            // User pressed Cancel button. Write Logic Here
				            	Integer id1=Integer.parseInt(id.get(arg2));

				            	db.open();
					            db.deleteNote( id1);
					            db.close();
					            fun_GetValue();
					         
				            }
				        });

				        // Setting Negative "NO" Button
				        alertDialog.setNegativeButton("Restore", new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				            // User pressed No button. Write Logic Here
				            	Integer id1=Integer.parseInt(id.get(arg2));
				           // Toast.makeText(getApplicationContext(), ""+id1, Toast.LENGTH_SHORT).show();
				            	db.open();
				            db.updatenotedelete("0", id1);
				            db.close();
				            fun_GetValue();
				         
				            }
		  });

				        

				        // Showing Alert Message
				        alertDialog.show();
					}
//					
//					
				});
	}
	class Test extends BaseAdapter{
		Context con;
public Test(Context m) {
	// TODO Auto-generated constructor stub
	con=m;
}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView newtext;
			if(convertView==null)
			{
				newtext=new TextView(con);
			}
			else{
				newtext=(TextView) convertView;
			}
			newtext.setText(name.get(position));
			newtext.setTextSize(34);
			newtext.setTextColor(Color.parseColor("#00FFFF"));
			return newtext;
			
			
		}

}}

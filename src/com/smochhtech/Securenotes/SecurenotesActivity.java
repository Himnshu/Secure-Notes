package com.smochhtech.Securenotes;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecurenotesActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private TextView txt_hdLine;
	
	private Button btn_LogIn;
	private Button btn_Reg;
	
	
	public Typeface face;
	public Typeface face_ForButton;
	
	public DataBaseSampleActivity1 db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		overridePendingTransition(R.anim.slidedown, 0);
        setContentView(R.layout.main);
        
        db = new DataBaseSampleActivity1(getApplicationContext());
        
        face = Typeface.createFromAsset(getAssets(),"fonts/Bleeding_Cowboys.ttf");
        face_ForButton = Typeface.createFromAsset(getAssets(),"fonts/Dreamwish.ttf");
        
        
        txt_hdLine = (TextView)		findViewById(R.id.txt_hdLine);
        
        btn_LogIn = (Button)	findViewById(R.id.btn_Login);
        btn_Reg = (Button)		findViewById(R.id.btn_Reg);
        
        
        
        
        txt_hdLine.setTypeface(face);
        btn_LogIn.setTypeface(face_ForButton);
        btn_Reg.setTypeface(face_ForButton);
        
        
        btn_LogIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),Login_Profile.class);
				startActivity(intent);
				
			}
		});
        btn_Reg.setOnClickListener(this);
        
       
        
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	db.open();
    	Cursor cur = db.getEmpValues();
    	if (cur.getCount() > 0) {
    		btn_Reg.setVisibility(View.GONE);
		}else {
			btn_Reg.setVisibility(View.VISIBLE);
		}
    	db.close();
    }
    
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btn_Reg:
			
			Intent login_intent=new Intent(getApplicationContext(),Registration_Profile.class);
			startActivity(login_intent);
			
			break;
			
			case R.id.btn_Login:
		
			break;

		default:
			break;
		}
		
	}
}
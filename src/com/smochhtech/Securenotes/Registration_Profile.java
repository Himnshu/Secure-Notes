package com.smochhtech.Securenotes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.smochhtech.Securenotes.R;
import com.smoketech.DataBase.DataBaseSampleActivity1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Registration_Profile extends Activity implements OnClickListener {

	private TextView txt_heading, txt_password, txt_secure, txt_setimage,
			txt_username;
	public Typeface face_login, face_others;
	private EditText edt_username, edt_password, edt_confpass;
	private Button btn_submit, btn_cancel;
	private ImageView image_person;
	TextView txt_number;
	EditText edt_num;
	String number;
	private Bitmap photo;
	private File sdImageMainDirectory;

	public DataBaseSampleActivity1 db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_profile);

		db = new DataBaseSampleActivity1(getApplicationContext());

		txt_heading = (TextView) findViewById(R.id.txt_heading);
		txt_username = (TextView) findViewById(R.id.txt_username);
		txt_password = (TextView) findViewById(R.id.txt_password);
		txt_secure = (TextView) findViewById(R.id.txt_confpass);
		txt_setimage = (TextView) findViewById(R.id.txt_setimage);
		edt_num = (EditText) findViewById(R.id.edt_mobno);
		txt_number = (TextView) findViewById(R.id.txt_mobno);

		edt_username = (EditText) findViewById(R.id.edt_username);
		edt_password = (EditText) findViewById(R.id.edt_password);
		edt_confpass = (EditText) findViewById(R.id.edt_confpass);

		image_person = (ImageView) findViewById(R.id.image_person);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);

		face_login = Typeface.createFromAsset(getAssets(),
				"fonts/atmostsphere.ttf");
		face_others = Typeface.createFromAsset(getAssets(),
				"fonts/ArchitectsDaughter.ttf");

		txt_heading.setTypeface(face_login);
		txt_password.setTypeface(face_others);
		txt_secure.setTypeface(face_others);
		txt_setimage.setTypeface(face_others);
		txt_username.setTypeface(face_others);
		btn_submit.setTypeface(face_others);
		btn_cancel.setTypeface(face_others);

		btn_submit.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);

		image_person.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.image_person:
			image_person.setBackgroundResource(0);

			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, 1);

			break;

		case R.id.btn_submit:

			String username = edt_username.getText().toString();
			String password = edt_password.getText().toString();
			String conf_pass = edt_confpass.getText().toString();

			number = edt_num.getText().toString();
			if (username.equals("") || password.equals("")
					|| conf_pass.equals("")) {
				Toast.makeText(getApplicationContext(),
						"Please Enter The Required Fields", 5000).show();
			} else if (edt_num.getText().length() != 10
					|| edt_num.getText().toString().contains("a")
					|| edt_num.getText().toString().contains("b")
					|| edt_num.getText().toString().contains("c")
					|| edt_num.getText().toString().contains("d")
					|| edt_num.getText().toString().contains("e")
					|| edt_num.getText().toString().contains("f")
					|| edt_num.getText().toString().contains("g")
					|| edt_num.getText().toString().contains("h")
					|| edt_num.getText().toString().contains("i")
					|| edt_num.getText().toString().contains("j")
					|| edt_num.getText().toString().contains("k")
					|| edt_num.getText().toString().contains("l")
					|| edt_num.getText().toString().contains("m")
					|| edt_num.getText().toString().contains("n")
					|| edt_num.getText().toString().contains("o")
					|| edt_num.getText().toString().contains("p")
					|| edt_num.getText().toString().contains("q")
					|| edt_num.getText().toString().contains("r")
					|| edt_num.getText().toString().contains("s")
					|| edt_num.getText().toString().contains("t")
					|| edt_num.getText().toString().contains("u")
					|| edt_num.getText().toString().contains("v")
					|| edt_num.getText().toString().contains("w")
					|| edt_num.getText().toString().contains("x")
					|| edt_num.getText().toString().contains("y")
					|| edt_num.getText().toString().contains("z")
					|| edt_num.getText().toString().contains("A")
					|| edt_num.getText().toString().contains("B")
					|| edt_num.getText().toString().contains("C")
					|| edt_num.getText().toString().contains("D")
					|| edt_num.getText().toString().contains("E")
					|| edt_num.getText().toString().contains("F")
					|| edt_num.getText().toString().contains("G")
					|| edt_num.getText().toString().contains("H")
					|| edt_num.getText().toString().contains("I")
					|| edt_num.getText().toString().contains("J")
					|| edt_num.getText().toString().contains("L")
					|| edt_num.getText().toString().contains("M")
					|| edt_num.getText().toString().contains("N")
					|| edt_num.getText().toString().contains("O")
					|| edt_num.getText().toString().contains("P")
					|| edt_num.getText().toString().contains("Q")
					|| edt_num.getText().toString().contains("R")
					|| edt_num.getText().toString().contains("S")
					|| edt_num.getText().toString().contains("T")
					|| edt_num.getText().toString().contains("U")
					|| edt_num.getText().toString().contains("V")
					|| edt_num.getText().toString().contains("W")
					|| edt_num.getText().toString().contains("X")
					|| edt_num.getText().toString().contains("Y")
					|| edt_num.getText().toString().contains("Z")
					|| edt_num.getText().toString().contains("<")
					|| edt_num.getText().toString().contains(".")
					|| edt_num.getText().toString().contains("/")
					|| edt_num.getText().toString().contains(";")
					|| edt_num.getText().toString().contains("'")
					|| edt_num.getText().toString().contains("(")
					|| edt_num.getText().toString().contains(")")
					|| edt_num.getText().toString().contains("_")
					|| edt_num.getText().toString().contains("-")
					|| edt_num.getText().toString().contains("+")
					|| edt_num.getText().toString().contains("=")
					|| edt_num.getText().toString().contains("@")
					|| edt_num.getText().toString().contains("!")
					|| edt_num.getText().toString().contains("#")
					|| edt_num.getText().toString().contains("$")
					|| edt_num.getText().toString().contains("%")
					|| edt_num.getText().toString().contains("^")
					|| edt_num.getText().toString().contains(" ")) {
				Toast.makeText(getApplicationContext(),
						"Mobile numnber must be of 10 digits", 3000).show();

			} else {
				if (password.equals(conf_pass)) {
					fun_SaveImageToGallery(photo);
					db.open();
					db.insert(username, password, "" + sdImageMainDirectory,
							number, null);
					db.close();
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "Does not Match",
							5000).show();
				}
			}
			break;

		case R.id.btn_cancel:
			finish();

			break;

		default:
			break;
		}

	}

	public void fun_SaveImageToGallery(Bitmap outputimg) {
		OutputStream fOut = null;

		try {
			// creating folder Photo Editor in gallery to store photos after
			// adding effects
			File root = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "SecureNotes" + File.separator);
			if (!root.exists())
				root.mkdirs();

			// file name generation
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			String imageFileName = timeStamp + ".jpeg";

			// creating new file in the Photo Editor Folder
			sdImageMainDirectory = new File(root, imageFileName);
			Log.e(getClass().getSimpleName(), "sdImageMainDirectory"
					+ sdImageMainDirectory);
			fOut = new FileOutputStream(sdImageMainDirectory);
		} catch (Exception e) {
			Toast.makeText(this, "Error occured. Please try again later.",
					Toast.LENGTH_SHORT).show();
		}

		// compressing image and then sending and saving in gallery
		try {
			outputimg.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			fOut.flush();
			fOut.close();
			Toast.makeText(this, "Image Saved to Gallery", Toast.LENGTH_SHORT)
					.show();

			// refreshing gallery
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
					Uri.parse("file://"
							+ Environment.getExternalStorageDirectory())));
		} catch (Exception e) {
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Getting image from gallery
		if (resultCode == RESULT_OK && data != null)

		{
			Uri photoUri = data.getData();
			if (photoUri != null) {
				try {
					String[] filePathColumn = { MediaStore.Images.Media.DATA };
					Cursor cursor = getContentResolver().query(photoUri,
							filePathColumn, null, null, null);
					cursor.moveToFirst();
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String filePath = cursor.getString(columnIndex);
					cursor.close();

					photo = BitmapFactory.decodeFile(filePath);

					image_person.setImageBitmap(photo);

				} catch (Exception e) {
				}
			}
		}
	}
}

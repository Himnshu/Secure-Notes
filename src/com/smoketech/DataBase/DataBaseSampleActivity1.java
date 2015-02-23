package com.smoketech.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseSampleActivity1 {

	/** for database */
	static final String DataBaseName = "EmployeDB";

	/** for employee table */
	static final String EmployeTable = "Employees";

	public static final String ColEmpID = "EmpId";
	public static final String ColEmpName = "EmpName";
	public static final String ColEmpAge = "EmpAge";
	public static final String ColDept = "Dept";
	public static final String ColNum = "Num";

	/** for employee table */
	static final String securenotestable = "securenotestb";

	public static final String Colsecurenotesid = "securenotesid";
	public static final String Colsecurenotestname = "securenotestname";
	public static final String Colsecurenotescontents = "securenotescontents";
	public static final String ColTrash = "Trash";
	public static final String Colsecurenotesdateandtime = "securenotesdateandtime";

	public static final int DATABASE_VERSION = 1;

	// private static final String KEY_ROWID = "_id";

	private static final String EMPLOYEE_TABLE_CREATE = "Create table "
			+ EmployeTable
			+
			// "(_id INTEGER UNIQUE," + [old code]
			"(" + ColEmpID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ColEmpName
			+ " VARCHAR(15) ," + ColEmpAge + " VARCHAR(15) ," + ColDept
			+ " VARCHAR(15) ," + ColNum + " VARCHAR(15))";
	
	private static final String SECURE_NOTES_TABLE_CREATE = "Create table "
			+ securenotestable
			+
			// "(_id INTEGER UNIQUE," + [old code]
			"(" + Colsecurenotesid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Colsecurenotestname + " VARCHAR(15) ," + Colsecurenotescontents
			+ " VARCHAR(15) ," + ColTrash
			+ " VARCHAR(15) ," + Colsecurenotesdateandtime + " VARCHAR(15)) ";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DataBaseSampleActivity1(Context ctx) {
		Log.i("test****", "**test***");
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DataBaseName, null, DATABASE_VERSION);
			Log.i("context", "context");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(EMPLOYEE_TABLE_CREATE);
			db.execSQL(SECURE_NOTES_TABLE_CREATE);
			Log.i("************", "table created");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w("tag", "Upgrading database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + EmployeTable);
			onCreate(db);
		}

	};

	public DataBaseSampleActivity1 open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		Log.i("open", "message");
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	// public long insert(Integer empid, String empname, Integer empage, String
	// empdept) {
	public long insert(String empname, String empage, String empdept,
			String number, String non_null) {
		Log.i("**** suruchitest **** ", "*** test ***");
		ContentValues initialValues = new ContentValues();

		// initialValues.put(ColEmpID, empid);
		initialValues.put(ColNum, number);
		initialValues.put(ColEmpName, empname);
		initialValues.put(ColEmpAge, empage);
		initialValues.put(ColDept, empdept);

		// insert into EmployeTable(ColEmpName,ColEmpAge,ColDept) values
		// ("hjvjkd",18,"vndfvhdfk");
		return db.insert(EmployeTable, null, initialValues);
	}

	public long insert(String secure_note_name, String secure_notedetails,String TrashNon,
			String secure_datetime) {
		Log.i("**** suruchitest **** ", "*** test ***");
		ContentValues initialValues = new ContentValues();

		// initialValues.put(ColEmpID, empid);
		initialValues.put(Colsecurenotestname, secure_note_name);
		initialValues.put(Colsecurenotescontents, secure_notedetails);
		initialValues.put(ColTrash, TrashNon);
		initialValues.put(Colsecurenotesdateandtime, secure_datetime);

		// insert into EmployeTable(ColEmpName,ColEmpAge,ColDept) values
		// ("hjvjkd",18,"vndfvhdfk");
		return db.insert(securenotestable, null, initialValues);
	}

	public Cursor getEmpValues() {

		// Select * from EmployeTable;
		Cursor mCursor = db.query(EmployeTable, null, null, null, null, null,
				null);
		return mCursor;
	}

	public Cursor getNotesValues(String chkStatus) {

		// Select * from securetable;
		Cursor nCursor = db.query(securenotestable, null, ColTrash+"="+"'"+chkStatus+"'", null, null,
				null, null);
		return nCursor;
	}

	public boolean deleteEmpList(long rowId) {
		Toast.makeText(context, "deleted", 2000).show();
		return db.delete(EmployeTable, ColEmpID + " = " + rowId, null) > 0;
	}

	public boolean deleteNote(long rowId) {
		Toast.makeText(context, "deleted", 2000).show();
		return db.delete(securenotestable, Colsecurenotesid + " = " + rowId,
				null) > 0;
	}

	public boolean updateEmplist(String empname, Integer empage,
			String empdept, Integer rowid) {

		ContentValues initialValues = new ContentValues();
		Log.i("#####  " + rowid, "" + empname + " " + empage + " " + empdept);

		// initialValues.put(ColEmpID, rowid);
		initialValues.put(ColEmpName, empname);
		initialValues.put(ColEmpAge, empage);
		initialValues.put(ColDept, empdept);

		try {
			int b = db.update(EmployeTable, initialValues, ColEmpID + " = "
					+ rowid, null);
			Log.i("update", "up " + rowid + "  ddd   " + b);
			return true;
		} catch (Exception e) {
			Log.d("asdfasdfsadfasdf", "_--___--__--_=-_");
			return false;
		}
	}

	public boolean updatenotelist(String notename, String content, Integer rowid) {

		ContentValues initialValues = new ContentValues();
		Log.i("#####  " + rowid, "" + notename + " " + " " + content);

		initialValues.put(Colsecurenotestname, notename);
		initialValues.put(Colsecurenotescontents, content);

		try {
			int b = db.update(securenotestable, initialValues, Colsecurenotesid
					+ " = " + rowid, null);
			Log.i("update", "up " + rowid + "  ddd   " + b);
			return true;
		} catch (Exception e) {
			Log.d("asdfasdfsadfasdf", "_--___--__--_=-_");
			return false;
		}
	}
	
	
	public boolean updatenotedelete(String status, Integer rowid) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(ColTrash, status);

		try {
			int b = db.update(securenotestable, initialValues, Colsecurenotesid
					+ " = " + rowid, null);
			Log.i("update", "up " + rowid + "  ddd   " + b);
			return true;
		} catch (Exception e) {
			Log.d("asdfasdfsadfasdf", "_--___--__--_=-_");
			return false;
		}
	}
}

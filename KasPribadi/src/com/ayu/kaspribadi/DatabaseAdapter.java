package com.ayu.kaspribadi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	private static final String DB_NAME = "kaspribadi";
	private static final int DB_VERSION = 1;

	public static final String CREATE_TABLE1 = "create table pengeluaran"
			+ "(_id integer primary key autoincrement, tanggal text," +
			    "label text, nominal integer,deskripsi text);";
	public static final String CREATE_TABLE2 = "create table pemasukan"
			+ "(_id integer primary key autoincrement, tanggal text," +
				"label text, nominal integer, deskripsi text);";
	public static final String CREATE_TABLE4 = "create table sisa_uang(sisa integer DEFAULT 0)";
	
	private SQLiteDatabase db;
	private final Context context;
	private DBhelper dbhelper;

	public DatabaseAdapter(Context ctx) {
		context = ctx;
		dbhelper = new DBhelper(context, DB_NAME, null, DB_VERSION);
	}

	public DatabaseAdapter open() throws SQLException {
		db = dbhelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public Cursor getAllPengeluaran() {
		return db.query("pengeluaran", new String[] { "_id","tanggal","label", "nominal", "deskripsi" }, null,
				null, null, null, null);
	}
	
	public Cursor getAllPemasukan() {
		return db.query("pemasukan", new String[] { "_id","tanggal","label", "nominal", "deskripsi" }, null,
				null, null, null, null);
	}
	
	

	public long insertPengeluaran(String tanggal, String label, int nominal, String deskripsi) {
		ContentValues cv = new ContentValues();
		cv.put("tanggal",tanggal);
		cv.put("label", label);
		cv.put("nominal", nominal);
		cv.put("deskripsi", deskripsi);
		return db.insert("pengeluaran", null, cv);
	}
	
	public long insertPemasukan(String tanggal, String label, int nominal, String deskripsi) {
		ContentValues cv = new ContentValues();
		cv.put("tanggal",tanggal);
		cv.put("label", label);
		cv.put("nominal", nominal);
		cv.put("deskripsi", deskripsi);
		return db.insert("pemasukan", null, cv);
	}
	

	public void insertSisa(int sisa) {
		ContentValues cv = new ContentValues();
		cv.put("sisa", sisa);
		db.insert("sisa_uang", null, cv);
	}

	public Cursor getSisaUang() {
		return db.query("sisa_uang", new String[] { "sisa" }, null, null, null,
				null, null);
	}
	
	public int getTotalPengeluaran(){
		Cursor c = db.rawQuery("SELECT SUM(nominal) FROM pengeluaran", null);
		c.moveToFirst();
		return c.getInt(0);
	}
	
	
	public int getTotalPemasukan(){
		Cursor c = db.rawQuery("SELECT SUM(nominal) FROM pemasukan", null);
		c.moveToFirst();
		return c.getInt(0);
	}
	
	public void deleteData(String table){
		db.execSQL("DELETE FROM "+table);
	}
	
	public void updateSisaUang(int values){
		db.execSQL("UPDATE sisa_uang SET sisa= "+values);
	}

	private static class DBhelper extends SQLiteOpenHelper {

		public DBhelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("DROP TABLE IF EXISTS pengeluaran");
			db.execSQL("DROP TABLE IF EXISTS pemasukan");
			db.execSQL("DROP TABLE IF EXISTS sisa_uang");
			db.execSQL(CREATE_TABLE1);
			db.execSQL(CREATE_TABLE2);
			db.execSQL(CREATE_TABLE4);
			db.execSQL("INSERT INTO sisa_uang VALUES (0)");
			insertPengeluaran(db, " ", " ", 0, " ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}
		
		public long insertPengeluaran(SQLiteDatabase db, String tanggal, String label, int nominal, String deskripsi) {
			ContentValues cv = new ContentValues();
			cv.put("tanggal",tanggal);
			cv.put("label", label);
			cv.put("nominal", nominal);
			cv.put("deskripsi", deskripsi);
			return db.insert("pengeluaran", null, cv);
		}

	}

}

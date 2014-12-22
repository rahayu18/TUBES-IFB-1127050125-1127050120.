package com.ayu.kaspribadi;

import android.app.ActionBar;
import com.ayu.kaspribadi.DatabaseAdapter;
import com.ayu.kaspribadi.DateTime;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addKeluar extends Activity implements OnItemSelectedListener,
		OnClickListener {
	Button btnTambahOut;
	EditText textNominal;
	EditText textTanggal;
	EditText textLabel;
	EditText textDeskripsi;
	SQLiteDatabase db;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tambah);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Pengeluaran");
		actionBar.setDisplayHomeAsUpEnabled(false);

		// call database helper / , createTable() method

		// init edittext
		textNominal = (EditText) findViewById(R.id.textNominal);
		textLabel = (EditText) findViewById(R.id.textLabel);
		textDeskripsi = (EditText) findViewById(R.id.textDeskripsi);

		// setonclick event
		btnTambahOut = (Button) findViewById(R.id.btnTambahIn);
		btnTambahOut.setOnClickListener(this);

	}

	// return int harga from edittext @textHarga
	private String getNominal() {
		return textNominal.getText().toString();
	}
	
	private String getLabel() {
		return textLabel.getText().toString();
	}
	
	private String getDeskripsi() {
		return textDeskripsi.getText().toString();
	}


	public void displayResult(Cursor c) {
		Toast.makeText(
				this,
				"label : " + c.getString(3)+ "nominal : " + c.getString(4)
				+ "deskripsi : " + c.getString(5), Toast.LENGTH_LONG)
		.show();
	}

	@Override
	public void onClick(View v) {
		DateTime date = new DateTime();
		if (this.getNominal().isEmpty()) {
			Toast.makeText(this, "Masukan Nominal!", Toast.LENGTH_LONG).show();
		} else if (this.getLabel().isEmpty()) {
			Toast.makeText(this, "Masukan Label!", Toast.LENGTH_LONG).show();
		} else if (this.getDeskripsi().isEmpty()) {
			Toast.makeText(this, "Masukan Deskripsi!", Toast.LENGTH_LONG).show();
		} else {

			try {
				DatabaseAdapter dba = new DatabaseAdapter(this);
				dba.open();
				dba.insertPengeluaran(date.getTanggal(), 
						getLabel(), Integer.parseInt(getNominal()), getDeskripsi());
				//get sisa_uang
				Cursor c2 = dba.getSisaUang();
				c2.moveToFirst();
				int sisa_uang = c2.getInt(0);
				sisa_uang = sisa_uang - Integer.parseInt(getNominal());
				//update sisa_uang
				dba.updateSisaUang(sisa_uang);
				startActivity(new Intent(this,LapPengeluaran.class));
				this.finish();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				Log.e("numberexc", e.getMessage());
				Toast.makeText(this, "number e", Toast.LENGTH_LONG).show();
			} catch (SQLException e) {
				e.printStackTrace();
				Log.e("sqlerr", e.getMessage());
				Toast.makeText(this, "sql e", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}

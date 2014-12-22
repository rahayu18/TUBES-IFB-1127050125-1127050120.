package com.ayu.kaspribadi;

import com.ayu.kaspribadi.DatabaseAdapter;
import com.ayu.kaspribadi.DateTime;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addMasuk extends Activity implements OnItemSelectedListener,
		OnClickListener {
	private Button btnTambahIn;
	private EditText textNominalIn;
	private EditText textLabelIn;
	private EditText textDeskripsiIn;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tambah);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Pemasukan");
		actionBar.setDisplayHomeAsUpEnabled(false);

		btnTambahIn = (Button) findViewById(R.id.btnTambahIn);
		btnTambahIn.setOnClickListener(this);

		textNominalIn = (EditText) findViewById(R.id.textNominal);
		textLabelIn = (EditText) findViewById(R.id.textLabel);
		textDeskripsiIn = (EditText) findViewById(R.id.textDeskripsi);
	}

	public String getNominalIn() {
		return textNominalIn.getText().toString();
	}
	
	public String getLabelIn() {
		return textLabelIn.getText().toString();
	}
	
	public String getDeskripsiIn() {
		return textDeskripsiIn.getText().toString();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		DateTime date = new DateTime();
		if (this.getNominalIn().isEmpty()) {
			Toast.makeText(this, "Masukan Nominal!", Toast.LENGTH_LONG).show();
		} else if (this.getLabelIn().isEmpty()) {
			Toast.makeText(this, "Masukan Label!", Toast.LENGTH_LONG).show();
		} else if (this.getDeskripsiIn().isEmpty()) {
			Toast.makeText(this, "Masukan Deskripsi!", Toast.LENGTH_LONG).show();
		} else {

			try {
				DatabaseAdapter dba = new DatabaseAdapter(this);
				dba.open();
				dba.insertPemasukan(date.getTanggal(), 
						getLabelIn(), Integer.parseInt(getNominalIn()), getDeskripsiIn());
				
				//get sisa_uang
				Cursor c = dba.getSisaUang();
				c.moveToFirst();
				int sisa_uang = c.getInt(0);
				sisa_uang = sisa_uang + Integer.parseInt(getNominalIn());
				//update sisa_uang
				dba.updateSisaUang(sisa_uang);
				dba.close();
				startActivity(new Intent(this,LapPemasukan.class));
				this.finish();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
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

	public void displayResult(Cursor c) {
		
	}
}

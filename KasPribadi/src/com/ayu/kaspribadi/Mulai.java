package com.ayu.kaspribadi;

import com.ayu.kaspribadi.DatabaseAdapter;
import com.ayu.kaspribadi.DateTime;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Mulai extends Activity implements OnClickListener {
	private Button btnMasuk;
	private Button btnKeluar;
	private Button btnLapMasuk;
	private Button btnLapKeluar;
	private TextView textHari;
	private TextView textSisaUang;
	private TextView textPengeluaran;
	private TextView textPemasukan; 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mulai);
		
		//setonclick event
		btnMasuk = (Button)findViewById(R.id.btnMasuk);
		btnMasuk.setOnClickListener(this);
		
		btnKeluar = (Button)findViewById(R.id.btnKeluar);
		btnKeluar.setOnClickListener(this);
		
		btnLapMasuk =(Button)findViewById(R.id.btnLapMasuk);
		btnLapMasuk.setOnClickListener(this);
		
		btnLapKeluar =(Button)findViewById(R.id.btnLapKeluar);
		btnLapKeluar.setOnClickListener(this);
		
		textHari = (TextView)findViewById(R.id.textHari);
		textSisaUang = (TextView)findViewById(R.id.textSisaUang);
		
		
		//textview pengeluaran & pemasukan
		textPengeluaran = (TextView)findViewById(R.id.textPengeluaran);
		textPemasukan = (TextView)findViewById(R.id.textPemasukan);
		
		initStartView();
	}
	
	public void onResume(){
		super.onResume();
		initStartView();
	}
	
	public void initStartView(){
		DateTime date = new DateTime();
		DatabaseAdapter dba = new DatabaseAdapter(this);
		dba.open();
		
		Cursor c = dba.getSisaUang();
		c.moveToFirst();
		c.getString(0);
		
		//set text hari & tanggal
		textHari.setText(date.getHari());
		
		textSisaUang.setText("Rp. "+String.format("%,d", c.getInt(0)).replace(",", "."));
		//set text pengeluaran
		textPengeluaran.setText("Rp. "+String.format("%,d", dba.getTotalPengeluaran()).replace(",", "."));
		
		//set text pemasukan
		textPemasukan.setText("Rp. "+String.format("%,d",dba.getTotalPemasukan()).replace(",","."));
		
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMasuk:
			startActivity(new Intent(this,addMasuk.class));
			break;
		
		case R.id.btnKeluar:
			startActivity(new Intent(this,addKeluar.class));
			break;
		
		case R.id.btnLapMasuk:
			startActivity(new Intent(this,LapPemasukan.class));
			break;
		
		case R.id.btnLapKeluar:
			startActivity(new Intent(this,LapPengeluaran.class));
			break;

		default:
			break;
		}
	}
}

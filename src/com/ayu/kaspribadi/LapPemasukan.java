package com.ayu.kaspribadi;

import android.app.ActionBar;
import com.ayu.kaspribadi.DatabaseAdapter;
import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LapPemasukan extends Activity{
	private View view;
	private ViewGroup parent;
	private TextView lapTanggal;
	private TextView lapLabel;
	private TextView lapNominal;
	private TextView lapDeskripsi;
	private TextView textPemasukan; 
	private ImageView logo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laporan);
        
        ActionBar actionBar = getActionBar();
		actionBar.setTitle("Laporan Pemasukan");
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		//textview pemasukan
		textPemasukan = (TextView)findViewById(R.id.textPemasukan);
		initStartView();
        
        parent = (ViewGroup)findViewById(R.id.linearParentIn);
        
        //init textview
        DatabaseAdapter dba = new DatabaseAdapter(this);
        dba.open();
        
        try {
			Cursor c = dba.getAllPemasukan();
			if (c.moveToFirst()) {
				do {
					view = LayoutInflater.from(getBaseContext()).inflate(R.layout.row, null);
					lapTanggal = (TextView)view.findViewById(R.id.textLapTanggal);
				    lapLabel = (TextView)view.findViewById(R.id.textLapJenis);
				    lapNominal = (TextView)view.findViewById(R.id.textLapHarga);
				    lapDeskripsi = (TextView)view.findViewById(R.id.textLapKeterangan);
				    logo = (ImageView)view.findViewById(R.id.img_logo);
				    Resources res = getResources(); /** from an Activity */
					logo.setImageDrawable(res.getDrawable(R.drawable.ic_pemasukan));
					
				    lapTanggal.setText(""+c.getString(1));
					lapLabel.setText(""+c.getString(2));
					lapNominal.setText("Rp. "+String.format("%,d", c.getInt(3)).replace(",", "."));
					lapDeskripsi.setText(""+c.getString(4));
					
					parent.addView(view);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("list", e.getMessage());
		}
        
        dba.close();
        
    }
	
	public void initStartView(){
		DateTime date = new DateTime();
		DatabaseAdapter dba = new DatabaseAdapter(this);
		dba.open();
		
		//set text pemasukan
		textPemasukan.setText("Rp. "+String.format("%,d",dba.getTotalPemasukan()).replace(",","."));
		
	}
}

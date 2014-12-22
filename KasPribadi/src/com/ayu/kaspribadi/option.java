package com.ayu.kaspribadi;

import android.app.ActionBar;
import com.ayu.kaspribadi.DatabaseAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class option extends Activity implements OnClickListener {
	private Button btnOptDel;
	private Button btnAbout;
	DatabaseAdapter db = new DatabaseAdapter(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Pilihan");
		actionBar.setDisplayHomeAsUpEnabled(false);

		btnOptDel = (Button) findViewById(R.id.btnOptDel);
		btnAbout = (Button) findViewById(R.id.btnOptAbout);

		btnOptDel.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOptDel:
			popUp();
			break;

		case R.id.btnOptAbout:
			startActivity(new Intent(this,about.class));
			break;

		default:
			break;
		}
	}
	
	public void popUp(){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
					db.open();
					db.deleteData("pengeluaran");
					db.deleteData("pemasukan");
					db.deleteData("sisa_uang");
					db.insertSisa(0);
					db.insertPengeluaran(" ", " ", 0, " ");
					
					popUpOk();
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
		    }
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Hapus seluruh data?").setPositiveButton("Yes", dialogClickListener)
		    .setNegativeButton("No", dialogClickListener).show();
	}
	
	public void popUpOk(){
		new AlertDialog.Builder( this )
		.setMessage( "Data berhasil dihapus" )
		.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.d( "AlertDialog", "Positive" );
			}
		})
		.show();
	}
	
}

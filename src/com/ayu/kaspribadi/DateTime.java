package com.ayu.kaspribadi;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime {
	private String namaHari[]={"Minggu","Senin","Selasa","Rabu","Kamis"
			,"Jum'at","Sabtu"
	};
	
	GregorianCalendar cal = new GregorianCalendar();
	int tanggal = cal.get(Calendar.DATE);
	int bulan = cal.get(Calendar.MONTH) + 1;
	int tahun = cal.get(Calendar.YEAR);
	int jam = cal.get(Calendar.HOUR_OF_DAY);
	int menit = cal.get(Calendar.MINUTE);
	int minggu = cal.get(Calendar.WEEK_OF_MONTH);
	int hari = cal.get(Calendar.DAY_OF_WEEK);

	public String getTanggal() {
		return tanggal + "/" + bulan + "/" + tahun;
	}

	public String getWaktu() {
		if (menit < 10) {
			return jam + ":0" + menit;
		} else
			return jam + ":" + menit;
	}
	
	public String getHari(){
		return namaHari[hari-1]+", "+getTanggal();
	}

	public int getMinggu() {
		return minggu;
	}
}

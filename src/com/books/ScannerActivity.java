package com.books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerActivity extends Activity{

	//private Button scanBtn;
	public String scanresult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner);
		//retrieve scan button and listen for clicks
		//scanBtn = (Button)findViewById(R.id.scannerActivity_btn);
		//scanBtn.setOnClickListener(this);
		
		
		//instantiate ZXing integration class
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		//start scanning
		scanIntegrator.initiateScan();

	}


	/*public void onClick(View v){
		//check for scan button
		if(v.getId()==R.id.scannerActivity_btn){
			//instantiate ZXing integration class
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			//start scanning
			scanIntegrator.initiateScan();
		}
	}*/
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			//String scanFormat = scanningResult.getFormatName();
			//Log.v("SCAN", "content: "+scanContent+" - format: "+scanFormat);
			
			Intent resultintent = new Intent();
			resultintent.putExtra("scanresult", scanContent);
			
			setResult(20, resultintent);
			
			
			
			
			finish();
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No book scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}

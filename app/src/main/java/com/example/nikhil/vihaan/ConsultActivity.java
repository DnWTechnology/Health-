package com.example.nikhil.vihaan;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

public class ConsultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        if (ContextCompat.checkSelfPermission(ConsultActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ConsultActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
        PaytmPGService Service = PaytmPGService.getStagingService();
        HashMap<String, String> paramMap = new HashMap<String,String>();
        paramMap.put( "MID" , "jiFswe09569048044799");
// Key in your staging and production MID available in your dashboard
        paramMap.put( "ORDER_ID" , "order1");
        paramMap.put( "CUST_ID" , "cust123");
        paramMap.put( "MOBILE_NO" , "8826879948");
        paramMap.put( "EMAIL" , "username@emailprovider.com");
        paramMap.put( "CHANNEL_ID" , "WAP");
        paramMap.put( "TXN_AMOUNT" , "100.12");
        paramMap.put( "WEBSITE" , "WEBSTAGING");
// This is the staging value. Production value is available in your dashboard
        paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
// This is the staging value. Production value is available in your dashboard
        paramMap.put( "CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=order1");
        //paramMap.put( "CHECKSUMHASH" , "gtxImvRY8Hw2wQx1");
        PaytmOrder Order = new PaytmOrder(paramMap);
        Service.initialize(Order, null);


        Service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {
                Toast.makeText(ConsultActivity.this, ""+inErrorMessage, Toast.LENGTH_SHORT).show();
               
            }
            public void onTransactionResponse(Bundle inResponse) {
                Toast.makeText(ConsultActivity.this, ""+inResponse.toString(), Toast.LENGTH_SHORT).show();
                Log.d("error", "onTransactionResponse: "+inResponse.toString());
            }
            public void networkNotAvailable() {
                Toast.makeText(ConsultActivity.this, "No Network", Toast.LENGTH_SHORT).show();
            }
            public void clientAuthenticationFailed(String inErrorMessage) {
                Toast.makeText(ConsultActivity.this, ""+inErrorMessage, Toast.LENGTH_SHORT).show();
            }
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                Toast.makeText(ConsultActivity.this, ""+inErrorMessage, Toast.LENGTH_SHORT).show();
            }
            public void onBackPressedCancelTransaction() {
                Toast.makeText(ConsultActivity.this, "onBackPressed", Toast.LENGTH_SHORT).show();
            }
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                Toast.makeText(ConsultActivity.this, ""+inErrorMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }
}

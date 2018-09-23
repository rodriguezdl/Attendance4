package com.example.daniel.attendance4;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.nio.charset.Charset;

public class NFC extends AppCompatActivity {

    NfcManager manager;
    NfcAdapter adapter;
    NdefMessage message;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNFC2();
            }
        });
        try
        {
            adapter = NfcAdapter.getDefaultAdapter(this);
        }
        catch (Error e)
        {

        }
        //user = new NdefRecord();
        //mail = new NdefRecord(new byte[2]);
        String[] data = {"walker", "vincent"};
        message = new NdefMessage(createRecords( data ));
        adapter.setNdefPushMessage(message, this);



    }

    public void openNFC2(){
        Intent intent = new Intent(this, NFC_Receiver.class);
        startActivity(intent);
    }

    public NdefRecord[] createRecords(String[] messages) {

        NdefRecord[] records = new NdefRecord[messages.length];

        for (int i = 0; i < messages.length; i++){

            byte[] payload = messages[i].
                    getBytes(Charset.forName("UTF-8"));

            NdefRecord record = new NdefRecord(
                    NdefRecord.TNF_WELL_KNOWN,  //Our 3-bit Type name format
                    NdefRecord.RTD_TEXT,        //Description of our payload
                    new byte[0],                //The optional id for our Record
                    payload);                   //Our payload for the Record

            records[i] = record;
        }
        return records;
    }

}

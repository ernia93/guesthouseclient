package ch.ffhs.pa5.guest_house_client;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClick();
            }
        });
    }

    // handling ACTION_TAG_DISCOVERED action from intent:
    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String uid = this.ByteArrayToHexString(getIntent().getByteArrayExtra(NfcAdapter.EXTRA_ID));
            String outStr = "Tag UID: " + uid;
            ((TextView)findViewById(R.id.text)).setText(outStr);
        }
    }

    private void buttonClick() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    // Converting byte[] to hex string:
    private String ByteArrayToHexString(byte [] inarray) {
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out = "";

        for(int j = 0 ; j < inarray.length ; ++j)
        {
            int in = (int) inarray[inarray.length - 1 - j] & 0xff;
            out += hex[(in >> 4) & 0x0f];
            out += hex[in & 0x0f];
        }

        return out;
    }
}
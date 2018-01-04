package esaka.qrcode_reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = (Button)findViewById(R.id.delete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //処理を書く
                Log.v("event", "push button!");
                qr_reader();
            }
        });
        qr_reader();
    }

    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        super.onActivityResult(requestCode, resultcode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultcode, data);
        if(scanResult != null){
            Toast.makeText(this, scanResult.getContents(), Toast.LENGTH_LONG).show();
            TextView result_text = (TextView)findViewById(R.id.result_text);
            result_text.setText(scanResult.getContents());
        }
    }

    void qr_reader() {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setCaptureActivity(CaptureActivityAnyOrientation.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }
}

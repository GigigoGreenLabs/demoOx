package demoox.gigigo.com.demoox;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gigigo.orchextra.Orchextra;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListeners();
    }

    private Button button,button2,button3;
    private EditText txtKey, txtSecret;

    private void getViews() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        txtKey = (EditText) findViewById(R.id.txtKey);
        txtKey.setText(App.preferencesImp.getKey());
        txtSecret = (EditText) findViewById(R.id.txtSecret);
        txtSecret.setText(App.preferencesImp.getSecret());
    }

    private void setListeners() {
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Orchextra.stop();
            Orchextra.changeCredentials(txtKey.getText().toString(),txtSecret.getText().toString());
            App.preferencesImp.setOx_key(txtKey.getText().toString());
            App.preferencesImp.setOx_secret(txtSecret.getText().toString());
            //Orchextra.start();

        }

        if (v.getId() == R.id.button2) {
            Orchextra.startScannerActivity();
        }
        if (v.getId() == R.id.button3) {
            Orchextra.startImageRecognition();
        }
    }
}

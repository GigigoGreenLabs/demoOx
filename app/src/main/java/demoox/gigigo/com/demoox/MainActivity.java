package demoox.gigigo.com.demoox;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gigigo.orchextra.Orchextra;
import com.github.pedrovgs.lynx.LynxActivity;
import com.github.pedrovgs.lynx.LynxConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListeners();
    }

    private Button button,button2,button3,btnlogcat;
    private EditText txtKey, txtSecret;

    private void getViews() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        btnlogcat=(Button)findViewById(R.id.btnLogcat);


        txtKey = (EditText) findViewById(R.id.txtKey);
        txtKey.setText(App.preferencesImp.getKey());
        txtSecret = (EditText) findViewById(R.id.txtSecret);
        txtSecret.setText(App.preferencesImp.getSecret());
    }

    private void setListeners() {
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        btnlogcat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Orchextra.stop();
            Orchextra.changeCredentials(txtKey.getText().toString(),txtSecret.getText().toString());
            App.preferencesImp.setOx_key(txtKey.getText().toString());
            App.preferencesImp.setOx_secret(txtSecret.getText().toString());
            Orchextra.start();

        }

        if (v.getId() == R.id.button2) {
            Orchextra.startScannerActivity();
        }
        if (v.getId() == R.id.button3) {
            Orchextra.startImageRecognition();
        }
        if (v.getId() == R.id.btnLogcat) {
            openLynxActivity();
        }
    }

    private void openLynxActivity() {
        LynxConfig lynxConfig = new LynxConfig();
        lynxConfig.setMaxNumberOfTracesToShow(4000)
                .setFilter("okhtt");

        Intent lynxActivityIntent = LynxActivity.getIntent(this, lynxConfig);
        startActivity(lynxActivityIntent);
    }
}

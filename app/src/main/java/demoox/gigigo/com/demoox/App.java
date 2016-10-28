package demoox.gigigo.com.demoox;

import android.app.Application;
import android.support.designox.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.gigigo.orchextra.CustomSchemeReceiver;
import com.gigigo.orchextra.Orchextra;
import com.gigigo.orchextra.OrchextraBuilder;
import com.gigigo.orchextra.OrchextraCompletionCallback;
import com.gigigo.orchextra.OrchextraLogLevel;
import com.gigigo.vuforiaimplementation.ImageRecognitionVuforiaImpl;

//import communicator.gigigo.com.actionfactory.ActionFactory;

/**
 * Created by nubor on 30/09/2016.
 */
public class App extends Application implements CustomSchemeReceiver {

    //todo set credenciales del projecto double pushtest
    public static String API_KEY = "3805de10dd1b363d3030456a86bf01a7449f4b4f";
    public static String API_SECRET = "2f15ac2b9d291034a2f66eea784f9b3be6e668e6";


    public static PreferencesImp preferencesImp;
    public static final String SENDER_ID = "Your_Sender_ID";//if is not valid sender id, orchextra disabled push receive(only inform for using pushnotifications)

    public static OrchextraCompletionCallback orchextraCompletionCallback;

    @Override
    public void onCreate() {
        super.onCreate();

        orchextraCompletionCallback = new OrchextraCompletionCallback() {
            @Override
            public void onSuccess() {
                Log.d("APP", "onSuccess");

//                new AlertDialog.Builder(this)
//                        .setTitle("SDK onSuccess")
//                        .setMessage("Succesfull")
//                        .show();
            }

            @Override
            public void onError(String s) {
                Log.d("APP", "onError: " + s);
//                new AlertDialog.Builder(this)
//                        .setTitle("SDK onError")
//                        .setMessage(s)
//                        .show();
            }

            @Override
            public void onInit(String s) {
                Log.d("APP", "onInit: " + s);

                //                new AlertDialog.Builder(this)
                //                        .setTitle("SDK onInit")
                //                        .setMessage(s)
                //                        .show();
            }
        };

        initOrchextra();

    }

    public void initOrchextra() {

        preferencesImp = new PreferencesImp(this);

        API_KEY = preferencesImp.getKey();
        API_SECRET = preferencesImp.getSecret();

        Toast.makeText(this,"Ini con ApiKey:" + API_KEY + "\n ApiSecret:"+API_SECRET, Toast.LENGTH_LONG).show();

        OrchextraBuilder builder = new OrchextraBuilder(this)
                .setApiKeyAndSecret(API_KEY, API_SECRET)
                .setLogLevel(OrchextraLogLevel.NETWORK)
                .setOrchextraCompletionCallback(orchextraCompletionCallback)
                .setImageRecognitionModule(new ImageRecognitionVuforiaImpl())
                .setGcmSenderId(SENDER_ID);

        //init Orchextra with builder configuration
        Orchextra.initialize(builder);

        //your can re set custom Scheme in other places(activities,services..)
        Orchextra.setCustomSchemeReceiver(this);

        //start Orchextra running, you can call stop() if you need
        Orchextra.start(); //for only one time, each time you start Orchextra get orchextra project configuration is call


    }


    @Override
    public void onReceive(String scheme) {
        Log.d("APP", "Scheme: " + scheme);
        new AlertDialog.Builder(this)
                .setTitle("Custom Scheme onReceive")
                .setMessage(scheme)
                .show();
    }
}




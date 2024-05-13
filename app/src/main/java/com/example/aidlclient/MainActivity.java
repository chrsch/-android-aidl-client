package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aidlserver.IAIDLColorInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    IAIDLColorInterface IAIDLColorService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IAIDLColorService = IAIDLColorInterface.Stub.asInterface(iBinder);
            Log.d(TAG, "Remote Color Service connected.");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = new Intent("IAIDLColorService");
        intent.setPackage("com.example.aidlserver");

        bindService(intent, mConnection, BIND_AUTO_CREATE);
        Log.d(TAG, "bindservice called");

        Button b = findViewById((R.id.button));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // If the binService was not successful the service obj will be null... log error and return doing nothing.
                    if( IAIDLColorService == null ){
                        Log.e(TAG, "IAIDLColorService is null!");
                        return;
                    }
                    int color = IAIDLColorService.getColor();
                    b.setBackgroundColor(color);
                } catch (RemoteException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }
}
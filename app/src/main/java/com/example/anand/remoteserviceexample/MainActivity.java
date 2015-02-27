package com.example.anand.remoteserviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    Messenger myService = null;
    boolean isBound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(getApplicationContext(),RemoteService.class);
        bindService(intent,connetction, Context.BIND_AUTO_CREATE);

    }
    public void sendMessage(View view)
    {
        if (!isBound) return;

        Message msg = Message.obtain();

        Bundle bundle = new Bundle();
        bundle.putString("MyString", "Message Received");

        msg.setData(bundle);

        try {
            myService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
private ServiceConnection connetction = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
             myService=new Messenger(service);
              isBound=true;

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
              myService=null;
              isBound=false;
    }
};

}

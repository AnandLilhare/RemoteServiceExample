package com.example.anand.remoteserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;


/**
 * Created by Anand on 1/23/2015.
 */
public class RemoteService extends Service {

  final Messenger message=new Messenger(new IncomingHandler());
    public RemoteService()
    {

    }

    class IncomingHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle=msg.getData();
            String dataString=bundle.getString("MyString");
            Toast.makeText(getApplicationContext(),dataString,Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return message.getBinder();
    }
}

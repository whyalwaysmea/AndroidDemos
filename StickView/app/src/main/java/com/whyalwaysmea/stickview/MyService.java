package com.whyalwaysmea.stickview;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    class DownloadBinder extends Binder {
        public void start() {
            System.out.println("start");
        }

        public void end() {
            System.out.println("end");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("onBind");

        return new DownloadBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        System.out.println("unbindService");

    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");

        super.onDestroy();
    }
}

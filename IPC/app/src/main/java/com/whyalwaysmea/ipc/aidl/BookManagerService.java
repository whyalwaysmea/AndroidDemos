package com.whyalwaysmea.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Long
 * on 2017/1/4.
 */

public class BookManagerService extends Service {

    // CopyOnWriteArrayList支持并发读/写
    // 因为AIDL方法是在服务端的Binder线程池中执行的，因此当多个客户端同时连接的时候，会存在多个线程同时访问的情形
    // 所以我们要在AIDL方法中处理线程同步
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub(){

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*if(!mListeners.contains(listener)) {
                mListeners.add(listener);
                System.out.println("订阅成功");

            } else {
                System.out.println("已经存在");
            }
            System.out.println("总的listener : " + mListeners.size());*/

            mListeners.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*if(mListeners.contains(listener)) {
                mListeners.remove(listener);
                System.out.println("取消成功");

            } else {
                System.out.println("本来不存在");
            }
            System.out.println("总的listener : " + mListeners.size());*/

            mListeners.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "android"));
        mBookList.add(new Book(2, "ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while(!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book book = new Book(bookId, "new Book # " + bookId);
                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        /*mBookList.add(book);
        for (int i = 0; i < mListeners.size(); i++) {
            IOnNewBookArrivedListener iOnNewBookArrivedListener = mListeners.get(i);
            iOnNewBookArrivedListener.onNewBookArrived(book);
        }*/
        mBookList.add(book);
        int N = mListeners.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener arrivedListener = (IOnNewBookArrivedListener) mListeners.getBroadcastCookie(N);
            if(arrivedListener != null) {
                arrivedListener.onNewBookArrived(book);
            }
        }
        mListeners.finishBroadcast();
    }
}


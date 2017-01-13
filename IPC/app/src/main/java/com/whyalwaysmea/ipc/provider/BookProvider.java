package com.whyalwaysmea.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Long
 * on 2017/1/5.
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = "BookProvider";

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate .. current thread :" + Thread.currentThread().getName());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query .. current thread :" + Thread.currentThread().getName());

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType");

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert");

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete");

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update");

        return 0;
    }
}

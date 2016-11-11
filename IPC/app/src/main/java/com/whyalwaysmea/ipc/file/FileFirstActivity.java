package com.whyalwaysmea.ipc.file;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.whyalwaysmea.ipc.BaseActivity;
import com.whyalwaysmea.ipc.R;
import com.whyalwaysmea.ipc.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileFirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_first);

        Button writeData = (Button) findViewById(R.id.write_data);
        writeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeDataToFile();
            }
        });

        Button ipcByFile = (Button) findViewById(R.id.ipcByFile);
        ipcByFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(FileSecondActivity.class);
            }
        });
    }

    private void writeDataToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User heihei = new User("heihei", 99);
                String fileDir = Environment.getExternalStorageDirectory().getPath() + "/ipc";
                File dir = new File(fileDir);
                if(!dir.exists()) {
                    dir.mkdir();
                }
                File userFile = new File(fileDir, "user");
                if(!userFile.exists()) {
                    ObjectOutputStream objectOutputStream = null;
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(userFile));
                        objectOutputStream.writeObject(heihei);
                        Log.e("FileFirstActivity", "write data over");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(objectOutputStream != null)
                                objectOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}

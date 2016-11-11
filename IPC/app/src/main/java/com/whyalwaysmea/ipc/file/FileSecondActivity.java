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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileSecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_second);

        Button readData = (Button) findViewById(R.id.read_data);
        readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }

    private void readData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User heihei = null;
                String fileDir = Environment.getExternalStorageDirectory().getPath() + "/ipc";

                File userFile = new File(fileDir, "user");

                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(userFile));
                    heihei = (User) objectInputStream.readObject();
                    Log.e("FileSecondActivity", "User : " + heihei.getName() + ".." + heihei.getAge());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(objectInputStream != null)
                            objectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

package com.concepcion.eisen.persistent4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class act2 extends AppCompatActivity {
    TextView tName, tPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
        tName = findViewById(R.id.tvName);
        tPassword = findViewById(R.id.tvPassword);
    }

    public void previous (View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void loadData(View v){
        SharedPreferences pref = getSharedPreferences("Data1", MODE_PRIVATE);
        String uname = pref.getString("user", null);
        String password = pref.getString("password", null);
        tName.setText(uname);
        tPassword.setText(password);
    }

    public void loadInternal(View v){
        StringBuffer buffer;
        try {
            FileInputStream fis = openFileInput("Data2.txt");
            buffer = new StringBuffer();
            int read = 0;
            while((read = fis.read()) != -1 ){
                buffer.append((char)read);
            }
            tName.setText(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void greetFromExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        try {
            fin = new FileInputStream(file);
            while((c=fin.read()) != -1){
                buffer.append((char) c);
            }

            tName.setText(buffer);

        } catch (Exception e) {
            Toast.makeText(this, "Error reading in SD card", Toast.LENGTH_LONG);
        }
    }
}

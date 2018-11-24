package com.concepcion.eisen.persistent4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText eName, ePwD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.etName);
        ePwD = findViewById(R.id.etPassword);


    }

    public void next(View v) {
        Intent i = new Intent(this, act2.class);
        startActivity(i);
    }

    public void saveData(View v) {
        String uname = eName.getText().toString();
        String pass = ePwD.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("user", uname);
        writer.putString("password", pass);
        writer.commit();
        Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
    }

    public void saveInternal(View v){
        String uname = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data2.txt", MODE_PRIVATE);
            fos.write(uname.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "Error writing to file..");
        } finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      Toast.makeText(this, "Saved in Internal shit,", Toast.LENGTH_LONG).show();
    }

    public void saveExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        String uname = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(uname.getBytes());
            Toast.makeText(this, "Data saved in SD card..", Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error writing in SD card", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }
    }

    public void saveExternalCache(View v){
        File folder = getExternalCacheDir();
        File file = new File(folder, "exCache.txt");
        String uname = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(uname.getBytes());
            Toast.makeText(this, getExternalCacheDir().toString(), Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error writing in Cache", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }

    }

    public void saveInternalCache(View v){
        File folder = getCacheDir();
        File file = new File(folder, "exCache.txt");
        String uname = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(uname.getBytes());
            Toast.makeText(this, getCacheDir().toString(), Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error writing in Cache", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }

    }

}

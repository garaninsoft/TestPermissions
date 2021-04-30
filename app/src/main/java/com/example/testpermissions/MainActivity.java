package com.example.testpermissions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestForSpecificPermission() {
        String[] permissions = new String[]{
/*                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, */
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        ActivityCompat.requestPermissions(this, permissions, 101);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String classname = new Object(){}.getClass().getName();
//        String name = new Object(){}.getClass().getEnclosingMethod().getName();
//        StackTraceElement[] elements = Thread.currentThread().getStackTrace();


        Gson gson = new Gson();

        String responseBody = "{\"sta\"\"Hello\"}";

        try{
            GetStateModel getStateModel = gson.fromJson(responseBody, GetStateModel.class);
        }catch (Exception e){
            e.printStackTrace();
        }




        if (!checkIfAlreadyhavePermission()) {
            requestForSpecificPermission();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("","");
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        new IOFile("test.txt", IOFile.EPathDestination.ExternalStorage, this).write("This is test file. It may be to delete.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this,"No permission", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
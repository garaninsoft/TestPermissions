package com.example.testpermissions;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOFile {


    public enum EPathDestination {
        AppEnvironment,
        ExternalStorage;
    }

    private final String fileName;
    private final File path;
    private final File file;
    
    @RequiresApi(api = Build.VERSION_CODES.R)
    public IOFile(String fileName, EPathDestination pathDestination, Context context) {

        switch (pathDestination){
            case AppEnvironment:
                this.path = context.getFilesDir();
                break;
            case ExternalStorage:
                //this.path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                ///sdcard/Download
                this.path = new File("sdcard/Download/");
                //this.path = Environment.getExternalStoragePublicDirectory(Environment.MEDIA_SHARED);
                break;
            default:
                this.path = null;
        }

        this.fileName = fileName;
        if (this.path != null){
            this.file = new File(this.path, fileName);
        }else {
            this.file = null;
        }

    }

    public void write(String msg) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(msg.getBytes());
        stream.close();
    }

    public String read() throws IOException {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = new FileInputStream(file);
        int l = in.read(bytes);
        in.close();
        return new String(bytes);
    }

    public boolean isCreate(){
        return file.exists();
    }

}

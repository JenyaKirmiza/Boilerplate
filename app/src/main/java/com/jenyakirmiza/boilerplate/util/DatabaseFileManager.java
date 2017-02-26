package com.jenyakirmiza.boilerplate.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.jenyakirmiza.boilerplate.injection.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by eugen on 2/26/2017.
 */
@Singleton
public class DatabaseFileManager {

    private Context mContext;

    @Inject
    public DatabaseFileManager(@ApplicationContext Context context){
        this.mContext=context;
    }


    public void copyAssets() {
        AssetManager assetManager = mContext.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("ribots.db");
            //createDirIfNotExists("/data/data/com.jenyakirmiza.boilerplate/databases/");
            String currentDBPath = "/data/data/com.jenyakirmiza.boilerplate/databases/books";
            out = new FileOutputStream(currentDBPath);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
        } catch(IOException e) {

            Log.e("tag", "Failed to copy asset file " , e);
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    private static boolean createDirIfNotExists(String path) {
        boolean ret = true;

        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");
                ret = false;
            }
        }
        return ret;
    }
}

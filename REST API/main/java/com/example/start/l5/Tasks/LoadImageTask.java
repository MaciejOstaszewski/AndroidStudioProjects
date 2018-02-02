package com.example.start.l5.Tasks;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by start on 2017-11-15.
 */

public class LoadImageTask extends AsyncTask<String, Integer, String> {
    private ProgressBar pb;
    private ImageView iv;

    public LoadImageTask(ProgressBar pb, ImageView iv) {
        super();
        this.pb = pb;
        this.iv = iv;
    }

    @Override
    protected void onPostExecute(String s) {
        iv.setImageURI(Uri.parse(s));
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pb.setProgress(values[0]);
    }

    @Override
    protected String doInBackground(String... strings) {
        int count;

        File yourFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "/2011.jpg");

        try {
            URL url = new URL(strings[0]);
            URLConnection conection = url.openConnection();
            conection.connect();

            int lenghtOfFile = conection.getContentLength();

            InputStream input;
            input = new BufferedInputStream(url.openStream(), 8192);

            if (!yourFile.exists()) {
                yourFile.createNewFile();
            }

            FileOutputStream oFile = new FileOutputStream(yourFile, false);
            OutputStream output = oFile;
            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;

                publishProgress((int) ((total * 100) / lenghtOfFile));

                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("TaskError: ", e.getMessage());
        }

        return yourFile.getAbsolutePath();
    }
}

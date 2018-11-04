package com.example.ajhs2.myapplication;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

// NOTE: This is from https://hc.apache.org/downloads.cgi
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Run this to actually send our POST
        Post p = new Post();
        p.execute();
        try {
            p.get(); // This awaits completion of async task
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Use the string returned
        String reply = p.getRetVal();
        Toast toast = Toast.makeText(getApplicationContext(), reply, Toast.LENGTH_SHORT);
        toast.show();
    }
}

// This is where we actually POST
class Post extends AsyncTask {
    private String retVal = "";

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            CloseableHttpClient hc = HttpClients.createDefault();
            // TODO: Put your IP here
            HttpPost request = new HttpPost("http://130.108.218.229:5000/api/post_some_data");

            // Create our text/image entity
            MultipartEntityBuilder entity = MultipartEntityBuilder.create();
            entity.setCharset(Charset.defaultCharset());
            File rootPath = Environment.getExternalStorageDirectory();
            // TODO: Put your file here. Be sure to change extension in the Python code too if not a PNG
            entity.addBinaryBody("image", new File(rootPath + "/DCIM/Drawings/09082018161409.png"));
            entity.addTextBody("text", "dis be some text yo");
            request.setEntity(entity.build());

            // Send the entity to the server
            CloseableHttpResponse response = hc.execute(request);

            // Set our response for access from the outside
            retVal = EntityUtils.toString(response.getEntity());
            return null;
        } catch (Exception ex) {
            System.out.println("oops");
            ex.printStackTrace();
            return null;
        }
    }

    String getRetVal() {
        return retVal;
    }
}

package com.homework.week07_threading_asynctask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private ProgressBar mProgressBar;
private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mTv = (TextView) findViewById(R.id.smryTV);


    DownloadTask dt = new DownloadTask();
   // dt.execute("https://google.com",
     ///       "Https://Wikipidia.com",
        //    "https://farmingdale.edu");

        mTv.setOnClickListener(this::endableAlert);
}
    private void endableAlert(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Kathleen Anderson"); // add message to alert
        alert.setPositiveButton("OKAY", (v,a)-> {

        Toast.makeText(MainActivity.this, "you clicked yes on the alert", Toast.LENGTH_LONG).show();
        });
            alert.setNegativeButton("NO", (v,a)->{
            Toast.makeText(MainActivity.this, "you clicked NO on the alert", Toast.LENGTH_LONG).show();
        });
        alert.create().show();
    }

    private boolean downloadURL(String URL) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // slow execution down
        return true;
    }

    class DownloadTask extends AsyncTask<String, Integer, Integer> // What are these three parameters? generic objects
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(String... url)  { // what u want to process //abstract method that must be provide //... means variable length (array)
            int downloadSuccess = 0;
            for(int i = 0; i< url.length; i++)
            {
                if(downloadURL(url[i]))
                {
                    downloadSuccess++;
                }
                publishProgress((i+1)* 100/url.length); // if you want to send something/update you have to publish
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer integer) { //integer is the amount of time it runs
            super.onPostExecute(integer);
            mTv.setText("Done");
            if(integer > 2)
            {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        }
    }

}
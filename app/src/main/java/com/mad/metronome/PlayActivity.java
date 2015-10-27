package com.mad.metronome;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ProgressCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    String GroupID = "";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        GroupID = (String) getIntent().getExtras().get("GroupID");
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("downloading");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        getSong(GroupID);

    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mProgressDialog.dismiss();
        mp.start();
    }

    public void getSong(String GroupId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Songs");
        query.whereEqualTo("GroupID", GroupId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null && list != null) {

                    ParseObject poObject = (ParseObject) list.get(0);
                    Log.d("demo", poObject.getObjectId() + poObject.getCreatedAt());
                    Log.d("demo", poObject.toString());

                    ParseFile songPFFile = (ParseFile) poObject.get("songFile");

                    Log.d("demo", songPFFile.getName() + songPFFile.getUrl());

                    songPFFile.getDataInBackground(new GetDataCallback() {


                        @Override
                        public void done(byte[] bytes, ParseException e) {
                            try {
                                // mProgressDialog.hide();
                                // create temp file that will hold byte array
                                Log.d("demo", "iin done");
                                File tempMp3 = File.createTempFile("metronomeSong", "m4a", getCacheDir());
                                Log.d("demo", tempMp3.getAbsolutePath());
                                tempMp3.deleteOnExit();
                                FileOutputStream fos = new FileOutputStream(tempMp3);
                                fos.write(bytes);
                                fos.close();
                                MediaPlayer mediaPlayer = new MediaPlayer();
                                FileInputStream fis = new FileInputStream(tempMp3);
                                mediaPlayer.setDataSource(fis.getFD());
                                mediaPlayer.prepareAsync();

                            } catch (IOException ex) {
                                String s = ex.toString();
                                ex.printStackTrace();
                            }

                        }
                    }, new ProgressCallback() {
                        @Override
                        public void done(Integer integer) {
                            Log.d("demo", integer + "*********");
                            mProgressDialog.setProgress(integer.intValue());
                        }
                    });


                }
            }
        });

    }


}


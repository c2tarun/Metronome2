package com.mad.metronome;

import android.content.Context;
import android.media.MediaPlayer;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by tarun on 10/25/15.
 */
public class ParseUtil {

    private static final String TAG = ParseUtil.class.getSimpleName();
    public static final String TB_GROUP_STRENGTH = "GROUP_STRENGTH";
    public static final String CL_GROUP_ID = "ID";
    public static final String CL_GROUP_STRENGTH = "Strength";
    private static final String TB_GROUP_STATUS = "GROUP_STATUS";
    private static final String CL_USER_ID = "UserId";
    private static final String CL_STATUS = "Status";

    public static void updateStrengthTable(final String groupId, final int strength) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TB_GROUP_STRENGTH);
        query.whereEqualTo(CL_GROUP_ID, groupId);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (parseObject == null) {
                    ParseObject strengthTable = new ParseObject(TB_GROUP_STRENGTH);
                    strengthTable.put(CL_GROUP_ID, groupId);
                    strengthTable.put(CL_GROUP_STRENGTH, strength);
                    strengthTable.saveInBackground();
                } else {
                    parseObject.put(CL_GROUP_STRENGTH, strength);
                    parseObject.saveInBackground();
                }
            }
        });
    }

    public static ParseQuery<ParseObject> getGroupStrengthQuery(String groupId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TB_GROUP_STRENGTH);
        query.whereEqualTo(CL_GROUP_ID, groupId);
        return query;
    }

    public static void insertMemberStatus(final String groupId, final String userId, final String status) {

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TB_GROUP_STATUS);
        query.whereEqualTo(CL_GROUP_ID, groupId);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (parseObject != null) {
                    parseObject.put(CL_STATUS, status);
                    parseObject.saveInBackground();
                } else {
                    ParseObject statusTable = new ParseObject(TB_GROUP_STATUS);
                    statusTable.put(CL_GROUP_ID, groupId);
                    statusTable.put(CL_USER_ID, userId);
                    statusTable.put(CL_STATUS, status);
                    statusTable.saveInBackground();
                }
            }
        });
    }

    public static void getSong(String groupId, final Context context){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GroupID");
        query.whereEqualTo("GroupID",groupId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                    ParseObject poObject = (ParseObject)list.get(0);
                    ParseFile songPFFile = (ParseFile) poObject.get("songFile");
                    songPFFile.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] bytes, ParseException e) {
                            try {
                                // create temp file that will hold byte array
                                File tempMp3 = File.createTempFile("kurchina", "m4a",context.getCacheDir());
                                tempMp3.deleteOnExit();
                                FileOutputStream fos = new FileOutputStream(tempMp3);
                                fos.write(bytes);
                                fos.close();

                                // Tried reusing instance of media player
                                // but that resulted in system crashes...
                                MediaPlayer mediaPlayer = new MediaPlayer();

                                // Tried passing path directly, but kept getting
                                // "Prepare failed.: status=0x1"
                                // so using file descriptor instead
                                FileInputStream fis = new FileInputStream(tempMp3);
                                mediaPlayer.setDataSource(fis.getFD());

                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } catch (IOException ex) {
                                String s = ex.toString();
                                ex.printStackTrace();
                            }

                        }
                    });

                }
            }
        });
    }



}

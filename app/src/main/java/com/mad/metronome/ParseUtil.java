package com.mad.metronome;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by tarun on 10/25/15.
 */
public class ParseUtil {

    private static final String TAG = ParseUtil.class.getSimpleName();

    public static void updateStrengthTable(String groupId, int strength) {
        ParseObject strengthTable = new ParseObject("GroupStrength");
        strengthTable.put("GroupdID", groupId);
        strengthTable.put("GroupStrength", strength);
        strengthTable.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }
}

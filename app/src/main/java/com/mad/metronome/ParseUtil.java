package com.mad.metronome;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by tarun on 10/25/15.
 */
public class ParseUtil {

    private static final String TAG = ParseUtil.class.getSimpleName();
    public static final String TB_GROUP_STRENGTH = "GROUP_STRENGTH";
    public static final String CL_GROUP_ID = "ID";
    public static final String CL_GROUP_STRENGTH = "Strength";

    public static void updateStrengthTable(String groupId, int strength) {
        ParseObject strengthTable = new ParseObject(TB_GROUP_STRENGTH);
        strengthTable.put(CL_GROUP_ID, groupId);
        strengthTable.put(CL_GROUP_STRENGTH, strength);
        strengthTable.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    public static ParseQuery<ParseObject> getGroupStrengthQuery(String groupId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TB_GROUP_STRENGTH);
        query.whereEqualTo(CL_GROUP_ID, groupId);
        return query;
    }
}

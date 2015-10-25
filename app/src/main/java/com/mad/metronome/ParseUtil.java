package com.mad.metronome;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
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
}

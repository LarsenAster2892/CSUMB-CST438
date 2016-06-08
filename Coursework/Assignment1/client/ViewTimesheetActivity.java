package team10.cst438.sl_time_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewTimesheetActivity extends AppCompatActivity
{
    private String myJSON;

    private static final String TAG_STUDENT_FNAME        = "studentFname";
    private static final String TAG_STUDENT_LNAME        = "studentLname";
    private static final String TAG_INSTRUCTOR_FNAME     = "instructorFname";
    private static final String TAG_INSTRUCTOR_LNAME     = "instructorLname";
    private static final String TAG_SUPERVISOR_FNAME     = "supervisorFname";
    private static final String TAG_SUPERVISOR_LNAME     = "supervisorLname";
    private static final String TAG_SERVICE_SITE         = "serviceSite";
    private static final String TAG_COURSE               = "course";
    private static final String TAG_TIMESHEET_START_DATE = "timesheetStartDate";
    private static final String TAG_SUNDAY_HOURS         = "sundayHours";
    private static final String TAG_MONDAY_HOURS         = "mondayHours";
    private static final String TAG_TUESDAY_HOURS        = "tuesdayHours";
    private static final String TAG_WEDNESDAY_HOURS      = "wednesdayHours";
    private static final String TAG_THURSDAY_HOURS       = "thursdayHours";
    private static final String TAG_FRIDAY_HOURS         = "fridayHours";
    private static final String TAG_SATURDAY_HOURS       = "saturdayHours";
    private static final String TAG_TOTAL_WEEK_HOURS     = "totalWeekHours";
    private static final String TAG_RESULTS              = "result";

    private JSONArray timesheet = null;
    private ArrayList<HashMap<String, String>> timesheetList;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timesheet);

        list            = (ListView) findViewById(R.id.listView);
        timesheetList   = new ArrayList<HashMap<String,String>>();

        // !IMPORTANT! URL for PHP script needs to be entered here.
        ParseJSON j    = new ParseJSON("http://www.blank.com/viewTimsheet.php");

        j.execute();
        myJSON = j.getMyJSON();
        showList();
    }

    protected void showList()
    {
        try
        {
            JSONObject jsonObj = new JSONObject(myJSON);
            timesheet = jsonObj.getJSONArray(TAG_RESULTS);

            for( int i = 0; i < timesheet.length(); i++ )
            {
                JSONObject c = timesheet.getJSONObject(i);

                String studentFname       = c.getString(TAG_STUDENT_FNAME);
                String studentLname       = c.getString(TAG_STUDENT_LNAME);
                String instructorFname    = c.getString(TAG_INSTRUCTOR_FNAME);
                String instructorLname    = c.getString(TAG_INSTRUCTOR_LNAME);
                String supervisorFname    = c.getString(TAG_SUPERVISOR_FNAME);
                String supervisorLname    = c.getString(TAG_SUPERVISOR_LNAME);
                String serviceSite        = c.getString(TAG_SERVICE_SITE);
                String course             = c.getString(TAG_COURSE);
                String timesheetStartDate = c.getString(TAG_TIMESHEET_START_DATE);
                String sundayHours        = c.getString(TAG_SUNDAY_HOURS);
                String mondayHours        = c.getString(TAG_MONDAY_HOURS);
                String tuesdayHours       = c.getString(TAG_TUESDAY_HOURS);
                String wednesdayHours     = c.getString(TAG_WEDNESDAY_HOURS);
                String thursdayHours      = c.getString(TAG_THURSDAY_HOURS);
                String fridayHours        = c.getString(TAG_FRIDAY_HOURS);
                String saturdayHours      = c.getString(TAG_SATURDAY_HOURS);
                String totalWeekHours     = c.getString(TAG_TOTAL_WEEK_HOURS);

                HashMap<String,String> timesheetHash = new HashMap<String,String>();

                timesheetHash.put(TAG_STUDENT_FNAME, studentFname);
                timesheetHash.put(TAG_STUDENT_LNAME, studentLname);
                timesheetHash.put(TAG_INSTRUCTOR_FNAME, instructorFname);
                timesheetHash.put(TAG_INSTRUCTOR_LNAME, instructorLname);
                timesheetHash.put(TAG_SUPERVISOR_FNAME, supervisorFname);
                timesheetHash.put(TAG_SUPERVISOR_LNAME, supervisorLname);
                timesheetHash.put(TAG_SERVICE_SITE, serviceSite);
                timesheetHash.put(TAG_COURSE, course);
                timesheetHash.put(TAG_TIMESHEET_START_DATE, timesheetStartDate);
                timesheetHash.put(TAG_SUNDAY_HOURS, sundayHours);
                timesheetHash.put(TAG_MONDAY_HOURS, mondayHours);
                timesheetHash.put(TAG_TUESDAY_HOURS, tuesdayHours);
                timesheetHash.put(TAG_WEDNESDAY_HOURS, wednesdayHours);
                timesheetHash.put(TAG_THURSDAY_HOURS, thursdayHours);
                timesheetHash.put(TAG_FRIDAY_HOURS, fridayHours);
                timesheetHash.put(TAG_SATURDAY_HOURS, saturdayHours);
                timesheetHash.put(TAG_TOTAL_WEEK_HOURS, totalWeekHours);

                timesheetList.add(timesheetHash);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ViewTimesheetActivity.this, timesheetList, R.layout.list_item,
                    new String[]{
                                    TAG_STUDENT_FNAME,TAG_STUDENT_LNAME,
                                    TAG_INSTRUCTOR_FNAME, TAG_INSTRUCTOR_LNAME,
                                    TAG_SUPERVISOR_FNAME, TAG_SUPERVISOR_LNAME,
                                    TAG_SERVICE_SITE, TAG_COURSE,
                                    TAG_TIMESHEET_START_DATE, TAG_SUNDAY_HOURS,
                                    TAG_MONDAY_HOURS, TAG_TUESDAY_HOURS,
                                    TAG_WEDNESDAY_HOURS, TAG_THURSDAY_HOURS,
                                    TAG_FRIDAY_HOURS, TAG_SATURDAY_HOURS,
                                    TAG_TOTAL_WEEK_HOURS
                                },
                    new int[]   {
                                    R.id.studentFname, R.id.studentLname,
                                    R.id.instructorFname, R.id.instructorLname,
                                    R.id.supervisorFname, R.id.supervisorLname,
                                    R.id.serviceSite, R.id.course,
                                    R.id.timesheetStartDate, R.id.sundayHours,
                                    R.id.mondayHours, R.id.tuesdayHours,
                                    R.id.wednesdayHours, R.id.thursdayHours,
                                    R.id.fridayHours, R.id.saturdayHours,
                                    R.id.totalWeekHours
                                }
            );

            list.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}

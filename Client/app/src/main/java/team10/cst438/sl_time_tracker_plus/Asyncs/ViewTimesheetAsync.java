package team10.cst438.sl_time_tracker_plus.Asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import team10.cst438.sl_time_tracker_plus.DataClasses.Timesheet;

/**
 * Created by Nigel on 11/24/2015.
 */

public class ViewTimesheetAsync extends AsyncTask<String, Void, Timesheet>
{
    private TextView sundayHoursTextView, mondayHoursTextView, tuesdayHoursTextView,
                     wednesdayHoursTextView, thursdayHoursTextView, fridayHoursTextView,
                     saturdayHoursTextView, statusTextView;

    private Context context;

    public ViewTimesheetAsync(Context context, TextView sundayHoursTextView,TextView mondayHoursTextView,
                                               TextView tuesdayHoursTextView, TextView wednesdayHoursTextView,
                                               TextView thursdayHoursTextView, TextView fridayHoursTextView,
                                               TextView saturdayHoursTextView, TextView statusTextView)
    {
        this.context = context;
        this.sundayHoursTextView = sundayHoursTextView;
        this.mondayHoursTextView = mondayHoursTextView;
        this.tuesdayHoursTextView = tuesdayHoursTextView;
        this.wednesdayHoursTextView = wednesdayHoursTextView;
        this.thursdayHoursTextView = thursdayHoursTextView;
        this.fridayHoursTextView = fridayHoursTextView;
        this.saturdayHoursTextView = saturdayHoursTextView;
        this.statusTextView = statusTextView;
    }

    protected void onPreExecute()
    {

    }

    @Override
    protected Timesheet doInBackground(String... arg0)
    {
        try
        {
            String username = (String)arg0[0];
            String timesheetStartDate = (String)arg0[1];

            String link=""; // This needs to be updated.

            // Add the parameters.
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(timesheetStartDate, "UTF-8");

            // Open the connection.
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Open the output and write the parameters.
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            // Clean up.
            wr.write( data );
            wr.flush();
            wr.close();

            // Read the input and convert the json to a pojo.
            InputStreamReader in = new InputStreamReader( conn.getInputStream() );
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Timesheet timesheet = gson.fromJson(in, Timesheet.class);

            return timesheet;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Timesheet timesheet)
    {
        if (timesheet != null)
        {
            this.sundayHoursTextView.setText(timesheet.getSundayHours());
            this.mondayHoursTextView.setText(timesheet.getMondayHours());
            this.tuesdayHoursTextView.setText(timesheet.getTuesdayHours());
            this.wednesdayHoursTextView.setText(timesheet.getWednesdayHours());
            this.thursdayHoursTextView.setText(timesheet.getThursdayHours());
            this.fridayHoursTextView.setText(timesheet.getFridayHours());
            this.saturdayHoursTextView.setText(timesheet.getSaturdayHours());
            this.statusTextView.setText(timesheet.getStatusAsString());
        }
        else
        {
            this.sundayHoursTextView.setText("Error retrieving timesheet");
        }
    }
}

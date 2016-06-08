package team10.cst438.sl_time_tracker_plus.Asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import team10.cst438.sl_time_tracker_plus.DataClasses.Timesheet;
import team10.cst438.sl_time_tracker_plus.DataClasses.TimesheetList;

/**
 * Created by Nigel on 11/25/2015.
 */

public class ViewReportAsync extends AsyncTask<String, Void, TimesheetList>
{
    private TextView totalHoursTextView, approvedHoursTextView, nonApprovedHoursTextView,
            approvedTimesheetsTextView, nonApprovedTimesheetsTextView;

    private Context context;

    public ViewReportAsync(Context context, TextView totalHoursTextView, TextView approvedHoursTextView,
                           TextView nonApprovedHoursTextView, TextView approvedTimesheetTextView,
                           TextView nonApprovedTimesheetTextView)
    {
        this.context = context;
        this.totalHoursTextView = totalHoursTextView;
        this.approvedHoursTextView = approvedHoursTextView;
        this.nonApprovedHoursTextView = nonApprovedHoursTextView;
        this.approvedTimesheetsTextView = nonApprovedTimesheetTextView;
        this.nonApprovedTimesheetsTextView = nonApprovedTimesheetTextView;
    }

    protected void onPreExecute()
    {

    }

    @Override
    protected TimesheetList doInBackground(String... arg0)
    {
        try
        {
            String username = (String)arg0[0];

            String link=""; // This needs to be updated.

            // Add the paramter.
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

            // Open the connection.
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Open output and send the paramter.
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            // Clean up.
            wr.write( data );
            wr.flush();
            wr.close();

            // Get json from input and convert it to a pojo.
            InputStreamReader in = new InputStreamReader( conn.getInputStream() );
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            TimesheetList timesheetList = gson.fromJson(in, TimesheetList.class);

            return timesheetList;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(TimesheetList timesheetList)
    {
        if (timesheetList != null)
        {
            this.totalHoursTextView.setText(timesheetList.getTotalHours());
            this.approvedHoursTextView.setText(timesheetList.getApprovedHours());
            this.nonApprovedHoursTextView.setText(timesheetList.getNonApprovedHours());
            this.approvedTimesheetsTextView.setText(timesheetList.getNumberApproved());
            this.nonApprovedTimesheetsTextView.setText(timesheetList.getNumberNonApproved());
        }
        else
        {
            this.totalHoursTextView.setText("Error retrieving data");
        }
    }
}

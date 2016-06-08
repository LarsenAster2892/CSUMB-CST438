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

/**
 * Created by Nigel on 11/25/2015.
 */

public class CreateTimesheetAsync extends AsyncTask<String, Void, Boolean>
{
    private String startDate, sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    private boolean status;
    private Context context;
    private Timesheet timesheet;
    private TextView resultTextView;

    public CreateTimesheetAsync(Context context, String startDate, String sunday, String monday, String tuesday,
                              String wednesday, String thursday, String friday, String saturday, boolean status, TextView resultTextView)
    {
        // Create a timesheet object to be passed via json/gson.
        timesheet = new Timesheet(startDate, Integer.parseInt(sunday), Integer.parseInt(monday), Integer.parseInt(tuesday),
                Integer.parseInt(wednesday), Integer.parseInt(thursday), Integer.parseInt(friday),
                Integer.parseInt(saturday), status);

        this.context = context;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.status = status;
        this.resultTextView = resultTextView;
    }

    protected void onPreExecute()
    {

    }

    @Override
    protected Boolean doInBackground(String... arg0)
    {
        boolean result = false;

        try
        {
            String username = (String)arg0[0];
            String timesheetStartDate = (String)arg0[1];

            String link=""; // This needs to be updated to reflect the url to access the servlet.

            // Add paramters.
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(timesheetStartDate, "UTF-8");

            // Create connection.
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Set output to true.
            conn.setDoOutput(true);

            // Write data to output.
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);

            // Convert timesheet object to json. Write it to output.
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            gson.toJson(timesheet, wr);

            // Clean up.
            wr.flush(); // Needs to be tested
            wr.close();

            // Get server Response.
            InputStreamReader in = new InputStreamReader( conn.getInputStream() );

            // Read server response.
            String line = in.toString();

            if (line.matches("INSERT SUCCESS"))
                result = true;

            return result;
        }
        catch(Exception e)
        {
            return result;
        }
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if (result)
        {
            resultTextView.setText("Update Successful");
        }
        else
        {
            resultTextView.setText("Update failed");
        }
    }
}

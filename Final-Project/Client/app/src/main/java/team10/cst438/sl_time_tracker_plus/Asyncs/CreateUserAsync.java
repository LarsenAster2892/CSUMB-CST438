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
import team10.cst438.sl_time_tracker_plus.DataClasses.User;

/**
 * Created by Nigel on 11/26/2015.
 */

public class CreateUserAsync extends AsyncTask<String, Void, Boolean>
{
    private String fname, lname, email, username, password, role;
    private int roleAsInt;
    private Context context;
    private User user;
    private TextView resultTextView;

    public CreateUserAsync(Context context, String fname, String lname, String email,
                                String username, String password, String role, TextView resultTextView)
    {
        this.roleAsInt = 0;
        this.context = context;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.resultTextView = resultTextView;

        // If blocks used to get int value of role.
        if (role.matches("Student"))
        {
            roleAsInt = 1;
        }
        else if (role.matches("Instructor"))
        {
            roleAsInt = 2;
        }
        else if (role.matches("Supervisor"))
        {
            roleAsInt = 3;
        }
        else if (role.matches("Administrator"))
        {
            roleAsInt = 4;
        }

        // Set user object to be converted to json/gson.
        user = new User(username, fname, lname, email, roleAsInt);
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
            String link=""; // Needs to be updated with proper url.

            // Open connection.
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Set output to true.
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            /// Convert user to json and write it to output.
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            gson.toJson(user, wr);

            // Clean up.
            wr.flush(); // Needs to be tested
            wr.close();

            // Get server response.
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

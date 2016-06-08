package team10.cst438.sl_time_tracker_plus.Asyncs;

import android.app.Activity;
import android.content.Intent;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import team10.cst438.sl_time_tracker_plus.Menus.AdministratorMenuActivity;
import team10.cst438.sl_time_tracker_plus.Menus.InstructorMenuActivity;
import team10.cst438.sl_time_tracker_plus.Menus.StudentMenuActivity;
import team10.cst438.sl_time_tracker_plus.DataClasses.User;
import team10.cst438.sl_time_tracker_plus.Menus.SupervisorMenuActivity;

/**
 * Created by Nigel on 11/24/2015.
 */

public class SignInAsync extends AsyncTask<String,Void,User>
{
    private TextView statusField;
    private Context context;
    public static User user;

    public SignInAsync(Context context,TextView statusField)
    {
        this.context = context;
        this.statusField = statusField;
    }

    protected void onPreExecute()
    {

    }

    @Override
    protected User doInBackground(String... arg0)
    {
        try
        {
            String username = (String)arg0[0];
            String password = (String)arg0[1];

            String link=""; // Needs to be updated.

            // Add paramters.
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            // Open the connection.
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Open output and send the paramters.
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );

            // Clean up.
            wr.flush();
            wr.close();

            // Read the input and convert the json to a pojo.
            InputStreamReader in = new InputStreamReader( conn.getInputStream() );
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            user = gson.fromJson(in, User.class);

            return user;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(User user)
    {
        if (user != null)
        {
            this.statusField.setText("Login Successful");

            // Get the role value from the user object and open the corresponding menu.
            if (user.getRoleAsInt() == 1)
            {
                Intent intent = new Intent(context, StudentMenuActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
            else if (user.getRoleAsInt() == 2)
            {
                Intent intent = new Intent(context, InstructorMenuActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
            else if (user.getRoleAsInt() == 3)
            {
                Intent intent = new Intent(context, SupervisorMenuActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
            else if (user.getRoleAsInt() == 4)
            {
                Intent intent = new Intent(context, AdministratorMenuActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }
        else
        {
            this.statusField.setText("Login Unsuccessful");
        }
    }
}

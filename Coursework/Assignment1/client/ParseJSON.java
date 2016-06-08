package team10.cst438.sl_time_tracker;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Nigel on 11/16/2015.
 */

public class ParseJSON extends AsyncTask<String, Void, String>
{
    private String url;
    private String myJSON;

    public ParseJSON(String u)
    {
        url = u;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String result = null;

        try
        {
            String link = url;

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }

            result = sb.toString();
        }
        catch (Exception e)
        {
            result = new String("Exception: " + e.getMessage());
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result)
    {
        myJSON = result;
    }

    public String getMyJSON()
    {
        return myJSON;
    }
}



import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class ServerFetcher 
{
  private String link;
	
	public ServerFetcher(String u) { link = u; }
	
	public Timesheet getTimesheet(String id, String timesheetStartDate) throws Exception
	{	
		try
    {
      String data  = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
			data += "&" + URLEncoder.encode("timesheetStartDate", "UTF-8") + "=" + URLEncoder.encode(timesheetStartDate, "UTF-8");

      URL url = new URL(link);
      URLConnection connection = url.openConnection();

      connection.setDoOutput(true);
      OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

      wr.write( data );
      wr.flush();
			
			InputStreamReader in = new InputStreamReader( connection.getInputStream() );
			
			GsonBuilder builder = new GsonBuilder();
      Gson gson = builder.create();
      Timesheet timesheet = gson.fromJson(in, Timesheet.class);
			
			return timesheet;
    }
		finally 
		{
      connection.disconnect();
    }
	}
	
  public boolean login(String... arg0) throws Exception
  {
		boolean result = false;
		
    try
    {
      String username = (String)arg0[0];
      String password = (String)arg0[1];

      String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
      data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

      URL url = new URL(link);
      URLConnection connection = url.openConnection();
			
      connection.setDoOutput(true);
      OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

      wr.write( data );
      wr.flush();

      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      StringBuilder sb = new StringBuilder();
      String line = reader.readLine();

			if (line == "ACCEPT") 
				result = true;
	
      return result;
    }
    catch(Exception e)
    {
      return result; 
    }
		finally 
		{
      connection.disconnect();
    }
  }
	
	public setURL(String u) { link = u; }
	public getURL() { return link; }
}


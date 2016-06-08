/**
 *  SL_Time_Tracker_Plus
 *
 *  This app allows students, instructors, and supervisors to track service time for service
 *  learning assignments.
 *
 *  Team 10
 *
 *  Nigel DeVaughn
 *  Luciano Avendano
 *  Clarence Mitchell
 *  Caitlin Kuleck
 *
 *  This is the client side portion of a client/server application.
 */

package team10.cst438.sl_time_tracker_plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.SignInAsync;

/**
 * Created by Nigel on 11/19/2015.
 */

public class    MainActivity extends AppCompatActivity
{
    private EditText usernameField,passwordField;
    private TextView status;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.usernameEditText);
        passwordField = (EditText)findViewById(R.id.passwordEditText);

        status = (TextView)findViewById(R.id.statusTextView);

        loginButton = (Button)findViewById(R.id.loginButton);

        // Listener for login button.
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login(v);
            }
        });
    }

    public void login(View view)
    {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // Call to async task to sign in.
        new SignInAsync(this,status).execute(username, password);
    }
}

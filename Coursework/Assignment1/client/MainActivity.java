package team10.cst438.sl_time_tracker;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity
{
    private EditText usernameField,passwordField;
    private TextView status,role;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);

        status = (TextView)findViewById(R.id.textView6);
        role = (TextView)findViewById(R.id.textView7);
    }

    public void loginPost(View view)
    {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        new SignIn(this,status,role).execute(username,password);
    }
}

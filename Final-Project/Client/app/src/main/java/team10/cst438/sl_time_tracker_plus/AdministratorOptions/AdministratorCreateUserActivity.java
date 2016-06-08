package team10.cst438.sl_time_tracker_plus.AdministratorOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.CreateUserAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/10/2015.
 */

public class AdministratorCreateUserActivity extends AppCompatActivity
{
    private Button createUserButton;
    private EditText firstNameEditText, lastNameEditText, emailEditText, usernameEditText, passwordEditText, passwordVerifyEditText;
    private Spinner roleSpinner;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_create_user);

        createUserButton = (Button)findViewById(R.id.createUserButton);
        firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        passwordVerifyEditText = (EditText)findViewById(R.id.passwordVerifyEditText);
        resultTextView = (TextView)findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roleSpinner.setAdapter(adapter);

        // Listener for the create user button.
        createUserButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createUser(v);
            }
        });
    }

    public void createUser(View v)
    {
        // verify the passwords match.
        if (passwordEditText.getText().toString().matches(passwordVerifyEditText.getText().toString()))
        {
            String fname = firstNameEditText.getText().toString();
            String lname = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String role = roleSpinner.getSelectedItem().toString();

            // call to async task to create the user.
            new CreateUserAsync(this, fname, lname, email, username, password, role, resultTextView).execute();
        }
        else
        {
            resultTextView.setText("Password mismatch");
        }
    }
}

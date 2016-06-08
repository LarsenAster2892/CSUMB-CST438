package team10.cst438.sl_time_tracker_plus.SupervisorOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.EditTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.SignInAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.ViewTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/09/2015.
 */

public class SupervisorViewTimesheetActivity extends AppCompatActivity
{
    private Button submitButton, approveButton, denyButton;
    private TextView sundayHoursTextView, mondayHoursTextView, tuesdayHoursTextView,
            wednesdayHoursTextView, thursdayHoursTextView, fridayHoursTextView,
            saturdayHoursTextView, statusTextView;
    private EditText usernameEditText;
    private DatePicker dateField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_timesheet);

        submitButton = (Button)findViewById(R.id.submitButton);
        approveButton = (Button)findViewById(R.id.approveButton);
        denyButton = (Button)findViewById(R.id.denyButton);
        approveButton.setEnabled(false);
        denyButton.setEnabled(false);
        sundayHoursTextView = (TextView)findViewById(R.id.sundayHoursTextView);
        mondayHoursTextView = (TextView)findViewById(R.id.mondayHoursTextView);
        tuesdayHoursTextView = (TextView)findViewById(R.id.tuesdayHoursTextView);
        wednesdayHoursTextView = (TextView)findViewById(R.id.wednesdayHoursTextView);
        thursdayHoursTextView = (TextView)findViewById(R.id.thursdayHoursTextView);
        fridayHoursTextView = (TextView)findViewById(R.id.fridayHoursTextView);
        saturdayHoursTextView = (TextView)findViewById(R.id.saturdayHoursTextView);
        statusTextView = (TextView)findViewById(R.id.statusTextView);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        dateField = (DatePicker)findViewById(R.id.dateField);

        // Listener for submit button.
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTimesheet(v);
                approveButton.setEnabled(true);
                denyButton.setEnabled(true);
            }
        });

        // Listeners for approve and deny buttons.
        approveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                approve(v);
            }
        });
        denyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                deny(v);
            }
        });

    }

    public void getTimesheet(View v)
    {
        String username = usernameEditText.getText().toString();

        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        String timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Call to async task to view the timesheet.
        new ViewTimesheetAsync(this, sundayHoursTextView, mondayHoursTextView,
                tuesdayHoursTextView, wednesdayHoursTextView, thursdayHoursTextView,
                fridayHoursTextView, saturdayHoursTextView, statusTextView).execute(username, timesheetStartDate);
    }

    public void approve(View v)
    {
        String username = usernameEditText.getText().toString();

        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        String timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Updates status to true.
        new EditTimesheetAsync(this,sundayHoursTextView.getText().toString(), mondayHoursTextView.getText().toString(),
                tuesdayHoursTextView.getText().toString(), wednesdayHoursTextView.getText().toString(),
                thursdayHoursTextView.getText().toString(), fridayHoursTextView.getText().toString(),
                saturdayHoursTextView.getText().toString(), true, statusTextView).execute(username, timesheetStartDate);
    }

    public void deny(View v)
    {
        String username = usernameEditText.getText().toString();

        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        String timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Updates status to false.
        new EditTimesheetAsync(this,sundayHoursTextView.getText().toString(), mondayHoursTextView.getText().toString(),
                tuesdayHoursTextView.getText().toString(), wednesdayHoursTextView.getText().toString(),
                thursdayHoursTextView.getText().toString(), fridayHoursTextView.getText().toString(),
                saturdayHoursTextView.getText().toString(), false, statusTextView).execute(username, timesheetStartDate);
    }
}

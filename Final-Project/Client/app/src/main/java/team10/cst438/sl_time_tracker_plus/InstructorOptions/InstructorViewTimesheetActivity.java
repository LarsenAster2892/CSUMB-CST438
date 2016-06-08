package team10.cst438.sl_time_tracker_plus.InstructorOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.ViewTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/2/2015.
 */

public class InstructorViewTimesheetActivity extends AppCompatActivity
{
    private Button submitButton;
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

        // Listener for the submit button.
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTimesheet(v);
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
}

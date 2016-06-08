package team10.cst438.sl_time_tracker_plus.StudentOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import team10.cst438.sl_time_tracker_plus.Asyncs.CreateTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.EditTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.SignInAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.ViewTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/04/2015.
 */

public class StudentCreateTimesheetActivity extends AppCompatActivity
{
    private Button createTimesheetButton;
    private EditText sundayHoursEditText, mondayHoursEditText, tuesdayHoursEditText,
            wednesdayHoursEditText, thursdayHoursEditText, fridayHoursEditText, saturdayHoursEditText;
    private TextView resultTextView;
    private DatePicker dateField;
    private Calendar today;
    private String timesheetStartDate;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_timesheet);

        createTimesheetButton = (Button)findViewById(R.id.createTimesheetButton);
        sundayHoursEditText = (EditText)findViewById(R.id.sundayHoursEditText);
        mondayHoursEditText = (EditText)findViewById(R.id.mondayHoursEditText);
        tuesdayHoursEditText = (EditText)findViewById(R.id.tuesdayHoursEditText);
        wednesdayHoursEditText = (EditText)findViewById(R.id.wednesdayHoursEditText);
        thursdayHoursEditText = (EditText)findViewById(R.id.thursdayHoursEditText);
        fridayHoursEditText = (EditText)findViewById(R.id.fridayHoursEditText);
        saturdayHoursEditText = (EditText)findViewById(R.id.saturdayHoursEditText);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        dateField = (DatePicker)findViewById(R.id.dateField);
        today = Calendar.getInstance();
        username = SignInAsync.user.getUsername();

        // Listener for the create timesheet button.
        createTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createTimesheet(v);
            }
        });

    }

    public void createTimesheet(View v)
    {
        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Call to async task to create the timesheet.
        new CreateTimesheetAsync(this, timesheetStartDate, sundayHoursEditText.getText().toString(), mondayHoursEditText.getText().toString(), tuesdayHoursEditText.getText().toString(),
                wednesdayHoursEditText.getText().toString(), thursdayHoursEditText.getText().toString(), fridayHoursEditText.getText().toString(),
                saturdayHoursEditText.getText().toString(), false, resultTextView).execute(username, timesheetStartDate);
    }
}

package team10.cst438.sl_time_tracker_plus.AdministratorOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import team10.cst438.sl_time_tracker_plus.Asyncs.EditTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.SignInAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.ViewTimesheetAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/10/2015.
 */

public class AdministratorEditTimesheetActivity extends AppCompatActivity
{
    private Button editButton;
    private EditText usernameEditText, sundayHoursEditText, mondayHoursEditText, tuesdayHoursEditText,
            wednesdayHoursEditText, thursdayHoursEditText, fridayHoursEditText, saturdayHoursEditText;
    private TextView resultTextView, status;
    private DatePicker dateField;
    private Calendar today;
    private String timesheetStartDate;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_timesheet);

        editButton = (Button)findViewById(R.id.editButton);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        sundayHoursEditText = (EditText)findViewById(R.id.sundayHoursEditText);
        mondayHoursEditText = (EditText)findViewById(R.id.mondayHoursEditText);
        tuesdayHoursEditText = (EditText)findViewById(R.id.tuesdayHoursEditText);
        wednesdayHoursEditText = (EditText)findViewById(R.id.wednesdayHoursEditText);
        thursdayHoursEditText = (EditText)findViewById(R.id.thursdayHoursEditText);
        fridayHoursEditText = (EditText)findViewById(R.id.fridayHoursEditText);
        saturdayHoursEditText = (EditText)findViewById(R.id.saturdayHoursEditText);
        status = (TextView)findViewById(R.id.status);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        dateField = (DatePicker)findViewById(R.id.dateField);
        today = Calendar.getInstance();

        // Listener for the datepicker.
        dateField.init( today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener()
                {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        // Get the timesheet for the selected date.
                        getTimesheet();
                    }
                });

        // Listener for the update button.
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateTimesheet(v);
            }
        });

    }

    public void getTimesheet()
    {
        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Call to async task to view the timesheet.
        new ViewTimesheetAsync(this, sundayHoursEditText, mondayHoursEditText,
                tuesdayHoursEditText, wednesdayHoursEditText, thursdayHoursEditText,
                fridayHoursEditText, saturdayHoursEditText, status).execute(username, timesheetStartDate);
    }

    public void updateTimesheet(View v)
    {
        username = usernameEditText.getText().toString();

        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth() + 1;
        int year = dateField.getYear();

        timesheetStartDate = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);

        // Call to async task to update the timesheet.
        new EditTimesheetAsync(this, sundayHoursEditText.getText().toString(), mondayHoursEditText.getText().toString(), tuesdayHoursEditText.getText().toString(),
                wednesdayHoursEditText.getText().toString(), thursdayHoursEditText.getText().toString(), fridayHoursEditText.getText().toString(),
                saturdayHoursEditText.getText().toString(), false, resultTextView).execute(username, timesheetStartDate);
    }
}

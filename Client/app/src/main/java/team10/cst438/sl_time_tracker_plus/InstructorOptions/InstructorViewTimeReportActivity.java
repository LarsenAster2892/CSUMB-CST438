package team10.cst438.sl_time_tracker_plus.InstructorOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.ViewReportAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/2/2015.
 */

public class InstructorViewTimeReportActivity extends AppCompatActivity
{
    private Button submitButton;
    private EditText usernameEditText;
    private TextView totalHoursTextView, approvedHoursTextView, nonApprovedHoursTextView,
            approvedTimesheetsTextView, nonApprovedTimesheetsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_timesheet);

        submitButton = (Button)findViewById(R.id.submitButton);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        totalHoursTextView = (TextView)findViewById(R.id.totalHoursTextView);
        approvedHoursTextView = (TextView)findViewById(R.id.approvedHoursTextView);
        nonApprovedHoursTextView = (TextView)findViewById(R.id.nonApprovedHoursTextView);
        approvedTimesheetsTextView = (TextView)findViewById(R.id.approvedTimesheetsTextView);
        nonApprovedTimesheetsTextView = (TextView)findViewById(R.id.nonApprovedTimesheetsTextView);

        // Listener for the submit button.
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getReport(v);
            }
        });

    }

    public void getReport(View v)
    {
        String username = usernameEditText.getText().toString();

        // Call to async task to view the report.
        new ViewReportAsync(this, totalHoursTextView, approvedHoursTextView, nonApprovedHoursTextView,
                approvedTimesheetsTextView, nonApprovedTimesheetsTextView).execute(username);
    }
}

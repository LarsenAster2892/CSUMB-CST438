package team10.cst438.sl_time_tracker_plus.StudentOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team10.cst438.sl_time_tracker_plus.Asyncs.SignInAsync;
import team10.cst438.sl_time_tracker_plus.Asyncs.ViewReportAsync;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 12/04/2015.
 */

public class StudentViewTimeReportActivity extends AppCompatActivity
{
    private TextView totalHoursTextView, approvedHoursTextView, nonApprovedHoursTextView,
            approvedTimesheetsTextView, nonApprovedTimesheetsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_timesheet);

        totalHoursTextView = (TextView)findViewById(R.id.totalHoursTextView);
        approvedHoursTextView = (TextView)findViewById(R.id.approvedHoursTextView);
        nonApprovedHoursTextView = (TextView)findViewById(R.id.nonApprovedHoursTextView);
        approvedTimesheetsTextView = (TextView)findViewById(R.id.approvedTimesheetsTextView);
        nonApprovedTimesheetsTextView = (TextView)findViewById(R.id.nonApprovedTimesheetsTextView);

        getReport();
   }

    public void getReport()
    {
        String username = SignInAsync.user.getUsername();

        // Call to async task to get the report.
        new ViewReportAsync(this, totalHoursTextView, approvedHoursTextView, nonApprovedHoursTextView,
                approvedTimesheetsTextView, nonApprovedTimesheetsTextView).execute(username);
    }
}

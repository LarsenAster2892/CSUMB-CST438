package team10.cst438.sl_time_tracker_plus.Menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team10.cst438.sl_time_tracker_plus.R;
import team10.cst438.sl_time_tracker_plus.SupervisorOptions.SupervisorViewTimeReportActivity;
import team10.cst438.sl_time_tracker_plus.SupervisorOptions.SupervisorViewTimesheetActivity;

/**
 * Created by Nigel on 11/30/2015.
 */

public class SupervisorMenuActivity extends AppCompatActivity
{
    private Button viewTimesheetButton, viewTimeReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        viewTimesheetButton = (Button)findViewById(R.id.viewTimesheetButton);
        viewTimeReportButton = (Button)findViewById(R.id.viewTimeReportButton);

        // Below lists the supervisors menu options and intents for appropriate activities.
        viewTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SupervisorViewTimesheetActivity.class);
                startActivity(intent);
            }
        });

        viewTimeReportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SupervisorViewTimeReportActivity.class);
                startActivity(intent);
            }
        });
    }
}

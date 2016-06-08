package team10.cst438.sl_time_tracker_plus.Menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team10.cst438.sl_time_tracker_plus.R;
import team10.cst438.sl_time_tracker_plus.StudentOptions.StudentCreateTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.StudentOptions.StudentEditTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.StudentOptions.StudentViewTimeReportActivity;
import team10.cst438.sl_time_tracker_plus.StudentOptions.StudentViewTimesheetActivity;

/**
 * Created by Nigel on 11/30/2015.
 */

public class StudentMenuActivity extends AppCompatActivity
{
    private Button viewTimesheetButton, createTimesheetButton, editTimesheetButton, viewTimeReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        viewTimesheetButton = (Button)findViewById(R.id.viewTimesheetButton);
        createTimesheetButton = (Button)findViewById(R.id.createTimesheetButton);
        editTimesheetButton = (Button)findViewById(R.id.editButton);
        viewTimeReportButton = (Button)findViewById(R.id.viewTimeReportButton);

        // Below lists the students menu options and intents for appropriate activities.
        viewTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), StudentViewTimesheetActivity.class);
                startActivity(intent);
            }
        });

        createTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), StudentCreateTimesheetActivity.class);
                startActivity(intent);
            }
        });

        editTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), StudentEditTimesheetActivity.class);
                startActivity(intent);
            }
        });

        viewTimeReportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), StudentViewTimeReportActivity.class);
                startActivity(intent);
            }
        });
    }
}

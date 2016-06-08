package team10.cst438.sl_time_tracker_plus.Menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team10.cst438.sl_time_tracker_plus.AdministratorOptions.AdministratorCreateUserActivity;
import team10.cst438.sl_time_tracker_plus.AdministratorOptions.AdministratorDeleteTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.AdministratorOptions.AdministratorEditTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.AdministratorOptions.AdministratorCreateTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.AdministratorOptions.AdministratorViewTimesheetActivity;
import team10.cst438.sl_time_tracker_plus.R;

/**
 * Created by Nigel on 11/30/2015.
 */

public class AdministratorMenuActivity extends AppCompatActivity
{
    private Button viewTimesheetButton, createTimesheetButton, editTimesheetButton, deleteTimesheetButton, createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        viewTimesheetButton = (Button)findViewById(R.id.viewTimesheetButton);
        createTimesheetButton = (Button)findViewById(R.id.createTimesheetButton);
        editTimesheetButton = (Button)findViewById(R.id.editButton);
        deleteTimesheetButton = (Button)findViewById(R.id.deleteTimesheetButton);
        createUserButton = (Button)findViewById(R.id.createUserButton);

        // Below lists the administrators menu options and intents for appropriate activities.
        viewTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), AdministratorViewTimesheetActivity.class);
                startActivity(intent);
            }
        });

        createTimesheetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), AdministratorCreateTimesheetActivity.class);
                startActivity(intent);
            }
        });

        editTimesheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdministratorEditTimesheetActivity.class);
                startActivity(intent);
            }
        });

        deleteTimesheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdministratorDeleteTimesheetActivity.class);
                startActivity(intent);
            }
        });

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdministratorCreateUserActivity.class);
                startActivity(intent);
            }
        });
    }
}

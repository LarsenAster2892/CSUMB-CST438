package team10.cst438.sl_time_tracker_plus.DataClasses;

import java.util.ArrayList;

import team10.cst438.sl_time_tracker_plus.DataClasses.Timesheet;

/**
 * Created by Nigel on 11/20/2015.
 */

public class TimesheetList
{
    // Instance array list.
    private ArrayList<Timesheet> timesheetList;

    // Constructor.
    public TimesheetList()
    {
        timesheetList = new ArrayList<Timesheet>();
    }

    // Function to add timesheets to the list.
    public void add(Timesheet t)
    {
        timesheetList.add(t);
    }

    // Removes a timesheet at a given index.
    public Timesheet removeAtIndex(int i)
    {
        Timesheet removed = timesheetList.remove(i);

        return removed;
    }

    // Searches and returns the index of a timesheet.
    public int search(Timesheet t)
    {
        int index = -1;

        if (timesheetList.contains(t))
        {
            index = timesheetList.indexOf(t);
        }

        return index;
    }

    // Returns a timesheet at a given index.
    public Timesheet getAtIndex(int i)
    {
        Timesheet result = null;

        if (i < timesheetList.size())
        {
            result = timesheetList.get(i);
        }

        return result;
    }

    // To string displays all timesheets in the list.
    public String toString()
    {
        String result = "";

        for (int i = 0; i < timesheetList.size(); i++)
        {
            result.concat(timesheetList.get(i).toString());
            result.concat("\n-------------------------------------------------\n");
        }

        return result;
    }

    // Returns the total hours worked.
    public int getTotalHours()
    {
        int total = 0;

        for (int i = 0; i < timesheetList.size(); i++)
        {
            total += timesheetList.get(i).getTotalHours();
        }

        return total;
    }

    // Returns number of approved timesheets.
    public int getNumberApproved()
    {
        int total = 0;

        for (int i = 0; i < timesheetList.size(); i++)
        {
            if (timesheetList.get(i).getStatus())
                total += 1;
        }

        return total;
    }

    // Returns the size of the list.
    public int size()
    {
        return timesheetList.size();
    }

    // Returns the amount of hours approved.
    public int getApprovedHours()
    {
        int total = 0;

        for (int i = 0; i < timesheetList.size(); i++)
        {
            if (timesheetList.get(i).getStatus())
                total += timesheetList.get(i).getTotalHours();
        }

        return total;
    }

    // Returns the amount of timesheets not approved.
    public int getNumberNonApproved()
    {
        return this.size() - this.getNumberApproved();
    }

    // Returns the amount of hours not approved.
    public int getNonApprovedHours()
    {
        return this.getTotalHours() - this.getApprovedHours();
    }
}

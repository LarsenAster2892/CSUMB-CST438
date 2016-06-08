package team10.cst438.sl_time_tracker_plus.DataClasses;

/**
 * Created by Nigel on 11/20/2015.
 */

public class User
{
    // Instance variables
    private String username, fname, lname, email;
    private int role;

    // Constructor
    public User(String u, String f, String l, String e, int r)
    {
        this.username = u;
        this.fname = f;
        this.lname = l;
        this.email = e;
        this.role = r;
    }

    // Mutator methods
    public void setUsername(String u) { username = u; }
    public void setFname(String f) { fname = f; }
    public void setLname(String l) { lname = l; }
    public void setEmail(String e) { email = e; }
    public void setRole(int r) { role = r; }

    // Accessor methods
    public String getUsername() { return username; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getEmail() { return email; }
    public int getRoleAsInt() { return role; }

    // Converts the role value to a string and returns.
    public String getRoleAsString()
    {
        String str = "";

        if (role == 1)
        {
            str = "Student";
        }
        else if (role == 2)
        {
            str = "Instructor";
        }
        else if (role == 3)
        {
            str = "Supervisor";
        }
        else if (role == 4)
        {
            str = "Administrator";
        }
        else
        {
            str = "INVALID ROLE ERROR";
        }
        return str;
    }

    // To String function.
    public String toString()
    {
        String result = "";

        result.concat("Username: ");
        result.concat(this.getUsername());
        result.concat("\nFirst Name: ");
        result.concat(this.getFname());
        result.concat("\nLast Name: ");
        result.concat(this.getLname());
        result.concat("\nEmail: ");
        result.concat(this.getEmail());
        result.concat("\nRole: ");
        result.concat(this.getRoleAsString());

        return result;
    }
}

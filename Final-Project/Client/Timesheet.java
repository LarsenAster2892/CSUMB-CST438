public class Timesheet
{
  private String studentFname;
	private String studentLname;
	private String instructorFname;
	private String instructorLname;
	private String supervisorFname;
	private String supervisorLname;
	private String serviceSite;
	private String course;
	private Date   timesheetStartDate;
	private double sundayHours;
	private double mondayHours;
	private double tuesdayHours;
	private double wednesdayHours;
	private double thursdayHours;
	private double fridayHours;
	private double saturdayHours;
	
	public Timesheet(String stuFirst, String stuLast, String insFirst, String insLast, String supFirst, 
	                 String supLast, String serSite, String c, Date startDate, double sunHrs, 
					 double monHrs, double tueHrs, double wedHrs, double thuHrs, double friHrs,
					 double satHrs)
	{
		studentFname 		= stuFirst;
		studentLname 		= stuLast;
		instructorFname 	= insFirst;
		instructorLname 	= insLast;
		supervisorFname 	= supFirst;
		supervisorLname 	= supLast;
		serviceSite 		= serSite;
		course 				= c;
		timesheetStartDate 	= startDate;
		sundayHours 		= sunHrs;
		mondayHours 		= monHrs;
		tuesdayHours 		= tueHrs;
		wednesdayHours 		= wedHrs;
		thursdayHours 		= thuHrs;
		fridayHours 		= friHrs;
		saturdayHours 		= satHrs;	 
	}
	
	public String getStudentFname()    { return studentFname; }
	public String getStudentLname()    { return studentLname; }
	public String getInstructorFname() { return instructorFname; }
	public String getInstructorLname() { return instructorLname; }
	public String getSupervisorFname() { return supervisorFname; }
	public String getSupervisorLname() { return supervisorLname; }
	public String getServiceSite()     { return serviceSite; }
	public String getCourse()          { return course; }
	public Date   getStartDate()       { return timesheetStartDate; }
	public double getSundayHours()     { return sundayHours; }
	public double getMondayHours()     { return mondayHours; }
	public double getTuesdayHours()    { return tuesdayHours; }
	public double getWednesdayHours()  { return wednesdayHours; }
	public double getThursdayHours()   { return thursdayHours; }
	public double getFridayHours()     { return fridayHours; }
	public double getSaturdayHours()   { return saturdayHours; }
	
	public void setStudentFname(String n)    { studentFname = n; }
	public void setStudentLname(String n)    { studentLname = n; }
	public void setInstructorFname(String n) { instructorFname = n; }
	public void setInstructorLname(String n) { instructorLname = n; }
	public void setSupervisorFname(String n) { supervisorFname = n; }
	public void setSupervisorLname(String n) { supervisorLname = n; }
	public void setServiceSite(String s)     { serviceSite = s; }
	public void setCourse(String c)          { course = c; }
	public void setStartDate(Date d)         { timesheetStartDate = d; }
	public void setSundayHours(double h)     { sundayHours = h; }
	public void setMondayHours(double h)     { mondayHours = h; }
	public void setTuesdayHours(double h)    { tuesdayHours = h; }
	public void setWednesdayHours(double h)  { wednesdayHours = h; }
	public void setThursdayHours(double h)   { thursdayHours = h; }
	public void setFridayHours(double h)     { fridayHours = h; }
	public void setSaturdayHours(double h)   { saturdayHours = h; }
	
	public double getTotalHours()
	{
		return (sundayHours + mondayHours + tuesdayHours + wednesdayHours + thursdayHours + fridayHours + saturdayHours);
	}
}

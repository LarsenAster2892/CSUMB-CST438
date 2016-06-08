<?php

	require 'dbConnection.php'; // Used for database connection

	$sql = "CREATE TABLE Timesheets (
			timesheetID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
			studentID,
			instructorID,
			supervisorID,
			timesheetStartDate DATE,
			sundayHours DOUBLE, 
			mondayHours DOUBLE,
			tuesdayHours DOUBLE,
			wednesdayHours DOUBLE,
			thursdayHours DOUBLE,
			fridayHours DOUBLE,
			saturdayHours DOUBLE 
			FOREIGN KEY (studentID) REFERENCES Students(studentID)
			FOREIGN KEY (instructorID) REFERENCES Instructors(instructorID)
			FOREIGN KEY (supervisorID) REFERENCES Supervisors(supervisorID)
			);";

	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute();

	echo "The timesheet table is created.";

	//
	
	$sql = "CREATE TABLE Students (
			studentID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
			studentFname VARCHAR (50),
			studentLname VARCHAR (50),
			email VARCHAR (30),
			course VARCHAR (30)
			);"
		
	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute();
	
	echo "The student table is created.";
	
	//
	
	$sql = "CREATE TABLE Instructors (
			instructorID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
			instructorFname VARCHAR (50),
			instructorLname VARCHAR (50),
			email VARCHAR (30),
			course VARCHAR (30)
			);"
		
	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute();
	
	echo "The instructor table is created.";
	

	//
	
	$sql = "CREATE TABLE Supervisors (
			supervisorID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
			supervisorFname VARCHAR (50),
			supervisorLname VARCHAR (50),
			email VARCHAR (30),
			serviceSite VARCHAR (30)
			);"
		
	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute();
	
	echo "The supervisor table is created.";
	
?>

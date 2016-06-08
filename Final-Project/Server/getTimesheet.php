<?php

	require 'dbConnection.php'; // Used for database connection

	$id = $_POST['id'];
	$timesheetStartDate = $_POST['timesheetStartDate'];

	$sql = "SELECT *                              
        	FROM timesheet
        	WHERE studentID = :id
			AND timesheetStartDate = :timesheetStartDate"; 
			
	$stmt = $dbConn -> prepare($sql);    
	$stmt -> execute();                  
		
	$result = $stmt->fetchAll(); 
	
	echo json_encode($result);
	
	$dbConn = null;

?>

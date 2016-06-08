<?php

	//session_start();

	require 'dbConnection.php'; // Used for database connection
	
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql = "SELECT *
			FROM admin
			WHERE username = :username
			AND password = :password ";

	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute(array(":username" => $_POST['username'], ":password" => hash("sha1", $_POST['password'])));

	$record = $stmt -> fetch();

	if (empty($record))
	{
		echo "REJECT";
	}
	else 
	{
		$_SESSION['username'] = $record['username'];
		$_SESSION['name'] = $record['firstname'] . " " . $record['lastname'];
		$loginTime = date("h:i:sa");
		$loginDate = date("y-m-d");
		
		$sql = "INSERT INTO log
				(username, loginDate, loginTime)
				VALUES
				(:username, :loginDate, :loginTime) ";
				
		$stmt = $dbConn -> prepare($sql);
		$stmt -> execute(array(":username"=>$_SESSION['username'],
							   ":loginDate"=>$loginDate,
						       ":loginTime"=>$loginTime)); 
		
		echo "ACCEPT";
	}
	
	$dbConn = null;

?>

-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2015 at 04:10 AM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `slltracker`
--
CREATE DATABASE IF NOT EXISTS `slltracker` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `slltracker`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkAuthentication`(IN `iUsername` VARCHAR(50), IN `iPassword` VARCHAR(50), OUT `oMessage` VARCHAR(50), OUT `oUserCnt` INT)
BEGIN

 SELECT count(*)

 INTO oUserCnt
 FROM useraccount
 WHERE LOWER(UserAccount) = LOWER(TRIM(iUsername)) AND Password = SHA1(iPassword)
 LIMIT 1;
 IF (oUserCnt > 0) THEN
 	 SET oMessage = 'Valid username and password';
 ELSE
 	SET oMessage = 'Invalid username and password';
 END IF;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTimeSheetCnt`(IN `iUsername` VARCHAR(50), OUT `oMessage` VARCHAR(50), OUT `oUserCnt` INT)
BEGIN
 SET oUserCnt = -1;
 IF (iUsername IS NULL OR iUsername = '') THEN
    SET oMessage = 'Missing username'; 
 ELSE
	SELECT COUNT(*) INTO oUserCnt
	  FROM timesheet ts
	  INNER JOIN useraccount ua
		 ON (ts.UserID = ua.ID)
	WHERE LOWER(ua.UserAccount) = LOWER(TRIM(iUsername));	 
		
  END IF;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserProjects`(IN `iUsername` VARCHAR(50), OUT `oMessage` VARCHAR(50))
BEGIN
 IF (iUsername IS NULL OR iUsername = '') THEN
    SET oMessage = 'Missing username'; 
 ELSE

	SELECT Title, Description, StartDate, EndDate
	  FROM Project p
	  INNER JOIN useraccount ua
		 ON (p.ID = ua.ProjectID)
	WHERE LOWER(ua.UserAccount) = LOWER(TRIM(iUsername))	 
	LIMIT 1;	
  END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserWeekTimeSheetProject`(IN `iUsername` VARCHAR(50), IN `iWeekEndingDate` DATE, OUT `oMessage` VARCHAR(50))
BEGIN
 IF (iUsername IS NULL OR iUsername = '') THEN
    SET oMessage = 'Missing username'; 
 ELSEIF (iWeekEndingDate IS NULL OR iWeekEndingDate = '') THEN
    SET oMessage = 'Missing End Week Date'; 
 ELSE

	SELECT t.ID, WeekEndingDate, s.StatusValue
	  FROM timesheet t
	  INNER JOIN useraccount ua
		 ON (t.ID = ua.ProjectID)
	  LEFT JOIN statustypes s
	    on ( t. StatusType = s.StatusID )
	WHERE LOWER(ua.UserAccount) = LOWER(TRIM(iUsername)) 
      AND WeekEndingDate = iWeekEndingDate;	
  END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `CourseNumber` varchar(50) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `InstructorID` int(11) DEFAULT NULL,
  `ArchiveFlag` bit(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Service Learning Courses';

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`ID`, `Name`, `CourseNumber`, `StartDate`, `EndDate`, `InstructorID`, `ArchiveFlag`) VALUES
(1, 'Technology Tutoring', 'CST361s', '2015-11-01', '2015-12-31', NULL, NULL),
(2, 'Service Learning', 'CST361f', '2015-11-01', '2015-12-31', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `ID` int(11) unsigned NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `CourseID` int(11) NOT NULL,
  `Notes` varchar(500) DEFAULT '',
  `DateCreated` datetime NOT NULL DEFAULT '2015-11-01 00:00:00',
  `LastUpdatedBy` int(11) unsigned DEFAULT NULL,
  `LastUpdateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ArchiveFlag` bit(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Service Learning Projects.  Linked to course via course id';

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`ID`, `Title`, `Description`, `StartDate`, `EndDate`, `CourseID`, `Notes`, `DateCreated`, `LastUpdatedBy`, `LastUpdateDate`, `ArchiveFlag`) VALUES
(1, 'Seaside Elementary School Network', 'Upgrade of Netowrk', '2015-11-15 00:00:00', '2015-12-25 00:00:00', 1, '''', '2015-11-01 00:00:00', NULL, NULL, NULL),
(2, 'Monterey County Office', 'IT Assistance to  Monterey County', '2015-11-15 00:00:00', '2015-12-25 00:00:00', 2, '''', '2015-11-01 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `projectapprovers`
--

CREATE TABLE IF NOT EXISTS `projectapprovers` (
  `ID` int(11) unsigned NOT NULL,
  `ProjectID` int(11) unsigned NOT NULL,
  `ApproverID` int(11) NOT NULL,
  `Notes` varchar(255) DEFAULT '',
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LastUpdatedBy` int(11) unsigned DEFAULT NULL,
  `LastUpdateDate` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Service Learning TimeSheet Approvers for Projects.  Linked to Project via project id';

--
-- Dumping data for table `projectapprovers`
--

INSERT INTO `projectapprovers` (`ID`, `ProjectID`, `ApproverID`, `Notes`, `DateCreated`, `LastUpdatedBy`, `LastUpdateDate`) VALUES
(1, 1, 3, NULL, '2015-11-01 00:00:00', NULL, NULL),
(2, 2, 2, '', '2015-11-01 00:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `statustypes`
--

CREATE TABLE IF NOT EXISTS `statustypes` (
  `StatusID` varchar(1) NOT NULL,
  `StatusValue` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statustypes`
--

INSERT INTO `statustypes` (`StatusID`, `StatusValue`) VALUES
('A', 'Approved'),
('N', 'New'),
('R', 'Rejected'),
('S', 'Submitted');

-- --------------------------------------------------------

--
-- Table structure for table `timesheet`
--

CREATE TABLE IF NOT EXISTS `timesheet` (
  `ID` int(11) unsigned NOT NULL,
  `StatusType` varchar(1) DEFAULT NULL,
  `UserID` int(11) unsigned NOT NULL,
  `projectID` int(11) unsigned NOT NULL,
  `WeekEndingDate` date NOT NULL,
  `CreatedBy` int(11) unsigned NOT NULL,
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LastUpdatedBy` int(11) unsigned DEFAULT NULL,
  `LastUpdateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ArchiveFlag` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Service Learning TimesSheets for students';

-- --------------------------------------------------------

--
-- Table structure for table `timesheetaction`
--

CREATE TABLE IF NOT EXISTS `timesheetaction` (
  `ID` int(11) NOT NULL,
  `TimeSheetID` int(11) unsigned NOT NULL,
  `StudentID` int(11) NOT NULL,
  `ApproverID` int(11) NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `CourseID` int(11) NOT NULL,
  `ActionBy` int(11) unsigned NOT NULL,
  `ActionDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ActionType` tinyint(4) NOT NULL COMMENT 'Values\n2 - Submitted TimeSheet\n3 - Approved TimeSheet\n4 - Rejected TimeSheet'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Service Learning TimeSheet Actions.  Contains list of actions that require response.\nTimeSheet submit generates a record that requires approve or reject\nTimeSheet reject generates a record that requires resubmit';

-- --------------------------------------------------------

--
-- Table structure for table `timesheetdetail`
--

CREATE TABLE IF NOT EXISTS `timesheetdetail` (
  `ID` int(11) unsigned NOT NULL,
  `TimeSheetID` int(11) unsigned NOT NULL,
  `UserID` int(11) unsigned NOT NULL,
  `WeekDate` date DEFAULT NULL,
  `WeekDayNumber` tinyint(4) DEFAULT NULL,
  `SeqNumber` int(11) DEFAULT NULL,
  `Hours` decimal(10,0) unsigned DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Service Learning Timesheet entry records  (1 for each start and end time on timesheet)';

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE IF NOT EXISTS `useraccount` (
  `ID` int(11) unsigned NOT NULL,
  `UserAccount` varchar(50) NOT NULL COMMENT 'If Student then User OtterID, otherwise first Letter of User''s first name, first 3 of User''s last name and random 4 digits...',
  `CourseID` int(11) DEFAULT NULL,
  `ProjectID` int(11) unsigned DEFAULT NULL COMMENT 'ID of project user is assigned to if type is student',
  `UserType` varchar(1) DEFAULT NULL COMMENT 'A - AdminP - ApproverS - Student',
  `FirstName` varchar(255) NOT NULL COMMENT 'User''s First Name',
  `LastName` varchar(255) NOT NULL COMMENT 'User''s Last Name',
  `OtherName` varchar(255) DEFAULT NULL COMMENT 'User''s Nick Name or Other name',
  `EmailAddress` varchar(50) NOT NULL COMMENT 'User''s Email Address',
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `CellPhoneNumber` varchar(15) DEFAULT NULL,
  `Password` varchar(50) NOT NULL DEFAULT '',
  `Notes` varchar(1000) DEFAULT NULL,
  `SecurityQuestion` varchar(255) NOT NULL DEFAULT '',
  `SecurityAnswer` varchar(255) NOT NULL DEFAULT '',
  `IsActive` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `LoginReties` tinyint(4) DEFAULT NULL,
  `NextPasswordChangeDate` date DEFAULT NULL,
  `LastLoginDate` datetime DEFAULT NULL,
  `LastUpdatedBy` int(11) unsigned DEFAULT NULL,
  `LastUpdateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ArchiveFlag` bit(1) DEFAULT NULL COMMENT 'Set true (not zero) when user account has been archived otherwise false (zero)'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Service Learning User accounts that can access the system';

--
-- Dumping data for table `useraccount`
--

INSERT INTO `useraccount` (`ID`, `UserAccount`, `CourseID`, `ProjectID`, `UserType`, `FirstName`, `LastName`, `OtherName`, `EmailAddress`, `PhoneNumber`, `CellPhoneNumber`, `Password`, `Notes`, `SecurityQuestion`, `SecurityAnswer`, `IsActive`, `LoginReties`, `NextPasswordChangeDate`, `LastLoginDate`, `LastUpdatedBy`, `LastUpdateDate`, `ArchiveFlag`) VALUES
(1, 'tmcgee1111', NULL, NULL, 'Z', 'Tim', 'McGee', 'Probie', 'Tmcgee@CSUMB.edu', '(831) 111-1111', '(831) 111-1112', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, '''', '''', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'JJones222', 2, 2, 'S', 'Jessica', 'Jones', NULL, 'JJones@CSUMB.edu', '(831) 222-2222', '(831) 222-2221', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', '', 'Greatest Superhero', 'myself', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'NFurry9999', NULL, NULL, 'A', 'Nick', 'Furry', '', 'NFurry@CSUMB.edu', '(831) 999-9999', '(831) 999-9991', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, '''', '''', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'PPaker5555', 1, 1, 'S', 'Peter', 'Parker', 'Spiderman', 'PParker@CSUMB.edu', '(831) 555-5555', '(831) 555-5551', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, '''', '''', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'SRogers6666', NULL, NULL, 'A', 'Steve', 'Rogers', 'Captain America', 'SRgoers@CSUMB.edu', '(831) 666-6661', '(831) 666-6662', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, '''', '''', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'TStark7777', NULL, NULL, 'I', 'Tony', 'Stark', 'Ironman', 'TStark@CSUMB.edu', '(831) 777-7777', '(831) 777-7771', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, '''', '''', 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `userprojects`
--

CREATE TABLE IF NOT EXISTS `userprojects` (
  `ID` int(11) NOT NULL,
  `UserID` int(11) unsigned NOT NULL,
  `ProjectID` int(11) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Links users to projects.  User can be on multiple projects and projects can have multiple users';

--
-- Dumping data for table `userprojects`
--

INSERT INTO `userprojects` (`ID`, `UserID`, `ProjectID`) VALUES
(1, 5, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `usertypes`
--

CREATE TABLE IF NOT EXISTS `usertypes` (
  `TypeID` varchar(1) NOT NULL,
  `TypeValue` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usertypes`
--

INSERT INTO `usertypes` (`TypeID`, `TypeValue`) VALUES
('A', 'Approver'),
('I', 'Instructor'),
('S', 'Student'),
('Z', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `project` (`Title`),
  ADD KEY `fk_courseprojects` (`CourseID`);

--
-- Indexes for table `projectapprovers`
--
ALTER TABLE `projectapprovers`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_project_approvers` (`ProjectID`);

--
-- Indexes for table `statustypes`
--
ALTER TABLE `statustypes`
  ADD PRIMARY KEY (`StatusID`),
  ADD UNIQUE KEY `StatusID` (`StatusID`);

--
-- Indexes for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_timesheet_user` (`UserID`),
  ADD KEY `StatusType` (`StatusType`) USING BTREE;

--
-- Indexes for table `timesheetaction`
--
ALTER TABLE `timesheetaction`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_timesheet_action` (`TimeSheetID`);

--
-- Indexes for table `timesheetdetail`
--
ALTER TABLE `timesheetdetail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_timesheetdetail_timesheet` (`TimeSheetID`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `UserAccount` (`UserAccount`),
  ADD KEY `CourseID` (`CourseID`),
  ADD KEY `ProjectID` (`ProjectID`),
  ADD KEY `fk_usercourse` (`CourseID`),
  ADD KEY `fk_project_users` (`ProjectID`),
  ADD KEY `UserType` (`UserType`);

--
-- Indexes for table `userprojects`
--
ALTER TABLE `userprojects`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_userprojects` (`UserID`),
  ADD KEY `fk_projectusers` (`ProjectID`);

--
-- Indexes for table `usertypes`
--
ALTER TABLE `usertypes`
  ADD PRIMARY KEY (`TypeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `projectapprovers`
--
ALTER TABLE `projectapprovers`
  MODIFY `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `timesheet`
--
ALTER TABLE `timesheet`
  MODIFY `ID` int(11) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `timesheetaction`
--
ALTER TABLE `timesheetaction`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `timesheetdetail`
--
ALTER TABLE `timesheetdetail`
  MODIFY `ID` int(11) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `userprojects`
--
ALTER TABLE `userprojects`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `fk_courseprojects` FOREIGN KEY (`CourseID`) REFERENCES `course` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `projectapprovers`
--
ALTER TABLE `projectapprovers`
  ADD CONSTRAINT `fk_project_approvers` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `timesheet`
--
ALTER TABLE `timesheet`
  ADD CONSTRAINT `fk_timesheet_user` FOREIGN KEY (`UserID`) REFERENCES `useraccount` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `timesheetaction`
--
ALTER TABLE `timesheetaction`
  ADD CONSTRAINT `fk_timesheet_action` FOREIGN KEY (`TimeSheetID`) REFERENCES `timesheet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `timesheetdetail`
--
ALTER TABLE `timesheetdetail`
  ADD CONSTRAINT `fk_timesheetdetail_timesheet` FOREIGN KEY (`TimeSheetID`) REFERENCES `timesheet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD CONSTRAINT `fk_project_users` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usercourse` FOREIGN KEY (`CourseID`) REFERENCES `course` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `useraccount_ibfk_1` FOREIGN KEY (`UserType`) REFERENCES `usertypes` (`TypeID`);

--
-- Constraints for table `userprojects`
--
ALTER TABLE `userprojects`
  ADD CONSTRAINT `fk_projectusers` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_userprojects` FOREIGN KEY (`UserID`) REFERENCES `useraccount` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

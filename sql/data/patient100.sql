DELETE FROM users WHERE MID = 100;
/*For every insert, first clear the database of those entries.*/
INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
			VALUES (100, '1a91d62f7ca67399625a4368a6ab5d4a3baa6073', 'patient', 'Is it broken?', 'I can fix it.');
/*password: pw*/
DELETE FROM patients WHERE MID = 100;
INSERT INTO patients (MID, firstName, lastName, email, phone) 
VALUES (100, 'Anakin', 'Skywalker', 'thechosenone@itrust.com', '919-419-5555');

DELETE FROM officevisits WHERE PatientID = 100;
INSERT INTO officevisits(id,visitDate,HCPID,notes,HospitalID,PatientID)
	VALUES (1093,'2012-1-01',9000000000,'Testing midi-chlorian count.','1',100);

DELETE FROM ovmedication WHERE VisitID = 1093;
INSERT INTO ovmedication(NDCode, VisitID, StartDate, EndDate, Dosage, Instructions)
	VALUES ('483012382', 1093, '2012-3-01', ADDDATE(CURDATE(),9000), 50, 'Take twice daily');
	/*In that OV, Kelly Doctor documents a future rx for Midichlominene from Mar. 1 to a long time from now in a galaxy far far away*/

DELETE FROM ovdiagnosis WHERE VisitID = 1093;
INSERT INTO ovdiagnosis(ICDCode, VisitID) VALUES 
			(493.00, 1093);
			
INSERT INTO allergies(PatientID, Description, FirstFound, Code)
	VALUES (100,'Lantus','2012-2-03', '00882219'); /* Allergy for Lantus */
			
DELETE FROM declaredhcp WHERE PatientID = 100;
INSERT INTO declaredhcp(PatientID, HCPID) VALUES (100, 9000000000);
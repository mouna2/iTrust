INSERT INTO patients
(MID,
lastName, 
firstName,
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
dateofbirth,
mothermid,
fathermid,
bloodtype,
ethnicity,
gender,
topicalnotes)
VALUES
(3,
'Needs',
'Care',
'fake@email.com',
'1247 Noname Dr',
'Suite 106',
'Raleigh', 
'NC',
'27606-1234',
'919-971-0000',
'Mum',
'704-532-2117',
'Aetna', 
'1234 Aetna Blvd', 
'Suite 602',
'Charlotte',
'NC',
'28215',
'704-555-1234', 
'ChetumNHowe', 
'1950-05-10',
1,
0,
'AB+',
'African American',
'Male',
'')
 ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
			VALUES (3, '1a91d62f7ca67399625a4368a6ab5d4a3baa6073', 'patient', 'opposite of yin', 'yang')
 ON DUPLICATE KEY UPDATE MID = MID;
 /*password: pw*/

INSERT INTO personalhealthinformation
(PatientID,Height,Weight,Smoker,SmokingStatus,BloodPressureN,BloodPressureD,CholesterolHDL,CholesterolLDL,CholesterolTri,HCPID, AsOfDate)
VALUES ( 3,  72,   180,   0, 9,     100,          100,           40,             100,         100,          9000000000, '2005-06-07 20:33:58')
 ON DUPLICATE KEY UPDATE PatientID = PatientID;

INSERT INTO officevisits(id,visitDate,HCPID,notes,HospitalID,PatientID)
VALUES (111,'2005-10-10',9000000000,'Old office visit.','',3)
 ON DUPLICATE KEY UPDATE id = id;

INSERT INTO ovdiagnosis(ICDCode, VisitID) VALUES 
(487.00, 111)
 ON DUPLICATE KEY UPDATE id = id;

INSERT INTO declaredhcp(PatientID,HCPID) VALUE(3, 9000000003)
 ON DUPLICATE KEY UPDATE PatientID = PatientID;

INSERT INTO representatives(RepresenterMID, RepresenteeMID) VALUES(2,3)
 ON DUPLICATE KEY UPDATE RepresenterMID = RepresenterMID;

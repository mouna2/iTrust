INSERT INTO personnel(
MID,
AMID,
role,
lastName, 
firstName, 
address1,
address2,
city,
state,
zip,
phone,
specialty,
email)
VALUES (
5000000002,
null,
'lt',
'Guy',
'Nice',
'4321 My Road St',
'PO BOX 2',
'CityName',
'NY',
'12345-1234',
'999-888-7777',
'tissue',
'nguy@iTrust.org'
)ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) VALUES(5000000002, '1a91d62f7ca67399625a4368a6ab5d4a3baa6073', 'lt', 'first letter?', 'a')
ON DUPLICATE KEY UPDATE MID = MID;
/*password: pw*/

INSERT INTO hcpassignedhos(HCPID, HosID) VALUES(5000000002,'1')
ON DUPLICATE KEY UPDATE HCPID = HCPID;

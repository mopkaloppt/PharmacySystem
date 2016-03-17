drop table Patient;
drop table Doctor;
drop table Drug;
drop table Pharmacy;
drop table Pharmacist;
drop table Prescribed;
drop table Inventory;
drop table Purchase;

CREATE TABLE Patient (
	patientID INTEGER,
	doctorID INTEGER,
	name CHAR(20),
	illness CHAR(20),
	address CHAR(30),
	PRIMARY KEY (patientID, doctorID),
	FOREIGN KEY (doctorID) REFERENCES Doctor);
  
grant select on Patient to public;
 
CREATE TABLE Doctor(
	doctorID INTEGER,
	name CHAR(20),
	credentials CHAR(30),
	address CHAR(30),
	phoneNumber CHAR(10),
	PRIMARY KEY (doctorID));
	
grant select on Doctor to public;
 
CREATE TABLE Drug(
	drugID INTEGER,
	name CHAR(30),
	info CHAR(30),
	dosage CHAR(30),
	PRIMARY KEY (drugID));
  
grant select on Drug to public;
 
CREATE TABLE Pharmacy(
	pharmacyID INTEGER,
	name CHAR(20),
	address CHAR(20),
	PRIMARY KEY (pharmacyID));
  
grant select on Pharmacy to public;
 
CREATE TABLE Pharmacist(
	employeeID INTEGER,
	name CHAR(20),
	pharmacyID INTEGER NOT NULL,
	PRIMARY KEY (employeeID),
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy);
  
grant select on Pharmacist to public;
 
CREATE TABLE Prescribed(
	patientID INTEGER,
	doctorID INTEGER,
	drugID INTEGER,
	isRenewable BIT,
	datePrescribed DATE,
	PRIMARY KEY (patientID, doctorID, drugID),
	FOREIGN KEY (patientID) REFERENCES Patient,
	FOREIGN KEY (doctorID) REFERENCES Doctor,
	FOREIGN KEY (drugID), REFERENCES Drug));
  
grant select on Prescribed to public;
 
CREATE TABLE Inventory(
	drugID INTEGER,
	pharmacyID INTEGER,
	stock INTEGER,
	PRIMARY KEY (drugID, pharmacyID),
	FOREIGN KEY (drugID) REFERENCES Drug,
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy));
  
grant select on Inventory to public;
 
CREATE TABLE Purchase(
	receiptNumber INTEGER,
	patientID INTEGER NOT NULL,
	drugID INTEGER NOT NULL,
	pharmacyID INTEGER NOT NULL,
	price REAL,
	datePurchased DATE,
	PRIMARY KEY (receiptNumber),
	FOREIGN KEY (patientID) REFERENCES Patient,
	FOREIGN KEY (drugID) REFERENCES Drug,
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy));
  
 grant select on Purchase to public;
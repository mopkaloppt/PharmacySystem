drop table Purchase;
drop table Inventory;
drop table Prescribed;
drop table Pharmacist;
drop table Patient;
drop table Doctor;
drop table Drug;
drop table Pharmacy;


CREATE TABLE Doctor(
	doctorID INTEGER NOT NULL,
	name CHAR(20) NULL,
	credentials CHAR(30) NULL,
	address CHAR(30) NULL,
	phoneNumber CHAR(10) NULL,
	PRIMARY KEY (doctorID));
	
grant select on Doctor to public;
 
 CREATE TABLE Patient (
	patientID INTEGER NOT NULL,
	doctorID INTEGER NOT NULL,
	name CHAR(20) NULL,
	illness CHAR(20) NULL,
	address CHAR(30) NULL,
	primary key (patientID),
	FOREIGN KEY (doctorID) REFERENCES Doctor);
  
grant select on Patient to public;
 
CREATE TABLE Drug(
	drugID INTEGER NOT NULL,
	name CHAR(30) NULL,
	info CHAR(30) NULL,
	dosage CHAR(30) NULL,
	PRIMARY KEY (drugID));
  
grant select on Drug to public;
 
CREATE TABLE Pharmacy(
	pharmacyID INTEGER NOT NULL,
	name CHAR(20) NULL,
	address CHAR(20) NULL,
	PRIMARY KEY (pharmacyID));
  
grant select on Pharmacy to public;
 
CREATE TABLE Pharmacist(
	employeeID INTEGER NOT NULL,
	name CHAR(20) NULL,
	pharmacyID INTEGER NOT NULL,
	PRIMARY KEY (employeeID),
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy);
  
grant select on Pharmacist to public;
 
CREATE TABLE Prescribed(
	patientID INTEGER NOT NULL,
	doctorID INTEGER NOT NULL,
	drugID INTEGER NOT NULL,
	isRenewable CHAR NULL,
	datePrescribed DATE NULL,
	PRIMARY KEY (patientID, doctorID, drugID),
	FOREIGN KEY (patientID) REFERENCES Patient,
	FOREIGN KEY (doctorID) REFERENCES Doctor,
	FOREIGN KEY (drugID) REFERENCES Drug);
  
grant select on Prescribed to public;
 
CREATE TABLE Inventory(
	drugID INTEGER NOT NULL,
	pharmacyID INTEGER NOT NULL,
	stock INTEGER NULL,
	PRIMARY KEY (drugID, pharmacyID),
	FOREIGN KEY (drugID) REFERENCES Drug,
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy);
  
grant select on Inventory to public;
 
CREATE TABLE Purchase(
	receiptNumber INTEGER NOT NULL,
	patientID INTEGER NOT NULL,
	drugID INTEGER NOT NULL,
	pharmacyID INTEGER NOT NULL,
	price REAL NULL,
	datePurchased DATE NULL,
	PRIMARY KEY (receiptNumber),
	FOREIGN KEY (patientID) REFERENCES Patient,
	FOREIGN KEY (drugID) REFERENCES Drug,
	FOREIGN KEY (pharmacyID) REFERENCES Pharmacy);
  
 grant select on Purchase to public;

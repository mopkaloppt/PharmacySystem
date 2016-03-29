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
 
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('943-67-1622', 'Amy Walker', 'MD, UBC', '47 Westridge Alley', '504-(301)536-4500');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('673-66-6693', 'Benjamin Arnold', 'MD, Harvard', '2286 Chinook Way', '62-(334)893-1089');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('640-59-7623', 'Clarence Stevens', 'MD, UBC', '81031 Rowland Road', '81-(114)158-1687');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('145-30-5767', 'Louise Bennett', 'MD, Yale', '6174 Northfield Court', '62-(853)846-9199');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('429-33-6412', 'Aaron Knight', 'MD, Stanford', '0 Armistice Court', '27-(618)654-1697');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('206-56-5246', 'Justin Wells', 'MD, UCLA', '5512 Ridgeway Avenue', '46-(691)136-0898');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('662-97-6371', 'Kimberly Hicks', 'MD, U of T', '299 Tony Place', '95-(627)287-6892');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('311-01-1505', 'Aaron Pierce', 'MD, McGill', '0 Huxley Center', '62-(806)527-5839');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('962-90-3067', 'Judy Riley', 'MD, UBC ', '29 Schiller Place', '503-(896)777-8676');
insert into Doctor (doctorID, name, credentials, address, phoneNumber) values ('475-44-2475', 'Kathryn Hicks', 'MD, UBC', '78 Brown Court', '66-(500)460-0006');

insert into Patient (patientID, doctorID, name, illness, address) values ('846-71-2990', '281-24-1573', 'Arthur Dixon', 'Cough', '41 Maryland Road');
insert into Patient (patientID, doctorID, name, illness, address) values ('769-72-3810', '408-85-9359', 'Aaron Wilson', 'Cold', '65091 Macpherson Circle');
insert into Patient (patientID, doctorID, name, illness, address) values ('649-90-6841', '470-09-9489', 'Andrew Nichols', 'Flu', '0 Red Cloud Road');
insert into Patient (patientID, doctorID, name, illness, address) values ('944-40-7727', '693-82-7244', 'Juan Ruiz', 'Headache', '7176 Aberg Way');
insert into Patient (patientID, doctorID, name, illness, address) values ('543-40-1716', '479-46-7261', 'Gary Kelley', 'Fever', '548 Scott Hill');
insert into Patient (patientID, doctorID, name, illness, address) values ('720-02-4798', '473-50-1689', 'Jesse Wilson', 'Asthma', '72 Wayridge Alley');
insert into Patient (patientID, doctorID, name, illness, address) values ('943-81-9749', '778-66-4196', 'Andrew Fernandez', 'Diabetes', '80782 Merry Park');
insert into Patient (patientID, doctorID, name, illness, address) values ('749-64-0579', '670-21-1175', 'Christopher Hanson', 'Joint Pain', '94605 Mcguire Lane');
insert into Patient (patientID, doctorID, name, illness, address) values ('582-38-2357', '385-71-7486', 'William Bailey', 'Cancer', '9553 Kinsman Park');
insert into Patient (patientID, doctorID, name, illness, address) values ('909-69-9375', '359-07-4930', 'Maria Welch', 'Nausea', '0 Lake View Parkway');

insert into Drug (drugID, name, info, dosage) values ('43857-0138', 'Acetylcholine Chloride', 'Multisource radiosurgery', '100mg');
insert into Drug (drugID, name, info, dosage) values ('50268-334', 'Fluoxetine', 'Punctum repair NEC', '100mg');
insert into Drug (drugID, name, info, dosage) values ('59779-166', 'Ibuprofen', 'Cell blk/pap-lower GI', '200mg');
insert into Drug (drugID, name, info, dosage) values ('59779-470', 'Head Congestion Cold Relief', 'Anterior remov vitreous', '250mg');
insert into Drug (drugID, name, info, dosage) values ('41250-837', 'pain relief pm', 'Op red-int fix tib/fibul', '250mg');
insert into Drug (drugID, name, info, dosage) values ('52125-774', 'Ceftriaxone', 'Los excis prostatic les', '300mg');
insert into Drug (drugID, name, info, dosage) values ('49288-0652', 'Blue Spruce', 'Wide exc bony palate les', '150mg');
insert into Drug (drugID, name, info, dosage) values ('15127-332', 'Acetaminophen', 'Closed brain biopsy', '150mg');
insert into Drug (drugID, name, info, dosage) values ('63560-001', 'Oxygen', 'Ins non-d-e non-cor stnt', '500mg');
insert into Drug (drugID, name, info, dosage) values ('67457-582', 'fondaparinux sodium', 'Other spinal dx proc', '500mg');

insert into Pharmacy (pharmacyID, name, address) values ('487-54-2265', 'Topicshots', '9930 Orin Pass');
insert into Pharmacy (pharmacyID, name, address) values ('487-72-7362', 'Katz Pharmacy', '38 Rutledge Pass');
insert into Pharmacy (pharmacyID, name, address) values ('248-84-8911', 'Shopper’s Drug Mart ', '92 Dovetail Point');
insert into Pharmacy (pharmacyID, name, address) values ('859-23-9061', 'Jen’s Pharmacy', '05526 Sunnyside Crossing');
insert into Pharmacy (pharmacyID, name, address) values ('968-18-3896', 'Pharmacare', '95305 Hansons Drive');
insert into Pharmacy (pharmacyID, name, address) values ('624-70-8085', 'Save On Drugs', '2 Del Sol Court');
insert into Pharmacy (pharmacyID, name, address) values ('876-02-0624', 'Drugs R’ Us', '37 Hanover Court');
insert into Pharmacy (pharmacyID, name, address) values ('330-68-0491', 'Super Drugs', '9605 Merrick Place');
insert into Pharmacy (pharmacyID, name, address) values ('172-06-7655', 'Health First Pharmacy', '876 Warrior Trail');
insert into Pharmacy (pharmacyID, name, address) values ('393-92-4416', 'Holy Cross Pharmacy', '3 Holy Cross Alley');

insert into Pharmacist (employeeID, name, pharmacyID) values ('945-89-1816', 'Phyllis Nichols', '186-49-3340');
insert into Pharmacist (employeeID, name, pharmacyID) values ('466-27-1517', 'Julie Welch', '608-76-9920');
insert into Pharmacist (employeeID, name, pharmacyID) values ('509-64-0127', 'Evelyn Collins', '688-56-6645');
insert into Pharmacist (employeeID, name, pharmacyID) values ('314-97-6582', 'Donna Sanchez', '374-39-2043');
insert into Pharmacist (employeeID, name, pharmacyID) values ('898-59-2626', 'Paul Welch', '521-15-1820');
insert into Pharmacist (employeeID, name, pharmacyID) values ('138-78-3352', 'Susan Jacobs', '123-69-2123');
insert into Pharmacist (employeeID, name, pharmacyID) values ('595-92-0180', 'Brandon Medina', '487-20-0361');
insert into Pharmacist (employeeID, name, pharmacyID) values ('120-54-6986', 'Nicole Kennedy', '934-01-4811');
insert into Pharmacist (employeeID, name, pharmacyID) values ('552-78-7482', 'Christine Sullivan', '610-52-4344');
insert into Pharmacist (employeeID, name, pharmacyID) values ('599-94-9389', 'Lisa Allen', '169-07-1558');

insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('495-76-3239', '896-09-8027', '60456-576', true, '4/6/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('111-86-1672', '892-10-2903', '49288-0174', false, '2/2/2016');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('750-13-0993', '381-85-6874', '61786-041', false, '4/16/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('108-34-7871', '623-00-0256', '52533-002', true, '5/13/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('835-05-0444', '680-01-3957', '54162-926', false, '7/21/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('616-13-9150', '818-27-1723', '57366-0001', false, '4/19/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('658-50-0883', '527-43-2880', '41163-445', false, '5/29/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('120-51-9098', '480-64-2133', '49349-536', false, '5/29/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('838-15-1634', '575-27-1135', '68828-101', false, '6/21/2015');
insert into Prescribed (patientID, doctorID, drugID, isRenewable, datePrescribed) values ('722-37-6736', '339-94-9814', '23155-047', true, '2/1/2016');

insert into Inventory (drugID, pharmacyID, stock) values ('63187-022', '394-27-9031', 61);
insert into Inventory (drugID, pharmacyID, stock) values ('56151-682', '241-31-8380', 71);
insert into Inventory (drugID, pharmacyID, stock) values ('60681-9002', '869-96-4677', 32);
insert into Inventory (drugID, pharmacyID, stock) values ('0603-5772', '280-76-8416', 9);
insert into Inventory (drugID, pharmacyID, stock) values ('10812-814', '242-21-7042', 95);
insert into Inventory (drugID, pharmacyID, stock) values ('64117-291', '218-42-7543', 26);
insert into Inventory (drugID, pharmacyID, stock) values ('34876-001', '650-15-8618', 41);
insert into Inventory (drugID, pharmacyID, stock) values ('60429-902', '123-30-4903', 8);
insert into Inventory (drugID, pharmacyID, stock) values ('53499-0293', '615-00-5542', 4);
insert into Inventory (drugID, pharmacyID, stock) values ('68682-355', '109-08-4596', 93);

insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('67592379859448053', '604-82-0355', '0093-5455', '960-35-7672', '$63.81', '1/20/2016');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('30296814679948', '860-53-0620', '54868-1949', '362-88-4700', '$29.24', '1/28/2016');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('3531155093000432', '220-84-2984', '55910-235', '668-07-0254', '$16.62', '8/22/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('3544684574078935', '860-05-8071', '52698-001', '370-73-8043', '$92.51', '2/11/2016');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('5020032478927930', '153-34-2859', '55154-3380', '689-28-8349', '$14.50', '12/22/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('501871161213168746', '654-14-1742', '10096-0152', '696-29-5090', '$20.35', '6/18/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('3533518134844183', '730-98-1344', '0054-3566', '602-35-5838', '$54.24', '3/22/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('201520360177921', '670-97-5502', '55714-4510', '128-33-8656', '$47.97', '11/3/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('30379282750373', '441-58-7512', '63629-3021', '385-48-9381', '$19.34', '12/29/2015');
insert into Purchase (receiptNumber, patientID, drugID, pharmacyID, price, datePurchased) values ('3537861903356054', '458-38-8111', '50804-200', '930-58-2856', '$68.36', '2/16/2016');

-- specializations
insert into clinic.specialization values(1, "Osteologist");
insert into clinic.specialization values(2,"Gynecologist");
insert into clinic.specialization values(3,"Pediatrician");
insert into clinic.specialization values(4,"Surgeo");
insert into clinic.specialization values(5,"Dermatologist");
insert into clinic.specialization values(6,"Ophthalmologist");
insert into clinic.specialization values(7,"Otolaryngologist");
insert into clinic.specialization values(8,"Cardiologist");
insert into clinic.specialization values(9,"Neurologist");
insert into clinic.specialization values(10,"Radiologist");
insert into clinic.specialization values(11,"Internal Medicine");


-- Reservation Types
insert into clinic.reservation_type values(1, "Visit");
insert into clinic.reservation_type values(2, "Test");

-- Prescription Types
insert into clinic.prescription_type values(1, "Medicine");
insert into clinic.prescription_type values(2, "Test");

-- Test
insert into clinic.test values (1, 'CBC', 20);


-- users
INSERT INTO clinic.user VALUES (1,'DrOmran','dr123john',3);
INSERT INTO clinic.user VALUES (2,'elGamal','dr123spar',3);
INSERT INTO clinic.user VALUES (3,'aa','aa123456',2);
INSERT INTO clinic.user VALUES (4,'oKher','ok987',2);
INSERT INTO clinic.user VALUES (5,'ahasan','aHasan1',2);
INSERT INTO clinic.user VALUES (6,'Admin','Admin',1);


-- doctors
insert into clinic.doctor values (1, 'Mostafa omran', 'Cairo', 25.45646, 23.9874231, 1, 'j@j.com', '01000000', 1);
insert into clinic.doctor values (2, 'Emad ElGamal', 'Giza' , 22.876543 , 23.234567, 2, 'j.black@hospital.com', '011222333', 1)

-- doctor availability
INSERT INTO clinic.doctor_availability VALUES (1,'Sun',4,1);
INSERT INTO clinic.doctor_availability VALUES (2,'Sun',4.5,1);
INSERT INTO clinic.doctor_availability VALUES (3,'Sun',5,1);
INSERT INTO clinic.doctor_availability VALUES (4,'Sun',5.5,1);
INSERT INTO clinic.doctor_availability VALUES (5,'Wed',4,2);
INSERT INTO clinic.doctor_availability VALUES (6,'Wed',4.5,2);
INSERT INTO clinic.doctor_availability VALUES (7,'Wed',5,2);
INSERT INTO clinic.doctor_availability VALUES (8,'Wed',5.5,2);

-- Patients
INSERT INTO clinic.patient VALUES (1,'Ahmed Ali',3,'a.ali@yahoo.com','012345678');
INSERT INTO clinic.patient VALUES (2,'Omar Kheirat',4,'o.kher@live.com','0118765433');
INSERT INTO clinic.patient VALUES (3,'Ahmed Hasan',5,'aHasan@yahoo.com','012345678');


-- Messages
INSERT INTO clinic.message  VALUES (1,'Reserve your visit in the next week and get 25% dicsount on the next visit',6,3,'2017-05-03 00:24:06');
INSERT INTO clinic.message  VALUES (2,'Reserve your visit in the next week and get 25% dicsount on the next visit',6,4,'2017-05-03 00:24:06');
INSERT INTO clinic.message  VALUES (3,'\"Exclusive discount on all test during May\"',6,3,'2017-05-03 00:29:06');
INSERT INTO clinic.message  VALUES (4,'\"Exclusive discount on all test during May\"',6,4,'2017-05-03 00:29:06');
INSERT INTO clinic.message  VALUES (5,'\"Exclusive discount on all test during May\"',6,5,'2017-05-03 00:29:06');



-- Bills
INSERT INTO clinic.bill VALUES (1,200.00,1,100.00,100.00);
INSERT INTO clinic.bill VALUES (2,100.00,2,100.00,100.00);
INSERT INTO clinic.bill VALUES (3,150.00,3,80.00,70.00);
INSERT INTO clinic.bill VALUES (4,250.00,2,250.00,0.00);


-- Reservations
INSERT INTO clinic.patient_reservation (id, dateandtime, bill_id, doctor_availability_id, doctor_id, reservation_type_id, reservation_code) VALUES ('1', '2017-05-03 00:16:00', '1', '5', '1', '1', 'VS_0101');
INSERT INTO clinic.patient_reservation (id, dateandtime, bill_id, doctor_availability_id, doctor_id, reservation_type_id, reservation_code) VALUES ('2', '2017-05-07 00:16:00', '2', '1', '2', '1', 'VS_0201');

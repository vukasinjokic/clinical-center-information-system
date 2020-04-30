-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- Inserting clinic center admins ----------------------------------------------------
insert into clinic_center_admins			-- id = 1
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values (nextval('ust_seq_user'), 'nikola@gmail.com', 'superadmin', 'Nikola', 'Stojanović', 'Nepoznata 1', 'Orašac', 'Srbija', '0641234567', '11111111111');

insert into clinic_center_admins			-- id = 2
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values (nextval('ust_seq_user'), 'vukasin@gmail.com', 'superadmin', 'Vukašin', 'Jokić', 'Nepoznata 2', 'Vršac', 'Srbija', '0641234567', '22222222222');

insert into clinic_center_admins			-- id = 3
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values (nextval('ust_seq_user'), 'nemanja@gmail.com', 'superadmin', 'Nemanja', 'Jevtić', 'Nepoznata 3', 'Dobrinci', 'Srbija', '0641234567', '33333333333');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting code books ---------------------------------------------------------
insert into code_book (id) values (1);			-- id = 1
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting diagnoses ----------------------------------------------------------
insert into diagnoses			-- id = 1
(code_book_id, description, code)
values (1, 'Kolera', 'A00');

insert into diagnoses			-- id = 2
(code_book_id, description, code)
values (1, 'Tifusna groznica i paratifusna groznica', 'A01');

insert into diagnoses			-- id = 3
(code_book_id, description, code)
values (1, 'Trbušni tifus', 'A010');

insert into diagnoses			-- id = 4
(code_book_id, description, code)
values (1, 'Paratifus A', 'A011');

insert into diagnoses			-- id = 5
(code_book_id, description, code)
values (1, 'Druge infekcije uzrokovane salmonelama', 'A02');

insert into diagnoses			-- id = 6
(code_book_id, description, code)
values (1, 'Zapaljenje tankog creva uzrokovanog salmonelama', 'A020');

insert into diagnoses			-- id = 7
(code_book_id, description, code)
values (1, 'Šigeloza', 'A03');

insert into diagnoses			-- id = 8
(code_book_id, description, code)
values (1, 'Botulizam', 'A051');

insert into diagnoses			-- id = 9
(code_book_id, description, code)
values (1, 'Trovanje hranom', 'A052');

insert into diagnoses			-- id = 10
(code_book_id, description, code)
values (1, 'Amibijaza', 'A06');

insert into diagnoses			-- id = 11
(code_book_id, description, code)
values (1, 'Apsces jetre prouzrokovan amebama', 'A064');

insert into diagnoses			-- id = 12
(code_book_id, description, code)
values (1, 'Amebijaza kože', 'A067');

insert into diagnoses			-- id = 13
(code_book_id, description, code)
values (1, 'Ostale crevne infekcije uzrokovane protozoama', 'A07');

insert into diagnoses			-- id = 14
(code_book_id, description, code)
values (1, 'Balantidijaza', 'A070');

insert into diagnoses			-- id = 15
(code_book_id, description, code)
values (1, 'Đardijaza', 'A071');

insert into diagnoses			-- id = 16
(code_book_id, description, code)
values (1, 'Kriptosporidijaza', 'A072');

insert into diagnoses			-- id = 17
(code_book_id, description, code)
values (1, 'Izosporijaza', 'A073');

insert into diagnoses			-- id = 18
(code_book_id, description, code)
values (1, 'Turbekoloza nernog sistema', 'A17');

insert into diagnoses			-- id = 19
(code_book_id, description, code)
values (1, 'Kuga', 'A209');

insert into diagnoses			-- id = 20
(code_book_id, description, code)
values (1, 'Antraks', 'A22');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------- Inserting medications ---------------------------------------------------------
insert into medications			-- id = 1
(code_book_id, description, code)
values (1, 'Daktanol', '2157101');

insert into medications			-- id = 2
(code_book_id, description, code)
values (1, 'Omeprol', '1122460');

insert into medications			-- id = 3
(code_book_id, description, code)
values (1, 'Ortanol', '1122846');

insert into medications			-- id = 4
(code_book_id, description, code)
values (1, 'Omeprazol', '1122935');

insert into medications			-- id = 5
(code_book_id, description, code)
values (1, 'Nolpaza', '1122920');

insert into medications			-- id = 6
(code_book_id, description, code)
values (1, 'Sabax', '1122160');

insert into medications			-- id = 7
(code_book_id, description, code)
values (1, 'Emanera', '1122882');

insert into medications			-- id = 8
(code_book_id, description, code)
values (1, 'Klometol', '3124300');

insert into medications			-- id = 9
(code_book_id, description, code)
values (1, 'Ursofalk', '1127176');

insert into medications			-- id = 10
(code_book_id, description, code)
values (1, 'Reglan', '1124303');

insert into medications			-- id = 11
(code_book_id, description, code)
values (1, 'Rasetron', '1124104');

insert into medications			-- id = 12
(code_book_id, description, code)
values (1, 'Loperamid', '1126401');

insert into medications			-- id = 13
(code_book_id, description, code)
values (1, 'Budosan', '1129930');

insert into medications			-- id = 14
(code_book_id, description, code)
values (1, 'Kreon', '1121154');

insert into medications			-- id = 15
(code_book_id, description, code)
values (1, 'Gluformin', '1043060');

insert into medications			-- id = 16
(code_book_id, description, code)
values (1, 'Tefor', '1043071');

insert into medications			-- id = 17
(code_book_id, description, code)
values (1, 'Glucophage', '1043107');

insert into medications			-- id = 18
(code_book_id, description, code)
values (1, 'Dilacor', '1100252');

insert into medications			-- id = 19
(code_book_id, description, code)
values (1, 'Propafen', '1101130');

insert into medications			-- id = 20
(code_book_id, description, code)
values (1, 'Amiodaron', '1101402');

insert into medications			-- id = 21
(code_book_id, description, code)
values (1, 'Cordarone', '1101354');

insert into medications			-- id = 22
(code_book_id, description, code)
values (1, 'Nitroglicerin', '1102101');

insert into medications			-- id = 23
(code_book_id, description, code)
values (1, 'Isosorb retard', '1102060');

insert into medications			-- id = 24
(code_book_id, description, code)
values (1, 'Lasix', '1400142');

insert into medications			-- id = 25
(code_book_id, description, code)
values (1, 'Lometazid', '1401290');

insert into medications			-- id = 26
(code_book_id, description, code)
values (1, 'Hemopres', '1400400');

insert into medications			-- id = 27
(code_book_id, description, code)
values (1, 'Presolol', '1107496');

insert into medications			-- id = 28
(code_book_id, description, code)
values (1, 'Corvitol', '1107580');

insert into medications			-- id = 29
(code_book_id, description, code)
values (1, 'Meksena', '1107990');

insert into medications			-- id = 30
(code_book_id, description, code)
values (1, 'Bisprol', '1107048');

insert into medications			-- id = 31
(code_book_id, description, code)
values (1, 'Concor', '1107605');

insert into medications			-- id = 32
(code_book_id, description, code)
values (1, 'Amlogal', '1402141');

insert into medications			-- id = 33
(code_book_id, description, code)
values (1, 'Amlodipin', '1402850');

insert into medications			-- id = 34
(code_book_id, description, code)
values (1, 'Tenox', '1402852');

insert into medications			-- id = 35
(code_book_id, description, code)
values (1, 'Cardipine', '1402000');

insert into medications			-- id = 36
(code_book_id, description, code)
values (1, 'Vazotal', '1402878');

insert into medications			-- id = 37
(code_book_id, description, code)
values (1, 'Zorkaptil', '1103631');

insert into medications			-- id = 38
(code_book_id, description, code)
values (1, 'Katopil', '1103220');

insert into medications			-- id = 39
(code_book_id, description, code)
values (1, 'Prilenap', '1103578');

insert into medications			-- id = 40
(code_book_id, description, code)
values (1, 'Enalapril', '1103178');

insert into medications			-- id = 41
(code_book_id, description, code)
values (1, 'Enap', '1103204');

insert into medications			-- id = 42
(code_book_id, description, code)
values (1, 'Skopryl', '1103565');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting clinics -----------------------------------------------------------
insert into clinics			-- id = 1
(name, address, description, price_list, rating, code_book_id)
values ('Poliklinika Sparta', 'Bulevar Evrope 22, Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam porttitor,
		arcu quis iaculis facilisis, purus ipsum vehicula nunc,vitae finibus.',
	   '1000', 0.0, 1);

insert into clinics			-- id = 2
(name, address, description, price_list, rating, code_book_id)
values ('Poliklinika Medicina', 'Bulevar Oslobodjenja 79, Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin magna turpis,
		tincidunt in dictum et, fringilla at eros. Pellentesque eu est pharetra, interdum quam a.',
	   '1250', 0.0, 1);

insert into clinics			-- id = 3
(name, address, description, price_list, rating, code_book_id)
values ('Poliklinika Žekić', 'Grčkoškolska 3, Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ante diam, auctor in mauris et,
		dapibus cursus nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Quisque id.',
	   '1500', 0.0, 1);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------- Inserting clinic admins --------------------------------------------------------
insert into clinic_admins			-- id = 4
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'mejl1@gmail.com', 'admin', 'Veljko', 'Plećaš', 'Nepoznata 4', 'Novi Sad', 'Srbija', '0641234567', '44444444444', 1);

insert into clinic_admins			-- id = 5
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'mejl2@gmail.com', 'admin', 'Nikola', 'Kabašaj', 'Nepoznata 5', 'Sremska Mitrovica', 'Srbija', '0641234567', '55555555555', 1);

insert into clinic_admins			-- id = 6
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'),'mejl3@gmail.com', 'admin', 'Jovan', 'Bodroža', 'Nepoznata 6', 'Banatsko Aranđelovo', 'Srbija', '0641234567', '66666666666', 2);

insert into clinic_admins			-- id = 7
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'mejl4@gmail.com', 'admin', 'Petar', 'Petrović', 'Nepoznata 7', 'Subotica', 'Srbija', '0641234567', '77777777777', 2);

insert into clinic_admins			-- id = 8
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'),'mejl5@gmail.com', 'admin', 'Dejan', 'Dejanović', 'Nepoznata 8', 'Kikinda', 'Srbija', '0641234567', '88888888888', 3);

insert into clinic_admins			-- id = 9
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'mejl6@gmail.com', 'admin', 'Jovan', 'Jovanović', 'Nepoznata 9', 'Loznica', 'Srbija', '0641234567', '99999999999', 3);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting calendars ----------------------------------------------------------
insert into calendars (id) values (1);
insert into calendars (id) values (2);
insert into calendars (id) values (3);
insert into calendars (id) values (4);
insert into calendars (id) values (5);
insert into calendars (id) values (6);
insert into calendars (id) values (7);
insert into calendars (id) values (8);
insert into calendars (id) values (9);
insert into calendars (id) values (10);
insert into calendars (id) values (11);
insert into calendars (id) values (12);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting nursers -----------------------------------------------------------
insert into nurses			-- id = 10
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl7@gmail.com', 'nurse', 'Ljubica', 'Ljuvinčić', 'Nepoznata 19', 'Knjaževac', 'Srbija', '0641234567', '10101010101', 1, 1);

insert into nurses			-- id = 11
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl8@gmail.com', 'nurse', 'Marijana', 'Marijanović', 'Nepoznata 20', 'Kruševac', 'Srbija', '0641234567', '20202020202', 2, 1);

insert into nurses			-- id = 12
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl9@gmail.com', 'nurse', 'Marina', 'Marinić', 'Nepoznata 21', 'Mladenovac', 'Srbija', '0641234567', '21212121212', 3, 2);

insert into nurses			-- id = 13
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl10@gmail.com', 'nurse', 'Dušan', 'Dušanović', 'Nepoznata 22', 'Niš', 'Srbija', '0641234567', '23232323232', 4, 2);

insert into nurses			-- id = 14
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl11@gmail.com', 'nurse', 'Marko', 'Marković', 'Nepoznata 23', 'Gornji Milanovac', 'Srbija', '0641234567', '24242424242', 5, 3);

insert into nurses			-- id = 15
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl12@gmail.com', 'nurse', 'Aljoša', 'Aljošić', 'Nepoznata 24', 'Donji Milanovac', 'Srbija', '0641234567', '252525252', 6, 3);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------- Inserting business hours -------------------------------------------------------
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 1
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 2
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 3
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 4
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 5
insert into business_hours (started, ended) values ('7:00:00', '14:00:00');				-- id = 6
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- Inserting examination types -------------------------------------------------------
insert into examination_types (name, duration) values ('Tip 1', 15.0);			-- id = 1
insert into examination_types (name, duration) values ('Tip 2', 15.0);			-- id = 2
insert into examination_types (name, duration) values ('Tip 3', 15.0);			-- id = 3
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting doctors -----------------------------------------------------------
insert into doctors			-- id = 16
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'),  'mejl13@gmail.com', 'doctor', 'Duško', 'Dušković', 'Nepoznata 34', 'Prokuplje', 'Srbija', '0641234567', '36363636363', 7, 0.0, 1, 1, 1);

insert into doctors			-- id = 17
(id ,email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl14@gmail.com', 'doctor', 'Vesna', 'Vesnić', 'Nepoznata 35', 'Smederevo', 'Srbija', '0641234567', '37373737373', 8, 0.0, 2, 2, 1);

insert into doctors			-- id = 18
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl15@gmail.com', 'doctor', 'Ivan', 'Ivanović', 'Nepoznata 36', 'Jagodina', 'Srbija', '0641234567', '38383838383', 9, 0.0, 3, 3, 2);

insert into doctors			-- id = 19
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'), 'mejl16@gmail.com', 'doctor', 'Zorana', 'Zoranović', 'Nepoznata 37', 'Bor', 'Srbija', '0641234567', '39393939393', 10, 0.0, 4, 1, 2);

insert into doctors			-- id = 20
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'),'mejl17@gmail.com', 'doctor', 'Zoran', 'Zorić', 'Nepoznata 38', 'Šabac', 'Srbija', '0641234567', '40404040404', 11, 0.0, 5, 2, 3);

insert into doctors			-- id = 21
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, rating, bus_hours_id, ex_type_id, clinic_id)
values (nextval('ust_seq_user'),'mejl18@gmail.com', 'doctor', 'Goran', 'Gorić', 'Nepoznata 39', 'Pančevo', 'Srbija', '0641234567', '41414141414', 12, 0.0, 3, 3, 3);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Inserting medical records -------------------------------------------------------
insert into medical_records (id) values (1);			-- id = 1
insert into medical_records (id) values (2);			-- id = 2
insert into medical_records (id) values (3);			-- id = 3
insert into medical_records (id) values (4);			-- id = 4
insert into medical_records (id) values (5);			-- id = 5
insert into medical_records (id) values (6);			-- id = 6
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting histories ----------------------------------------------------------
insert into histories (medical_record_id, history) values (1, 'Kolera');												-- id = 1
insert into histories (medical_record_id, history) values (1, 'Tifusna groznica i paratifusna groznica');				-- id = 2
insert into histories (medical_record_id, history) values (1, 'Trbušni tifus');											-- id = 3

insert into histories (medical_record_id, history) values (2, 'Paratifus A');											-- id = 4
insert into histories (medical_record_id, history) values (2, 'Infekcije uzrokovane salmonelama');						-- id = 5
insert into histories (medical_record_id, history) values (2, 'Zapaljenje tankog creva uzrokovanog salmonelama');		-- id = 6

insert into histories (medical_record_id, history) values (3, 'Šigeloza');												-- id = 7
insert into histories (medical_record_id, history) values (3, 'Botulizam');												-- id = 8
insert into histories (medical_record_id, history) values (3, 'Trovanje hranom');										-- id = 9

insert into histories (medical_record_id, history) values (4, 'Amibijaza');												-- id = 10
insert into histories (medical_record_id, history) values (4, 'Apsces jetre prouzrokovan amebama');						-- id = 11
insert into histories (medical_record_id, history) values (4, 'Amebijaza kože');										-- id = 12

insert into histories (medical_record_id, history) values (5, 'Ostale crevne infekcije uzrokovane protozoama');			-- id = 13
insert into histories (medical_record_id, history) values (5, 'Balantidijaza');											-- id = 14
insert into histories (medical_record_id, history) values (5, 'Đardijaza');												-- id = 15
insert into histories (medical_record_id, history) values (5, 'Kriptosporidijaza');										-- id = 16

insert into histories (medical_record_id, history) values (6, 'Izosporijaza');											-- id = 17
insert into histories (medical_record_id, history) values (6, 'Turbekoloza nernog sistema');							-- id = 18
insert into histories (medical_record_id, history) values (6, 'Kuga');													-- id = 19
insert into histories (medical_record_id, history) values (6, 'Antraks');												-- id = 20
insert into histories (medical_record_id, history) values (6, 'Kolera');												-- id = 21
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting patients -----------------------------------------------------------
insert into patients			-- id = 22
(id ,email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'),'mejl19@gmail.com', 'pacijent', 'Ljubiša', 'Ljubišić', 'Nepoznata 10', 'Novi Sad', 'Srbija', '0641234567', '12121212121', 1);

insert into patients			-- id = 23
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'),'mejl20@gmail.com', 'pacijent', 'Ana', 'Anić', 'Nepoznata 11', 'Sremska Mitrovica', 'Srbija', '0641234567', '13131313131', 2);

insert into patients			-- id = 24
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'),'mejl21@gmail.com', 'admin', 'Milica', 'Milicić', 'Nepoznata 12', 'Banatsko Aranđelovo', 'Srbija', '0641234567', '14141414141', 3);

insert into patients			-- id = 25
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'),'mejl22@gmail.com', 'admin', 'Gordana', 'Gordanović', 'Nepoznata 13', 'Subotica', 'Srbija', '0641234567', '15151515151', 4);

insert into patients			-- id = 26
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'),'mejl23@gmail.com', 'admin', 'Olja', 'Oljić', 'Nepoznata 14', 'Kikinda', 'Srbija', '0641234567', '16161616161', 5);

insert into patients			-- id = 27
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, medical_record_id)
values (nextval('ust_seq_user'), 'mejl24@gmail.com', 'admin', 'Milena', 'Milenić', 'Nepoznata 15', 'Loznica', 'Srbija', '0641234567', '17171717171', 6);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Inserting operation rooms -------------------------------------------------------
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 1','100', 7, 1);				-- id = 1
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 2','101', 7, 1);				-- id = 2
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 3','102', 8, 1);				-- id = 3
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 4','103', 8, 1);				-- id = 4
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 5','104', 9, 2);				-- id = 5
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 6','105', 9, 2);				-- id = 6
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 7','106', 10, 2);			-- id = 7
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 8','107', 10, 2);			-- id = 8
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 9','108', 11, 3);			-- id = 9
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 10','109', 11, 3);			-- id = 10
insert into operation_rooms (name,number, calendar_id, clinic_id) values ('Sala 11','110', 12, 3);			-- id = 11
insert into operation_rooms  (name,number, calendar_id, clinic_id) values ('Sala 12','111', 12, 3);			-- id = 12
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------- Inserting appointments --------------------------------------------------------
insert into appointments			-- id = 1
(				time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-01 09:00:00',  1000.0, 	 10.0, 		16, 				1, 					1, 				22, 				1, 				1);

insert into appointments			-- id = 2
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-02 10:00:00',  700.0, 	  0.0, 		    16, 				2, 					1, 				23, 				2, 				1);

insert into appointments			-- id = 3
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-03 10:00:00',  800.0, 	  5.0, 		    17, 				3, 					2, 				24, 				3, 				1);

insert into appointments			-- id = 4
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-04 10:00:00',  900.0, 	  5.0, 		    17, 				4, 					2, 				25,				4,				1);




insert into appointments			-- id = 5
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-05 10:00:00',  850.0, 	  20.0, 	    18, 				5, 					3, 				26, 				5,				2);

insert into appointments			-- id = 6
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-06 10:00:00',  725.0, 	  10.0, 	    18, 				6, 					3, 				27, 				6, 				2);

insert into appointments			-- id = 7
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-07 10:00:00',  550.0, 	  15.0, 	    19, 				7, 					1, 				22, 				1,				2);

insert into appointments			-- id = 8
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-08 10:00:00',  500.0, 	  10.0, 	    19, 				8, 					1, 				23, 				2, 				2);




insert into appointments			-- id = 9
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-09 10:00:00',  650.0, 	  5.0, 	   	    20, 				9, 					2, 				24, 				3,				3);

insert into appointments			-- id = 10
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-10 10:00:00',  725.0, 	  10.0, 	    20, 				10, 				2, 				25, 				4, 				3);

insert into appointments			-- id = 11
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-11 10:00:00',  725.0, 	  15.0, 	    21, 				11, 				3, 				26, 				5,				3);

insert into appointments			-- id = 12
				(time, 			price, 	discount, 	doctor_id, operation_room_id, examination_type_id, patient_id, medical_record_id, clinic_id)
values ('2019-03-12 10:00:00',  600.0, 	  20.0, 	    21, 				12, 				3, 				27, 				6, 				3);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting text here ----------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

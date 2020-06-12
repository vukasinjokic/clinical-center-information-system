-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinke za sve korisnike i zahteve za registraciju su: 987654321

-----------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------- Inserting authorities ---------------------------------------------------------
insert into authorities (name) values ('ROLE_CLINIC_CENTER_ADMIN');           -- id = 1
insert into authorities (name) values ('ROLE_CLINIC_ADMIN');                  -- id = 2
insert into authorities (name) values ('ROLE_DOCTOR');                        -- id = 3
insert into authorities (name) values ('ROLE_NURSE');                         -- id = 4
insert into authorities (name) values ('ROLE_PATIENT');                       -- id = 5
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- Inserting clinic center admins ----------------------------------------------------
insert into clinic_center_admins			-- id = 1
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, is_password_changed)
values (nextval('ust_seq_user'), 'nikola@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Nikola', 'Stojanović', 'Nepoznata 1', 'Orašac', 'Srbija', '0641234567', '11111111111', 'T');

insert into clinic_center_admins			-- id = 2
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, is_password_changed)
values (nextval('ust_seq_user'), 'vukasin@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Vukašin', 'Jokić', 'Nepoznata 2', 'Vršac', 'Srbija', '0641234567', '22222222222', 'T');

insert into clinic_center_admins			-- id = 3
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, is_password_changed)
values (nextval('ust_seq_user'), 'nemanja@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Nemanja', 'Jevtić', 'Nepoznata 3', 'Dobrinci', 'Srbija', '0641234567', '33333333333', 'T');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting code books ---------------------------------------------------------
insert into code_books (id) values (1);			-- id = 1
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

insert into diagnoses			-- id = 21
(code_book_id, description, code)
values (1, 'Opsti pregled', 'Z00');
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
----------------------------------------------------------- Inserting ratings -----------------------------------------------------------
-- Doctors ratings
insert into ratings(average_grade) values (9.5);            -- id = 1, doctorId = 16
insert into ratings(average_grade) values (7.5);            -- id = 2, doctorId = 17
insert into ratings(average_grade) values (5.5);            -- id = 3, doctorId = 18
insert into ratings(average_grade) values (6.5);            -- id = 4, doctorId = 19
insert into ratings(average_grade) values (8.5);            -- id = 5, doctorId = 20
insert into ratings(average_grade) values (10.0);            -- id = 6, doctorId = 21

-- Clinics ratings
insert into ratings(average_grade) values (8.5);            -- id = 7, clinicId = 1
insert into ratings(average_grade) values (4.0);            -- id = 8, clinicId = 2
insert into ratings(average_grade) values (8.0);            -- id = 9, clinicId = 3
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------- Inserting patient_ids ---------------------------------------------------------
-- doctorId = 16
insert into patient_ids(rating_id, patient_id) values (1, 22);
insert into patient_ids(rating_id, patient_id) values (1, 23);

-- doctorId = 17
insert into patient_ids(rating_id, patient_id) values (2, 24);
insert into patient_ids(rating_id, patient_id) values (2, 25);

-- doctorId = 18
insert into patient_ids(rating_id, patient_id) values (3, 26);
insert into patient_ids(rating_id, patient_id) values (3, 27);

-- doctorId = 19
insert into patient_ids(rating_id, patient_id) values (4, 22);
insert into patient_ids(rating_id, patient_id) values (4, 23);

-- doctorId = 20
insert into patient_ids(rating_id, patient_id) values (5, 24);
insert into patient_ids(rating_id, patient_id) values (5, 25);

-- doctorId = 21
insert into patient_ids(rating_id, patient_id) values (6, 26);


-- clinicId = 1
insert into patient_ids(rating_id, patient_id) values (7, 22);
insert into patient_ids(rating_id, patient_id) values (7, 23);
insert into patient_ids(rating_id, patient_id) values (7, 24);
insert into patient_ids(rating_id, patient_id) values (7, 25);

-- clinicId = 2
insert into patient_ids(rating_id, patient_id) values (8, 26);
insert into patient_ids(rating_id, patient_id) values (8, 27);
insert into patient_ids(rating_id, patient_id) values (8, 22);
insert into patient_ids(rating_id, patient_id) values (8, 23);

-- clinicId = 3
insert into patient_ids(rating_id, patient_id) values (9, 24);
insert into patient_ids(rating_id, patient_id) values (9, 25);
insert into patient_ids(rating_id, patient_id) values (9, 26);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting grades ------------------------------------------------------------
-- doctorId = 16
insert into grades(rating_id, grade) values (1, 10);
insert into grades(rating_id, grade) values (1, 9);

-- doctorId = 17
insert into grades(rating_id, grade) values (2, 8);
insert into grades(rating_id, grade) values (2, 7);

-- doctorId = 18
insert into grades(rating_id, grade) values (3, 6);
insert into grades(rating_id, grade) values (3, 5);

-- doctorId = 19
insert into grades(rating_id, grade) values (4, 6);
insert into grades(rating_id, grade) values (4, 7);

-- doctorId = 20
insert into grades(rating_id, grade) values (5, 8);
insert into grades(rating_id, grade) values (5, 9);

-- doctorId = 21
insert into grades(rating_id, grade) values (6, 10);



-- clinicId = 1
insert into grades(rating_id, grade) values (7, 10);
insert into grades(rating_id, grade) values (7, 9);
insert into grades(rating_id, grade) values (7, 8);
insert into grades(rating_id, grade) values (7, 7);

-- clinicId = 2
insert into grades(rating_id, grade) values (8, 6);
insert into grades(rating_id, grade) values (8, 5);
insert into grades(rating_id, grade) values (8, 5);
insert into grades(rating_id, grade) values (8, 4);

-- clinicId = 3
insert into grades(rating_id, grade) values (9, 7);
insert into grades(rating_id, grade) values (9, 8);
insert into grades(rating_id, grade) values (9, 9);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------



-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting clinics -----------------------------------------------------------
insert into clinics			-- id = 1
(name, address, description, rating_id, code_book_id)
values ('Poliklinika Sparta', 'Bulevar Evrope 22 Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam porttitor,
		arcu quis iaculis facilisis, purus ipsum vehicula nunc,vitae finibus.',
	    7, 1);

insert into clinics			-- id = 2
(name, address, description, rating_id, code_book_id)
values ('Poliklinika Medicina', 'Bulevar Oslobodjenja 79 Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin magna turpis,
		tincidunt in dictum et, fringilla at eros. Pellentesque eu est pharetra, interdum quam a.',
	    8, 1);

insert into clinics			-- id = 3
(name, address, description, rating_id, code_book_id)
values ('Poliklinika Žekić', 'Grčkoškolska 3 Novi Sad',
	   'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ante diam, auctor in mauris et,
		dapibus cursus nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Quisque id.',
	    9, 1);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------- Inserting clinic admins --------------------------------------------------------
insert into clinic_admins			-- id = 4
(id, email, password, first_name, last_name,is_password_changed ,address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin1@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Veljko', 'Plećaš','T', 'Nepoznata 4', 'Novi Sad', 'Srbija', '0641234567', '44444444444', 1);

insert into clinic_admins			-- id = 5
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin2@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Nikola', 'Kabašaj','F', 'Nepoznata 5', 'Sremska Mitrovica', 'Srbija', '0641234567', '55555555555', 1);

insert into clinic_admins			-- id = 6
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin3@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Jovan', 'Bodroža','T', 'Nepoznata 6', 'Banatsko Aranđelovo', 'Srbija', '0641234567', '66666666666', 2);

insert into clinic_admins			-- id = 7
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin4@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Petar', 'Petrović','T' ,'Nepoznata 7', 'Subotica', 'Srbija', '0641234567', '77777777777', 2);

insert into clinic_admins			-- id = 8
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin5@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Dejan', 'Dejanović','T', 'Nepoznata 8', 'Kikinda', 'Srbija', '0641234567', '88888888888', 3);

insert into clinic_admins			-- id = 9
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'admin6@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Jovan', 'Jovanović','F', 'Nepoznata 9', 'Loznica', 'Srbija', '0641234567', '99999999999', 3);
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

insert into calendars (id) values (13);
insert into calendars (id) values (14);
insert into calendars (id) values (15);
insert into calendars (id) values (16);
insert into calendars (id) values (17);
insert into calendars (id) values (18);
insert into calendars (id) values (19);
insert into calendars (id) values (20);
insert into calendars (id) values (21);
insert into calendars (id) values (22);
insert into calendars (id) values (23);
insert into calendars (id) values (24);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------- Inserting event_start_dates ----------------------------------------------------------
insert into event_start_dates (calendar_id, event_start_dates) values (7,'2020-05-01 08:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (7,'2020-05-01 10:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (7,'2020-05-02 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (8,'2020-05-03 10:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (8,'2020-05-04 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (9,'2020-05-05 10:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (9,'2020-05-06 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (10,'2020-05-07 10:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (10,'2020-05-08 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (11,'2020-05-09 10:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (11,'2020-05-10 10:00:00');


insert into event_start_dates (calendar_id, event_start_dates) values (12,'2020-05-11 10:00:00');
------------------------------------------------------------------------------------------------
-----------------------------------SOBE---------------------------------------------------------

insert into event_start_dates (calendar_id, event_start_dates) values (13,'2020-05-01 08:00:00');
insert into event_start_dates (calendar_id, event_start_dates) values (13,'2020-05-01 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (14,'2020-05-02 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (15,'2020-05-03 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (16,'2020-05-04 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (17,'2020-05-05 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (18,'2020-05-06 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (19,'2020-05-07 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (20,'2020-05-08 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (21,'2020-05-09 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (22,'2020-05-10 10:00:00');

insert into event_start_dates (calendar_id, event_start_dates) values (23,'2020-05-11 10:00:00');

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------------------------------------------
--
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- -------------------------------------------------- Inserting event_end_dates ------------------------------------------------------------
insert into event_end_dates (calendar_id, event_end_dates) values (7, '2020-05-01 08:30:00');
insert into event_end_dates (calendar_id, event_end_dates) values (7, '2020-05-01 11:00:00');
insert into event_end_dates (calendar_id, event_end_dates) values (7, '2020-05-02 10:30:00');

insert into event_end_dates (calendar_id, event_end_dates) values (8,'2020-05-03 11:00:00');
insert into event_end_dates (calendar_id, event_end_dates) values (8,'2020-05-04 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (9,'2020-05-05 11:00:00');
insert into event_end_dates (calendar_id, event_end_dates) values (9,'2020-05-06 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (10, '2020-05-07 10:30:00');
insert into event_end_dates (calendar_id, event_end_dates) values (10, '2020-05-08 10:30:00');

insert into event_end_dates (calendar_id, event_end_dates) values (11,'2020-05-09 11:00:00');
insert into event_end_dates (calendar_id, event_end_dates) values (11,'2020-05-10 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (12,'2020-05-11 11:00:00');
------------------------------------------------------------------------------------------------
-----------------------------------SOBE---------------------------------------------------------

insert into event_end_dates (calendar_id, event_end_dates) values (13, '2020-05-01 08:30:00');
insert into event_end_dates (calendar_id, event_end_dates) values (13, '2020-05-01 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (14, '2020-05-02 10:30:00');

insert into event_end_dates (calendar_id, event_end_dates) values (15,'2020-05-03 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (16,'2020-05-04 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (17,'2020-05-05 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (18,'2020-05-06 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (19, '2020-05-07 10:30:00');

insert into event_end_dates (calendar_id, event_end_dates) values (20, '2020-05-08 10:30:00');

insert into event_end_dates (calendar_id, event_end_dates) values (21,'2020-05-09 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (22,'2020-05-10 11:00:00');

insert into event_end_dates (calendar_id, event_end_dates) values (23,'2020-05-11 11:00:00');

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- -------------------------------------------------- Inserting event_names ----------------------------------------------------------------
insert into event_names (calendar_id, event_names) values (7, 'Sistematski pregled');
insert into event_names (calendar_id, event_names) values (7, 'Operacija');
insert into event_names (calendar_id, event_names) values (7, 'Sistematski pregled');

insert into event_names (calendar_id, event_names) values (8, 'Ultrazvuk');
insert into event_names (calendar_id, event_names) values (8, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (9, 'Operacija');
insert into event_names (calendar_id, event_names) values (9, 'Operacija');

insert into event_names (calendar_id, event_names) values (10, 'Sistematski pregled');
insert into event_names (calendar_id, event_names) values (10, 'Sistematski pregled');

insert into event_names (calendar_id, event_names) values (11, 'Ultrazvuk');
insert into event_names (calendar_id, event_names) values (11, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (12, 'Operacija');

------------------------------------------------------------------------------------------------
-----------------------------------SOBE---------------------------------------------------------
insert into event_names (calendar_id, event_names) values (13, 'Sistematski pregled');
insert into event_names (calendar_id, event_names) values (13, 'Operacija');

insert into event_names (calendar_id, event_names) values (14, 'Sistematski pregled');

insert into event_names (calendar_id, event_names) values (15, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (16, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (17, 'Operacija');

insert into event_names (calendar_id, event_names) values (18, 'Operacija');

insert into event_names (calendar_id, event_names) values (19, 'Sistematski pregled');

insert into event_names (calendar_id, event_names) values (20, 'Sistematski pregled');

insert into event_names (calendar_id, event_names) values (21, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (22, 'Ultrazvuk');

insert into event_names (calendar_id, event_names) values (23, 'Operacija');


-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------ Inserting calendar appointment ids -----------------------------------------------------
insert into appointment_ids (calendar_id, appointment_id) values (7, 1);
insert into appointment_ids (calendar_id, appointment_id) values (7, 2);
insert into appointment_ids (calendar_id, appointment_id) values (7, 3);

insert into appointment_ids (calendar_id, appointment_id) values (8, 4);
insert into appointment_ids (calendar_id, appointment_id) values (8, 5);

insert into appointment_ids (calendar_id, appointment_id) values (9, 6);
insert into appointment_ids (calendar_id, appointment_id) values (9, 7);

insert into appointment_ids (calendar_id, appointment_id) values (10, 8);
insert into appointment_ids (calendar_id, appointment_id) values (10, 9);

insert into appointment_ids (calendar_id, appointment_id) values (11, 10);
insert into appointment_ids (calendar_id, appointment_id) values (11, 11);

insert into appointment_ids (calendar_id, appointment_id) values (12, 12);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------ Sobe -----------------------------------------------------
insert into appointment_ids (calendar_id, appointment_id) values (13, 1);
insert into appointment_ids (calendar_id, appointment_id) values (13, 2);

insert into appointment_ids (calendar_id, appointment_id) values (14, 3);

insert into appointment_ids (calendar_id, appointment_id) values (15, 4);

insert into appointment_ids (calendar_id, appointment_id) values (16, 5);

insert into appointment_ids (calendar_id, appointment_id) values (17, 6);

insert into appointment_ids (calendar_id, appointment_id) values (18, 7);

insert into appointment_ids (calendar_id, appointment_id) values (19, 8);

insert into appointment_ids (calendar_id, appointment_id) values (20, 9);

insert into appointment_ids (calendar_id, appointment_id) values (21, 10);

insert into appointment_ids (calendar_id, appointment_id) values (22, 11);

insert into appointment_ids (calendar_id, appointment_id) values (23, 12);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting nursers -----------------------------------------------------------
insert into nurses			-- id = 10
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra1@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Ljubica', 'Ljuvinčić', 'Nepoznata 19', 'Knjaževac', 'Srbija', '0641234567', '10101010101', 1, 1, 'T');

insert into nurses			-- id = 11
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra2@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Marijana', 'Marijanović', 'Nepoznata 20', 'Kruševac', 'Srbija', '0641234567', '20202020202', 2, 1, 'T');

insert into nurses			-- id = 12
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra3@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Marina', 'Marinić', 'Nepoznata 21', 'Mladenovac', 'Srbija', '0641234567', '21212121212', 3, 2, 'T');

insert into nurses			-- id = 13
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra4@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Dušan', 'Dušanović', 'Nepoznata 22', 'Niš', 'Srbija', '0641234567', '23232323232', 4, 2, 'T');

insert into nurses			-- id = 14
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra5@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Marko', 'Marković', 'Nepoznata 23', 'Gornji Milanovac', 'Srbija', '0641234567', '24242424242', 5, 3, 'T');

insert into nurses			-- id = 15
(id, email, password, first_name, last_name, address, city, country, phone_number, social_security_number, calendar_id, clinic_id, is_password_changed)
values (nextval('ust_seq_user'), 'sestra6@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Aljoša', 'Aljošić', 'Nepoznata 24', 'Donji Milanovac', 'Srbija', '0641234567', '252525252', 6, 3, 'T');
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
insert into examination_types (name, duration) values ('Sistematski pregled', 0.5);			-- id = 1
insert into examination_types (name, duration) values ('Ultrazvuk', 1);			-- id = 2
insert into examination_types (name, duration) values ('Operacija', 1);			-- id = 3
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------Inserting price list ----------------------------------------------------------------------------------
insert into price_list (clinic_id) values (1);          -- id = 1
insert into price_list (clinic_id) values (2);          -- id = 2
insert into price_list (clinic_id) values (3);          -- id = 3
------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------Inserting into price list item--------------------------------------------
insert into price_list_item (price, examination_type_id) values (1000, 1);  -- id = 1
insert into price_list_item (price, examination_type_id) values (2000, 2);  -- id = 2
insert into price_list_item (price, examination_type_id) values (3000, 3);  -- id = 3

insert into price_list_item (price, examination_type_id) values (500,  1);  -- id = 4
insert into price_list_item (price, examination_type_id) values (1500, 2);  -- id = 5
insert into price_list_item (price, examination_type_id) values (2500, 3);  -- id = 6

insert into price_list_item (price, examination_type_id) values (1500, 1);  -- id = 7
insert into price_list_item (price, examination_type_id) values (2500, 2);  -- id = 8
insert into price_list_item (price, examination_type_id) values (3500, 3);  -- id = 9
----------------------------------------------------------Pricelist item--------------------------------------------------------------------------------
insert into price_list_items (price_list_id, items_id) values (1, 1);        -- id = 1
insert into price_list_items (price_list_id, items_id) values (1, 2);        -- id = 2
insert into price_list_items (price_list_id, items_id) values (1, 3);        -- id = 3

insert into price_list_items (price_list_id, items_id) values (2, 4);        -- id = 4
insert into price_list_items (price_list_id, items_id) values (2, 5);        -- id = 5
insert into price_list_items (price_list_id, items_id) values (2, 6);        -- id = 6

insert into price_list_items (price_list_id, items_id) values (3, 7);        -- id = 7
insert into price_list_items (price_list_id, items_id) values (3, 8);        -- id = 8
insert into price_list_items (price_list_id, items_id) values (3, 9);        -- id = 9
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------- Inserting doctors -----------------------------------------------------------
insert into doctors			-- id = 16
(id, email, password, first_name, last_name,is_password_changed ,address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor1@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Duško', 'Dušković','T', 'Nepoznata 34', 'Prokuplje', 'Srbija', '0641234567', '36363636363', 7, 1, 1, 1, 1, 'T');

insert into doctors			-- id = 17
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor2@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Vesna', 'Vesnić','F' ,'Nepoznata 35', 'Smederevo', 'Srbija', '0641234567', '37373737373', 8, 2, 2, 2, 1,'T');

insert into doctors			-- id = 18
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor3@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Ivan', 'Ivanović','F', 'Nepoznata 36', 'Jagodina', 'Srbija', '0641234567', '38383838383', 9, 3, 3, 3, 2, 'T');

insert into doctors			-- id = 19
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor4@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Zorana', 'Zoranović','F', 'Nepoznata 37', 'Bor', 'Srbija', '0641234567', '39393939393', 10, 4, 4, 1, 2, 'T');

insert into doctors			-- id = 20
(id, email, password, first_name, last_name,is_password_changed ,address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor5@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Zoran', 'Zorić','F' ,'Nepoznata 38', 'Šabac', 'Srbija', '0641234567', '40404040404', 11, 5, 5, 2, 3, 'T');

insert into doctors			-- id = 21
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, calendar_id, rating_id, bus_hours_id, ex_type_id, clinic_id,activity)
values (nextval('ust_seq_user'), 'doktor6@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Goran', 'Gorić','F' ,'Nepoznata 39', 'Pančevo', 'Srbija', '0641234567', '41414141414', 12, 6, 3, 3, 3, 'T');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting patients -----------------------------------------------------------
insert into patients			-- id = 22
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'pacijent1@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Ljubiša', 'Ljubišić', 'T', 'Nepoznata 10', 'Novi Sad', 'Srbija', '0641234567', '12121212121', 1);

insert into patients			-- id = 23
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'pacijent2@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Ana', 'Anić','T' , 'Nepoznata 11', 'Sremska Mitrovica', 'Srbija', '0641234567', '13131313131', 1);

insert into patients			-- id = 24
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'pacijent3@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Milica', 'Milicić','F' ,'Nepoznata 12', 'Banatsko Aranđelovo', 'Srbija', '0641234567', '14141414141',1);

insert into patients			-- id = 25
(id, email, password, first_name, last_name, is_password_changed, address, city, country, phone_number, social_security_number,clinic_id)
values (nextval('ust_seq_user'), 'pacijent4@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Gordana', 'Gordanović','F' ,'Nepoznata 13', 'Subotica', 'Srbija', '0641234567', '15151515151', 2);

insert into patients			-- id = 26
(id,  email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number, clinic_id)
values (nextval('ust_seq_user'), 'pacijent5@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Olja', 'Oljić','F', 'Nepoznata 14', 'Kikinda', 'Srbija', '0641234567', '16161616161',2);

insert into patients			-- id = 27
(id, email, password, first_name, last_name,is_password_changed, address, city, country, phone_number, social_security_number,clinic_id)
values (nextval('ust_seq_user'), 'pacijent6@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Milena', 'Milenić','F' ,'Nepoznata 15', 'Loznica', 'Srbija', '0641234567', '17171717171', 2);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Inserting medical records -------------------------------------------------------
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (22, '80.0 kg', '180.0 cm', '0 D',   '0 D',   'A+');			-- id = 1
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (23, '70.0 kg', '170.0 cm', '-1 D', '-1 D',   'B-');			-- id = 2
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (24, '60.0 kg', '160.0 cm', '1 D',   '1 D',   'AB+');			-- id = 3
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (25, '50.0 kg', '150.0 cm', '0.5 D', '1 D',   'O-');			-- id = 4
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (26, '75.0 kg', '175.0 cm', '-2 D', '-1.5 D', 'A-');			-- id = 5
insert into medical_records (patient_id, weight, height, left_eye, right_eye, blood_type) values (27, '85.0 kg', '185.0 cm', '0 D',   '0 D',   'O+');			-- id = 6
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting histories ----------------------------------------------------------
insert into reports (medical_record_id, report, doctor_email) values (1, 'Kolera', 'doktor1@gmail.com');												-- id = 1
insert into reports (medical_record_id, report, doctor_email) values (1, 'Tifusna groznica i paratifusna groznica', 'doktor2@gmail.com');				-- id = 2
insert into reports (medical_record_id, report, doctor_email) values (1, 'Trbušni tifus', 'doktor2@gmail.com');											-- id = 3

insert into reports (medical_record_id, report, doctor_email) values (2, 'Paratifus A', 'doktor1@gmail.com');											-- id = 4
insert into reports (medical_record_id, report, doctor_email) values (2, 'Infekcije uzrokovane salmonelama', 'doktor3@gmail.com');						-- id = 5
insert into reports (medical_record_id, report, doctor_email) values (2, 'Zapaljenje tankog creva uzrokovanog salmonelama', 'doktor3@gmail.com');		-- id = 6

insert into reports (medical_record_id, report, doctor_email) values (3, 'Šigeloza', 'doktor1@gmail.com');												-- id = 7
insert into reports (medical_record_id, report, doctor_email) values (3, 'Botulizam', 'doktor1@gmail.com');												-- id = 8
insert into reports (medical_record_id, report, doctor_email) values (3, 'Trovanje hranom', 'doktor1@gmail.com');										-- id = 9

insert into reports (medical_record_id, report, doctor_email) values (4, 'Amibijaza', 'doktor2@gmail.com');												-- id = 10
insert into reports (medical_record_id, report, doctor_email) values (4, 'Apsces jetre prouzrokovan amebama', 'doktor2@gmail.com');						-- id = 11
insert into reports (medical_record_id, report, doctor_email) values (4, 'Amebijaza kože', 'doktor2@gmail.com');										-- id = 12

insert into reports (medical_record_id, report, doctor_email) values (5, 'Ostale crevne infekcije uzrokovane protozoama', 'doktor4@gmail.com');			-- id = 13
insert into reports (medical_record_id, report, doctor_email) values (5, 'Balantidijaza', 'doktor4@gmail.com');											-- id = 14
insert into reports (medical_record_id, report, doctor_email) values (5, 'Đardijaza', 'doktor4@gmail.com');												-- id = 15
insert into reports (medical_record_id, report, doctor_email) values (5, 'Kriptosporidijaza', 'doktor4@gmail.com');										-- id = 16

insert into reports (medical_record_id, report, doctor_email) values (6, 'Izosporijaza', 'doktor5@gmail.com');											-- id = 17
insert into reports (medical_record_id, report, doctor_email) values (6, 'Turbekoloza nernog sistema', 'doktor5@gmail.com');							-- id = 18
insert into reports (medical_record_id, report, doctor_email) values (6, 'Kuga', 'doktor5@gmail.com');													-- id = 19
insert into reports (medical_record_id, report, doctor_email) values (6, 'Antraks', 'doktor5@gmail.com');												-- id = 20
insert into reports (medical_record_id, report, doctor_email) values (6, 'Kolera', 'doktor5@gmail.com');												-- id = 21
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Inserting rooms -------------------------------------------------------
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 1','100', 13, 1, 'APPOINTMENT','T');				-- id = 1
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 2','101', 14, 1, 'APPOINTMENT','T');				-- id = 2
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 3','102', 15, 1, 'APPOINTMENT','T');				-- id = 3
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 4','103', 16, 1, 'APPOINTMENT','T');				-- id = 4
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 5','104', 17, 2, 'APPOINTMENT','T');				-- id = 5
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 6','105', 18, 2, 'APPOINTMENT','T');				-- id = 6
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 7','106', 19, 2, 'OPERATION','T');			-- id = 7
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 8','107', 20, 2, 'OPERATION','T');			-- id = 8
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 9','108', 21, 3, 'OPERATION','T');			-- id = 9
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 10','109', 22, 3, 'OPERATION','T');			-- id = 10
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 11','110', 23, 3, 'OPERATION','T');			-- id = 11
insert into rooms (name,number, calendar_id, clinic_id, room_type,activity) values ('Sala 12','111', 24, 3, 'OPERATION','T');			-- id = 12
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------- Inserting appointments --------------------------------------------------------
insert into appointments			-- id = 1
(				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-06-08 08:00:00',  1000.0, 	 10.0, 		16, 				1, 					1, 				22, 			1, 'T');

insert into appointments			-- id = 2
(				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-06-08 10:00:00',  1000.0, 	 10.0, 		16, 				1, 					3, 				23, 			1, 'T');

insert into appointments			-- id = 3
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-07-02 10:00:00',  700.0, 	  0.0, 		    16, 				2, 					1, 				23, 			1, 'F');

insert into appointments			-- id = 4
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-07-03 10:00:00',  800.0, 	  5.0, 		    17, 				3, 					2, 				24, 			1, 'F');

insert into appointments			-- id = 5
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-07-04 10:00:00',  900.0, 	  5.0, 		    17, 				4, 					2, 				25,				1, 'F');




insert into appointments			-- id = 6
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-05 10:00:00',  850.0, 	  20.0, 	    18, 				5, 					3, 				26, 			2, 'T');

insert into appointments			-- id = 7
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-06 10:00:00',  725.0, 	  10.0, 	    18, 				6, 					3, 				27, 		2, 'T');


insert into appointments			-- id = 8
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-07 10:00:00',  550.0, 	  15.0, 	    19, 				7, 					1, 				22, 			2, 'T');

insert into appointments			-- id = 9
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-08 10:00:00',  500.0, 	  10.0, 	    19, 				8, 					1, 				23, 			2, 'T');




insert into appointments			-- id = 10
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-09 10:00:00',  650.0, 	  5.0, 	   	    20, 				9, 					2, 				24, 			3, 'F');

insert into appointments			-- id = 11
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-10 10:00:00',  725.0, 	  10.0, 	    20, 				10, 				2, 				25, 			3, 'F');

insert into appointments			-- id = 12
				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id, finished)
values ('2020-05-11 10:00:00',  725.0, 	  15.0, 	    21, 				11, 				3, 				26, 		3, 'F');

-- insert into appointments			-- id = 12
-- 				(time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, patient_id, clinic_id)
-- values ('2020-05-12 10:00:00',  600.0, 	  20.0, 	    21, 				12, 				3, 				27, 				3);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------- Inserting predefined appointments --------------------------------------------------
-- insert into appointments			-- id = 13
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-08 08:00:00',  1000.0, 	 10.0, 		16, 			1, 				1, 	 			1, 'F');
--
-- insert into appointments			-- id = 14
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-09 08:00:00',  500.0, 	 5.0, 		17, 			2, 				2, 	 			1, 'F');
--
-- insert into appointments			-- id = 15
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-08 08:00:00',  1000.0, 	 10.0, 		18, 			7, 				3, 	 			2, 'F');
--
-- insert into appointments			-- id = 16
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-09 08:00:00',  500.0, 	 5.0, 		19, 			5, 				1, 	 			2, 'F');
--
-- insert into appointments			-- id = 15
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-08 08:00:00',  1000.0, 	 10.0, 		20, 		9, 				2, 	 			3, 'F');
--
-- insert into appointments			-- id = 16
-- (				time, 			price, 	discount, 	doctor_id, room_id, examination_type_id, clinic_id, finished)
-- values ('2020-08-09 08:00:00',  500.0, 	 5.0, 		21, 			10, 				3, 	 			3, 'F');
--


-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------- Inserting user_authority --------------------------------------------------------
-- ROLE_CLINIC_CENTER_ADMIN
insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (2, 1);
insert into user_authority (user_id, authority_id) values (3, 1);

-- ROLE_CLINIC_ADMIN
insert into user_authority (user_id, authority_id) values (4, 2);
insert into user_authority (user_id, authority_id) values (5, 2);
insert into user_authority (user_id, authority_id) values (6, 2);
insert into user_authority (user_id, authority_id) values (7, 2);
insert into user_authority (user_id, authority_id) values (8, 2);
insert into user_authority (user_id, authority_id) values (9, 2);

-- ROLE_NURSE
insert into user_authority (user_id, authority_id) values (10, 4);
insert into user_authority (user_id, authority_id) values (11, 4);
insert into user_authority (user_id, authority_id) values (12, 4);
insert into user_authority (user_id, authority_id) values (13, 4);
insert into user_authority (user_id, authority_id) values (14, 4);
insert into user_authority (user_id, authority_id) values (15, 4);

-- ROLE_DOCTOR
insert into user_authority (user_id, authority_id) values (16, 3);
insert into user_authority (user_id, authority_id) values (17, 3);
insert into user_authority (user_id, authority_id) values (18, 3);
insert into user_authority (user_id, authority_id) values (19, 3);
insert into user_authority (user_id, authority_id) values (20, 3);
insert into user_authority (user_id, authority_id) values (21, 3);

-- ROLE_PATIENT
insert into user_authority (user_id, authority_id) values (22, 5);
insert into user_authority (user_id, authority_id) values (23, 5);
insert into user_authority (user_id, authority_id) values (24, 5);
insert into user_authority (user_id, authority_id) values (25, 5);
insert into user_authority (user_id, authority_id) values (26, 5);
insert into user_authority (user_id, authority_id) values (27, 5);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- Inserting appointment requests -----------------------------------------------------
insert into appointment_requests (discount,   price,        time,           app_req_type, doctor_id, patient_id, predef_appointment_id)
values                           (0.0,       1000.0,  '2020-05-11 10:00:00',   'DOCTOR',    16,          22,              null);

insert into appointment_requests ( app_req_type,  patient_id, predef_appointment_id)
values                           (   'PATIENT',             23,              1);

insert into appointment_requests (discount,   price,        time,           app_req_type, doctor_id, patient_id, predef_appointment_id)
values                           (0.0,       1000.0,  '2020-05-11 09:00:00',   'DOCTOR',    17,          23,              null);
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting prescriptions-------------------------------------------------------
insert into prescriptions( is_verified, medical_record_id, clinic_id) values ('F', 1, 1);
insert into prescriptions( is_verified, medical_record_id, clinic_id) values ('F', 2, 1);
insert into prescriptions( is_verified, medical_record_id, clinic_id) values ('T', 2, 1);
-- insert into clinics_prescriptions(prescriptions_id, clinic_id) values (1, 1);
-- insert into clinics_prescriptions(prescriptions_id, clinic_id) values (2, 1);

insert into prescriptions_contents(prescription_id, description, medication) values (1, '10mg dnevno', 'Daktanol');
insert into prescriptions_contents(prescription_id, description, medication) values (1, '50mg nedeljno', 'Katopil');
insert into prescriptions_contents(prescription_id, description, medication) values (2, '100mg svaki drugi dan', 'Omeprol');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------- Inserting registration requests -------------------------------------------------------
insert into user_register_request
( email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values ( 'pacijent7@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Milorad', 'Radic', 'Nepoznata 10', 'Novi Sad', 'Srbija', '0641234567', '678678678');


insert into user_register_request
( email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values ( 'pacijent8@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Kris', 'Kristic', 'Nepoznata 10', 'Novi Sad', 'Srbija', '0641234567', '876876876');

insert into user_register_request
( email, password, first_name, last_name, address, city, country, phone_number, social_security_number)
values ( 'pacijent9@gmail.com', '$2a$10$8eEItCz.KnYCjgUy8iQBbeDQbg4LRLd8E3/632x/BzbQA3OQa3R6.', 'Radoje', 'Radic', 'Nepoznata 10', 'Novi Sad', 'Srbija', '0641234567', '098098098');
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------- Inserting text here ----------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

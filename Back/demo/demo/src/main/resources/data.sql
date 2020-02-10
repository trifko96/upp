insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (100, "Pavle", "Trifkovic", "admin",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", false, true, "ADMIN", false);
	
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (200, "Korisnik", "Korisnik", "urednik1",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", false, true, "UREDNIK", false);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (300, "Korisnik", "Korisnik", "urednik2",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", false, true, "UREDNIK", false);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (400, "Nikola", "Pavlovic", "pavlovic1996",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "pavlovic@urednik.com", "Srbija", "Novi Sad", "student", false, true, "UREDNIK", false);

insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (500, "Recenzent", "1", "rec1",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", true, true, "RECENZENT", true);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (600, "Recenzent", "2", "rec2",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", true, true, "RECENZENT", true);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (700, "Recenzent", "3", "rec3",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", true, true, "RECENZENT", true);

insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (800, "Lola", "Savi4", "olga1996",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "trifko96@gmail.com", "Srbija", "Novi Sad", "student", false, true, "AUTOR", false);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (900, "Gandri", "Borkovac", "gandri1996",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "gandri@autor.com", "Srbija", "Novi Sad", "student", false, true, "AUTOR", false);
insert into db_new.korisnik (id, ime, prezime, username, password, email, drzava, grad, titula, recenzent, aktivan, tip, odobren_recenzent) 
	values (1000, "Petar", "Savic", "tata1996",  "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "tata@autor.com", "Srbija", "Novi Sad", "student", false, true, "AUTOR", false);

	
insert into db_new.casopis(id, naziv, issn, open_access, aktivan, izabran, glavni_urednik_id)
	values(100, "Casopis1", "100", false, true, false, 200);
insert into db_new.casopis(id, naziv, issn, open_access, aktivan, izabran, glavni_urednik_id)
	values(200, "Casopis2", "200", true, true, false, 200);
insert into db_new.casopis(id, naziv, issn, open_access, aktivan, izabran, glavni_urednik_id)
	values(300, "Casopis3", "300", true, true, false, 200);	
	

insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(100, "Geografija");
insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(200, "Istorija");
insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(300, "Biologija");
insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(400, "Ekologija");
insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(500, "Knjizevnost");
insert into db_new.naucna_oblast_casopis(id, nazivNO)
	values(600, "Umetnost");	
	
	
INSERT INTO db_new.casopis_recenzent VALUES (100, 500), (100, 600), (100, 700);	
INSERT INTO db_new.casopis_urednik VALUES (100, 300), (100, 400);

INSERT INTO db_new.casopis_no VALUES (100, 100), (100, 300), (200, 200), (200, 400), (300, 500), (300, 600);	
	
INSERT INTO db_new.nocas_recenzent VALUES (100, 500), (100, 600), (300, 700);	
INSERT INTO db_new.nocas_urednik VALUES (100, 300), (300, 400);	

insert into db_new.clanarina(id, aktivna, korisnik_id, casopis_id)
	values(100, true, 900, 300);
insert into db_new.clanarina(id, aktivna, korisnik_id, casopis_id)
	values(200, true, 1000, 200);

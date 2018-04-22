insert into Korisnik (username, password, enabled) 
	values ('admin', '$2a$04$iUK/HUedCxtR4umVSnFADOyXwxizc7.kAe5hyzPHs7.xgHEBu4lF6', 1);

insert into Korisnik (username, password, enabled)
	values ('student', '$2a$04$M7WfkaktG11A/BK5KuZu3ubE9JfIHcagApUO/KSB/jx3Nf.LkYoCi', 1);

insert into KorisnikPrava (username, authority) values ('admin', 'ROLE_ADMIN');
insert into KorisnikPrava (username, authority) values ('admin', 'ROLE_USER');
insert into KorisnikPrava (username, authority) values ('student', 'ROLE_USER');

insert into Dvorana (naziv, kapacitet, dostupnost)
	values ('Dvorana1', 30, 0);
	
insert into Dvorana (naziv, kapacitet, dostupnost)
	values ('Dvorana2', 20, 1);
	
insert into Dvorana (naziv, kapacitet, dostupnost)
	values ('Dvorana3', 25, 1);
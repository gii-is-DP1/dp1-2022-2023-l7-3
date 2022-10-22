-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO users(username,password,enabled) VALUES ('olemornav','olemornav',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'olemornav','owner');

INSERT INTO users(username,password,enabled) VALUES ('ursgarsan','ursgarsan',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'ursgarsan','owner');

INSERT INTO users(username,password,enabled) VALUES ('marmarsol4','marmarsol4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'marmarsol4','owner');

INSERT INTO users(username,password,enabled) VALUES ('migrolgar2','migrolgar2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'migrolgar2','owner');

INSERT INTO users(username,password,enabled) VALUES ('juamornav3','juamornav3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'juamornav3','owner');

INSERT INTO users(username,password,enabled) VALUES ('alvurqmar','alvurqmar',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'alvurqmar','owner');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Olegario', 'Morato', '2335 Independence La.', 'Waunakee', '6085555487', 'olemornav');
INSERT INTO owners VALUES (12, 'Úrsula', 'Garrucho', '2335 Independence La.', 'Waunakee', '666999888', 'ursgarsan');
INSERT INTO owners VALUES (13, 'Maria', 'Marquez', '2515 Inazuma La.', 'Madison', '655685487', 'marmarsol4');
INSERT INTO owners VALUES (14, 'Miguel', 'Roldan', '27 Hopes and Dreams', 'Narnia', '694202496', 'migrolgar2');
INSERT INTO owners VALUES (15, 'Juan Carlos', 'Morato', '27 Hopes and Dreams', 'Narnia', '623426683', 'juamornav3');
INSERT INTO owners VALUES (16, 'Alvaro', 'Urquijo', '15 Mos Eisley', 'Tatooine', '679321375', 'alvurqmar');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Dante', '2013-06-08', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Perico', '2018-06-08', 5, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Yae', '2017-06-21', 1, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'GarfieldV2', '2019-12-12', 1, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18,'Federico pies descalzos segundo','2002-06-30',7,15);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (19, 'Luffy', '2018-10-18', 6, 16);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO card_types VALUES (1, 'luck');
INSERT INTO card_types VALUES (2, 'community_card');

INSERT INTO actions VALUES (1, 'pay');

INSERT INTO cards(id, card_type_id, action_id, quantity) VALUES (1,1,1,100);


/* STREETS COMPANIES AND STATIONS */
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (0, 'Ronda de Valencia', 60, 2, 30, false, 'url', 'BROWN', 50, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (1, 'Plaza Lavapiés', 60, 4, 30, false, '', 'BROWN', 50, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (2, 'Glorieta Cuatro Caminos', 100, 6, 50, false, '', 'LIGHTBLUE', 50, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (3, 'Avenida Reina Victoria', 100, 6, 50, false, '', 'LIGHTBLUE', 50, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (4, 'Calle Bravo Murillo', 120, 8, 60, false, '', 'LIGHTBLUE', 50, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (5, 'Glorieta de Bilbao', 140, 10, 70, false, '', 'PINK', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (6, 'Calle Alberto Aguilera', 140, 10, 70, false, '', 'PINK', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (7, 'Calle Fuencarral', 160, 12, 80, false, '', 'PINK', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (8, 'Avenida Felipe II', 180, 14, 90, false, '', 'ORANGE', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (9, 'Calle Velázquez', 180, 14, 90, false, '', 'ORANGE', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (10, 'Calle Serrano', 200, 16, 100, false, '', 'ORANGE', 100, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (11, 'Avenida de América', 220, 18, 110, false, '', 'RED', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (12, 'Calle María de Molina', 220, 18, 110, false, '', 'RED', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (13, 'Calle Cea Bermúdez', 240, 20, 120, false, '', 'RED', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (14, 'Avenida de los Reyes Católicos', 260, 22, 130, false, '', 'YELLOW', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (15, 'Calle Bailén', 260, 22, 130, false, '', 'YELLOW', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (16, 'Plaza de España', 280, 24, 140, false, '', 'YELLOW', 150, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (17, 'Puerta del Sol', 300, 26, 150, false, '', 'GREEN', 200, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (18, 'Calle Alcalá', 300, 26, 150, false, '', 'GREEN', 200, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (19, 'Avenida Reina Victoria', 320, 28, 160, false, '', 'GREEN', 200, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (20, 'Paseo de la Castellana', 350, 35, 175, false, '', 'BLUE', 200, 0, false );
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel) VALUES (21, 'Paseo del Prado', 400, 50, 200, false, '', 'BLUE', 200, 0, false );

INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (22, 'Compañía de Aguas', 150, 0, 75, false, '');
INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (23, 'Compañía de Electricidad', 150, 0, 75, false, '');

INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (24, 'Estación de las Delicias', 200, 25, 100, false, '');
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (25, 'Estación de Goya', 200, 25, 100, false, '');
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (26, 'Estación del Norte', 200, 25, 100, false, '');
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image) VALUES (27, 'Estación del Mediodía', 200, 25, 100, false, '');



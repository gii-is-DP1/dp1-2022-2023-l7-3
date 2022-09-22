-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'vet1','veterinarian');

INSERT INTO vets(id,version,first_name,last_name) VALUES (1,1, 'James', 'Carter');
INSERT INTO vets(id,version,first_name,last_name) VALUES (2,1, 'Helen', 'Leary');
INSERT INTO vets(id,version,first_name,last_name) VALUES (3,1, 'Linda', 'Douglas');
INSERT INTO vets(id,version,first_name,last_name) VALUES (4,1, 'Rafael', 'Ortega');
INSERT INTO vets(id,version,first_name,last_name) VALUES (5,1, 'Henry', 'Stevens');
INSERT INTO vets(id,version,first_name,last_name) VALUES (6,1, 'Sharon', 'Jenkins');

INSERT INTO specialties(id,version,name) VALUES (1,1, 'radiology');
INSERT INTO specialties(id,version,name) VALUES (2,1, 'surgery');
INSERT INTO specialties(id,version,name) VALUES (3,1, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types(id,version,name) VALUES (1,1, 'cat');
INSERT INTO types(id,version,name) VALUES (2,1, 'dog');
INSERT INTO types(id,version,name) VALUES (3,1, 'lizard');
INSERT INTO types(id,version,name) VALUES (4,1, 'snake');
INSERT INTO types(id,version,name) VALUES (5,1, 'bird');
INSERT INTO types(id,version,name) VALUES (6,1, 'hamster');

INSERT INTO owners VALUES (1,1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2,1, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3,1, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4,1, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5,1, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6,1, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7,1, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8,1, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9,1, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10,1, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (1,1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (2,1, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (3,1, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (4,1, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (5,1, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (6,1, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (7,1, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (8,1, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (9,1, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (10,1, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (11,1, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (12,1, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,version,name,birth_date,type_id,owner_id) VALUES (13,1, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,version,pet_id,visit_date,description) VALUES (1,1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,version,pet_id,visit_date,description) VALUES (2,1, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,version,pet_id,visit_date,description) VALUES (3,1, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,version,pet_id,visit_date,description) VALUES (4,1, 7, '2013-01-04', 'spayed');

INSERT INTO diseases(id,version,name,description) VALUES (1,1,'COVID-19','Es una ‎enfermedad infecciosacausada por un ‎coronavirus recientemente. ‎De acuerdo a los Centros para el Control y la Prevención de Enfermedades de los Estados Unidos, algunas mascotas — incluyendo perros y gatos — también se han infectado con el virus que causa la COVID-19. ‎Sin embargo, en base a la información limitada que existe, se considera poco el riesgo de que los animales trasmitan la COVID-19 a la gente.');
INSERT INTO diseases(id,version,name,description) VALUES (2,1,'Diabetes','La diabetes en perros es una enfermedad compleja causada por la falta de insulina o la respuesta inadecuada de esta. Cuando la mascota come, su sistema digestivo rompe los alimentos en varios componentes, incluyendo la glucosa, que es transportada a las células por la insulina, una hormona que segrega el páncreas. Cuando el animal no produce insulina o no puede utilizarla con normalidad, sus niveles de azúcar en sangre se elevan. El resultado es la hiperglucemia que si no se trata puede causar complicaciones.');

INSERT INTO DISEASES_PET_TYPESWITH_PREVALENCE(DISEASE_ID,PET_TYPESWITH_PREVALENCE_ID) VALUES (2,1);
INSERT INTO DISEASES_PET_TYPESWITH_PREVALENCE(DISEASE_ID,PET_TYPESWITH_PREVALENCE_ID) VALUES (2,2);
INSERT INTO DISEASES_PET_TYPESWITH_PREVALENCE(DISEASE_ID,PET_TYPESWITH_PREVALENCE_ID) VALUES (1,2);

INSERT INTO diagnoses(id,version,visit_id,disease_id,vet_id,description) VALUES (1,1,1,2,2,'La mascota presenta problemas de vista y comportamiento extraño. El dueño afirma alimentarlo regularmente con dulces y chucherías :-S.');
INSERT INTO diagnoses(id,version,visit_id,disease_id,vet_id,description) VALUES (2,1,2,1,1,'La mascota presenta fiebre, pérdida de apetito, y dificultad respiratoria.');
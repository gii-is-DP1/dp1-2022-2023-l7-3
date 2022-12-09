/* Dummy game to store default tiles for duplication */
INSERT INTO game (id) VALUES (0);

/* USERS */
INSERT INTO users (id, username, password, enabled, is_admin) VALUES (0, 'admin', 'admin', 1, 'admin'); 
INSERT INTO users (id, username, password, enabled) VALUES (1, 'xXPaco02Xx', 'paco', 1);
INSERT INTO users (id, username, password, enabled, is_admin) VALUES (2, 'CryptoBro64', 'cryptobro', 1, 'admin');
INSERT INTO users (id, username, password, enabled) VALUES (3, 'TuAndreita98', 'andreita', 1);
INSERT INTO users (id, username, password, enabled) VALUES (4, 'AnimeEnjoyer', 'anime', 1);

/* COMMUNITY BOX, LUCK AND TILES */
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (1,'LUCK', 'CHARGE', 50,'/resources/images/Luck1.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (2,'LUCK', 'PAY_TAX', 150, '/resources/images/Luck2.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (3,'LUCK', 'REPAIR', 0, '/resources/images/Luck3.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (4,'LUCK', 'CHARGE', 150,'/resources/images/Luck4.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (5,'LUCK', 'PAY_TAX', 150,'/resources/images/Luck5.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (6,'LUCK', 'MOVETO', 0,'/resources/images/Luck6.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (7,'LUCK', 'GOTOJAIL', 0,'/resources/images/Luck7.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (8,'LUCK', 'MOVETO',11,'/resources/images/Luck8.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (9,'LUCK', 'FREE', 0,'/resources/images/Luck9.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (10,'LUCK', 'MOVETO',24,'/resources/images/Luck10.PNG' );
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (11,'LUCK', 'MOVE', -3,'/resources/images/Luck11.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (12,'LUCK', 'PAY_TAX', 20,'/resources/images/Luck12.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (13,'LUCK', 'CHARGE', 100,'/resources/images/Luck13.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (14,'LUCK', 'PAY_TAX', 15,'/resources/images/Luck14.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (15,'LUCK', 'MOVETO', 39,'/resources/images/Luck15.PNG');

INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (16,'COMMUNITY_CARD', 'GOTOJAIL', 0,'/resources/images/CB1.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (17,'COMMUNITY_CARD', 'CHARGE', 50,'/resources/images/CB2.PNG' );
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (18,'COMMUNITY_CARD', 'CHARGE_PLAYERS', 10,'/resources/images/CB3.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (19,'COMMUNITY_CARD', 'CHARGE', 20,'/resources/images/CB4.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (20,'COMMUNITY_CARD', 'PAY_TAX', 100,'/resources/images/CB5.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (21,'COMMUNITY_CARD', 'CHARGE', 200,'/resources/images/CB6.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (22,'COMMUNITY_CARD', 'PAY_TAX', 50,'/resources/images/CB7.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (23,'COMMUNITY_CARD', 'CHARGE', 100,'/resources/images/CB8.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (24,'COMMUNITY_CARD', 'CHARGE',25,'/resources/images/CB9.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (25,'COMMUNITY_CARD', 'MOVETO', 0,'/resources/images/CB10.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (26,'COMMUNITY_CARD', 'CHARGE', 100,'/resources/images/CB11.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (27,'COMMUNITY_CARD', 'PAY_TAX', 50,'/resources/images/CB12.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (28,'COMMUNITY_CARD', 'CHARGE', 10,'/resources/images/CB13.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (29,'COMMUNITY_CARD', 'MOVETO', 1,'/resources/images/CB14.PNG');
INSERT INTO cards(id, card_type, action, quantity, badge_image) VALUES (30,'COMMUNITY_CARD', 'FREE', 0,'/resources/images/CB15.PNG');

INSERT INTO community_box(id, game) VALUES (2, 0);
INSERT INTO community_box(id, game) VALUES (17, 0);
INSERT INTO community_box(id, game) VALUES (33, 0);

INSERT INTO luck(id, game) VALUES(7, 0);
INSERT INTO luck(id, game) VALUES(22, 0);
INSERT INTO luck(id, game) VALUES(36, 0);

INSERT INTO taxes(id, price, game) VALUES(4, 200, 0);
INSERT INTO taxes(id, price, game) VALUES(38, 100, 0);

INSERT INTO generics(id, generic_type, game) VALUES(0, 'START', 0);
INSERT INTO generics(id, generic_type, game) VALUES(10, 'JAIL', 0);
INSERT INTO generics(id, generic_type, game) VALUES(20, 'PARKING', 0);
INSERT INTO generics(id, generic_type, game) VALUES(30, 'GOTOJAIL', 0);


/* STREETS COMPANIES AND STATIONS */
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (1, 'Ronda de Valencia', 60, 2, 30, false, '/resources/images/00.png', 'BROWN', 50, 0, false, 10, 30, 90, 160, 250, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (3, 'Plaza Lavapiés', 60, 4, 30, false, '/resources/images/01.png', 'BROWN', 50, 0, false, 20, 60, 180, 320, 450, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (6, 'Glorieta Cuatro Caminos', 100, 6, 50, false, '/resources/images/02.png', 'LIGHTBLUE', 50, 0, false, 30, 90, 270, 400, 550, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (8, 'Avenida Reina Victoria', 100, 6, 50, false, '/resources/images/03.png', 'LIGHTBLUE', 50, 0, false, 30, 90, 270, 400, 550, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (9, 'Calle Bravo Murillo', 120, 8, 60, false, '/resources/images/04.png', 'LIGHTBLUE', 50, 0, false, 40, 100, 300, 450, 600, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (11, 'Glorieta de Bilbao', 140, 10, 70, false, '/resources/images/05.png', 'PINK', 100, 0, false, 50, 150, 450, 625, 750, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (13, 'Calle Alberto Aguilera', 140, 10, 70, false, '/resources/images/06.png', 'PINK', 100, 0, false, 50, 150, 450, 625, 750, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (14, 'Calle Fuencarral', 160, 12, 80, false, '/resources/images/07.png', 'PINK', 100, 0, false, 60, 180, 500, 700, 900, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (16, 'Avenida Felipe II', 180, 14, 90, false, '/resources/images/08.png', 'ORANGE', 100, 0, false, 70, 200, 550, 750, 950, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (18, 'Calle Velázquez', 180, 14, 90, false, '/resources/images/09.png', 'ORANGE', 100, 0, false, 70, 200, 550, 750, 950, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (19, 'Calle Serrano', 200, 16, 100, false, '/resources/images/10.png', 'ORANGE', 100, 0, false, 80, 220, 600, 800, 1000, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (21, 'Avenida de América', 220, 18, 110, false, '/resources/images/11.png', 'RED', 150, 0, false, 90, 250, 700, 875, 1050, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (23, 'Calle María de Molina', 220, 18, 110, false, '/resources/images/12.png', 'RED', 150, 0, false, 90, 250, 700, 875, 1050, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (24, 'Calle Cea Bermúdez', 240, 20, 120, false, '/resources/images/13.png', 'RED', 150, 0, false, 100, 300, 750, 925, 1100, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (26, 'Avenida de los Reyes Católicos', 260, 22, 130, false, '/resources/images/14.png', 'YELLOW', 150, 0, false, 110, 330, 800, 975, 1150, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (27, 'Calle Bailén', 260, 22, 130, false, '/resources/images/15.png', 'YELLOW', 150, 0, false, 110, 330, 800, 975, 1150, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (29, 'Plaza de España', 280, 24, 140, false, '/resources/images/16.png', 'YELLOW', 150, 0, false, 120, 360, 850, 1025, 1200, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (31, 'Puerta del Sol', 300, 26, 150, false, '/resources/images/17.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (32, 'Calle Alcalá', 300, 26, 150, false, '/resources/images/18.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (34, 'Avenida Reina Victoria', 320, 28, 160, false, '/resources/images/19.png', 'GREEN', 200, 0, false, 150, 450, 1000, 1200, 1400, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (37, 'Paseo de la Castellana', 350, 35, 175, false, '/resources/images/20.png', 'BLUE', 200, 0, false, 175, 500, 1100, 1300, 1500, 0);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (39, 'Paseo del Prado', 400, 50, 200, false, '/resources/images/21.png', 'BLUE', 200, 0, false, 200, 600, 1400, 1700, 2000, 0);

INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (28, 'Compañía de Aguas', 150, 0, 75, false, '/resources/images/22.png', 0);
INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (12, 'Compañía de Electricidad', 150, 0, 75, false, '/resources/images/23.png', 0);

INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (15, 'Estación de las Delicias', 200, 25, 100, false, '/resources/images/24.png', 0);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (5, 'Estación de Goya', 200, 25, 100, false, '/resources/images/25.png', 0);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (35, 'Estación del Norte', 200, 25, 100, false, '/resources/images/26.png', 0);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (25, 'Estación del Mediodía', 200, 25, 100, false, '/resources/images/27.png', 0);


/* FINISHED GAME */
INSERT INTO game (id, date, duration, num_casas) VALUES (1, TO_DATE('11/10/2022', 'DD/MM/YYYY'), 107, 0);

INSERT INTO player (id, money, piece, tile, turn_order, has_exit_gate, is_jailed, is_winner, game_id, user_id) VALUES (0,  12000, 'BLUE', 27, 0, 0, 0, 1, 1, 1);
INSERT INTO player (id, money, piece, tile, turn_order, has_exit_gate, is_jailed, is_winner, game_id, user_id) VALUES (1,  69, 'GREEN', 10, 0, 1, 1, 0, 1, 2);
INSERT INTO player (id, money, piece, tile, turn_order, has_exit_gate, is_jailed, is_winner, game_id, user_id) VALUES (2,  420, 'RED', 2, 0, 2, 0, 0, 1, 3);
INSERT INTO player (id, money, piece, tile, turn_order, has_exit_gate, is_jailed, is_winner, game_id, user_id) VALUES (3,  0, 'YELLOW', 31, 0, 3, 0, 0, 1, 4);

INSERT INTO turns(turn_number, roll, game_id, player_id) VALUES (0, 9, 1, 0);
INSERT INTO turns(turn_number, roll, game_id, player_id) VALUES (1, 5, 1, 1);
INSERT INTO turns(turn_number, roll, game_id, player_id) VALUES (2, 7, 1, 2);

INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (1, 'Ronda de Valencia', 60, 2, 30, false, '/resources/images/00.png', 'BROWN', 50, 0, false, 10, 30, 90, 160, 250, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (3, 'Plaza Lavapiés', 60, 4, 30, false, '/resources/images/01.png', 'BROWN', 50, 0, false, 20, 60, 180, 320, 450, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (6, 'Glorieta Cuatro Caminos', 100, 6, 50, false, '/resources/images/02.png', 'LIGHTBLUE', 50, 0, false, 30, 90, 270, 400, 550, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (8, 'Avenida Reina Victoria', 100, 6, 50, false, '/resources/images/03.png', 'LIGHTBLUE', 50, 0, false, 30, 90, 270, 400, 550, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (9, 'Calle Bravo Murillo', 120, 8, 60, false, '/resources/images/04.png', 'LIGHTBLUE', 50, 0, false, 40, 100, 300, 450, 600, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (11, 'Glorieta de Bilbao', 140, 10, 70, false, '/resources/images/05.png', 'PINK', 100, 0, false, 50, 150, 450, 625, 750, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (13, 'Calle Alberto Aguilera', 140, 10, 70, false, '/resources/images/06.png', 'PINK', 100, 0, false, 50, 150, 450, 625, 750, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (14, 'Calle Fuencarral', 160, 12, 80, false, '/resources/images/07.png', 'PINK', 100, 0, false, 60, 180, 500, 700, 900, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (16, 'Avenida Felipe II', 180, 14, 90, false, '/resources/images/08.png', 'ORANGE', 100, 0, false, 70, 200, 550, 750, 950, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (18, 'Calle Velázquez', 180, 14, 90, false, '/resources/images/09.png', 'ORANGE', 100, 0, false, 70, 200, 550, 750, 950, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (19, 'Calle Serrano', 200, 16, 100, false, '/resources/images/10.png', 'ORANGE', 100, 0, false, 80, 220, 600, 800, 1000, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (21, 'Avenida de América', 220, 18, 110, false, '/resources/images/11.png', 'RED', 150, 0, false, 90, 250, 700, 875, 1050, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (23, 'Calle María de Molina', 220, 18, 110, false, '/resources/images/12.png', 'RED', 150, 0, false, 90, 250, 700, 875, 1050, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (24, 'Calle Cea Bermúdez', 240, 20, 120, false, '/resources/images/13.png', 'RED', 150, 0, false, 100, 300, 750, 925, 1100, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (26, 'Avenida de los Reyes Católicos', 260, 22, 130, false, '/resources/images/14.png', 'YELLOW', 150, 0, false, 110, 330, 800, 975, 1150, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (27, 'Calle Bailén', 260, 22, 130, false, '/resources/images/15.png', 'YELLOW', 150, 0, false, 110, 330, 800, 975, 1150, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (29, 'Plaza de España', 280, 24, 140, false, '/resources/images/16.png', 'YELLOW', 150, 0, false, 120, 360, 850, 1025, 1200, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (31, 'Puerta del Sol', 300, 26, 150, false, '/resources/images/17.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (32, 'Calle Alcalá', 300, 26, 150, false, '/resources/images/18.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (34, 'Avenida Reina Victoria', 320, 28, 160, false, '/resources/images/19.png', 'GREEN', 200, 0, false, 150, 450, 1000, 1200, 1400, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (37, 'Paseo de la Castellana', 350, 35, 175, false, '/resources/images/20.png', 'BLUE', 200, 0, false, 175, 500, 1100, 1300, 1500, 1);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (39, 'Paseo del Prado', 400, 50, 200, false, '/resources/images/21.png', 'BLUE', 200, 0, false, 200, 600, 1400, 1700, 2000, 1);

INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (28, 'Compañía de Aguas', 150, 0, 75, false, '/resources/images/22.png', 1);
INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (12, 'Compañía de Electricidad', 150, 0, 75, false, '/resources/images/23.png', 1);

INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (15, 'Estación de las Delicias', 200, 25, 100, false, '/resources/images/24.png', 1);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (5, 'Estación de Goya', 200, 25, 100, false, '/resources/images/25.png', 1);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (35, 'Estación del Norte', 200, 25, 100, false, '/resources/images/26.png', 1);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (25, 'Estación del Mediodía', 200, 25, 100, false, '/resources/images/27.png', 1);

INSERT INTO community_box(id, game) VALUES (2, 1);
INSERT INTO community_box(id, game) VALUES (17, 1);
INSERT INTO community_box(id, game) VALUES (33, 1);

INSERT INTO luck(id, game) VALUES(7, 1);
INSERT INTO luck(id, game) VALUES(22, 1);
INSERT INTO luck(id, game) VALUES(36, 1);

INSERT INTO taxes(id, price, game) VALUES(4, 200, 1);
INSERT INTO taxes(id, price, game) VALUES(38, 100, 1);

INSERT INTO generics(id, generic_type, game) VALUES(0, 'START', 1);
INSERT INTO generics(id, generic_type, game) VALUES(10, 'JAIL', 1);
INSERT INTO generics(id, generic_type, game) VALUES(20, 'PARKING', 1);
INSERT INTO generics(id, generic_type, game) VALUES(30, 'GOTOJAIL', 1);


/* Game being played now */
INSERT INTO game (id, date, num_casas) VALUES (2, TO_DATE('12/11/2022', 'DD/MM/YYYY'), 16);

INSERT INTO player (id, money, piece, tile, has_exit_gate, is_jailed, turn_order, game_id, user_id, is_bankrupcy) VALUES (4,  23000, 'BLUE', 27, 0, 0, 2, 2, 1, false);
INSERT INTO player (id, money, piece, tile, has_exit_gate, is_jailed, turn_order, game_id, user_id, is_bankrupcy) VALUES (5,  577, 'GREEN', 10, 0, 1, 0, 2, 2, false);
INSERT INTO player (id, money, piece, tile, has_exit_gate, is_jailed, turn_order, game_id, user_id, is_bankrupcy) VALUES (6,  1493, 'RED', 2, 0, 0, 1, 2, 3, false);
INSERT INTO player (id, money, piece, tile, has_exit_gate, is_jailed, turn_order, game_id, user_id, is_bankrupcy) VALUES (7,  1684, 'YELLOW', 31, 0, 0, 3, 2, 4, false);

/* STREETS COMPANIES AND STATIONS */
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (1, 'Ronda de Valencia', 60, 2, 30, false, '/resources/images/00.png', 'BROWN', 50, 2, false, 10, 30, 90, 160, 250, 2, 5);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (3, 'Plaza Lavapiés', 60, 4, 30, false, '/resources/images/01.png', 'BROWN', 50, 1, false, 20, 60, 180, 320, 450, 2, 5);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (6, 'Glorieta Cuatro Caminos', 100, 6, 50, false, '/resources/images/02.png', 'LIGHTBLUE', 50, 0, false, 30, 90, 270, 400, 550, 2, 6);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (8, 'Avenida Reina Victoria', 100, 6, 50, false, '/resources/images/03.png', 'LIGHTBLUE', 50, 2, false, 30, 90, 270, 400, 550, 2, 6);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (9, 'Calle Bravo Murillo', 120, 8, 60, false, '/resources/images/04.png', 'LIGHTBLUE', 50, 1, false, 40, 100, 300, 450, 600, 2, 6);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (11, 'Glorieta de Bilbao', 140, 10, 70, false, '/resources/images/05.png', 'PINK', 100, 0, false, 50, 150, 450, 625, 750, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (13, 'Calle Alberto Aguilera', 140, 10, 70, false, '/resources/images/06.png', 'PINK', 100, 2, false, 50, 150, 450, 625, 750, 2, 4);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (14, 'Calle Fuencarral', 160, 12, 80, false, '/resources/images/07.png', 'PINK', 100, 1, false, 60, 180, 500, 700, 900, 2, 4);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (16, 'Avenida Felipe II', 180, 14, 90, false, '/resources/images/08.png', 'ORANGE', 100, 0, false, 70, 200, 550, 750, 950, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (18, 'Calle Velázquez', 180, 14, 90, false, '/resources/images/09.png', 'ORANGE', 100, 2, false, 70, 200, 550, 750, 950, 2, 5);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (19, 'Calle Serrano', 200, 16, 100, false, '/resources/images/10.png', 'ORANGE', 100, 1, false, 80, 220, 600, 800, 1000, 2, 5);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (21, 'Avenida de América', 220, 18, 110, false, '/resources/images/11.png', 'RED', 150, 0, false, 90, 250, 700, 875, 1050, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (23, 'Calle María de Molina', 220, 18, 110, false, '/resources/images/12.png', 'RED', 150, 2, false, 90, 250, 700, 875, 1050, 2, 6);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (24, 'Calle Cea Bermúdez', 240, 20, 120, false, '/resources/images/13.png', 'RED', 150, 1, false, 100, 300, 750, 925, 1100, 2, 6);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (26, 'Avenida de los Reyes Católicos', 260, 22, 130, false, '/resources/images/14.png', 'YELLOW', 150, 0, false, 110, 330, 800, 975, 1150, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game, owner) VALUES (27, 'Calle Bailén', 260, 22, 130, false, '/resources/images/15.png', 'YELLOW', 150, 1, false, 110, 330, 800, 975, 1150, 2, 4);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (29, 'Plaza de España', 280, 24, 140, false, '/resources/images/16.png', 'YELLOW', 150, 0, false, 120, 360, 850, 1025, 1200, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (31, 'Puerta del Sol', 300, 26, 150, false, '/resources/images/17.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (32, 'Calle Alcalá', 300, 26, 150, false, '/resources/images/18.png', 'GREEN', 200, 0, false, 130, 390, 900, 1100, 1275, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (34, 'Avenida Reina Victoria', 320, 28, 160, false, '/resources/images/19.png', 'GREEN', 200, 0, false, 150, 450, 1000, 1200, 1400, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (37, 'Paseo de la Castellana', 350, 35, 175, false, '/resources/images/20.png', 'BLUE', 200, 0, false, 175, 500, 1100, 1300, 1500, 2);
INSERT INTO streets (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, color, building_price, house_num, have_hotel, rental_1_house, rental_2_house, rental_3_house, rental_4_house, rental_hotel, game) VALUES (39, 'Paseo del Prado', 400, 50, 200, false, '/resources/images/21.png', 'BLUE', 200, 0, false, 200, 600, 1400, 1700, 2000, 2);

INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (28, 'Compañía de Aguas', 150, 0, 75, false, '/resources/images/22.png', 2);
INSERT INTO companies (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (12, 'Compañía de Electricidad', 150, 0, 75, false, '/resources/images/23.png', 2);

INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game, owner) VALUES (15, 'Estación de las Delicias', 200, 25, 100, false, '/resources/images/24.png', 2, 5);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game, owner) VALUES (5, 'Estación de Goya', 200, 25, 100, false, '/resources/images/25.png', 2, 5);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (35, 'Estación del Norte', 200, 25, 100, false, '/resources/images/26.png', 2);
INSERT INTO stations (id, name, price, rental_price, mortage_price, is_mortage, badge_Image, game) VALUES (25, 'Estación del Mediodía', 200, 25, 100, false, '/resources/images/27.png', 2);


DELETE FROM restaurants;
DELETE FROM users;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('ivanov', 'ivanov@yandex.ru', 'password'),
  ('petrov', 'petrov@gmail.com', 'password');

INSERT INTO restaurants (name, address, phone) VALUES
  ('TerraMare', 'Tsvetnoi Blvd., 20/1', '+74956081519'),
  ('Salotto', 'Staropimenovskiy Ln., 11/6', '+79100000920'),
  ('La Bottega Siciliana', 'Okhotny Ryad, 2', '+74956600383'),
  ('Bosco Cafe', 'Krasnaya Sq., 3', '+74956203182'),
  ('Dolkabar', 'Krasina St., 7', '+74992547908'),
  ('Osteria Alberobello', 'Leninskiy Ave., 75A', '+74991343524'),
  ('Porosello', 'Lubyanskiy Drive, 25/2', '+74956235969'),
  ('Pasta and Basta', 'Sretenskiy bulvar, 4 | Metro Chistye Prudi', '+74956245252'),
  ('Osteria Mario', 'Baltiyskaya St., 9', '+74957907090'),
  ('Coffee Room', 'Arbat St., 13', '+74956973553'),
  ('Forte Bello', 'Mezhdunarodnaya St., 18 | Mall Vegas Crocus City', '+74952361072'),
  ('Donna Margarita', '1905 Goda St., 2A', '+74996827000'),
  ('Bosco Bar', 'Krasnaya Sq., 3', '+74956273703');

INSERT INTO menu (date, restaurant_id) VALUES
  ('2017-12-30', 100002),
  ('2017-12-30', 100003),
  ('2017-12-30', 100004),
  ('2017-12-30', 100005),
  ('2017-12-30', 100006),
  ('2017-12-30', 100007),
  ('2017-12-30', 100008),
  ('2017-12-30', 100009),
  ('2017-12-30', 100010),
  ('2017-12-30', 100011),
  ('2017-12-30', 100012),
  ('2017-12-30', 100013),
  ('2017-12-30', 100014),
  ('2017-12-31', 100002);

INSERT INTO dishes (description, price, menu_id) VALUES
  ('British crab spaghetti', 14.75, 100015),
  ('Oxtail Lasagne', 13.95, 100015),
  ('Tiramisu', 5.95, 100015),
  ('Squash Cannelloni', 12.95, 100016),
  ('The carbonara', 11.95, 100016),
  ('Sausage Ravioli', 12.95, 100017),
  ('Sicilian chicken', 14.95, 100017),
  ('Sweet potato fries', 4.00, 100018),
  ('The Porkie', 12.95, 100019),
  ('The Funghi', 12.95, 100020),
  ('The Tuscan', 12.95, 100021),
  ('The Meetball', 12.95, 100022),
  ('The Julietta', 11.95, 100023),
  ('Pumpkin arancini', 5.95, 100024),
  ('Mushroom fritti', 5.95, 100025),
  ('Italian meatballs', 6.25, 100026),
  ('Gennaro''s mixed grill', 19.95, 100027),
  ('Tender roasted aubergin', 9.95, 100028)

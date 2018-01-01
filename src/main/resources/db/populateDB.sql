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

INSERT INTO dishes (name, price, date, restaurant_id) VALUES
  ('British crab spaghetti', 14.75, '2017-12-30', 100002),
  ('Oxtail Lasagne', 13.95, '2017-12-30', 100002),
  ('Tiramisu', 5.95, '2017-12-30', 100002),
  ('Squash Cannelloni', 12.95, '2017-12-30', 100003),
  ('The carbonara', 11.95, '2017-12-30', 100003),
  ('Sausage Ravioli', 12.95, '2017-12-30', 100004),
  ('Sicilian chicken', 14.95, '2017-12-30', 100004),
  ('Sweet potato fries', 4.00, '2017-12-30', 100005),
  ('The Porkie', 12.95, '2017-12-30', 100006),
  ('The Funghi', 12.95, '2017-12-30', 100007),
  ('The Tuscan', 12.95, '2017-12-30', 100008),
  ('The Meetball', 12.95, '2017-12-30', 100009),
  ('The Julietta', 11.95, '2017-12-30', 100010),
  ('Pumpkin arancini', 5.95, '2017-12-30', 100011),
  ('Mushroom fritti', 5.95, '2017-12-30', 100012),
  ('Italian meatballs', 6.25, '2017-12-30', 100013),
  ('Gennaro''s mixed grill', 19.95, '2017-12-30', 100014),
  ('Tender roasted aubergin', 9.95, '2017-12-31', 100002)

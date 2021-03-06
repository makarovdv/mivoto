DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name              VARCHAR(255)               NOT NULL,
  email             VARCHAR(255)               NOT NULL,
  password          VARCHAR(255)               NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id           INTEGER NOT NULL,
  role              VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name              VARCHAR(255)               NOT NULL,
  address           VARCHAR(255)               NOT NULL,
  phone             VARCHAR(255)               NOT NULL,
  CONSTRAINT restaurants_unique_name           UNIQUE  (name)
);

CREATE TABLE menu
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date              DATE                       NOT NULL,
  restaurant_id     INTEGER                    NOT NULL,
  CONSTRAINT menu_unique_restaurant_and_date_idx         UNIQUE  (date, restaurant_id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  description       VARCHAR(255)               NOT NULL,
  price             DECIMAL(10,2)              NOT NULL,
  menu_id           INTEGER    NOT NULL,
  CONSTRAINT dishes_unique_menu_and_description_idx UNIQUE (menu_id, description),
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date              DATE                       NOT NULL,
  user_id           INTEGER                    NOT NULL,
  restaurant_id     INTEGER                    NOT NULL,
  CONSTRAINT votes_unique_date_and_user_idx UNIQUE  (date, user_id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
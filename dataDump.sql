/* DATA DUMP */

-- Stores
INSERT INTO store_ VALUES(DEFAULT, 'DCWORLD', 'Avenida Siempreviva 123', 1);
INSERT INTO store_ VALUES(DEFAULT, 'MarvelTrading', '742 Evergreen Terrace', 1);

-- Unities
INSERT INTO UNITY VALUES(DEFAULT, 'unidades', 1);
INSERT INTO UNITY VALUES(DEFAULT, 'comics', 1);
INSERT INTO UNITY VALUES(DEFAULT, 'chompas', 1);
INSERT INTO UNITY VALUES(DEFAULT, 'pantalones', 1);
INSERT INTO UNITY VALUES(DEFAULT, 'parches', 1);

-- Documents
INSERT INTO DOCUMENT VALUES(DEFAULT, 'Factura de Compra', 1);

-- Creation of users
INSERT INTO USERS VALUES(DEFAULT, 'admin', MD5('admin'), '00000000', 'Admin', 'Master', 1, 1);
INSERT INTO USERS VALUES(DEFAULT, 'user', MD5('user'), '11111111', 'User', 'Standar', 0, 1);
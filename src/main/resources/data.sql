INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(1, 'Company One', '6831 Hollister Ave', 'California', 'United States', 'companyone@companyone.com', '12345678910');

INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(2, 'Company Two', '3 Vere Street', 'London', 'England', null, '07958 404 012');

INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(3, 'Company Three', '3 De Vere Gardens', 'London', 'England', 'companythree@companythree.com', null);

INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(4, 'Company Four', '6831 Hollister Ave', 'California', 'United States', 'companyfour@companyfour.com', '0123456789');

INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(5, 'Company Five', 'Address Five', 'City Five', 'Country Five', 'companyfive@companyfive.com', '0123456789');

INSERT INTO Company (id, name, address, city, country, email, phone)
VALUES(6, 'Company without beneficial owner', 'Address Six', 'City Six', 'Country Six', 'companysix@companysix.com', '0123456789');

INSERT INTO Beneficial (id, name, company_id)
VALUES(1, 'Beneficial Owner One - Company One', 1);

INSERT INTO Beneficial (id, name, company_id)
VALUES(2, 'Beneficial Owner Two - Company One', 1);

INSERT INTO Beneficial (id, name, company_id)
VALUES(3, 'Beneficial Owner Three - Company One', 1);

INSERT INTO Beneficial (id, name, company_id)
VALUES(4, 'Beneficial Owner One - Company Two', 2);

INSERT INTO Beneficial (id, name, company_id)
VALUES(5, 'Beneficial Owner Two - Company Two', 2);

INSERT INTO Beneficial (id, name, company_id)
VALUES(6, 'Beneficial Owner One - Company Three', 3);

INSERT INTO Beneficial (id, name, company_id)
VALUES(7, 'Beneficial Owner One - Company Five', 5);
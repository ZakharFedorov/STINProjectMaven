SELECT * from Users WHERE email = 'zakhar.fedo@mail.ru';

CREATE TABLE Users (
 id serial NOT NULL UNIQUE,
 email VARCHAR(55) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 first_name varchar(50) NOT NULL,
 last_name varchar(50) NOT NULL,
 code int check (code between 1000000 and 9999999),
 verified boolean not null DEFAULT false,
PRIMARY KEY (id));

DROP TABLE Users CASCADE;

INSERT into Users (id, email, password, first_name, last_name, code, verified)
VALUES (1, 'zakhar.fedorov@tul.cz',
        '$2a$10$FmyQQHc9dq5DhAOnrwlGqOdhQJ6N75fsk7WQtadObHOc5REHB4.fq',
        'Zakhar', 'Fedorov', null, false),
       (2, 'zahar.fedo@mail.ru',
        '$2a$10$AzRy1hW8qHo2kxZuAYntGeVHzKbfWjIjWHFQJo7jTgMdKpVG.3HS6',
        'Zakhar', 'Fedorov', null, false);


CREATE TABLE Accounts (
 account_num INTEGER NOT NULL UNIQUE CHECK ( account_num between 1000000 and 9999999),
 crown_balance DECIMAL(11,2),
 dollar_balance DECIMAL(11,2),
 euro_balance DECIMAL(11,2),
 id_user INTEGER NOT NULL,
PRIMARY KEY (account_num),
FOREIGN KEY (id_user) REFERENCES Users(id));

DROP TABLE Accounts CASCADE;

INSERT into Accounts (account_num, crown_balance, dollar_balance, euro_balance, id_user)
VALUES (1737865, 12000.00, 421.24, null, 1),
       (5236547, null, 753.53, 14.95, 2);

CREATE TABLE Transactions (
 id serial NOT NULL UNIQUE,
 account_num INTEGER NOT NULL,
 amount DECIMAL(11,2) NOT NULL,
 currency VARCHAR(3) NOT NULL,
 date VARCHAR(30) NOT NULL,
 description VARCHAR(10) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (account_num) REFERENCES Accounts(account_num));

DROP TABLE Transactions CASCADE;

INSERT into Transactions (account_num, amount, currency, date, description)
VALUES (1737865, 345, 'EUR', '17.09.2022 18:08:53', 'Payment'),
       (1737865, 22, 'USD', '17.09.2022 12:15:01', 'Deposit');
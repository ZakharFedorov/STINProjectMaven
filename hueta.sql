SELECT * from Users WHERE email = 'zakhar.fedo@mail.ru';

CREATE TABLE Users (
 id INT NOT NULL UNIQUE,
 email VARCHAR(55) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 first_name varchar(50) NOT NULL,
 last_name varchar(50) NOT NULL,
 code int check (code between 1000000 and 9999999),
 verified boolean not null DEFAULT false,
PRIMARY KEY (id));

DROP TABLE Users;

INSERT into Users (id, email, password, first_name, last_name, code, verified)
VALUES (1, 'zakhar.fedorov@tul.cz',
        '$2a$10$FmyQQHc9dq5DhAOnrwlGqOdhQJ6N75fsk7WQtadObHOc5REHB4.fq',
        'Zakhar', 'Fedorov', 1237365, false),
       (2, 'zakhar.fedo@mail.ru',
        '$2a$10$AzRy1hW8qHo2kxZuAYntGeVHzKbfWjIjWHFQJo7jTgMdKpVG.3HS6',
        'Zakhar', 'Fedorov', 3472764, false);


CREATE TABLE Accounts (
 account_num INT NOT NULL UNIQUE CHECK ( account_num between 1000000 and 9999999),
 crown_balance DECIMAL(11,2) NOT NULL,
 dollar_balance DECIMAL(11,2) NOT NULL,
 euro_balance DECIMAL(11,2) NOT NULL,
 id_user INT NOT NULL,
PRIMARY KEY (account_num),
FOREIGN KEY (id_user) REFERENCES Users(id));

DROP TABLE Accounts;

INSERT into Accounts (account_num, crown_balance, dollar_balance, euro_balance, id_user)
VALUES (1737865, 12000.00, 421.24, 1.10, 1);

CREATE TABLE Transactions (
 id INT NOT NULL UNIQUE,
 account_num INT NOT NULL,
 amount DECIMAL(11,2) NOT NULL,
 currency VARCHAR(3) NOT NULL,
 date DATE NOT NULL,
 time TIME NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (account_num) REFERENCES Accounts(account_num));

DROP TABLE Transactions;
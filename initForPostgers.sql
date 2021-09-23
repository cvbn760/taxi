DROP TABLE IF EXISTS type_auto CASCADE;
DROP TABLE IF EXISTS transfer CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS driver CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS aggregator CASCADE;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE user_roles
(
    role_id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    role                  VARCHAR(20)                         NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (role_id, role)
);

CREATE TABLE type_auto
(
    type_id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    auto                  VARCHAR(20)                         NOT NULL,
    CONSTRAINT type_auto_idx UNIQUE (type_id, auto)
);

CREATE TABLE car
(
	car_id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	aggregator_id         INTEGER                             NOT NULL,
	title                 VARCHAR(100)                        NOT NULL,
	capacity              INTEGER                             NOT NULL,
	maxBaggageWeight      FLOAT                               NOT NULL,
	mileage               FLOAT                               NOT NULL,
	dateOfRelease         DATE                                NOT NULL,
	number_car            VARCHAR(10)                         NOT NULL,
	vinNumber             VARCHAR(17)                         NOT NULL,
	CONSTRAINT car_idx UNIQUE (car_id, number_car, vinNumber)
);

CREATE TABLE driver
(
	driver_id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	aggregator_id         INTEGER                            NOT NULL,
	first_name            VARCHAR(30)                        NOT NULL,
	last_name             VARCHAR(30)                        NOT NULL,
	driving_experience    FLOAT                              NOT NULL,
	rating                FLOAT                              NOT NULL,
	gender                BOOLEAN                            NOT NULL,
	phone                 VARCHAR(20)                        NOT NULL,
	email                 VARCHAR(50)                        NOT NULL,
	CONSTRAINT driver_idx UNIQUE (driver_id, email, phone)
);

CREATE TABLE passenger
(
	passenger_id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	aggregator_id         INTEGER                            NOT NULL,
	first_name            VARCHAR(30)                        NOT NULL,
	last_name             VARCHAR(30)                        NOT NULL,
	gender                BOOLEAN                            NOT NULL,
	phone                 VARCHAR(20)                        NOT NULL,
	email                 VARCHAR(50)                        NOT NULL,
	review_id             INTEGER                            NOT NULL,
	transfer_id           INTEGER                            NOT NULL,
	weight                FLOAT                              NOT NULL,
	height                FLOAT                              NOT NULL,
	CONSTRAINT passenger_idx UNIQUE (passenger_id, email, phone)
);

CREATE TABLE transfer
(
	transfer_id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	aggregator_id          INTEGER                            NOT NULL,
	review_id              INTEGER                            NOT NULL,
	car_id                 INTEGER                            NOT NULL,
	passenger_id           INTEGER                            NOT NULL,
	driver_id              INTEGER                            NOT NULL,
	price                  FLOAT                              NOT NULL,
	distance               FLOAT                              NOT NULL,
	begin_date_arrival     DATE                               NOT NULL,
	end_date_arrival       DATE                               NOT NULL,
	begin_date_departure   DATE                               NOT NULL,
	end_date_departure     DATE                               NOT NULL,
	begin_date_reservation DATE                               NOT NULL,
	end_date_reservation   DATE                               NOT NULL,
	addressExtraction      VARCHAR(100)                       NOT NULL,
	addressDeparture       VARCHAR(100)                       NOT NULL,
	CONSTRAINT transfer_idx UNIQUE (transfer_id)
);

CREATE TABLE review
(
	review_id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	review               VARCHAR(1000),
	reviewDateTime       DATE,             
	CONSTRAINT review_idx UNIQUE (review_id)
);

CREATE TABLE aggregator
(
	aggregator_id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
	name                 VARCHAR(50)                          NOT NULL,
	email                VARCHAR(50)                          NOT NULL,
	password             VARCHAR(100)                         NOT NULL,
	CONSTRAINT aggregator_idx UNIQUE (aggregator_id, email)
	
);

ALTER TABLE type_auto ADD FOREIGN KEY (type_id) REFERENCES car(car_id) ON DELETE CASCADE;
ALTER TABLE transfer ADD FOREIGN KEY (review_id) REFERENCES review(review_id) ON DELETE CASCADE;
ALTER TABLE transfer ADD FOREIGN KEY (car_id) REFERENCES car(car_id) ON DELETE CASCADE;
ALTER TABLE transfer ADD FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE;
ALTER TABLE transfer ADD FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE;
ALTER TABLE driver ADD FOREIGN KEY (aggregator_id ) REFERENCES aggregator(aggregator_id ) ON DELETE CASCADE;
ALTER TABLE car ADD FOREIGN KEY (aggregator_id) REFERENCES aggregator(aggregator_id) ON DELETE CASCADE;
ALTER TABLE passenger ADD FOREIGN KEY (aggregator_id) REFERENCES aggregator(aggregator_id) ON DELETE CASCADE;
ALTER TABLE transfer ADD FOREIGN KEY (aggregator_id) REFERENCES aggregator(aggregator_id) ON DELETE CASCADE;
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE;
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES driver(driver_id) ON DELETE CASCADE;
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES aggregator(aggregator_id) ON DELETE CASCADE;
ALTER TABLE passenger ADD FOREIGN KEY (review_id) REFERENCES review(review_id) ON DELETE CASCADE;

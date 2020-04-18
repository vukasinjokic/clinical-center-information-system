CREATE TABLE nurses (
    id          INTEGER         NOT NULL AUTO_INCREMENT,
    email       VARCHAR (50)    NOT NULL,
    password    VARCHAR (50)    NOT NULL,
    firstName   VARCHAR (50)    NOT NULL,
    lastName    VARCHAR (50)    NOT NULL,
    address     VARCHAR (50)    NOT NULL,
    city        VARCHAR (20)    NOT NULL,
    country     VARCHAR (20)    NOT NULL,
    phoneNumber VARCHAR (20)    NOT NULL,
    socialSecurityNumber    VARCHAR (20)    NOT NULL,
    calendar_id INTEGER         NOT NULL,

    PRIMARY KEY (id)

    CONSTRAINT nurses_calendar_id_fkey FOREIGN KEY (calendar_id)
        REFERENCES calendars (calendar_id)
);

CREATE TABLE patients (
    id          INTEGER         NOT NULL AUTO_INCREMENT,
    email       VARCHAR (50)    NOT NULL,
    password    VARCHAR (50)    NOT NULL,
    firstName   VARCHAR (50)    NOT NULL,
    lastName    VARCHAR (50)    NOT NULL,
    address     VARCHAR (50)    NOT NULL,
    city        VARCHAR (20)    NOT NULL,
    country     VARCHAR (20)    NOT NULL,
    phoneNumber VARCHAR (20)    NOT NULL,
    socialSecurityNumber    VARCHAR (20)    NOT NULL,
    medicalRecordId         INTEGER         NOT NULL,

    PRIMARY KEY (id)

    CONSTRAINT nurses_calendar_id_fkey FOREIGN KEY (calendar_id)
        REFERENCES calendars (calendar_id)
);

CREATE TABLE operationRooms (
    id          INTEGER         NOT NULL AUTO_INCEREMENT,
    name        VARCHAR (20)    NOT NULL,
    calendar_id INTEGER         NOT NULL,

    PRIMARY KEY (id)

    CONSTRAINT operationRooms_calendar_id_fkey FOREIGN KEY (calendat_id)
        REFERENCES calendars (calendar_id)
);
CREATE TABLE requests (
    id      INTEGER     NOT NULL AUTO_INCREMENT,
    description     VARCHAR (255)   NOT NULL,
    type    VARCHAR (20)

    PRIMARY KEY (id)
)
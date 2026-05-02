--creation of the tables
-- in the ids we use number(10) because we can save 10 digits of diferents ids

--USRPASS uses VARCHAR2(255) to store secure password hashes (e.g., BCrypt) instead of plain text.
CREATE TABLE USERS (
USERID NUMBER(10) PRIMARY KEY,
USERNAME VARCHAR2(30)NOT NULL,
LASTNAME VARCHAR2(50)NOT NULL,
EMAIL VARCHAR2(100) NOT NULL,
DNI VARCHAR2(12) NOT NULL,
USRPASS VARCHAR2(255) NOT NULL,
CREATE_DATE DATE DEFAULT SYSDATE,
CONSTRAINT UQ_USREMAIL UNIQUE (EMAIL),
CONSTRAINT UQ_DNI UNIQUE (DNI)
);

--we create a sequence so the java conection can use it in de ids of the users
CREATE SEQUENCE USERS_SEQ 
    START WITH 1 
    INCREMENT BY 1;
--we put checks so we cant have negative prices or durations
CREATE TABLE PLANS (
PLANID NUMBER(10) PRIMARY KEY,
PLANNAME VARCHAR2(40) NOT NULL,
PRICE NUMBER(10,2)NOT NULL,
PLANDURATION NUMBER(3) NOT NULL,
CONSTRAINT UQ_PLANNAME UNIQUE (PLANNAME),
CONSTRAINT CH_PRICE CHECK (PRICE > 0),
CONSTRAINT CH_DURATION CHECK (PLANDURATION > 0)
);
--we create a sequence so the java conection can use it in de ids of the PLANS
CREATE SEQUENCE PLAN_SEQ 
    START WITH 1 
    INCREMENT BY 1;

CREATE TABLE SUBSCRIPTION (
SUBSCRIPTIONID NUMBER(10) PRIMARY KEY,
DATESTART DATE DEFAULT SYSDATE ,
DATEEND DATE ,
USERID NUMBER(10) NOT NULL,
PLANID NUMBER(10) NOT NULL,
CONSTRAINT FK_USERID FOREIGN KEY(USERID) REFERENCES USERS(USERID),
CONSTRAINT FK_PLANID FOREIGN KEY(PLANID) REFERENCES PLANS(PLANID)
);
--we create a sequence so the java conection can use it in de ids of the SUBSCRIPTIONS
CREATE SEQUENCE SUBSCRIPTION_SEQ 
    START WITH 1 
    INCREMENT BY 1;

--creation of the index for a optimised search
CREATE INDEX IDX_FK_USERID ON SUBSCRIPTION(USERID);
CREATE INDEX IDX_FK_PLANID ON SUBSCRIPTION(PLANID);

--Trigger to automate de end date of a suscription
--creation of the trigger
--the trigger activates before any insert in the table subscription for any row that is going to be inserted
--we save the duration of a plan using the id of the new plan on the insert
--we have a if so we can save a start date in case ther isn't any one before
--and we use the function add_monts tu add de duration of the plan to the start date and have the end date
--finaly we have the exceptions if the id of the plan dosen't exist and other type of errors
CREATE OR REPLACE TRIGGER DATE_END
BEFORE INSERT ON SUBSCRIPTION
FOR EACH ROW 
DECLARE
    V_DURATION NUMBER;
BEGIN
    SELECT PLANDURATION INTO V_DURATION FROM PLANS WHERE PLANID =:NEW.PLANID;
    
    IF :NEW.DATESTART IS NULL THEN
        :NEW.DATESTART := SYSDATE;
    END IF;

    :NEW.DATEEND := ADD_MONTHS(:NEW.DATESTART, V_DURATION);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error: THE PLANID DOESNT EXIST.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'UNKOWN ERROR IN THE TRIGGER.');
END;

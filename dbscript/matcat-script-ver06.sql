DROP TABLE TBL_AUTH_MEMBER;
DROP TABLE TBL_AUTHORITY;
DROP TABLE TBL_CART;
DROP TABLE TBL_DELIVERY;
DROP TABLE TBL_FILES;
DROP TABLE TBL_PRD_ALLERGY;
DROP TABLE TBL_PRD_REVIEW;
DROP TABLE TBL_QNA;
DROP TABLE TBL_REFUND;
DROP TABLE TBL_REPLY;
DROP TABLE TBL_ALLERGY;
DROP TABLE TBL_NOTICE;
DROP TABLE TBL_ORDER_DETAIL;
DROP TABLE TBL_ORDER;
DROP TABLE TBL_PAYMENT;
DROP TABLE TBL_PRODUCT;
DROP TABLE TBL_ACCOUNT;
DROP TABLE TBL_CATEGORY;

CREATE TABLE TBL_ACCOUNT
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    MEMBER_ID    VARCHAR2(50) NOT NULL,
    MEMBER_PWD    VARCHAR2(50) NOT NULL,
    MEMBER_NAME    VARCHAR2(10) NOT NULL,
    MEMBER_EMAIL    VARCHAR2(50) NOT NULL,
    MEMBER_GENDER    CHAR(5) NOT NULL,
    MEMBER_PHONE    VARCHAR2(50) NOT NULL,
    MEMBER_ADDRESS    VARCHAR2(50) NOT NULL,
    MEMBER_LEAVE_YN    CHAR(1) NOT NULL,
    MEMBER_GRADE    VARCHAR2(5) NOT NULL,
    MEMBER_LEAVE_REASON    VARCHAR2(50)
);

CREATE UNIQUE INDEX PK_ACCOUNT ON TBL_ACCOUNT
( MEMBER_NO );

ALTER TABLE TBL_ACCOUNT
 ADD CONSTRAINT PK_ACCOUNT PRIMARY KEY ( MEMBER_NO )
 USING INDEX PK_ACCOUNT;


CREATE TABLE TBL_ALLERGY
(
    ALLERGY_NAME    VARCHAR2(10) NOT NULL,
    ALLERGY_CODE    NUMBER(3) NOT NULL
);

CREATE UNIQUE INDEX PK_ALLERGY ON TBL_ALLERGY
( ALLERGY_CODE );

ALTER TABLE TBL_ALLERGY
 ADD CONSTRAINT PK_ALLERGY PRIMARY KEY ( ALLERGY_CODE )
 USING INDEX PK_ALLERGY;


CREATE TABLE TBL_AUTH_MEMBER
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    AUTH_CODE    NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX PK_AUTH_MEMBER ON TBL_AUTH_MEMBER
( MEMBER_NO,AUTH_CODE );

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT PK_AUTH_MEMBER PRIMARY KEY ( MEMBER_NO,AUTH_CODE )
 USING INDEX PK_AUTH_MEMBER;

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT FK_MEMBER_NO3 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT FK_AUTH_CODE FOREIGN KEY ( AUTH_CODE )
 REFERENCES TBL_AUTHORITY (AUTH_CODE );


CREATE TABLE TBL_AUTHORITY
(
    AUTH_CODE    NUMBER(10) NOT NULL,
    AUTH_NAME    VARCHAR2(10) NOT NULL,
    AUTH_EXPLANATION    VARCHAR2(20) NOT NULL
);

CREATE UNIQUE INDEX PK_AUTHORITY ON TBL_AUTHORITY
( AUTH_CODE );

ALTER TABLE TBL_AUTHORITY
 ADD CONSTRAINT PK_AUTHORITY PRIMARY KEY ( AUTH_CODE )
 USING INDEX PK_AUTHORITY;


CREATE TABLE TBL_CART
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    PRD_CODE    NUMBER(10) NOT NULL,
    QUANTITY    NUMBER(5) NOT NULL
);

CREATE UNIQUE INDEX PK_CART ON TBL_CART
( MEMBER_NO,PRD_CODE );

ALTER TABLE TBL_CART
 ADD CONSTRAINT PK_CART PRIMARY KEY ( MEMBER_NO,PRD_CODE )
 USING INDEX PK_CART;

ALTER TABLE TBL_CART
 ADD CONSTRAINT FK_MEMBER_NO FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );

ALTER TABLE TBL_CART
 ADD CONSTRAINT FK_PRD_CODE FOREIGN KEY ( PRD_CODE )
 REFERENCES TBL_PRODUCT (PRD_CODE );


CREATE TABLE TBL_CATEGORY
(
    CATEGORY_CODE    NUMBER(3) NOT NULL,
    CATEGORY_NAME    VARCHAR2(10) NOT NULL
);

CREATE UNIQUE INDEX PK_CATEGORY ON TBL_CATEGORY
( CATEGORY_CODE );

ALTER TABLE TBL_CATEGORY
 ADD CONSTRAINT PK_CATEGORY PRIMARY KEY ( CATEGORY_CODE )
 USING INDEX PK_CATEGORY;


CREATE TABLE TBL_DELIVERY
(
    INVOICE_NUM    NUMBER(10),
    SHIP_ADDRESS    VARCHAR2(100) NOT NULL,
    SHIP_CHARGE    NUMBER(10) NOT NULL,
    RECIPIENT_NAME    VARCHAR2(10) NOT NULL,
    RECIPIENT_PHONE    VARCHAR2(20) NOT NULL,
    ORD_CODE    NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX PK_DELIVERY ON TBL_DELIVERY
( ORD_CODE );

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT PK_DELIVERY PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_DELIVERY;

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT FK_ORD_CODE FOREIGN KEY ( ORD_CODE )
 REFERENCES TBL_ORDER (ORD_CODE );


CREATE TABLE TBL_FILES
(
    FILE_PATH    VARCHAR2(1000) NOT NULL,
    FILE_SIZE    NUMBER(20) NOT NULL,
    FILE_UPLOAD_DATE    DATE NOT NULL,
    FILE_ORIGIN_NAME    VARCHAR2(100) NOT NULL,
    FILE_SAVED_NAME    VARCHAR2(100) NOT NULL,
    FILE_TYPE    VARCHAR2(20) NOT NULL,
    FILE_TITLE_IMG_YN    CHAR(1),
    POST_CODE    NUMBER(10),
    REV_CODE    NUMBER(10),
    INQ_CODE    NUMBER(20),
    PRD_CODE    NUMBER(20) NOT NULL,
    FILE_IMG_CODE    NUMBER(20) NOT NULL
);

CREATE UNIQUE INDEX PK_FILES ON TBL_FILES
( FILE_IMG_CODE );

ALTER TABLE TBL_FILES
 ADD CONSTRAINT PK_FILES PRIMARY KEY ( FILE_IMG_CODE )
 USING INDEX PK_FILES;

ALTER TABLE TBL_FILES
 ADD CONSTRAINT FK_PRD_CODE5 FOREIGN KEY ( PRD_CODE )
 REFERENCES TBL_PRODUCT (PRD_CODE );

ALTER TABLE TBL_FILES
 ADD CONSTRAINT FK_REV_CODE FOREIGN KEY ( REV_CODE )
 REFERENCES TBL_PRD_REVIEW (REV_CODE );

ALTER TABLE TBL_FILES
 ADD CONSTRAINT FK_POST_CODE3 FOREIGN KEY ( POST_CODE )
 REFERENCES TBL_NOTICE (POST_CODE );

ALTER TABLE TBL_FILES
 ADD CONSTRAINT FK_INQ_CODE2 FOREIGN KEY ( INQ_CODE )
 REFERENCES TBL_QNA (INQ_CODE );


CREATE TABLE TBL_NOTICE
(
    POST_CODE    NUMBER(10) NOT NULL,
    POST_TITLE    VARCHAR2(100) NOT NULL,
    POST_CONTENT    VARCHAR2(4000) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE
);

CREATE UNIQUE INDEX PK_NOTICE ON TBL_NOTICE
( POST_CODE );

ALTER TABLE TBL_NOTICE
 ADD CONSTRAINT PK_NOTICE PRIMARY KEY ( POST_CODE )
 USING INDEX PK_NOTICE;

ALTER TABLE TBL_NOTICE
 ADD CONSTRAINT FK_POST_CODE FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );


CREATE TABLE TBL_ORDER
(
    ORD_CODE    NUMBER(10) NOT NULL,
    ORD_DATE    DATE NOT NULL,
    ORD_PRICE    NUMBER(8) NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    ORD_TOTAL_COUNT    NUMBER(3) NOT NULL
);

CREATE UNIQUE INDEX PK_ORDER ON TBL_ORDER
( ORD_CODE );

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT PK_ORDER PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_ORDER;

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT FK_ORD_CODE3 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );


CREATE TABLE TBL_ORDER_DETAIL
(
    ORD_COUNT    NUMBER(3) NOT NULL,
    PRD_CODE    NUMBER(10) NOT NULL,
    ORD_CODE    NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX PK_ORDER_DETAIL ON TBL_ORDER_DETAIL
( PRD_CODE,ORD_CODE );

ALTER TABLE TBL_ORDER_DETAIL
 ADD CONSTRAINT PK_ORDER_DETAIL PRIMARY KEY ( PRD_CODE,ORD_CODE )
 USING INDEX PK_ORDER_DETAIL;

ALTER TABLE TBL_ORDER_DETAIL
 ADD CONSTRAINT FK_ORD_CODE2 FOREIGN KEY ( ORD_CODE )
 REFERENCES TBL_ORDER (ORD_CODE );

ALTER TABLE TBL_ORDER_DETAIL
 ADD CONSTRAINT FK_PRD_CODE4 FOREIGN KEY ( PRD_CODE )
 REFERENCES TBL_PRODUCT (PRD_CODE );


CREATE TABLE TBL_PAYMENT
(
    PAY_CODE    NUMBER(10) NOT NULL,
    PAY_DATE    DATE NOT NULL,
    PAY_AMOUNT    NUMBER(8) NOT NULL,
    PAY_STATUS    CHAR(1) NOT NULL,
    ORD_CODE    NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX PK_PAYMENT ON TBL_PAYMENT
( PAY_CODE );

ALTER TABLE TBL_PAYMENT
 ADD CONSTRAINT PK_PAYMENT PRIMARY KEY ( PAY_CODE )
 USING INDEX PK_PAYMENT;


CREATE TABLE TBL_PRD_ALLERGY
(
    ALLERGY_CODE    NUMBER(3) NOT NULL,
    PRD_CODE    NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX PK_PRD_ALLERGY ON TBL_PRD_ALLERGY
( ALLERGY_CODE,PRD_CODE );

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT PK_PRD_ALLERGY PRIMARY KEY ( ALLERGY_CODE,PRD_CODE )
 USING INDEX PK_PRD_ALLERGY;

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT FK_ALLERGY_CODE FOREIGN KEY ( ALLERGY_CODE )
 REFERENCES TBL_ALLERGY (ALLERGY_CODE );


CREATE TABLE TBL_PRD_REVIEW
(
    REV_CODE    NUMBER(10) NOT NULL,
    CONSUMER_INFO    NUMBER(10) NOT NULL,
    REV_CONTENT    VARCHAR2(4000) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE,
    REPORT_DATE    DATE NOT NULL,
    REV_STATUS    VARCHAR2(100) NOT NULL,
    MODY_HISTORY    VARCHAR2(4000)
);

CREATE UNIQUE INDEX PK_PRD_REVIEW ON TBL_PRD_REVIEW
( REV_CODE );

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT PK_PRD_REVIEW PRIMARY KEY ( REV_CODE )
 USING INDEX PK_PRD_REVIEW;

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT FK_CONSUMER_INFO FOREIGN KEY ( CONSUMER_INFO )
 REFERENCES TBL_PAYMENT (PAY_CODE );


CREATE TABLE TBL_PRODUCT
(
    PRD_CODE    NUMBER(10) NOT NULL,
    PRD_NAME    VARCHAR2(20) NOT NULL,
    PRD_PRICE    NUMBER(10) NOT NULL,
    PRD_QUAN    NUMBER(10) NOT NULL,
    CATEGORY_CODE    NUMBER(3) NOT NULL,
    PRD_STATUS    VARCHAR2(10) NOT NULL,
    PRD_REGIST_DATE    DATE,
    PRD_MODI_DATE    DATE
);

CREATE UNIQUE INDEX PK_PRODUCT ON TBL_PRODUCT
( PRD_CODE );

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT PK_PRODUCT PRIMARY KEY ( PRD_CODE )
 USING INDEX PK_PRODUCT;

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT FK_PRD_CODE3 FOREIGN KEY ( CATEGORY_CODE )
 REFERENCES TBL_CATEGORY (CATEGORY_CODE );


CREATE TABLE TBL_QNA
(
    INQ_CODE    NUMBER(10) NOT NULL,
    INQ_TYPE    VARCHAR2(20) NOT NULL,
    OCCUR_DATE    DATE,
    INQ_TITLE    VARCHAR2(50) NOT NULL,
    INQ_CONTENT    VARCHAR2(4000) NOT NULL,
    ANS_CONTENT    VARCHAR2(4000),
    PRD_CODE    NUMBER(10),
    ADMIN_CODE    NUMBER(10),
    MEMBER_CODE    NUMBER(10) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    ANS_DATE    DATE,
    INQ_MODY_DATE    DATE,
    ANS_MODY_DATE    DATE
);

CREATE UNIQUE INDEX PK_QNA ON TBL_QNA
( INQ_CODE );

ALTER TABLE TBL_QNA
 ADD CONSTRAINT PK_QNA PRIMARY KEY ( INQ_CODE )
 USING INDEX PK_QNA;

ALTER TABLE TBL_QNA
 ADD CONSTRAINT FK_PRD_CODE2 FOREIGN KEY ( PRD_CODE )
 REFERENCES TBL_PRODUCT (PRD_CODE );

ALTER TABLE TBL_QNA
 ADD CONSTRAINT FK_MEMBER_NO2 FOREIGN KEY ( ADMIN_CODE )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );

ALTER TABLE TBL_QNA
 ADD CONSTRAINT FK_MEMBER_NO5 FOREIGN KEY ( MEMBER_CODE )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );


CREATE TABLE TBL_REFUND
(
    REF_CODE    VARCHAR2(20) NOT NULL,
    PAY_CODE    NUMBER(10) NOT NULL,
    REF_DATE    DATE NOT NULL,
    REF_AMOUNT    NUMBER(8) NOT NULL,
    REF_REASON    VARCHAR2(200) NOT NULL,
    REF_DETAIL_REASON    VARCHAR2(4000) NOT NULL,
    REF_COMPLETION_DATE    DATE NOT NULL
);

CREATE UNIQUE INDEX PK_REFUND ON TBL_REFUND
( REF_CODE );

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT PK_REFUND PRIMARY KEY ( REF_CODE )
 USING INDEX PK_REFUND;

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT FK_PAY_CODE FOREIGN KEY ( PAY_CODE )
 REFERENCES TBL_PAYMENT (PAY_CODE );


CREATE TABLE TBL_REPLY
(
    POST_CODE    NUMBER(10) NOT NULL,
    REPLY_CONTENT    VARCHAR2(4000) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE
);

CREATE UNIQUE INDEX PK_REPLY ON TBL_REPLY
( POST_CODE );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT PK_REPLY PRIMARY KEY ( POST_CODE )
 USING INDEX PK_REPLY;

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_POST_CODE2 FOREIGN KEY ( POST_CODE )
 REFERENCES TBL_NOTICE (POST_CODE );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_MEMBER_NO4 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );





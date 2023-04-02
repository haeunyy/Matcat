-- ������ DROP
DROP SEQUENCE SEQ_ORD;
DROP SEQUENCE SEQ_MEMBER_NO;
DROP SEQUENCE SEQ_AUTHORITY_CODE;
DROP SEQUENCE SEQ_REPLY_NO;
DROP SEQUENCE SEQ_POST_CODE;
DROP SEQUENCE SEQ_CATE_CODE;
DROP SEQUENCE SEQ_PRD_CODE;
DROP SEQUENCE SEQ_ALRG_CODE;
--
DROP SEQUENCE SEQ_PAY_CODE;
--
--------------------------------------------------------------------------------
ALTER TABLE TBL_AUTH_MEMBER
DROP CONSTRAINT FK_AUTH_CODE;

DROP TABLE TBL_AUTHORITY;

CREATE TABLE TBL_AUTHORITY
(
    AUTH_CODE    NUMBER(10) NOT NULL,
    AUTH_NAME    VARCHAR2(20) NOT NULL,
    AUTH_EXPLANATION    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_CODE IS '���� �ڵ�';

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_NAME IS '���� ��';

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_EXPLANATION IS '���� ����';

COMMENT ON TABLE TBL_AUTHORITY IS '����';

CREATE UNIQUE INDEX PK_AUTHORITY ON TBL_AUTHORITY
( AUTH_CODE );

ALTER TABLE TBL_AUTHORITY
 ADD CONSTRAINT PK_AUTHORITY PRIMARY KEY ( AUTH_CODE )
 USING INDEX PK_AUTHORITY;


ALTER TABLE TBL_CART
DROP CONSTRAINT FK_MEMBER_NO;

ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_MEMBER_NO4;

ALTER TABLE TBL_AUTH_MEMBER
DROP CONSTRAINT FK_MEMBER_NO3;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_MEMBER_NO2;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_MEMBER_NO5;

ALTER TABLE TBL_POST
DROP CONSTRAINT FK_POST_CODE;

ALTER TABLE TBL_ORDER
DROP CONSTRAINT FK_ORD_CODE3;

DROP TABLE TBL_MEMBER;

CREATE TABLE TBL_MEMBER
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    MEMBER_ID    VARCHAR2(50) NOT NULL,
    MEMBER_PWD    VARCHAR2(100) ,
    MEMBER_NAME    VARCHAR2(20) NOT NULL,
    MEMBER_EMAIL    VARCHAR2(100),
    MEMBER_GENDER    CHAR(5),
    MEMBER_PHONE    VARCHAR2(100),
    MEMBER_ADDRESS    VARCHAR2(200),
    MEMBER_LEAVE_YN    CHAR(10) NOT NULL,
    MEMBER_GRADE    VARCHAR2(20) NOT NULL,
    MEMBER_LEAVE_REASON    VARCHAR2(50)
);

COMMENT ON COLUMN TBL_MEMBER.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_ID IS '���̵�';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_PWD IS '��й�ȣ';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_NAME IS '�̸�';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_EMAIL IS '�̸���';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_GENDER IS '����';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_PHONE IS '��ȭ��ȣ';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_ADDRESS IS '�ּ�';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_LEAVE_YN IS '����';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_GRADE IS '����';

COMMENT ON COLUMN TBL_MEMBER.MEMBER_LEAVE_REASON IS 'Ż�� ����';

COMMENT ON TABLE TBL_MEMBER IS '����';

CREATE UNIQUE INDEX PK_MEMBER ON TBL_MEMBER
( MEMBER_NO );

ALTER TABLE TBL_MEMBER
 ADD CONSTRAINT PK_MEMBER PRIMARY KEY ( MEMBER_NO )
 USING INDEX PK_MEMBER;


ALTER TABLE TBL_PRD_ALLERGY
DROP CONSTRAINT FK_ALLERGY_CODE;

DROP TABLE TBL_ALLERGY;

CREATE TABLE TBL_ALLERGY
(
    ALLERGY_CODE    NUMBER(10) NOT NULL,
    ALLERGY_NAME    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_ALLERGY.ALLERGY_CODE IS '�˷��� �ڵ�';

COMMENT ON COLUMN TBL_ALLERGY.ALLERGY_NAME IS '�˷�����';

COMMENT ON TABLE TBL_ALLERGY IS '�˷����з�';

CREATE UNIQUE INDEX PK_ALLERGY ON TBL_ALLERGY
( ALLERGY_CODE );

ALTER TABLE TBL_ALLERGY
 ADD CONSTRAINT PK_ALLERGY PRIMARY KEY ( ALLERGY_CODE )
 USING INDEX PK_ALLERGY;


ALTER TABLE TBL_PRODUCT
DROP CONSTRAINT FK_PRD_CODE3;

DROP TABLE TBL_CATEGORY;

CREATE TABLE TBL_CATEGORY
(
    CATEGORY_CODE    VARCHAR2(10) NOT NULL,
    CATEGORY_NAME    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_CATEGORY.CATEGORY_CODE IS 'ī�װ��� �ڵ�';

COMMENT ON COLUMN TBL_CATEGORY.CATEGORY_NAME IS 'ī�װ��� �̸�';

COMMENT ON TABLE TBL_CATEGORY IS 'ī�װ���';

CREATE UNIQUE INDEX PK_CATEGORY ON TBL_CATEGORY
( CATEGORY_CODE );

ALTER TABLE TBL_CATEGORY
 ADD CONSTRAINT PK_CATEGORY PRIMARY KEY ( CATEGORY_CODE )
 USING INDEX PK_CATEGORY;


        -- ī�װ��� ������
INSERT 
    INTO TBL_CATEGORY
    (CATEGORY_CODE, CATEGORY_NAME)
    VALUES
    ( SEQ_CATE_CODE.NEXTVAL, '�ѽ�');
INSERT 
    INTO TBL_CATEGORY
    (CATEGORY_CODE, CATEGORY_NAME)
    VALUES
    ( SEQ_CATE_CODE.NEXTVAL, '���');
INSERT 
    INTO TBL_CATEGORY
    (CATEGORY_CODE, CATEGORY_NAME)
    VALUES
    ( SEQ_CATE_CODE.NEXTVAL, '�߽�');
INSERT 
    INTO TBL_CATEGORY
    (CATEGORY_CODE, CATEGORY_NAME)
    VALUES
    ( SEQ_CATE_CODE.NEXTVAL, '�Ͻ�');
INSERT 
    INTO TBL_CATEGORY
    (CATEGORY_CODE, CATEGORY_NAME)
    VALUES
    ( SEQ_CATE_CODE.NEXTVAL, '��Ÿ');
-- 

 


ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_POST_CODE2;

ALTER TABLE TBL_POST
DROP CONSTRAINT FK_POST_CODE;

DROP TABLE TBL_POST;

CREATE TABLE TBL_POST
(
    POST_CODE    VARCHAR2(20) NOT NULL,
    POST_TITLE    VARCHAR2(100) NOT NULL,
    POST_CONTENT    VARCHAR2(4000) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE
);

COMMENT ON COLUMN TBL_POST.POST_CODE IS '�Խñ� �ڵ�';

COMMENT ON COLUMN TBL_POST.POST_TITLE IS '�Խñ� ����';

COMMENT ON COLUMN TBL_POST.POST_CONTENT IS '�Խñ� ����';

COMMENT ON COLUMN TBL_POST.REPORT_DATE IS '�ۼ� �ð�';

COMMENT ON COLUMN TBL_POST.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_POST.MODY_DATE IS '���� �ð�';

COMMENT ON COLUMN TBL_POST.DELETE_DATE IS '���� �ð�';

COMMENT ON TABLE TBL_POST IS '��������';

CREATE UNIQUE INDEX PK_POST ON TBL_POST
( POST_CODE );

ALTER TABLE TBL_POST
 ADD CONSTRAINT PK_POST PRIMARY KEY ( POST_CODE )
 USING INDEX PK_POST;

ALTER TABLE TBL_POST
 ADD CONSTRAINT FK_POST_CODE FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_MEMBER ( MEMBER_NO );


ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_ORD_CODE2;

ALTER TABLE TBL_DELIVERY
DROP CONSTRAINT FK_ORD_CODE;

ALTER TABLE TBL_ORDER
DROP CONSTRAINT FK_ORD_CODE3;

ALTER TABLE TBL_ORDER
DROP CONSTRAINT FK_STATUS_CODE1;

DROP TABLE TBL_ORDER;

CREATE TABLE TBL_ORDER
(
    ORD_CODE    NUMBER(10) NOT NULL,
    ORD_DATE    DATE NOT NULL,
    ORD_PRICE    NUMBER(8) NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    ORD_TOTAL_COUNT    NUMBER(3) NOT NULL,
    STATUS_CODE    VARCHAR2(10) NOT NULL
);

COMMENT ON COLUMN TBL_ORDER.ORD_CODE IS '�ֹ��ڵ�';

COMMENT ON COLUMN TBL_ORDER.ORD_DATE IS '�ֹ� �ð�';

COMMENT ON COLUMN TBL_ORDER.ORD_PRICE IS '�� �ֹ� �ݾ�';

COMMENT ON COLUMN TBL_ORDER.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_ORDER.ORD_TOTAL_COUNT IS '�� �ֹ� ����';

COMMENT ON COLUMN TBL_ORDER.STATUS_CODE IS '�ֹ������ڵ�';

COMMENT ON TABLE TBL_ORDER IS '�ֹ�';

CREATE UNIQUE INDEX PK_ORDER ON TBL_ORDER
( ORD_CODE );

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT PK_ORDER PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_ORDER;

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT FK_ORD_CODE3 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_MEMBER (MEMBER_NO );

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT FK_STATUS_CODE1 FOREIGN KEY ( STATUS_CODE )
 REFERENCES TBL_ORDER_STATUS (STATUS_CODE );


ALTER TABLE TBL_REFUND
DROP CONSTRAINT FK_PAY_CODE;

ALTER TABLE TBL_PRD_REVIEW
DROP CONSTRAINT FK_CONSUMER_INFO;

DROP TABLE TBL_PAYMENT;

CREATE TABLE TBL_PAYMENT
(
    PAY_CODE    NUMBER(10) NOT NULL,
    PAY_DATE    DATE NOT NULL,
    PAY_AMOUNT    NUMBER(8) NOT NULL,
    PAY_STATUS    CHAR(1) NOT NULL,
    ORD_CODE    NUMBER(10) NOT NULL,
    PAY_TYPE    VARCHAR2(20) NOT NULL,
    KAKAO_ID    VARCHAR2(20),
    CARD_NAME    VARCHAR2(20),
    CARD_ACC_CODE    NUMBER(20)
);

COMMENT ON COLUMN TBL_PAYMENT.PAY_CODE IS '�����ڵ�';

COMMENT ON COLUMN TBL_PAYMENT.PAY_DATE IS '���� ����';

COMMENT ON COLUMN TBL_PAYMENT.PAY_AMOUNT IS '���� �ݾ�';

COMMENT ON COLUMN TBL_PAYMENT.PAY_STATUS IS 'ó�� ����';

COMMENT ON COLUMN TBL_PAYMENT.ORD_CODE IS '�ֹ��ڵ�';

COMMENT ON COLUMN TBL_PAYMENT.PAY_TYPE IS '��������';

COMMENT ON COLUMN TBL_PAYMENT.KAKAO_ID IS 'ID';

COMMENT ON COLUMN TBL_PAYMENT.CARD_NAME IS 'ī���';

COMMENT ON COLUMN TBL_PAYMENT.CARD_ACC_CODE IS '���ι�ȣ';

COMMENT ON TABLE TBL_PAYMENT IS '����';

CREATE UNIQUE INDEX PK_PAYMENT ON TBL_PAYMENT
( PAY_CODE );

ALTER TABLE TBL_PAYMENT
ADD CONSTRAINT PK_PAYMENT PRIMARY KEY ( PAY_CODE )
USING INDEX PK_PAYMENT;
--
ALTER TABLE TBL_PAYMENT
ADD CONSTRAINT FK_ORD_CODE5 FOREIGN KEY ( ORD_CODE )
REFERENCES TBL_ORDER ( ORD_CODE );
--
ALTER TABLE TBL_REFUND
DROP CONSTRAINT FK_PAY_CODE;

ALTER TABLE TBL_REFUND
DROP CONSTRAINT FK_STATUS_CODE2;

DROP TABLE TBL_REFUND;

CREATE TABLE TBL_REFUND
(
    REF_CODE    VARCHAR2(20) NOT NULL,
    PAY_CODE    NUMBER(10) NOT NULL,
    REF_DATE    DATE NOT NULL,
    REF_AMOUNT    NUMBER(8) NOT NULL,
    REF_REASON    VARCHAR2(200) NOT NULL,
    REF_DETAIL_REASON    VARCHAR2(4000) NOT NULL,
    REF_COMPLETION_DATE    DATE NOT NULL,
    STATUS_CODE    VARCHAR2(10) NOT NULL
);

COMMENT ON COLUMN TBL_REFUND.REF_CODE IS 'ȯ�� �ڵ�';

COMMENT ON COLUMN TBL_REFUND.PAY_CODE IS '�����ڵ�';

COMMENT ON COLUMN TBL_REFUND.REF_DATE IS '���� ����';

COMMENT ON COLUMN TBL_REFUND.REF_AMOUNT IS 'ȯ�� �ݾ�';

COMMENT ON COLUMN TBL_REFUND.REF_REASON IS 'ȯ�� ����';

COMMENT ON COLUMN TBL_REFUND.REF_DETAIL_REASON IS 'ȯ�� �� ����';

COMMENT ON COLUMN TBL_REFUND.REF_COMPLETION_DATE IS 'ó�� ����';

COMMENT ON COLUMN TBL_REFUND.STATUS_CODE IS '�ֹ������ڵ�';

COMMENT ON TABLE TBL_REFUND IS 'ȯ��';

CREATE UNIQUE INDEX PK_REFUND ON TBL_REFUND
( REF_CODE );

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT PK_REFUND PRIMARY KEY ( REF_CODE )
 USING INDEX PK_REFUND;

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT FK_PAY_CODE FOREIGN KEY ( PAY_CODE )
 REFERENCES TBL_PAYMENT (PAY_CODE );

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT FK_STATUS_CODE2 FOREIGN KEY ( STATUS_CODE )
 REFERENCES TBL_ORDER_STATUS (STATUS_CODE );


ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_PRD_CODE4;

ALTER TABLE TBL_CART
DROP CONSTRAINT FK_PRD_CODE;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_PRD_CODE2;

ALTER TABLE TBL_PRODUCT
DROP CONSTRAINT FK_PRD_CODE3;

DROP TABLE TBL_PRODUCT;

CREATE TABLE TBL_PRODUCT
(
    PRD_CODE    VARCHAR2(10) NOT NULL,
    PRD_NAME    VARCHAR2(1000) NOT NULL,
    PRD_PRICE    NUMBER(10) NOT NULL,
    PRD_QUAN    NUMBER(10) DEFAULT 1000 NOT NULL,
    CATEGORY_CODE    VARCHAR2(10) NOT NULL,
    PRD_STATUS    VARCHAR2(20) DEFAULT '�Ǹ���' NOT NULL,
    PRD_REGIST_DATE    DATE DEFAULT SYSDATE,
    PRD_MODI_DATE    DATE
);

COMMENT ON COLUMN TBL_PRODUCT.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON COLUMN TBL_PRODUCT.PRD_NAME IS '�̸�';

COMMENT ON COLUMN TBL_PRODUCT.PRD_PRICE IS '����';

COMMENT ON COLUMN TBL_PRODUCT.PRD_QUAN IS '���';

COMMENT ON COLUMN TBL_PRODUCT.CATEGORY_CODE IS 'ī�װ��� �ڵ�';

COMMENT ON COLUMN TBL_PRODUCT.PRD_STATUS IS '����';

COMMENT ON COLUMN TBL_PRODUCT.PRD_REGIST_DATE IS '�����';

COMMENT ON COLUMN TBL_PRODUCT.PRD_MODI_DATE IS '������';

COMMENT ON TABLE TBL_PRODUCT IS '��ǰ';

CREATE UNIQUE INDEX PK_PRODUCT ON TBL_PRODUCT
( PRD_CODE );

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT PK_PRODUCT PRIMARY KEY ( PRD_CODE )
 USING INDEX PK_PRODUCT;

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT FK_PRD_CODE3 FOREIGN KEY ( CATEGORY_CODE )
 REFERENCES TBL_CATEGORY (CATEGORY_CODE );


ALTER TABLE TBL_DELIVERY
DROP CONSTRAINT FK_ORD_CODE;

DROP TABLE TBL_DELIVERY;

CREATE TABLE TBL_DELIVERY
(
    ORD_CODE    NUMBER(10) NOT NULL,
    SHIP_ADDRESS    VARCHAR2(1000) NOT NULL,
    SHIP_CHARGE    NUMBER(10) NOT NULL,
    RECIPIENT_NAME    VARCHAR2(10) NOT NULL,
    RECIPIENT_PHONE    VARCHAR2(20) NOT NULL,
    INVOICE_NUM    NUMBER(10)
);

COMMENT ON COLUMN TBL_DELIVERY.ORD_CODE IS '�ֹ��ڵ�';

COMMENT ON COLUMN TBL_DELIVERY.SHIP_ADDRESS IS '����ּ�';

COMMENT ON COLUMN TBL_DELIVERY.SHIP_CHARGE IS '��ۺ�';

COMMENT ON COLUMN TBL_DELIVERY.RECIPIENT_NAME IS '������';

COMMENT ON COLUMN TBL_DELIVERY.RECIPIENT_PHONE IS '���ɿ���ó';

COMMENT ON COLUMN TBL_DELIVERY.INVOICE_NUM IS '�����ȣ';

COMMENT ON TABLE TBL_DELIVERY IS '���';

CREATE UNIQUE INDEX PK_DELIVERY ON TBL_DELIVERY
( ORD_CODE );

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT PK_DELIVERY PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_DELIVERY;

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT FK_ORD_CODE FOREIGN KEY ( ORD_CODE )
 REFERENCES TBL_ORDER (ORD_CODE );


ALTER TABLE TBL_PRD_REVIEW
DROP CONSTRAINT FK_CONSUMER_INFO;

DROP TABLE TBL_PRD_REVIEW;

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

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CODE IS '�����ڵ�';

COMMENT ON COLUMN TBL_PRD_REVIEW.CONSUMER_INFO IS '����������';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CONTENT IS '����';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_DATE IS '�����ð�';

COMMENT ON COLUMN TBL_PRD_REVIEW.DELETE_DATE IS '�����ð�';

COMMENT ON COLUMN TBL_PRD_REVIEW.REPORT_DATE IS '�ۼ��ð�';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_STATUS IS '����';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_HISTORY IS '��������';

COMMENT ON TABLE TBL_PRD_REVIEW IS '��ǰ ����';

CREATE UNIQUE INDEX PK_PRD_REVIEW ON TBL_PRD_REVIEW
( REV_CODE );

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT PK_PRD_REVIEW PRIMARY KEY ( REV_CODE )
 USING INDEX PK_PRD_REVIEW;

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT FK_CONSUMER_INFO FOREIGN KEY ( CONSUMER_INFO )
 REFERENCES TBL_PAYMENT (PAY_CODE );


ALTER TABLE TBL_AUTH_MEMBER
DROP CONSTRAINT FK_MEMBER_NO3;

ALTER TABLE TBL_AUTH_MEMBER
DROP CONSTRAINT FK_AUTH_CODE;

DROP TABLE TBL_AUTH_MEMBER;

CREATE TABLE TBL_AUTH_MEMBER
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    AUTH_CODE    NUMBER(10) NOT NULL
);

COMMENT ON COLUMN TBL_AUTH_MEMBER.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_AUTH_MEMBER.AUTH_CODE IS '���� �ڵ�';

COMMENT ON TABLE TBL_AUTH_MEMBER IS 'ȸ�� �� ����';

CREATE UNIQUE INDEX PK_AUTH_MEMBER ON TBL_AUTH_MEMBER
( MEMBER_NO,AUTH_CODE );

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT PK_AUTH_MEMBER PRIMARY KEY ( MEMBER_NO,AUTH_CODE )
 USING INDEX PK_AUTH_MEMBER;

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT FK_MEMBER_NO3 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_MEMBER (MEMBER_NO );

ALTER TABLE TBL_AUTH_MEMBER
 ADD CONSTRAINT FK_AUTH_CODE FOREIGN KEY ( AUTH_CODE )
 REFERENCES TBL_AUTHORITY (AUTH_CODE );


ALTER TABLE TBL_CART
DROP CONSTRAINT FK_MEMBER_NO;

ALTER TABLE TBL_CART
DROP CONSTRAINT FK_PRD_CODE;

DROP TABLE TBL_CART;

CREATE TABLE TBL_CART
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    PRD_CODE    VARCHAR2(10) NOT NULL,
    QUANTITY    NUMBER(10) NOT NULL
);

COMMENT ON COLUMN TBL_CART.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_CART.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON COLUMN TBL_CART.QUANTITY IS '����';

COMMENT ON TABLE TBL_CART IS '��ٱ���';

CREATE UNIQUE INDEX PK_CART ON TBL_CART
( MEMBER_NO,PRD_CODE );

ALTER TABLE TBL_CART
 ADD CONSTRAINT PK_CART PRIMARY KEY ( MEMBER_NO,PRD_CODE )
 USING INDEX PK_CART;

ALTER TABLE TBL_CART
 ADD CONSTRAINT FK_MEMBER_NO FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_MEMBER (MEMBER_NO );

ALTER TABLE TBL_CART
 ADD CONSTRAINT FK_PRD_CODE FOREIGN KEY ( PRD_CODE )
 REFERENCES TBL_PRODUCT (PRD_CODE );


ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_PRD_CODE2;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_MEMBER_NO2;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_MEMBER_NO5;

DROP TABLE TBL_QNA;

CREATE TABLE TBL_QNA
(
    INQ_CODE    NUMBER(10) NOT NULL,
    INQ_TYPE    VARCHAR2(20) NOT NULL,
    OCCUR_DATE    DATE,
    INQ_TITLE    VARCHAR2(50) NOT NULL,
    INQ_CONTENT    VARCHAR2(4000) NOT NULL,
    ANS_CONTENT    VARCHAR2(4000),
    PRD_CODE    VARCHAR2(10),
    ADMIN_CODE    NUMBER(10),
    MEMBER_CODE    NUMBER(10) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    ANS_DATE    DATE,
    INQ_MODY_DATE    DATE,
    ANS_MODY_DATE    DATE
);

COMMENT ON COLUMN TBL_QNA.INQ_CODE IS '���� �ڵ�';

COMMENT ON COLUMN TBL_QNA.INQ_TYPE IS '���� ����';

COMMENT ON COLUMN TBL_QNA.OCCUR_DATE IS '�߻� �ð�';

COMMENT ON COLUMN TBL_QNA.INQ_TITLE IS '���� ����';

COMMENT ON COLUMN TBL_QNA.INQ_CONTENT IS '���� ����';

COMMENT ON COLUMN TBL_QNA.ANS_CONTENT IS '�亯 ����';

COMMENT ON COLUMN TBL_QNA.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON COLUMN TBL_QNA.ADMIN_CODE IS '�亯��';

COMMENT ON COLUMN TBL_QNA.MEMBER_CODE IS '������';

COMMENT ON COLUMN TBL_QNA.REPORT_DATE IS '�����ۼ��ð�';

COMMENT ON COLUMN TBL_QNA.ANS_DATE IS '�亯�ۼ��ð�';

COMMENT ON COLUMN TBL_QNA.INQ_MODY_DATE IS '���Ǽ����ð�';

COMMENT ON COLUMN TBL_QNA.ANS_MODY_DATE IS '�亯�����ð�';

COMMENT ON TABLE TBL_QNA IS '1:1 ����';

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
 REFERENCES TBL_MEMBER (MEMBER_NO );

ALTER TABLE TBL_QNA
 ADD CONSTRAINT FK_MEMBER_NO5 FOREIGN KEY ( MEMBER_CODE )
 REFERENCES TBL_MEMBER (MEMBER_NO );


ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_POST_CODE2;

ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_MEMBER_NO4;

DROP TABLE TBL_REPLY;

CREATE TABLE TBL_REPLY
(
    POST_CODE    VARCHAR2(20) NOT NULL,
    REPLY_NO    VARCHAR2(20) NOT NULL,
    REPLY_CONTENT    VARCHAR2(4000) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE
);

COMMENT ON COLUMN TBL_REPLY.POST_CODE IS '�Խñ� �ڵ�';

COMMENT ON COLUMN TBL_REPLY.REPLY_NO IS '���ù�ȣ';

COMMENT ON COLUMN TBL_REPLY.REPLY_CONTENT IS '��� ����';

COMMENT ON COLUMN TBL_REPLY.REPORT_DATE IS '�ۼ� �Ͻ�';

COMMENT ON COLUMN TBL_REPLY.MEMBER_NO IS 'ȸ�� �ڵ�';

COMMENT ON COLUMN TBL_REPLY.MODY_DATE IS '�����ð�';

COMMENT ON COLUMN TBL_REPLY.DELETE_DATE IS '�����ð�';

COMMENT ON TABLE TBL_REPLY IS '���';

CREATE UNIQUE INDEX PK_REPLY ON TBL_REPLY
( POST_CODE,REPLY_NO );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT PK_REPLY PRIMARY KEY ( POST_CODE,REPLY_NO )
 USING INDEX PK_REPLY;

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_POST_CODE2 FOREIGN KEY ( POST_CODE )
 REFERENCES TBL_POST (POST_CODE );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_MEMBER_NO4 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_MEMBER (MEMBER_NO );


ALTER TABLE TBL_PRD_ALLERGY
DROP CONSTRAINT FK_ALLERGY_CODE;

DROP TABLE TBL_PRD_ALLERGY;

CREATE TABLE TBL_PRD_ALLERGY
(
    ALLERGY_CODE    NUMBER(10) NOT NULL,
    PRD_CODE    VARCHAR2(10) NOT NULL
);

COMMENT ON COLUMN TBL_PRD_ALLERGY.ALLERGY_CODE IS '�˷��� �ڵ�';

COMMENT ON COLUMN TBL_PRD_ALLERGY.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON TABLE TBL_PRD_ALLERGY IS '��ǰ�� �˷���';

CREATE UNIQUE INDEX PK_PRD_ALLERGY ON TBL_PRD_ALLERGY
( ALLERGY_CODE,PRD_CODE );

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT PK_PRD_ALLERGY PRIMARY KEY ( ALLERGY_CODE,PRD_CODE )
 USING INDEX PK_PRD_ALLERGY;

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT FK_ALLERGY_CODE FOREIGN KEY ( ALLERGY_CODE )
 REFERENCES TBL_ALLERGY (ALLERGY_CODE );


ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_ORD_CODE2;

ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_PRD_CODE4;

DROP TABLE TBL_ORDER_DETAIL;

CREATE TABLE TBL_ORDER_DETAIL
(
    ORD_COUNT    NUMBER(10) NOT NULL,
    PRD_CODE    VARCHAR2(10) NOT NULL,
    ORD_CODE    NUMBER(10) NOT NULL
);

COMMENT ON COLUMN TBL_ORDER_DETAIL.ORD_COUNT IS '�ֹ� ����';

COMMENT ON COLUMN TBL_ORDER_DETAIL.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON COLUMN TBL_ORDER_DETAIL.ORD_CODE IS '�ֹ��ڵ�';

COMMENT ON TABLE TBL_ORDER_DETAIL IS '�ֹ���';

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


ALTER TABLE TBL_ORDER
DROP CONSTRAINT FK_STATUS_CODE1;

ALTER TABLE TBL_REFUND
DROP CONSTRAINT FK_STATUS_CODE2;

DROP TABLE TBL_ORDER_STATUS;

CREATE TABLE TBL_ORDER_STATUS
(
    STATUS_CODE    VARCHAR2(10) NOT NULL,
    STATUS_NAME    VARCHAR2(200) NOT NULL
);

COMMENT ON COLUMN TBL_ORDER_STATUS.STATUS_CODE IS '�ֹ������ڵ�';

COMMENT ON COLUMN TBL_ORDER_STATUS.STATUS_NAME IS '�ֹ����¸�';

COMMENT ON TABLE TBL_ORDER_STATUS IS '�ֹ�����';

CREATE UNIQUE INDEX PK_ORDER_STATUS ON TBL_ORDER_STATUS
( STATUS_CODE );

ALTER TABLE TBL_ORDER_STATUS
 ADD CONSTRAINT PK_ORDER_STATUS PRIMARY KEY ( STATUS_CODE )
 USING INDEX PK_ORDER_STATUS;

 
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 1, '�ű��ֹ�');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 2, '����Ȯ��');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 3, '��۽���');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 4, '��ۿϷ�');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 5, '����Ȯ��');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 6, '�űԹ�ǰ');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 7, '��ǰȮ����');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 8, '��ǰ����');
INSERT INTO TBL_ORDER_STATUS
( STATUS_CODE, STATUS_NAME ) VALUES ( 9, '��ǰ�Ϸ�');

-------

DROP TABLE TBL_FILE_TYPE;

CREATE TABLE TBL_FILE_TYPE
(
    FILE_TYPE    VARCHAR2(20) NOT NULL,
    FILE_TYPE_NAME    VARCHAR2(20) NOT NULL,
    PRD_CODE    VARCHAR2(10),
    INQ_CODE    NUMBER(10),
    POST_CODE    VARCHAR2(20),
    REV_CODE    NUMBER(10)
);

COMMENT ON COLUMN TBL_FILE_TYPE.FILE_TYPE IS '���ϱ���';

COMMENT ON COLUMN TBL_FILE_TYPE.FILE_TYPE_NAME IS '���ϱ��и�';

COMMENT ON COLUMN TBL_FILE_TYPE.PRD_CODE IS '��ǰ �ڵ�';

COMMENT ON COLUMN TBL_FILE_TYPE.INQ_CODE IS '���� �ڵ�';

COMMENT ON COLUMN TBL_FILE_TYPE.POST_CODE IS '�Խñ� �ڵ�';

COMMENT ON COLUMN TBL_FILE_TYPE.REV_CODE IS '�����ڵ�';

COMMENT ON TABLE TBL_FILE_TYPE IS '÷�����ϱ���';

CREATE UNIQUE INDEX PK_FILE_TYPE ON TBL_FILE_TYPE
( FILE_TYPE );

ALTER TABLE TBL_FILE_TYPE
 ADD CONSTRAINT PK_FILE_TYPE PRIMARY KEY ( FILE_TYPE )
 USING INDEX PK_FILE_TYPE;


DROP TABLE TBL_FILES;

CREATE TABLE TBL_FILES
(
    FILE_IMG_CODE    NUMBER(20) NOT NULL,
    FILE_PATH    VARCHAR2(1000) NOT NULL,
    FILE_SIZE    NUMBER(20) NOT NULL,
    FILE_UPLOAD_DATE    DATE NOT NULL,
    FILE_ORIGIN_NAME    VARCHAR2(100) NOT NULL,
    FILE_SAVED_NAME    VARCHAR2(100) NOT NULL,
    FILE_TYPE    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_FILES.FILE_IMG_CODE IS '�̹����ڵ�';

COMMENT ON COLUMN TBL_FILES.FILE_PATH IS '���� ���';

COMMENT ON COLUMN TBL_FILES.FILE_SIZE IS 'ũ��';

COMMENT ON COLUMN TBL_FILES.FILE_UPLOAD_DATE IS '���ε� �ð�';

COMMENT ON COLUMN TBL_FILES.FILE_ORIGIN_NAME IS '�������ϸ�';

COMMENT ON COLUMN TBL_FILES.FILE_SAVED_NAME IS '����� ���ϸ�';

COMMENT ON COLUMN TBL_FILES.FILE_TYPE IS '���ϱ���';

COMMENT ON TABLE TBL_FILES IS '÷������';

CREATE UNIQUE INDEX PK_FILES ON TBL_FILES
( FILE_IMG_CODE );

ALTER TABLE TBL_FILES
 ADD CONSTRAINT PK_FILES PRIMARY KEY ( FILE_IMG_CODE )
 USING INDEX PK_FILES;


--------------------------------------------------------------------------------




-- ������ ����
CREATE SEQUENCE SEQ_ORD NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_MEMBER_NO NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_AUTHORITY_CODE NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_REPLY_NO NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_POST_CODE NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_CATE_CODE START WITH 10 INCREMENT BY 10 NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_PRD_CODE NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ALRG_CODE NOCYCLE NOCACHE;
--
CREATE SEQUENCE SEQ_PAY_CODE NOCYCLE NOCACHE;
--
--------------------------------------------------------------------------------

-- FK DISABLE

ALTER TABLE TBL_POST
 DISABLE CONSTRAINT FK_POST_CODE;

ALTER TABLE TBL_ORDER
 DISABLE CONSTRAINT FK_ORD_CODE3;

ALTER TABLE TBL_ORDER
 DISABLE CONSTRAINT FK_STATUS_CODE1;

ALTER TABLE TBL_REFUND
 DISABLE CONSTRAINT FK_PAY_CODE;

ALTER TABLE TBL_REFUND
 DISABLE CONSTRAINT FK_STATUS_CODE2;

ALTER TABLE TBL_PRODUCT
 DISABLE CONSTRAINT FK_PRD_CODE3;

ALTER TABLE TBL_DELIVERY
 DISABLE CONSTRAINT FK_ORD_CODE;

ALTER TABLE TBL_PRD_REVIEW
 DISABLE CONSTRAINT FK_CONSUMER_INFO;

ALTER TABLE TBL_AUTH_MEMBER
 DISABLE CONSTRAINT FK_MEMBER_NO3;

ALTER TABLE TBL_AUTH_MEMBER
 DISABLE CONSTRAINT FK_AUTH_CODE;

ALTER TABLE TBL_CART
 DISABLE CONSTRAINT FK_MEMBER_NO;

ALTER TABLE TBL_CART
 DISABLE CONSTRAINT FK_PRD_CODE;

ALTER TABLE TBL_QNA
 DISABLE CONSTRAINT FK_PRD_CODE2;

ALTER TABLE TBL_QNA
 DISABLE CONSTRAINT FK_MEMBER_NO2;

ALTER TABLE TBL_QNA
 DISABLE CONSTRAINT FK_MEMBER_NO5;

ALTER TABLE TBL_REPLY
 DISABLE CONSTRAINT FK_POST_CODE2;

ALTER TABLE TBL_REPLY
 DISABLE CONSTRAINT FK_MEMBER_NO4;

ALTER TABLE TBL_PRD_ALLERGY
 DISABLE CONSTRAINT FK_ALLERGY_CODE;

ALTER TABLE TBL_ORDER_DETAIL
 DISABLE CONSTRAINT FK_ORD_CODE2;

ALTER TABLE TBL_ORDER_DETAIL
 DISABLE CONSTRAINT FK_PRD_CODE4;


------------------------------------------------------------------------
-- TEST ������ ����
INSERT INTO TBL_AUTHORITY
(
  AUTH_CODE,
  AUTH_NAME,
  AUTH_EXPLANATION
)
VALUES
(
  SEQ_AUTHORITY_CODE.NEXTVAL,
  'ROLE_MEMBER',
  '�Ϲ�ȸ��'
);
INSERT INTO TBL_AUTHORITY
(
  AUTH_CODE,
  AUTH_NAME,
  AUTH_EXPLANATION
)
VALUES
(
  SEQ_AUTHORITY_CODE.NEXTVAL,
  'ROLE_ADMIN',
  '������'
  );

--------------------------------------------------------------------------------
-- FK ENABLE

ALTER TABLE TBL_POST
 ENABLE CONSTRAINT FK_POST_CODE;

ALTER TABLE TBL_ORDER
 ENABLE CONSTRAINT FK_ORD_CODE3;

ALTER TABLE TBL_ORDER
 ENABLE CONSTRAINT FK_STATUS_CODE1;

ALTER TABLE TBL_REFUND
 ENABLE CONSTRAINT FK_PAY_CODE;

ALTER TABLE TBL_REFUND
 ENABLE CONSTRAINT FK_STATUS_CODE2;

ALTER TABLE TBL_PRODUCT
 ENABLE CONSTRAINT FK_PRD_CODE3;

ALTER TABLE TBL_DELIVERY
 ENABLE CONSTRAINT FK_ORD_CODE;

ALTER TABLE TBL_PRD_REVIEW
 ENABLE CONSTRAINT FK_CONSUMER_INFO;

ALTER TABLE TBL_AUTH_MEMBER
 ENABLE CONSTRAINT FK_MEMBER_NO3;

ALTER TABLE TBL_AUTH_MEMBER
 ENABLE CONSTRAINT FK_AUTH_CODE;

ALTER TABLE TBL_CART
 ENABLE CONSTRAINT FK_MEMBER_NO;

ALTER TABLE TBL_CART
 ENABLE CONSTRAINT FK_PRD_CODE;

ALTER TABLE TBL_QNA
 ENABLE CONSTRAINT FK_PRD_CODE2;

ALTER TABLE TBL_QNA
 ENABLE CONSTRAINT FK_MEMBER_NO2;

ALTER TABLE TBL_QNA
 ENABLE CONSTRAINT FK_MEMBER_NO5;

ALTER TABLE TBL_REPLY
 ENABLE CONSTRAINT FK_POST_CODE2;

ALTER TABLE TBL_REPLY
 ENABLE CONSTRAINT FK_MEMBER_NO4;

ALTER TABLE TBL_PRD_ALLERGY
 ENABLE CONSTRAINT FK_ALLERGY_CODE;

ALTER TABLE TBL_ORDER_DETAIL
 ENABLE CONSTRAINT FK_ORD_CODE2;

ALTER TABLE TBL_ORDER_DETAIL
 ENABLE CONSTRAINT FK_PRD_CODE4;
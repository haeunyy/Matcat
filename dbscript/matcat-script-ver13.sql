----------------------------------------------------------------
-- auth_member 회원별권한 테이블
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

COMMENT ON COLUMN TBL_AUTH_MEMBER.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_AUTH_MEMBER.AUTH_CODE IS '권한 코드';

COMMENT ON TABLE TBL_AUTH_MEMBER IS '회원 별 권한';

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

-- 권한 테이블 -----------------------------------------------------

DROP TABLE TBL_AUTHORITY;

CREATE TABLE TBL_AUTHORITY
(
    AUTH_CODE    NUMBER(10) NOT NULL,
    AUTH_NAME    VARCHAR2(20) NOT NULL,
    AUTH_EXPLANATION    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_CODE IS '권한 코드';

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_NAME IS '권한 명';

COMMENT ON COLUMN TBL_AUTHORITY.AUTH_EXPLANATION IS '권한 설명';

COMMENT ON TABLE TBL_AUTHORITY IS '권한';

CREATE UNIQUE INDEX PK_AUTHORITY ON TBL_AUTHORITY
( AUTH_CODE );

ALTER TABLE TBL_AUTHORITY
 ADD CONSTRAINT PK_AUTHORITY PRIMARY KEY ( AUTH_CODE )
 USING INDEX PK_AUTHORITY;

-----------------------------------------------------
-- 계정 테이블 --------------------------------------------------
DROP TABLE TBL_ACCOUNT;

CREATE TABLE TBL_ACCOUNT
(
    MEMBER_NO    NUMBER(10) NOT NULL,
    MEMBER_ID    VARCHAR2(500) NOT NULL,
    MEMBER_PWD    VARCHAR2(100) NOT NULL,
    MEMBER_NAME    VARCHAR2(200) NOT NULL,
    MEMBER_EMAIL    VARCHAR2(500),
    MEMBER_GENDER    CHAR(5),
    MEMBER_PHONE    VARCHAR2(500),
    MEMBER_ADDRESS    VARCHAR2(500),
    MEMBER_LEAVE_YN    CHAR(10) NOT NULL,
    MEMBER_GRADE    VARCHAR2(20) NOT NULL,
    MEMBER_LEAVE_REASON    VARCHAR2(50)
);

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_ID IS '아이디';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_PWD IS '비밀번호';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_NAME IS '이름';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_EMAIL IS '이메일';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_GENDER IS '성별';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_PHONE IS '전화번호';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_ADDRESS IS '주소';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_LEAVE_YN IS '상태';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_GRADE IS '구분';

COMMENT ON COLUMN TBL_ACCOUNT.MEMBER_LEAVE_REASON IS '탈퇴 사유';

COMMENT ON TABLE TBL_ACCOUNT IS '계정';

CREATE UNIQUE INDEX PK_ACCOUNT ON TBL_ACCOUNT
( MEMBER_NO );


ALTER TABLE TBL_ACCOUNT
 ADD CONSTRAINT PK_ACCOUNT PRIMARY KEY ( MEMBER_NO )
 USING INDEX PK_ACCOUNT;


------------------------------------------------------------------
-- allergy 테이블

DROP TABLE TBL_ALLERGY;

CREATE TABLE TBL_ALLERGY
(
    ALLERGY_CODE    NUMBER(10) NOT NULL,
    ALLERGY_NAME    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_ALLERGY.ALLERGY_CODE IS '알러지 코드';

COMMENT ON COLUMN TBL_ALLERGY.ALLERGY_NAME IS '알러지명';

COMMENT ON TABLE TBL_ALLERGY IS '알러지분류';

CREATE UNIQUE INDEX PK_ALLERGY ON TBL_ALLERGY
( ALLERGY_CODE );

ALTER TABLE TBL_ALLERGY
 ADD CONSTRAINT PK_ALLERGY PRIMARY KEY ( ALLERGY_CODE )
 USING INDEX PK_ALLERGY;

----------------------------------------------------------------
-- category 테이블


DROP TABLE TBL_CATEGORY;

CREATE TABLE TBL_CATEGORY
(
    CATEGORY_CODE    VARCHAR2(10) NOT NULL,
    CATEGORY_NAME    VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN TBL_CATEGORY.CATEGORY_CODE IS '카테고리 코드';

COMMENT ON COLUMN TBL_CATEGORY.CATEGORY_NAME IS '카테고리 이름';

COMMENT ON TABLE TBL_CATEGORY IS '카테고리';

CREATE UNIQUE INDEX PK_CATEGORY ON TBL_CATEGORY
( CATEGORY_CODE );

ALTER TABLE TBL_CATEGORY
 ADD CONSTRAINT PK_CATEGORY PRIMARY KEY ( CATEGORY_CODE )
 USING INDEX PK_CATEGORY;

---------------------------------------------------------------
-- notice table

ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_REV_CODE;

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

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CODE IS '리뷰코드';

COMMENT ON COLUMN TBL_PRD_REVIEW.CONSUMER_INFO IS '구매자정보';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CONTENT IS '내용';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_DATE IS '수정시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.DELETE_DATE IS '삭제시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.REPORT_DATE IS '작성시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_STATUS IS '상태';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_HISTORY IS '수정내역';

COMMENT ON TABLE TBL_PRD_REVIEW IS '상품 리뷰';

CREATE UNIQUE INDEX PK_PRD_REVIEW ON TBL_PRD_REVIEW
( REV_CODE );

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT PK_PRD_REVIEW PRIMARY KEY ( REV_CODE )
 USING INDEX PK_PRD_REVIEW;

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT FK_CONSUMER_INFO FOREIGN KEY ( CONSUMER_INFO )
 REFERENCES TBL_PAYMENT (PAY_CODE );

-----------------------------------------------------------------
-- order 주문 테이블

ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_ORD_CODE2;

ALTER TABLE TBL_DELIVERY
DROP CONSTRAINT FK_ORD_CODE;

ALTER TABLE TBL_ORDER
DROP CONSTRAINT FK_ORD_CODE3;

DROP TABLE TBL_ORDER;

CREATE TABLE TBL_ORDER
(
    ORD_CODE    NUMBER(10) NOT NULL,
    ORD_DATE    DATE NOT NULL,
    ORD_PRICE    NUMBER(8) NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    ORD_TOTAL_COUNT    NUMBER(3) NOT NULL
);

COMMENT ON COLUMN TBL_ORDER.ORD_CODE IS '주문코드';

COMMENT ON COLUMN TBL_ORDER.ORD_DATE IS '주문 시간';

COMMENT ON COLUMN TBL_ORDER.ORD_PRICE IS '총 주문 금액';

COMMENT ON COLUMN TBL_ORDER.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_ORDER.ORD_TOTAL_COUNT IS '총 주문 수량';

COMMENT ON TABLE TBL_ORDER IS '주문';

CREATE UNIQUE INDEX PK_ORDER ON TBL_ORDER
( ORD_CODE );

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT PK_ORDER PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_ORDER;

ALTER TABLE TBL_ORDER
 ADD CONSTRAINT FK_ORD_CODE3 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );


----------------------------------------------------------------
-- product 상품 테이블
ALTER TABLE TBL_ORDER_DETAIL
DROP CONSTRAINT FK_PRD_CODE4;

ALTER TABLE TBL_CART
DROP CONSTRAINT FK_PRD_CODE;

ALTER TABLE TBL_QNA
DROP CONSTRAINT FK_PRD_CODE2;

ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_PRD_CODE5;

ALTER TABLE TBL_PRODUCT
DROP CONSTRAINT FK_PRD_CODE3;

DROP TABLE TBL_PRODUCT;

CREATE TABLE TBL_PRODUCT
(
    PRD_CODE    VARCHAR2(10) NOT NULL,
    PRD_NAME    VARCHAR2(20) NOT NULL,
    PRD_PRICE    NUMBER(10) NOT NULL,
    PRD_QUAN    NUMBER(10) DEFAULT 1000 NOT NULL,
    CATEGORY_CODE    VARCHAR2(10) NOT NULL,
    PRD_STATUS    VARCHAR2(20) DEFAULT '판매중' NOT NULL,
    PRD_REGIST_DATE    DATE DEFAULT SYSDATE,
    PRD_MODI_DATE    DATE
);

COMMENT ON COLUMN TBL_PRODUCT.PRD_CODE IS '상품 코드';

COMMENT ON COLUMN TBL_PRODUCT.PRD_NAME IS '이름';

COMMENT ON COLUMN TBL_PRODUCT.PRD_PRICE IS '가격';

COMMENT ON COLUMN TBL_PRODUCT.PRD_QUAN IS '재고';

COMMENT ON COLUMN TBL_PRODUCT.CATEGORY_CODE IS '카테고리 코드';

COMMENT ON COLUMN TBL_PRODUCT.PRD_STATUS IS '상태';

COMMENT ON COLUMN TBL_PRODUCT.PRD_REGIST_DATE IS '등록일';

COMMENT ON COLUMN TBL_PRODUCT.PRD_MODI_DATE IS '수정일';

COMMENT ON TABLE TBL_PRODUCT IS '상품';

CREATE UNIQUE INDEX PK_PRODUCT ON TBL_PRODUCT
( PRD_CODE );

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT PK_PRODUCT PRIMARY KEY ( PRD_CODE )
 USING INDEX PK_PRODUCT;

ALTER TABLE TBL_PRODUCT
 ADD CONSTRAINT FK_PRD_CODE3 FOREIGN KEY ( CATEGORY_CODE )
 REFERENCES TBL_CATEGORY (CATEGORY_CODE );

 REFERENCES TBL_AUTHORITY (AUTH_CODE );


-------------------------------------------------------------------
-- prd_allergy 테이블
ALTER TABLE TBL_PRD_ALLERGY
DROP CONSTRAINT FK_ALLERGY_CODE;

DROP TABLE TBL_PRD_ALLERGY;

CREATE TABLE TBL_PRD_ALLERGY
(
    ALLERGY_CODE    NUMBER(10) NOT NULL,
    PRD_CODE    VARCHAR2(10) NOT NULL
);

COMMENT ON COLUMN TBL_PRD_ALLERGY.ALLERGY_CODE IS '알러지 코드';

COMMENT ON COLUMN TBL_PRD_ALLERGY.PRD_CODE IS '상품 코드';

COMMENT ON TABLE TBL_PRD_ALLERGY IS '상품별 알러지';

CREATE UNIQUE INDEX PK_PRD_ALLERGY ON TBL_PRD_ALLERGY
( ALLERGY_CODE,PRD_CODE );

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT PK_PRD_ALLERGY PRIMARY KEY ( ALLERGY_CODE,PRD_CODE )
 USING INDEX PK_PRD_ALLERGY;

ALTER TABLE TBL_PRD_ALLERGY
 ADD CONSTRAINT FK_ALLERGY_CODE FOREIGN KEY ( ALLERGY_CODE )
 REFERENCES TBL_ALLERGY (ALLERGY_CODE );

---------------------------------------------------------------
-- payment 결제 테이블
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

COMMENT ON COLUMN TBL_PAYMENT.PAY_CODE IS '결제코드';

COMMENT ON COLUMN TBL_PAYMENT.PAY_DATE IS '결제 일자';

COMMENT ON COLUMN TBL_PAYMENT.PAY_AMOUNT IS '결제 금액';

COMMENT ON COLUMN TBL_PAYMENT.PAY_STATUS IS '처리 상태';

COMMENT ON COLUMN TBL_PAYMENT.ORD_CODE IS '주문코드';

COMMENT ON COLUMN TBL_PAYMENT.PAY_TYPE IS '결제수단';

COMMENT ON COLUMN TBL_PAYMENT.KAKAO_ID IS 'ID';

COMMENT ON COLUMN TBL_PAYMENT.CARD_NAME IS '카드사';

COMMENT ON COLUMN TBL_PAYMENT.CARD_ACC_CODE IS '승인번호';

COMMENT ON TABLE TBL_PAYMENT IS '결제';

CREATE UNIQUE INDEX PK_PAYMENT ON TBL_PAYMENT
( PAY_CODE );

ALTER TABLE TBL_PAYMENT
 ADD CONSTRAINT PK_PAYMENT PRIMARY KEY ( PAY_CODE )
 USING INDEX PK_PAYMENT;

----------------------------------------------------------------
-- refund 환불 테이블
ALTER TABLE TBL_REFUND
DROP CONSTRAINT FK_PAY_CODE;

DROP TABLE TBL_REFUND;

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

COMMENT ON COLUMN TBL_REFUND.REF_CODE IS '환불 코드';

COMMENT ON COLUMN TBL_REFUND.PAY_CODE IS '결제코드';

COMMENT ON COLUMN TBL_REFUND.REF_DATE IS '접수 일자';

COMMENT ON COLUMN TBL_REFUND.REF_AMOUNT IS '환불 금액';

COMMENT ON COLUMN TBL_REFUND.REF_REASON IS '환불 사유';

COMMENT ON COLUMN TBL_REFUND.REF_DETAIL_REASON IS '환불 상세 사유';

COMMENT ON COLUMN TBL_REFUND.REF_COMPLETION_DATE IS '처리 일자';

COMMENT ON TABLE TBL_REFUND IS '환불';

CREATE UNIQUE INDEX PK_REFUND ON TBL_REFUND
( REF_CODE );

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT PK_REFUND PRIMARY KEY ( REF_CODE )
 USING INDEX PK_REFUND;

ALTER TABLE TBL_REFUND
 ADD CONSTRAINT FK_PAY_CODE FOREIGN KEY ( PAY_CODE )
 REFERENCES TBL_PAYMENT (PAY_CODE );


----------------------------------------------------------------
-- delivery 배송 테이블
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

COMMENT ON COLUMN TBL_DELIVERY.ORD_CODE IS '주문코드';

COMMENT ON COLUMN TBL_DELIVERY.SHIP_ADDRESS IS '배송주소';

COMMENT ON COLUMN TBL_DELIVERY.SHIP_CHARGE IS '배송비';

COMMENT ON COLUMN TBL_DELIVERY.RECIPIENT_NAME IS '수령인';

COMMENT ON COLUMN TBL_DELIVERY.RECIPIENT_PHONE IS '수령연락처';

COMMENT ON COLUMN TBL_DELIVERY.INVOICE_NUM IS '송장번호';

COMMENT ON TABLE TBL_DELIVERY IS '배송';

CREATE UNIQUE INDEX PK_DELIVERY ON TBL_DELIVERY
( ORD_CODE );

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT PK_DELIVERY PRIMARY KEY ( ORD_CODE )
 USING INDEX PK_DELIVERY;

ALTER TABLE TBL_DELIVERY
 ADD CONSTRAINT FK_ORD_CODE FOREIGN KEY ( ORD_CODE )
 REFERENCES TBL_ORDER (ORD_CODE );


------------------------------------------------------------------
-- prd_review 상품 리뷰 테이블
ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_REV_CODE;

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

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CODE IS '리뷰코드';

COMMENT ON COLUMN TBL_PRD_REVIEW.CONSUMER_INFO IS '구매자정보';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_CONTENT IS '내용';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_DATE IS '수정시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.DELETE_DATE IS '삭제시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.REPORT_DATE IS '작성시간';

COMMENT ON COLUMN TBL_PRD_REVIEW.REV_STATUS IS '상태';

COMMENT ON COLUMN TBL_PRD_REVIEW.MODY_HISTORY IS '수정내역';

COMMENT ON TABLE TBL_PRD_REVIEW IS '상품 리뷰';

CREATE UNIQUE INDEX PK_PRD_REVIEW ON TBL_PRD_REVIEW
( REV_CODE );

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT PK_PRD_REVIEW PRIMARY KEY ( REV_CODE )
 USING INDEX PK_PRD_REVIEW;

ALTER TABLE TBL_PRD_REVIEW
 ADD CONSTRAINT FK_CONSUMER_INFO FOREIGN KEY ( CONSUMER_INFO )
 REFERENCES TBL_PAYMENT (PAY_CODE );


----------------------------------------------------------------
-- reply 댓글 테이블
ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_POST_CODE2;

ALTER TABLE TBL_REPLY
DROP CONSTRAINT FK_MEMBER_NO4;

DROP TABLE TBL_REPLY;

CREATE TABLE TBL_REPLY
(
    POST_CODE    VARCHAR2(200) NOT NULL,
    REPLY_NO    VARCHAR2(200) NOT NULL,
    REPLY_CONTENT    VARCHAR2(4000) NOT NULL,
    REPORT_DATE    DATE NOT NULL,
    MEMBER_NO    NUMBER(10) NOT NULL,
    MODY_DATE    DATE,
    DELETE_DATE    DATE
);

COMMENT ON COLUMN TBL_REPLY.POST_CODE IS '게시글 코드';

COMMENT ON COLUMN TBL_REPLY.REPLY_NO IS '리플번호';

COMMENT ON COLUMN TBL_REPLY.REPLY_CONTENT IS '댓글 내용';

COMMENT ON COLUMN TBL_REPLY.REPORT_DATE IS '작성 일시';

COMMENT ON COLUMN TBL_REPLY.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_REPLY.MODY_DATE IS '수정시간';

COMMENT ON COLUMN TBL_REPLY.DELETE_DATE IS '삭제시간';

COMMENT ON TABLE TBL_REPLY IS '댓글';

CREATE UNIQUE INDEX PK_REPLY ON TBL_REPLY
( POST_CODE,REPLY_NO );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT PK_REPLY PRIMARY KEY ( POST_CODE,REPLY_NO )
 USING INDEX PK_REPLY;

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_POST_CODE2 FOREIGN KEY ( POST_CODE )
 REFERENCES TBL_NOTICE (POST_CODE );

ALTER TABLE TBL_REPLY
 ADD CONSTRAINT FK_MEMBER_NO4 FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );


----------------------------------------------------------------
-- qna 1:1문의 테이블
ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_INQ_CODE2;

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

COMMENT ON COLUMN TBL_QNA.INQ_CODE IS '문의 코드';

COMMENT ON COLUMN TBL_QNA.INQ_TYPE IS '문의 유형';

COMMENT ON COLUMN TBL_QNA.OCCUR_DATE IS '발생 시간';

COMMENT ON COLUMN TBL_QNA.INQ_TITLE IS '문의 제목';

COMMENT ON COLUMN TBL_QNA.INQ_CONTENT IS '문의 내용';

COMMENT ON COLUMN TBL_QNA.ANS_CONTENT IS '답변 내용';

COMMENT ON COLUMN TBL_QNA.PRD_CODE IS '상품 코드';

COMMENT ON COLUMN TBL_QNA.ADMIN_CODE IS '답변자';

COMMENT ON COLUMN TBL_QNA.MEMBER_CODE IS '문의자';

COMMENT ON COLUMN TBL_QNA.REPORT_DATE IS '문의작성시간';

COMMENT ON COLUMN TBL_QNA.ANS_DATE IS '답변작성시간';

COMMENT ON COLUMN TBL_QNA.INQ_MODY_DATE IS '문의수정시간';

COMMENT ON COLUMN TBL_QNA.ANS_MODY_DATE IS '답변수정시간';

COMMENT ON TABLE TBL_QNA IS '1:1 문의';

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


---------------------------------------------------------------
-- cart 장바구니 테이블
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

COMMENT ON COLUMN TBL_CART.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_CART.PRD_CODE IS '상품 코드';

COMMENT ON COLUMN TBL_CART.QUANTITY IS '수량';

COMMENT ON TABLE TBL_CART IS '장바구니';

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


----------------------------------------------------------------
-- order_detail 주문상세 테이블
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

COMMENT ON COLUMN TBL_ORDER_DETAIL.ORD_COUNT IS '주문 수량';

COMMENT ON COLUMN TBL_ORDER_DETAIL.PRD_CODE IS '상품 코드';

COMMENT ON COLUMN TBL_ORDER_DETAIL.ORD_CODE IS '주문코드';

COMMENT ON TABLE TBL_ORDER_DETAIL IS '주문상세';

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


----------------------------------------------------------------
-- files 첨부파일 테이블
ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_PRD_CODE5;

ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_REV_CODE;

ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_POST_CODE3;

ALTER TABLE TBL_FILES
DROP CONSTRAINT FK_INQ_CODE2;

DROP TABLE TBL_FILES;

CREATE TABLE TBL_FILES
(
    FILE_IMG_CODE    NUMBER(20) NOT NULL,
    FILE_PATH    VARCHAR2(1000) NOT NULL,
    FILE_SIZE    NUMBER(20) NOT NULL,
    FILE_UPLOAD_DATE    DATE NOT NULL,
    FILE_ORIGIN_NAME    VARCHAR2(100) NOT NULL,
    FILE_SAVED_NAME    VARCHAR2(100) NOT NULL,
    FILE_TYPE    VARCHAR2(20) NOT NULL,
    PRD_CODE    VARCHAR2(20) NOT NULL,
    FILE_TITLE_IMG_YN    CHAR(1),
    POST_CODE    VARCHAR2(20),
    REV_CODE    NUMBER(10),
    INQ_CODE    NUMBER(20)
);

COMMENT ON COLUMN TBL_FILES.FILE_IMG_CODE IS '이미지코드';

COMMENT ON COLUMN TBL_FILES.FILE_PATH IS '파일 경로';

COMMENT ON COLUMN TBL_FILES.FILE_SIZE IS '크기';

COMMENT ON COLUMN TBL_FILES.FILE_UPLOAD_DATE IS '업로드 시간';

COMMENT ON COLUMN TBL_FILES.FILE_ORIGIN_NAME IS '원본파일명';

COMMENT ON COLUMN TBL_FILES.FILE_SAVED_NAME IS '저장된 파일명';

COMMENT ON COLUMN TBL_FILES.FILE_TYPE IS '구분';

COMMENT ON COLUMN TBL_FILES.PRD_CODE IS '상품 코드';

COMMENT ON COLUMN TBL_FILES.FILE_TITLE_IMG_YN IS '대표이미지';

COMMENT ON COLUMN TBL_FILES.POST_CODE IS '게시글 코드';

COMMENT ON COLUMN TBL_FILES.REV_CODE IS '리뷰코드';

COMMENT ON COLUMN TBL_FILES.INQ_CODE IS '문의 코드';

COMMENT ON TABLE TBL_FILES IS '첨부파일';

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

create sequence seq_member_no nocache nocycle;
create sequence seq_authority_code nocache nocycle;
create sequence seq_reply_no nocache nocycle;
create sequence seq_post_code nocache nocycle;

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
  '일반회원'
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
  '관리자'
  );
  
drop table tbl_notice;

ALTER TABLE TBL_NOTICE
DROP CONSTRAINT FK_POST_CODE;

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

COMMENT ON COLUMN TBL_NOTICE.POST_CODE IS '게시글 코드';

COMMENT ON COLUMN TBL_NOTICE.POST_TITLE IS '게시글 제목';

COMMENT ON COLUMN TBL_NOTICE.POST_CONTENT IS '게시글 내용';

COMMENT ON COLUMN TBL_NOTICE.REPORT_DATE IS '작성 시간';

COMMENT ON COLUMN TBL_NOTICE.MEMBER_NO IS '회원 코드';

COMMENT ON COLUMN TBL_NOTICE.MODY_DATE IS '수정 시간';

COMMENT ON COLUMN TBL_NOTICE.DELETE_DATE IS '삭제 시간';

COMMENT ON TABLE TBL_NOTICE IS '공지사항';

CREATE UNIQUE INDEX PK_NOTICE ON TBL_NOTICE
( POST_CODE );

ALTER TABLE TBL_NOTICE
 ADD CONSTRAINT PK_NOTICE PRIMARY KEY ( POST_CODE )
 USING INDEX PK_NOTICE;

ALTER TABLE TBL_NOTICE
 ADD CONSTRAINT FK_POST_CODE FOREIGN KEY ( MEMBER_NO )
 REFERENCES TBL_ACCOUNT (MEMBER_NO );
 
ALTER TABLE TBL_NOTICE
 DROP CONSTRAINT FK_POST_CODE;
 
INSERT INTO TBL_NOTICE
(
 POST_CODE,
 POST_TITLE,
 POST_CONTENT,
 REPORT_DATE,
 MEMBER_NO,
 MODY_DATE,
 DELETE_DATE
)
VALUES
(
  SEQ_POST_CODE.NEXTVAL,
  '이번엔 나오죠?',
  '나올때 됐죠?',
  SYSDATE,
  5,
  NULL,
  NULL
);
commit;


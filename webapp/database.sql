	create table USER_DETAILS(
user_id NUMBER(5) primary key,
username varchar2(10) not null,
firstname varchar2(25) not null,
lastname varchar2(25) not null,
password varchar2(10) not null,
emailId varchar2(50) not null,
birthdate date not null,
gender char(1) not null,
role varchar2(20) not null,
profile varchar2(50) not null,
status varchar2(25) default 'PENDING' not null,
is_online NUMBER(1) default 0 not null,
enabled NUMBER(1) default '1' not null
);

create sequence USER_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


create table blog(
blog_id NUMBER(5) primary key,
blog_name varchar2(50) not null,
status varchar2(25) default 'PENDING' not null,
description CLOB not null,
post_date Date default sysDate,
no_of_likes NUMBER(5),
no_of_comments NUMBER(5),
no_of_views NUMBER(5),
user_id NUMBER(5) not null,
user_name varchar2(10) not null
);

create sequence BLOG_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table BLOG_COMMENT (
BLOG_COMMENT_ID NUMBER(5) primary key,
BLOG_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
BLOG_COMMENT CLOB NOT NULL,
COMMENT_DATE DATE SYSDATE,
no_of_likes NUMBER(5)
)

CREATE sequence BLOG_COMMENT_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE FORUM_CATEGORY (
CATEGORY_ID NUMBER(5) PRIMARY KEY,
CATEGORY_NAME VARCHAR2(20) NOT NULL,
DESCRIPTION VARCHAR2(120),
status varchar2(25) default 'PENDING' not null
);

create sequence forum_category_seq
start with 1
increment by 1
NOCACHE
NOCYCLE

CREATE table FORUM (
FORUM_ID NUMBER(5) PRIMARY KEY,
FORUM_NAME VARCHAR2(20) NOT NULL,
FORUM_CONTAINT CLOB NOT NULL,
DATE_CREATED DATE DEFAULT SYSDATE,
NUMBER_OF_POSTS NUMBER(5),
NUMBER_OF_MEMBERS NUMBER(5),
NUMBER_OF_REQUESTS NUMBER(5),
STATUS VARCHAR2(25) default 'PENDING' not null,
CATEGORY_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null
);

CREATE SEQUENCE FORUM_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE FORUM_POSTS(
POST_ID NUMBER(5) PRIMARY KEY,
FORUM_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
POST_CONTAINT CLOB NOT NULL,
POST_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE FORUM_POST_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

create table forum_request (
request_id NUMBER(5) PRIMARY KEY,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
FORUM_ID NUMBER(5),
STATUS VARCHAR2(25) default 'PENDING' not null
);

CREATE SEQUENCE FORUM_REQ_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE JOBS_DETAIL (
JOB_ID NUMBER(5) PRIMARY KEY,
JOB_TITLE VARCHAR(50) NOT NULL,
PROFILE VARCHAR2(50) NOT NULL,
DESCRIPTION CLOB NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
QUALIFICATION VARCHAR2(120) NOT NULL,
POST_DATE DATE DEFAULT SYSDATE,
NO_OF_APPLICANTS NUMBER(5)
)

CREATE SEQUENCE JOB_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE JOB_APPLIED (
APPLIED_ID NUMBER(5) PRIMARY KEY,
JOB_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
APPLIED_DATE DATE DEFAULT SYSDATE,
STATUS VARCHAR2(25) default 'PENDING' not null
);

CREATE SEQUENCE JOB_APP_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE EVENTS (
EVENT_ID NUMBER(5) PRIMARY KEY,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
NAME VARCHAR2(25) not null,
VENUE VARCHAR2(100) NOT NULL,
DESCRIPTION CLOB NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
POST_DATE DATE DEFAULT SYSDATE NOT NULL
)

CREATE SEQUENCE EVENTS_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE EVENT_JOINED (
JOINED_ID NUMBER(5) PRIMARY KEY,
EVENT_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
JOINED_DATE DATE DEFAULT SYSDATE,
STATUS VARCHAR2(25) default 'PENDING' not null
);

create sequence EVENT_JOINED_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE
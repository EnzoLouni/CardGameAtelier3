CREATE DATABASE user_manager;
\c user_manager;
CREATE TABLE public.user (
  id       serial,
  login         varchar(50),
  password	    varchar(200),
  firstname	    varchar(20),
  surname	    varchar(20),
  email	        varchar(50),
  CONSTRAINT PK_USER PRIMARY KEY (id)
);
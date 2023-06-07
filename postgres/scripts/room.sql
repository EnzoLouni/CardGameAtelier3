CREATE DATABASE room_manager;
\c room_manager;

CREATE TABLE public.room (
  id       		serial,
  name			varchar(50),
  user_1        integer,
  user_2	    integer,
  bet 			float,
  CONSTRAINT PK_ROOM PRIMARY KEY (id)
);
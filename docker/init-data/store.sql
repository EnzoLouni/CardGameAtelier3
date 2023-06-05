CREATE DATABASE store_manager;
\c store_manager;

CREATE TABLE public.store_transaction (
    id            serial,
    user_id       integer,
    card_id	    integer,
    action	    varchar(30),
    timestamp	    date,
    CONSTRAINT PK_STORE_TRANSACTION PRIMARY KEY (id)
);
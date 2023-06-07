FROM postgres:latest

ENV POSTGRES_DB=postgres \
      POSTGRES_USER=card \
      POSTGRES_PASSWORD=card
	  
VOLUME /var/lib/postgresql/data

COPY scripts/card.sql /docker-entrypoint-initdb.d
COPY scripts/store.sql /docker-entrypoint-initdb.d
COPY scripts/user.sql /docker-entrypoint-initdb.d
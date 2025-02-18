# SaltyFish
A platform that connects people who provide services and people who require services

# Setup local Postgresql and PgAdmin from docker
docker network create postgres-network

## Start the PostgreSQL service:
docker run -d \
--name saltyFish_db_container \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=userpassword \
-e PGDATA=/data/postgres \
-v postgres:/data/postgres \
-p 5432:5432 \
--network postgres-network \
--restart unless-stopped \
postgres

## Start the pgAdmin service:
docker run -d \
--name pgadmin_container \
-e PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org \
-e PGADMIN_DEFAULT_PASSWORD=admin \
-e PGADMIN_CONFIG_SERVER_MODE=False \
-v pgadmin:/var/lib/pgadmin \
-p 5050:80 \
--network postgres-network \
--restart unless-stopped \
dpage/pgadmin4
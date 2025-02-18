# SaltyFish
A platform that connects people who provide services and people who require services

# Setup local Postgresql and PgAdmin from docker
docker network create local-network

docker run -d --name db --network local-network -e POSTGRES_PASSWORD=password postgres

docker run -d --name pgadmin --network local-network -e PGADMIN_DEFAULT_EMAIL=user@domain.com -e PGADMIN_DEFAULT_PASSWORD=password dpage/pgadmin4
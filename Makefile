run-tests:
	./gradlo

run:
	./gradlew run

bundle:
	./gradlew build

scan:
	./gradlew build --scan

install-python-depenencies:
	pip install faker

get-docker-dependencies:
	# docker database server
	docker pull postgres:16.2
	# docker database admin clienti
	docker pull dpage/pgadmin4:8.5
	
postgres-run:
	cd compose-postgres; docker-compose --env-file ./secrets.env up -d

show-dependencies:
	./gradlew :app:dependencies

run-tests:
	./gradlo

run:
	./gradlew run

bundle:
	./gradlew build

scan:
	./gradlew build --scan

install-depenencies:
	pip install faker

show-dependencies:
	./gradlew :app:dependencies

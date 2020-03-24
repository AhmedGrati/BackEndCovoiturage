sed -i 's/localhost/mariadb/g'   src/main/resources/application.properties
mvn package
sed -i 's/mariadb/localhost/g'   src/main/resources/application.properties
java -jar target/*.jar

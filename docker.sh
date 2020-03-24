sed -i 's/127.0.0.1/mariadb/g'   src/main/resources/application.properties
mvn  spring-boot:run

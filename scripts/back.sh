mvn package -DskipTests || (echo "problem building jar" && exit)
java -jar target/*jar
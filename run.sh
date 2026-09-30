mvn compile package -DskipTests
cd target/quarkus-app
java -jar -Xdebug -agentlib:jdwp="transport=dt_socket,server=y,suspend=n,address=5001" *-run*.jar
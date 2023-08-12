FROM eclipse-temurin:17-jdk-alpine

# Instalar MySQL
RUN apk add --no-cache mysql mysql-client

# Copiar el archivo .sql al contenedor
COPY database.sql /docker-entrypoint-initdb.d/

# Configurar el contenedor para iniciar MySQL
COPY my.cnf /etc/mysql/my.cnf
RUN chmod 644 /etc/mysql/my.cnf

# Exponer el puerto de MySQL
EXPOSE 3306

# Copiar el archivo JAR de la aplicación
COPY target/*.jar basic-task-list-0.0.1-SNAPSHOT.jar

# Comando para iniciar MySQL y luego la aplicación Spring Boot
ENTRYPOINT ["sh", "-c", "mysqld --user=root --datadir=/var/lib/mysql --init-file=/docker-entrypoint-initdb.d/database.sql & java -jar /basic-task-list-0.0.1-SNAPSHOT.jar"]
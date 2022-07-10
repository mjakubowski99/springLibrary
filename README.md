# springLibrary

Do uruchomienia projektu wystarczy mieć zainicjalizowaną bazę danych mysql.  
Dane dostępowe do bazy znajdują się w pliku application.properties

`
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/spring_library
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
`  
Testową bazę danych można odnaleźć pod plikiem spring_library.sql

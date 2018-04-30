Car Microservice

1. Execute the following sql queries to create the database in MySQL

create table cars (id bigint not null auto_increment, availability integer, daily_price decimal(19,2), model varchar(255), plate_number varchar(255), type varchar(255), primary key (id)) engine=InnoDB
alter table cars add constraint UK_i4q5rc6khlb31i0k3roukqfxf unique (plate_number)

2. Update the username and password in the properties files accordingly

3. Supply the profile(dev or qa) and run the service as Spring Boot application
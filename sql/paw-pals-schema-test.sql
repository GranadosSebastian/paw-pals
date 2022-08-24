drop database if exists paw_pals;
create database paw_pals;
use paw_pals;



create table `role` (
	role_id int primary key auto_increment,
    role_type varchar(25) not null,
    `description` varchar(100),
    user_access_level varchar(15) not null
);

create table `user` (
	user_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    address varchar(100) null,
    phone varchar(16) null,
    email varchar(50) not null,
    role_id int not null,
    constraint fk_user_role_id
		foreign key (role_id)
		references `role`(role_id)
);

create table login (
login_id int primary key auto_increment,
username varchar(50) not null,
password varchar(50) not null,
user_id int not null,
constraint fk_login_user_id
	foreign key (user_id)
    references `user`(user_id)
);


create table animal (
    animal_id int primary key auto_increment,
    animal_name varchar(25) not null,
    breed varchar(50) null,
    age int null,
    size char(10) not null,
    arrival_date date not null,
    friendliness_level varchar(10) null,
    is_available bit null,
    species varchar(20) not null,
    user_id int not null,
	constraint fk_animal_user_id
		foreign key (user_id)
		references `user`(user_id)
);

create table `schedule` (
schedule_id int primary key auto_increment,
`time` datetime not null,
user_id int not null,
animal_id int not null,
constraint fk_schedule_user_id
	foreign key (user_id)
	references `user`(user_id),
constraint fk_schedule_animal_id
	foreign key (animal_id)
	references animal(animal_id)
);

delimiter //
create procedure set_known_good_state()
begin
	delete from `schedule`;
    alter table `schedule` auto_increment = 1;
    delete from animal;
    alter table animal auto_increment = 1;
    delete from login;
    alter table login auto_increment = 1;
    delete from `user`;
    alter table `user` auto_increment = 1;
    delete from `role`;
    alter table `role` auto_increment = 1; 
    
	insert into `role` (role_type, user_access_level)
	values
		('staff', 'full_access'),
		('volunteer', 'partial_access'),
		('foster_parent', 'partial_access'),
		('adopter', 'limited_access');
    
	insert into `user` (first_name, last_name, address, phone, email, role_id)
	values
		('Dex', 'Fitch', '123 Nowhere Lane', '1234567890', 'fitchyfetch@gmail.com', 3),
		('Adel', 'Mozip', '908 Main Street', '0987654321', 'mozippeezagg@yahoo.com', 4),
		('Corbin', 'March', '456 Planters Way', '4564564566', 'marchymarch@hotmail.com', 4),
		('Angela', 'Mott', null, null, 'MottsandTotts@gmail.com', 1),
		('Sebastian', 'Granados', null, null, 'grandoltime@aol.com', 1),
		('Keri', 'Salanik', null, null, 'saladdressing@aol.com', 3);

	insert into animal (animal_name, breed, age, size, arrival_date, is_available, species, user_id)
	values
	('Bella', 'mixed', 8, 'medium', '2020-04-03', 1, 'dog', 6),
	('Zazu', 'tabby', 1, 'small', '2022-02-15', 1, 'cat', 1),
	('Missy', 'calico', 6, 'small', '2008-09-09', 0, 'cat', 3),
	('Bruno', 'chihuahua', 5,'small', '2010-10-23', 1, 'dog', 6),
	('Rocky', 'german shepard', 10,'large', '2016-12-24', 1, 'reptile', 1);

end //

delimiter ;		


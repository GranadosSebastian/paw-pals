drop database if exists paw_pals;
create database paw_pals;
use paw_pals;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled bit not null default(0),
	first_name varchar(50) not null,
    last_name varchar(50) not null,
    address varchar(100) null,
    phone varchar(16) null
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);


create table animal (
    animal_id int primary key auto_increment,
    animal_name varchar(25) not null,
    breed varchar(50) null,
    age int null,
    size char(10) not null,
    arrival_date date not null,
    friendliness_level varchar(250) null,
    is_available int not null,
    species varchar(20) not null,
    app_user_id int not null,
	constraint fk_animal_app_user_id
		foreign key (app_user_id)
		references app_user(app_user_id)
);

create table schedule (
schedule_id int primary key auto_increment,
`datetime` datetime not null,
animal_id int not null,
app_user_id int not null,
constraint fk_schedule_animal_id
	foreign key (animal_id)
    references animal(animal_id),
constraint fk_schedule_app_user_id
	foreign key (app_user_id)
    references app_user(app_user_id)


);


insert into app_role (`name`) values
    ('staff'),
    ('volunteer'),
    ('foster_parent'),
    ('adopter');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, disabled, first_name, last_name, address, phone)
    values
    ('fitchyfetch@gmail.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 'Dex', 'Fitch', '123 Nowhere Lane', '1234567890'),
    ('mozippeezagg@yahoo.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 'Adel', 'Mozip', '908 Main Street', '098765432'),
	('marchymarch@hotmail.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0,'Corbin', 'March', '456 Planters Way', '4564564566'),
    ('MottsandTotts@gmail.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 'Angela', 'Mott', null, null ),
    ('grandoltime@aol.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 'Sebastian', 'Granados', null, null ),
    ('saladdressing@aol.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0,'Keri', 'Salanik', null, null );

insert into app_user_role
    values
    (1, 3),
    (2, 1),
    (3, 4),
    (4, 2),
    (5, 4),
    (6, 3);

insert into animal (animal_name, breed, age, size, arrival_date, friendliness_level, is_available, species, app_user_id)
values
('Bella', 'mixed', 8, 'medium', '2020-04-03', 'is basically a cat', 1, 'dog', 2),
('Zazu', 'tabby', 1, 'small', '2022-02-15', 'friendly, loves people but not cars', 1, 'cat', 1),
('Missy', 'calico', 6, 'small', '2008-09-09','good with kids', 0, 'cat', 5),
('Bruno', 'chihuahua', 5,'small', '2010-10-23', 'tempermental but loving', 1, 'dog', 4),
('Rocky', 'german shepard', 10,'large', '2016-12-24', 'very active, loves other dogs', 1, 'reptile', 1),
('Hilde', 'germain shepard', 0, 'large', '2022-08-20', 'loves to bite', 1, 'dog', 4),
('Triscuit', 'chihuahua', 4, 'small', '2022-08-15', 'loves to play with people', 1, 'dog', 6);


insert into `schedule` (`datetime`, app_user_id, animal_id)
values
			('2023-06-06 10:00:00', 3, 4),
            ('2023-06-06 11:00:00', 6, 1),
            ('2023-06-07 11:30:00', 3, 2);





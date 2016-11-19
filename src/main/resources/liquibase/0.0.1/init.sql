--liquibase formatted sql

--changeset hanuska1:create-tables
drop table if exists material;
drop table if exists place_type;
drop table if exists app_user;
drop table if exists user_character;
drop table if exists inventory;
drop table if exists place;
drop table if exists user_activity;
drop table if exists role;

create table MATERIAL (
	ID_MATERIAL bigint(19) not null auto_increment,
    NAME varchar(255),
    primary key (ID_MATERIAL)
);

create table PLACE_TYPE(
	ID_PLACE_TYPE bigint(19) not null auto_increment,
	PLACE_TYPE varchar (255),
	IMG_URL varchar (255),
	ACTIVITY varchar (255),
	primary key (ID_PLACE_TYPE)
);

create table ROLE(
	ID_ROLE bigint(19) not null auto_increment,
	NAME varchar (50),
	primary key (ID_ROLE)
);

create table USER_CHARACTER(
	ID_CHARACTER bigint(19) not null auto_increment,
	CHARISMA int,
	POWER int,
	XP int,
	primary key (ID_CHARACTER)
);

create table APP_USER(
	ID_APP_USER bigint(19) not null auto_increment,
	USERNAME varchar(255),
	STAGNAME varchar(255),
	PASSWORD varchar(255),
	ID_CHARACTER bigint(19),
	primary key (ID_USER),
	constraint fk_character foreign key (ID_CHARACTER) references USER_CHARACTER(ID_CHARACTER)
);

create table INVENTORY(
	ID_INVENTORY bigint(19) not null auto_increment,
	ID_APP_USER bigint(19),
	ID_MATERIAL bigint(19),
	AMOUNT numeric(19,2),
	primary key (ID_INVENTORY),
	constraint fk_inventory_user foreign key fk_inventory_user(ID_APP_USER) references APP_USER(ID_APP_USER),
	constraint fk_inventory_material foreign key fk_inventory_material(ID_MATERIAL) references material (ID_MATERIAL)
);

create table PLACE(
	ID_PLACE bigint(19) not null auto_increment,
	CODE varchar(255),
	NAME varchar(50),
	DESCRIPTION varchar(255),
	FLOOR int,
	X_COORD int,
	Y_COORD int,
	ID_PLACE_TYPE bigint(19),
	ID_MATERIAL,
	primary key (ID_PLACE),
	constraint fk_place_place_type foreign key fk_place_place_type(ID_PLACE_TYPE) references PLACE_TYPE(ID_PLACE_TYPE),
	constraint fk_place_material foreign key fk_place_material (ID_MATERIAL) references MATERIAL(ID_MATERIAL)
);

create table USER_ACTIVITY(
	ID_USER_ACTIVITY BIGINT(19) NOT NULL AUTO_INCREMENT,
  	ID_APP_USER BIGINT(19),
  	ACTIVITY varchar(255),
 	ID_MATERIAL bigint(19),
  	MATERIAL_AMOUNT numeric(19,2),
  	START_TIME DATETIME,
  	PRIMARY KEY (ID_USER_ACTIVITY),
  	constraint fk_user_user_activity foreign key fk_user_user_activity (ID_APP_USER) references APP_USER (ID_APP_USER),
  	constraint fk_activity_material foreign key fk_activity_material (ID_MATERIAL) references MATERIAL (ID_MATERIAL)
);

create table USER_ROLE(
	ID_USER_ROLE bigint(19) not null auto_increment,
	ID_APP_USER bigint (19),
	ID_ROLE bigint (19),
	primary key (ID_USER_ROLE),
	constraint fk_user_user_role foreign key fk_user_user_role (ID_APP_USER) references APP_USER(ID_APP_USER),
	constraint fk_role_user_role foreign key fk_role_user_role (ID_ROLE) references ROLE(ID_ROLE)
);

--changeset hanuska1:insert-data
insert into material (name) values
	("GOLD"), 
    ("FOOD"), 
    ("WOOD"), 
    ("STONE"), 
    ("WORKER");
    
insert into place_type (place_type, img_url, activity) values
	("Naleziště", null, "MINE"),
	("Obchod", "money_icon.png", null),
	("Tržiště", null, null);
	
insert into role (name) values
	("ROLE_USER"),
	("ROLE_ADMIN");

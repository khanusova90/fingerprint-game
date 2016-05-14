--liquibase formatted sql

--changeset hanuska1:create-1
drop table if exists app_user;
drop table if exists inventory;
drop table if exists place;
drop table if exists resources;

create table APP_USER(
	ID_USER bigint(19) not null auto_increment,
	USERNAME varchar(255) not null,
	STAGNAME varchar(255),
	PASSWORD varchar(255) not null,
	primary key (ID_USER)
);
create table INVENTORY(
	ID_INVENTORY bigint(19) not null auto_increment,
	ID_USER bigint(19) not null,
	MATERIAL varchar(255) not null,
	AMOUNT numeric(19,2) not null default 0,
	primary key (ID_INVENTORY),
	constraint fk_inventory_user foreign key fk_inventory_user(ID_USER) references APP_USER(ID_USER)
);
create table PLACE(
	ID_PLACE bigint(19) not null auto_increment,
	CODE varchar(255) not null,
	DESCRIPTION varchar(255) not null,
	primary key (ID_PLACE)
);
create table RESOURCES(
	ID_RESOURCES bigint(19) not null auto_increment,
	ID_PLACE bigint(19),
	MATERIAL varchar(255) not null,
	primary key(ID_RESOURCES),
	constraint fk_resources_place foreign key fk_resources_place(ID_PLACE) references PLACE(ID_PLACE)
);
--rollback drop table if exists app_user; drop table if exists inventory; drop table if exists place; drop table if exists resources;

--changeset hanuska1:create-2
drop table if exists user_role;

create table USER_ROLE(
	ID_USER_ROLE bigint(19) not null auto_increment,
	ROLE varchar(255) not null,
	ID_USER bigint(19) not null,
	primary key(ID_USER_ROLE),
	constraint fk_user_roles foreign key fk_user_roles(ID_USER) references APP_USER(ID_USER)
);

--changeset hanuska1:create-3
alter table RESOURCES add X_COORD int not null;
alter table RESOURCES add Y_COORD int not null;

--changeset hanuska1:create-4
alter table PLACE add name varchar(50) not null;

--changeset hanuska1:create-5
create table C_PLACE_TYPE(
	ID_PLACE_TYPE bigint(19) not null auto_increment,
	PLACE_TYPE varchar(255) not null,
	DESCRIPTION varchar(255) not null,
	IMG_URL varchar(255),
	primary key(ID_PLACE_TYPE)
)

create table PLACE_PLACE_TYPE(
	ID_PLACE_PLACE_TYPE bigint(19) not null auto_increment,
	ID_PLACE bigint(19) not null,
	ID_PLACE_TYPE bigint(19) not null,
	primary key(ID_PLACE_PLACE_TYPE),
	constraint fk_place foreign key fk_place(ID_PLACE) references PLACE(ID_PLACE),
	constraint fk_place_type foreign key fk_place_type(ID_PLACE_TYPE) references C_PLACE_TYPE(ID_PLACE_TYPE)
	
create table C_ACTIVITY (
	ID_ACTIVITY bigint(19) not null auto_increment,
	NAME varchar(255) not null,
	primary key (ID_ACTIVITY)
);

create table PLACE_TYPE_ACTIVITY (
	ID_PLACE_TYPE_ACTIVITY bigint(19) not null auto_increment,
	ID_PLACE_TYPE bigint(19) not null,
	ID_ACTIVITY bigint(19) not null,
	primary key (ID_PLACE_TYPE_ACTIVITY),
	constraint fk_type foreign key fk_type(ID_PLACE_TYPE) references C_PLACE_TYPE(ID_PLACE_TYPE),
	constraint fk_activity foreign key fk_activity(ID_ACTIVITY) references C_ACTIVITY(ID_ACTIVITY)
);

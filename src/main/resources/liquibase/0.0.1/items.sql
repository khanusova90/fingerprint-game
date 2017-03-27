--liquibase formatted sql

--changeset hanuska1:items-1
CREATE TABLE ITEM_TYPE(
	ID_ITEM_TYPE BIGINT(19) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255),
	ACTIVITY VARCHAR(255),
	ID_MATERIAL BIGINT(19),
	PRIMARY KEY (ID_ITEM_TYPE),
	CONSTRAINT FK_ITEMTYPE_MATERIAL FOREIGN KEY (ID_MATERIAL) REFERENCES MATERIAL(ID_MATERIAL)
);
CREATE TABLE ITEM(
	ID_ITEM BIGINT(19) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255),
	ID_ITEM_TYPE BIGINT(19),
	LEVEL INT,
	IMG_URL VARCHAR(255),
	PRIMARY KEY (ID_ITEM),
	CONSTRAINT FK_ITEM_ITEMTYPE FOREIGN KEY (ID_ITEM_TYPE) REFERENCES ITEM_TYPE(ID_ITEM_TYPE)
);
CREATE TABLE USER_ITEM(
	ID_USER_ITEM BIGINT(19) NOT NULL AUTO_INCREMENT,
	ID_APP_USER BIGINT(19),
	ID_ITEM BIGINT(19),
	PRIMARY KEY (ID_USER_ITEM),
	CONSTRAINT FK_USERITEM_USER FOREIGN KEY (ID_APP_USER) REFERENCES APP_USER(ID_APP_USER),
	CONSTRAINT FK_USERITEM_ITEM FOREIGN KEY (ID_ITEM) REFERENCES ITEM(ID_ITEM)
);

--changeset hanuska1:items-2
insert into item_type (name, activity, id_material) values
	("AXE", "MINE", 3),
	("PICKAXE", "MINE", 4),
	("LADDER", "MINE", 2);

insert into item(name, id_item_type, level, img_url) values
	("Sekera", 1, 1, "axe.jpg"),
	("Bronzová sekera", 1, 2, "bronze_axe.jpg"),
	("Stříbrná sekera", 1, 3, "siver_axe.jpg"),
	("Zlatá sekyra", 1, 4, "gold_axe.jpg"),
	("Krumpáč", 2, 1, "pickaxe.jpg"),
	("Bronzový krumpáč", 2, 2, "bronze_pickaxe.jpg"),
	("Stříbrný krumpáč", 2, 3, "silver_pickaxe.jpg"),
	("Zlatý krumpáč", 2, 4, "gold_pickaxe.jpg"),
	("Žebřík", 3, 1, "ladder.jpg"),
	("Bronzový žebřík", 3, 2, "bronze_ladder.jpg"),
	("Stříbrný žebřík", 3, 3, "silver_ladder.jpg"),
	("Zlatý žebřík", 3, 4, "gold_ladder.jpg");

--changeset hanuska1:items-3
alter table item_type add PRICE bigint(19) default 100;

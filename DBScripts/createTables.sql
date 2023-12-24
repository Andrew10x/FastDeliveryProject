CREATE TABLE ORDERS(
    Id int PRIMARY KEY NOT NULL,
	UserId int NOT NULL,
	DirectionId int NOT NULL,
	DateOrd timestamp default current_timestamp,
	StatusId int NOT NULL,
	WeightOrd decimal(8, 3) NOT NULL,
	LengthOrd int NOT NULL,
	WidthOrd int NOT NULL,
	HeightOrd int NOT NULL,
	TypeId int NOT NULL,
	SumInsured decimal(9, 2) NOT NULL,
	Adress varchar(150) NOT NULL,
	DeliveryCost decimal(9, 2) NOT NULL,
	RecipientId int NOT NULL
); 

CREATE TABLE USERS(
	UserId serial primary key NOT NULL,
	UserName varchar(50) NOT NULL,
	Phone varchar(16) NOT NULL,
	Email varchar(50) NOT NULL,
	PasswordUsr varchar(30) NOT NULL,
	RoleId int NOT NULL
);

CREATE TABLE ROLES(
	RoleId serial primary key NOT NULL,
	RoleName varchar(30) NOT NULL
);

CREATE TABLE DIRECTIONS(
	DirectionId serial primary key NOT NULL,
	CityFromId int NOT NULL,
	CityToId int NOT NULL,
	Distance int NOT NULL 
);

CREATE TABLE CITIES(
	CityId serial primary key NOT NULL,
	CityName varchar(30) NOT NULL
);

CREATE TABLE STATUSES(
	StatusId serial primary key NOT NULL,
	StatusName varchar(30) NOT NULL
);

CREATE TABLE CARGOTYPES(
	TypeId serial primary key NOT NULL,
	TypeName varchar(30) NOT NULL
);

CREATE TABLE CONSTANTS(
	Id serial primary key NOT NULL,
	Name varchar(30) NOT NULL,
	MinValue decimal(9, 2) NOT NULL,
	MinPrice decimal(9, 2) NOT NULL,
	Coef decimal(9, 2) NOT NULL
);

CREATE TABLE RECIPIENTS (
	RecipientId serial primary key NOT NULL,
	RecitientName varchar(50) NOT NULL,
	RecipientPhone varchar(16) NOT NULL
);



ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_USERS 
    FOREIGN KEY(UserId)
        REFERENCES USERS (UserId);
		
ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_DIRECTIONS 
    FOREIGN KEY(DirectionId)
        REFERENCES DIRECTIONS (DirectionId);

ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_STATUSES 
    FOREIGN KEY(StatusId)
        REFERENCES STATUSES (StatusId);
		
ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_CARGOTYPES 
    FOREIGN KEY(TypeId)
        REFERENCES CARGOTYPES (TypeId);
		
ALTER TABLE DIRECTIONS ADD CONSTRAINT FK_DIRECTIONS_CityFrom 
    FOREIGN KEY(CityFromId)
        REFERENCES CITIES (CityId);
		
ALTER TABLE DIRECTIONS ADD CONSTRAINT FK_DIRECTIONS_CityTo 
    FOREIGN KEY(CityToId)
        REFERENCES CITIES (CityId);
		
ALTER TABLE USERS ADD CONSTRAINT FK_USERS_ROLES 
    FOREIGN KEY(RoleId)
        REFERENCES ROLES (RoleId);
		
ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_RECIPIENTS
	FOREIGN KEY(RecipientId)
		REFERENCES RECIPIENTS (RecipientId);
		


create table WAREHOUSES
(
    ID     integer primary key,
    UUID   varchar(36) not null,
    CLIENT varchar(50) not null,
    FAMILY varchar(3) not null,
    SIZE   numeric(2) not null
);

create table RACKS
(
    ID           integer primary key,
    WAREHOUSE_ID integer not null,
    UUID         varchar(36) not null,
    TYPE         varchar(1) not null,
    foreign key (WAREHOUSE_ID) references WAREHOUSES (ID)
);
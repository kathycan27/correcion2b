use tienda;
create table product(
codigo int, 
tipo varchar(20),
nombre varchar(20), 
precio dec(4,2), 
unidad int,
details varchar(20)
);
select* from product;

use tienda;
create table tipot
(
indice int,
tipo varchar(15)
);
insert into tipot(indice, tipo)values
(1, "fruta"),
(2, "carne"),
(3, "bebidas"),
(4, "lacteos"),
(5, "vegetales"),
(6, "snacks");
select * from tipos;

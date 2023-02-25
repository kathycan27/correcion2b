use tienda;
create table tipos
(
indice int,
tipo varchar(20)
);
insert into tipos(indice, tipo)values
(1, "fruta"),
(2, "carne"),
(3, "bebidas"),
(4, "lacteos"),
(5, "vegetales"),
(6, "snacks");
select * from tipos;

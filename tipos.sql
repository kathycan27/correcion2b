use tienda;
create table tipos1
(
indice int auto_increment primary key,
tipo varchar(20)
);
insert into tipos1( tipo)values
( "fruta"),
("carne"),
("bebidas"),
("lacteos"),
("vegetales"),
("snacks");
select * from tipos1;

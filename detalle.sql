use tienda;
create table detalles
( indice int auto_increment primary key,
detalle varchar(20)
);
insert into detalles(detalle)values
("unidad"),
("docena"),
("paquete"),
("caja");
 select* from detalles;



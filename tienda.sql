use tienda;
create table product1(
codigo int primary key, 
tipo varchar(20),
nombre varchar(20), 
precio dec(4,2), 
unidad int,
details varchar(20)
);
select* from product1;
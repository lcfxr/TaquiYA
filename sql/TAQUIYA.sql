drop database if exists taquiya;
create database taquiya;
use taquiya;

-- CREACION DE TABLAS
create table usuario(
	dni varchar(9) primary key,          
	nombre varchar (50),
	apellido1 varchar (50),
	apellido2 varchar (50),
	direccion varchar (100),
	correo varchar (50),
	telefono int
);

create table grupo(
	id_grupo varchar (9) primary key,
	nombre varchar (50),
	genero varchar (50),                
	telefono int,
	correo varchar (50)
);

create table lugar(
	id_lugar varchar (9) primary key,
	nombre varchar (50),
	tipo varchar (50),
	direccion varchar (100),
	ciudad varchar (100),
	aforo int,
	telefono int,
	correo varchar (50)
);

create table concierto(
	id_concierto varchar (9) primary key,
	fecha date,
	hora time,                           
	id_lugar varchar (9),                
	foreign key (id_lugar) references lugar(id_lugar)  
);

create table toca(
	id_grupo varchar(9),
	id_concierto varchar (9),
	primary key (id_grupo, id_concierto),              
	foreign key (id_grupo) references grupo(id_grupo),
	foreign key (id_concierto) references concierto(id_concierto)
);

create table tipo_entrada(
	id_tipo varchar(9) primary key,
	nombre varchar (50),
	precio double,
	cantidad_total int,                 
	id_concierto varchar (9),
	foreign key (id_concierto) references concierto(id_concierto)
);

create table entrada(
	id_entrada varchar(9) primary key,
	estado enum('V','R') not null default 'V',                 
	id_tipo varchar(9),
	dni_comprador varchar(9),        
	fecha_compra date,
	precio_compra double,
	foreign key (id_tipo) references tipo_entrada(id_tipo),
	foreign key (dni_comprador) references usuario(dni)
);

create table compra(
	id_compra varchar(9) primary key,
	dni varchar(9),
	id_entrada varchar(9),
	fecha date,
	precio double,
	foreign key (dni) references usuario(dni),
	foreign key (id_entrada) references entrada(id_entrada)
);

create table vende(
	id_venta varchar(9) primary key,
	dni varchar(9),
	id_entrada varchar(9),
	fecha date,
	precio double,
	foreign key (dni) references usuario(dni),
	foreign key (id_entrada) references entrada(id_entrada)
);

create table favorito(
	dni varchar(9),
	id_tipo varchar(9),
	fecha_guardado date,                
	primary key (dni, id_tipo),
	foreign key (dni) references usuario(dni),
	foreign key (id_tipo) references tipo_entrada(id_tipo)
);


-- INSERCCION DE DATOS
insert into usuario values
('11111111A', 'Nerea',  'García',  'Vergara', 'C/ Sierpes 1, Sevilla',              'nerea@correo.com',  611111111),
('22222222B', 'Lucía',  'López',   'Ruiz',    'C/ Feria 5, Sevilla',                'lucia@correo.com',  622222222),
('33333333C', 'Javier', 'Martín',  'Díaz',    'Av. de la Constitución 10, Sevilla', 'javier@correo.com', 633333333),
('44444444D', 'Marta',  'Sánchez', 'Pérez',   'C/ Betis 22, Sevilla',               'marta@correo.com',  644444444),
('55555555E', 'Carlos', 'Romero',  'Gil',     'C/ Mayor 3, Madrid',                 'carlos@correo.com', 655555555),
('66666666F', 'Ana',    'Moreno',  'Castro',  'C/ Alcalá 45, Madrid',               'ana@correo.com',    666666666),
('77777777G', 'David',  'Jiménez', 'Ortega',  'C/ Recogidas 8, Granada',            'david@correo.com',  677777777),
('88888888H', 'Laura',  'Navarro', 'Molina',  'C/ Larios 12, Málaga',               'laura@correo.com',  688888888),
('99999999J', 'Pablo',  'Ruiz',    'Herrera', 'C/ Tetuán 7, Sevilla',               'pablo@correo.com',  699999999),
('10101010K', 'Elena',  'Vega',    null,      'Av. Andalucía 30, Cádiz',            'elena@correo.com',  610101010);


insert into grupo values
('G1', 'Hierro Negro',     'Heavy metal',     954000001, 'hierronegro@correo.com'),
('G2', 'Tormenta del Sur', 'Power metal',     954000002, 'tormentadelsur@correo.com'),
('G3', 'Ceniza',           'Thrash metal',    954000003, 'ceniza@correo.com'),
('G4', 'Lobos de Asfalto', 'Hard rock',       954000004, 'lobosdeasfalto@correo.com'),
('G5', 'Noche Eterna',     'Metal sinfónico', 954000005, 'nocheeterna@correo.com'),
('G6', 'Ruido Blanco',     'Punk rock',       954000006, 'ruidoblanco@correo.com');


insert into lugar values
('L1', 'Sala Vulcano',    'sala',    'C/ Metalurgia 4',          'Sevilla', 800,   954100001, 'info@salavulcano.com'),
('L2', 'Estadio del Río', 'estadio', 'Av. del Guadalquivir s/n', 'Sevilla', 20000, 954100002, 'info@estadiodelrio.com'),
('L3', 'Sala Fragua',     'sala',    'C/ Hierro 12',             'Madrid',  1200,  914100003, 'info@salafragua.com'),
('L4', 'Sala Granito',    'sala',    'C/ Granito 3',             'Granada', 500,   958100004, 'info@salagranito.com');


insert into concierto values
('C1', '2026-10-10', '21:00:00', 'L1'),
('C2', '2026-12-05', '21:30:00', 'L1'),
('C3', '2026-12-19', '20:00:00', 'L2'),
('C4', '2027-01-16', '21:00:00', 'L3'),
('C5', '2027-02-06', '21:00:00', 'L4'),
('C6', '2027-03-13', '20:30:00', 'L2');


insert into toca values
('G3', 'C1'),
('G1', 'C2'), ('G6', 'C2'),
('G2', 'C3'), ('G5', 'C3'), ('G4', 'C3'),
('G1', 'C4'),
('G3', 'C5'), ('G6', 'C5'),
('G5', 'C6');


insert into tipo_entrada values
('T1', 'pista', 20, 800,   'C1'),
('T2', 'pista', 25, 800,   'C2'),
('T3', 'pista', 45, 15000, 'C3'),
('T4', 'grada', 60, 5000,  'C3'),
('T5', 'pista', 30, 1000,  'C4'),
('T6', 'grada', 40, 200,   'C4'),
('T7', 'pista', 18, 3,     'C5'),
('T8', 'pista', 50, 12000, 'C6'),
('T9', 'grada', 70, 8000,  'C6');


insert into entrada values
('E1',  'V', 'T1', '11111111A', '2026-09-01', 20),
('E2',  'V', 'T1', '33333333C', '2026-09-02', 20),
('E3',  'V', 'T2', '11111111A', '2026-10-20', 25),
('E4',  'V', 'T2', '22222222B', '2026-10-21', 25),
('E5',  'R', 'T2', '44444444D', '2026-10-22', 25),
('E6',  'V', 'T3', '33333333C', '2026-10-25', 45),
('E7',  'V', 'T3', '55555555E', '2026-10-26', 45),
('E8',  'V', 'T4', '66666666F', '2026-10-27', 60),
('E9',  'V', 'T4', null,        '2026-10-28', 60),
('E10', 'V', 'T5', '55555555E', '2026-11-02', 30),
('E11', 'V', 'T6', '66666666F', '2026-11-02', 40),
('E12', 'V', 'T7', '77777777G', '2026-11-03', 18),
('E13', 'V', 'T7', '88888888H', '2026-11-03', 18),
('E14', 'V', 'T7', null,        '2026-11-04', 18),
('E15', 'V', 'T8', '10101010K', '2026-11-05', 50),
('E16', 'R', 'T9', '99999999J', '2026-11-06', 70);


insert into vende values
('V1', '22222222B', 'E4', '2026-11-08', 30),
('V2', '66666666F', 'E8', '2026-11-09', 65);

insert into compra values
('CO1', '99999999J', 'E4', '2026-11-08', 30),
('CO2', '77777777G', 'E8', '2026-11-09', 65);


insert into favorito values
('11111111A', 'T3', '2026-11-01'),
('11111111A', 'T8', '2026-11-05'),
('22222222B', 'T5', '2026-11-02'),
('44444444D', 'T9', '2026-11-06'),
('99999999J', 'T2', '2026-10-30'),
('10101010K', 'T4', '2026-11-07'),
('88888888H', 'T5', '2026-11-08');

-- CONSULTAS

-- Buscar un usuario por correo
select *
from usuario
where correo = 'nerea@correo.com';

-- Proximos conciertos del lugar
select c.fecha, c.hora, l.nombre, l.ciudad
from concierto c
join lugar l on c.id_lugar = l.id_lugar
where c.fecha >= curdate()
order by c.fecha;

-- Conciertos de una ciudad
select c.fecha, c.hora, l.nombre, l.ciudad
from concierto c
join lugar l on c.id_lugar = l.id_lugar
where c.fecha >= curdate() and l.ciudad='Sevilla'
order by c.fecha;

-- Tipos de entradas de un concierto
select nombre, precio
from tipo_entrada
where id_concierto= 'C3';

-- Mis entradas
select e.id_entrada, t.nombre as zona, c.fecha, l.nombre as lugar
from entrada e
join tipo_entrada t on e.id_tipo = t.id_tipo
join concierto c    on t.id_concierto = c.id_concierto
join lugar l        on c.id_lugar = l.id_lugar
where e.dni_comprador = '11111111A'
order by c.fecha;

-- PROCEDIMIENTO
-- Calcula cuantas entradas quedan por vender
create procedure entradas_disponibles(in p_id_tipo varchar(9), out p_disponibles int)
begin
    select cantidad_total - (select count(*) from entrada where id_tipo = p_id_tipo)
    into p_disponibles                                  
    from tipo_entrada
    where id_tipo = p_id_tipo;
end;

call entradas_disponibles('T2', @quedan);
select @quedan;

-- TRANSACCION
-- Comprar una entrada oficial.
-- Si hay entrada se confirma si no hay entrada se deshace todo
create procedure comprar_entrada(in p_dni varchar(9), in p_id_tipo varchar(9))
begin
    declare v_quedan int;
    declare v_precio double;
    declare v_id varchar(9);
    declare exit handler for sqlexception
    begin
        rollback;
        resignal;
    end;
    start transaction;
    select precio into v_precio
    from tipo_entrada
    where id_tipo = p_id_tipo
    for update;
    call entradas_disponibles(p_id_tipo, v_quedan);
    if v_quedan <= 0 then
        rollback;
        signal sqlstate '45000' set message_text = 'No quedan entradas de este tipo';
    end if;
    select concat('E', ifnull(max(cast(substring(id_entrada, 2) as unsigned)), 0) + 1)
    into v_id
    from entrada;
    insert into entrada values (v_id, 'V', p_id_tipo, p_dni, curdate(), v_precio);
    commit;
end;

create table toko(
	id character varying(64) not null primary key,
	nama character varying(100) not null,
	alamat character varying(100)
);

insert into toko(id, nama, alamat) values ('001', 'SAMSUNG', 'Jakarta');
insert into toko(id, nama, alamat) values ('002', 'SONY', 'Surabaya');
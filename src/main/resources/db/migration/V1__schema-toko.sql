create table toko(
	id character varying(64) not null primary key,
	nama character varying(100) not null,
	alamat character varying(100)
);

insert into toko(id, nama, alamat) values ('001', 'Samsung', 'Jakarta');
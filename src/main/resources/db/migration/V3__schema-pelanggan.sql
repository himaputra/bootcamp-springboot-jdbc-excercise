create table pelanggan(
	id character varying(64) not null primary key,
	nama character varying(100) not null,
	alamat character varying(100)
);

insert into pelanggan(id, nama, alamat) values ('001', 'Himawan Eka Putra', 'Cimahi');
insert into pelanggan(id, nama, alamat) values ('002', 'Nanra Sukedy Hasibuan', 'Bandung');
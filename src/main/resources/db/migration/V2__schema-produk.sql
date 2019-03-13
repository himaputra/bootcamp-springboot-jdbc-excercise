create table produk(
	id character varying(64) not null primary key,
	toko_id character varying(64) not null,
	nama character varying(100) not null,
	harga decimal not null default 0,
	kuantitas int not null default 1
);

alter table produk
add constraint fk_produk_toko_id foreign key (toko_id)
references toko(id) on update cascade on delete restrict;

insert into produk(id, toko_id, nama, harga, kuantitas) values ('001', '001', 'Galaxy S10', 12000000, '99');
insert into produk(id, toko_id, nama, harga, kuantitas) values ('002', '001', 'Galaxy S9', 10000000, '50');
insert into produk(id, toko_id, nama, harga, kuantitas) values ('003', '002', 'Xperia XZ', 11000000, '40');
insert into produk(id, toko_id, nama, harga, kuantitas) values ('004', '002', 'Xperia Z5', 5000000, '20');
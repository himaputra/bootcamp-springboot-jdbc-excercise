create table produk(
	id character varying(64) not null primary key,
	nama character varying(100) not null,
	kuantitas int not null default 0
);
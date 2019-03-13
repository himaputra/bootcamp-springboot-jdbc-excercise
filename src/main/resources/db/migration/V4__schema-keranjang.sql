create table keranjang(
	id character varying(64) not null primary key,
	pelanggan_id character varying(64) not null,
	tanggal timestamp not null default now()
);

create table keranjang_detail(
	id character varying(64) not null primary key,
	keranjang_id character varying(64) not null,
	produk_id character varying(64) not null,
	kuantitas int not null default 1
);

alter table keranjang_detail
add constraint fk_keranjang_detail_keranjang_id foreign key (keranjang_id)
references keranjang(id) on update cascade on delete cascade;

alter table keranjang_detail
add constraint fk_keranjang_produk_id foreign key (produk_id)
references produk(id) on update cascade on delete restrict;

alter table keranjang
add constraint fk_keranjang_pelanggan_id foreign key (pelanggan_id)
references pelanggan(id) on update cascade on delete restrict;

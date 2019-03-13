package com.tabeldata.bootcamp.belajarspringboot.model;

import java.math.BigDecimal;

public class Produk {
	private String id;
	private Toko idToko;
	private String nama;
	private BigDecimal harga;
	private Integer kuantitas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Toko getIdToko() {
		return idToko;
	}

	public void setIdToko(Toko idToko) {
		this.idToko = idToko;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public BigDecimal getHarga() {
		return harga;
	}

	public void setHarga(BigDecimal harga) {
		this.harga = harga;
	}

	public Integer getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Integer kuantitas) {
		this.kuantitas = kuantitas;
	}

}

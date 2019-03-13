package com.tabeldata.bootcamp.belajarspringboot.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KeranjangDetail {
	private String id;
	private Keranjang idKeranjang;
	private Produk idProduk;
	private Integer kuantitas;
	private BigDecimal subTotal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Keranjang getIdKeranjang() {
		return idKeranjang;
	}

	public void setIdKeranjang(Keranjang idKeranjang) {
		this.idKeranjang = idKeranjang;
	}

	public Produk getIdProduk() {
		return idProduk;
	}

	public void setIdProduk(Produk idProduk) {
		this.idProduk = idProduk;
	}

	public Integer getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Integer kuantitas) {
		this.kuantitas = kuantitas;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}



}

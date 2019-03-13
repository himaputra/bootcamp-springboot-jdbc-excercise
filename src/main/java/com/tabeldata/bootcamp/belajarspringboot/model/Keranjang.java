package com.tabeldata.bootcamp.belajarspringboot.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Keranjang {
	private String id;
	private Pelanggan idPelanggan;
	private BigDecimal total;
	private Timestamp tanggal;
	List<KeranjangDetail> detail = new ArrayList<KeranjangDetail>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getTanggal() {
		return tanggal;
	}

	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	
	public Pelanggan getIdPelanggan() {
		return idPelanggan;
	}

	public void setIdPelanggan(Pelanggan idPelanggan) {
		this.idPelanggan = idPelanggan;
	}

	public List<KeranjangDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<KeranjangDetail> detail) {
		this.detail = detail;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}

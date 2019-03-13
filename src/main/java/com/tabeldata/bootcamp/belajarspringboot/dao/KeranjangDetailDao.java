package com.tabeldata.bootcamp.belajarspringboot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tabeldata.bootcamp.belajarspringboot.model.Keranjang;
import com.tabeldata.bootcamp.belajarspringboot.model.KeranjangDetail;
import com.tabeldata.bootcamp.belajarspringboot.model.Pelanggan;
import com.tabeldata.bootcamp.belajarspringboot.model.Produk;
import com.tabeldata.bootcamp.belajarspringboot.model.Toko;

@Repository
@Transactional(readOnly = true)
public class KeranjangDetailDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<KeranjangDetail> listKeranjangDetail(String id) {
		String SQL = "select k.id as keranjang_id, k.kuantitas as keranjang_qty, t.nama as toko_nama, p.id as produk_id, p.nama as produk_nama, p.harga as produk_harga, p.kuantitas as produk_qty from keranjang_detail k join produk p on k.produk_id = p.id join toko t on p.toko_id = t.id where k.keranjang_id = ?";
		return this.jdbc.query(SQL, new KeranjangDetailRowMapper(), new Object[] {id});
	}
	
	private class KeranjangDetailRowMapper implements RowMapper<KeranjangDetail> {

		@Override
		public KeranjangDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			KeranjangDetail kDetail = new KeranjangDetail();
			kDetail.setId(rs.getString("keranjang_id"));

			Produk produk = new Produk();
			produk.setId(rs.getString("produk_id"));
			produk.setNama(rs.getString("produk_nama"));
			produk.setHarga(rs.getBigDecimal("produk_harga"));
			Toko toko = new Toko();
			toko.setNama(rs.getString("toko_nama"));
			produk.setIdToko(toko);;
			kDetail.setIdProduk(produk);
			kDetail.setKuantitas(rs.getInt("keranjang_qty"));
			return kDetail;
		}
		
	}
}

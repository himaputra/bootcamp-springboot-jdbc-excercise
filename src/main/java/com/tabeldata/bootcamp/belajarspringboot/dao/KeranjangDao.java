package com.tabeldata.bootcamp.belajarspringboot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
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
public class KeranjangDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private KeranjangDetailDao dao;
	
	@Autowired
	private ProdukDao produkDao;

	@Transactional
	public void save(Keranjang keranjang) {
		String SQL = "insert into keranjang(id, pelanggan_id) values (?, ?)";
		this.jdbc.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, keranjang.getId());
				ps.setString(2, keranjang.getIdPelanggan().getId());
			}

		});

		String sqlDetail = "insert into keranjang_detail(id, keranjang_id, produk_id, kuantitas) values (?, ?, ?, ?)";
		this.jdbc.batchUpdate(sqlDetail, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				KeranjangDetail detail = keranjang.getDetail().get(i);
				ps.setString(1, detail.getId());
				ps.setString(2, keranjang.getId());
				ps.setString(3, detail.getIdProduk().getId());
				ps.setInt(4, detail.getKuantitas());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return keranjang.getDetail().size();
			}
		});
	}

	public List<Keranjang> listKeranjang() {
		String SQL = "select k.id, pelanggan.nama as pelanggan_nama, k.tanggal from keranjang k join pelanggan on k.pelanggan_id = pelanggan.id";
		return this.jdbc.query(SQL, new KeranjangRowMapperBaru());
	}
	
	public Keranjang checkout(String id) {
		String SQL = "select k.id, p.nama as pelanggan_nama, p.alamat as pelanggan_alamat, k.tanggal from keranjang k join pelanggan p on k.pelanggan_id = p.id where k.id = ?";
		return this.jdbc.queryForObject(SQL, new KeranjangRowMapper(id), new Object[] {id});
	}
	
	public Keranjang findById(String id) {
		String SQL = "select k.id, pelanggan.nama as pelanggan_nama, k.tanggal from keranjang k join pelanggan on k.pelanggan_id = pelanggan.id where k.id = ?";
		return this.jdbc.queryForObject(SQL, new KeranjangRowMapperBaru(), new Object[] {id});
	}
	
	@Transactional
	public void delete(String id) {
		String SQL = "delete from keranjang where id = ?";
		this.jdbc.update(SQL, new Object[] {id});
	}

//	Class
	private class KeranjangRowMapper implements RowMapper<Keranjang> {

		private String id;
		
		public KeranjangRowMapper(String id) {
			this.id = id;
		}
		
		@Override
		public Keranjang mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Keranjang aKeranjang = new Keranjang();
			aKeranjang.setId(rs.getString("id"));
			Pelanggan pelanggan = new Pelanggan();
			pelanggan.setNama(rs.getString("pelanggan_nama"));
			pelanggan.setAlamat(rs.getString("Pelanggan_alamat"));
			aKeranjang.setIdPelanggan(pelanggan);
			List<KeranjangDetail> list = dao.listKeranjangDetail(id);
			aKeranjang.setDetail(list);
			aKeranjang.setTanggal(rs.getTimestamp("tanggal"));
			return aKeranjang;
		}

	}
	
	private class KeranjangRowMapperBaru implements RowMapper<Keranjang> {

		
		@Override
		public Keranjang mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Keranjang aKeranjang = new Keranjang();
			aKeranjang.setId(rs.getString("id"));
			Pelanggan pelanggan = new Pelanggan();
			pelanggan.setNama(rs.getString("pelanggan_nama"));
			aKeranjang.setIdPelanggan(pelanggan);
			aKeranjang.setTanggal(rs.getTimestamp("tanggal"));
			return aKeranjang;
		}

	}
}

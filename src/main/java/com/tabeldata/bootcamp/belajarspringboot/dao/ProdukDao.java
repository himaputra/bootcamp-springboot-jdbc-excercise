package com.tabeldata.bootcamp.belajarspringboot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tabeldata.bootcamp.belajarspringboot.model.Produk;

@Repository
@Transactional(readOnly = true)
public class ProdukDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Transactional
	public void save(Produk produk) {
		String SQL = "insert into produk(id, nama, kuantitas) values (?, ?, ?)";
		this.jdbc.update(SQL, new InsertProduk(produk));
	}

	public List<Produk> listProduk() {
		String SQL = "select * from produk";
		return this.jdbc.query(SQL, new ProdukRowMapper());
	}

	public Produk findByName(String nama) {
		String SQL = "select * from produk where nama = ?";
		return this.jdbc.queryForObject(SQL, new ProdukRowMapper(), new Object[] { nama });
	}

	@Transactional
	public void update(Produk produk) {
		String SQL = "update produk set " + "nama = ?, " + "kuantitas = ? " + "where id = ?";
		this.jdbc.update(SQL, new UpdateProduk(produk));
	}
	
	@Transactional
	public void delete(String id) {
		String SQL = "delete from produk where id = ?";
		this.jdbc.update(SQL, new Object[] {id});
	}

//	Class
	public class UpdateProduk implements PreparedStatementSetter {

		final Produk produk;
		
		public UpdateProduk(Produk produk) {
			this.produk = produk;
		}
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, produk.getNama());
			ps.setInt(2, produk.getKuantitas());
			ps.setString(3, produk.getId());
		}

	}

	private class ProdukRowMapper implements RowMapper<Produk> {

		@Override
		public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Produk aProduk = new Produk();
			aProduk.setId(rs.getString("id"));
			aProduk.setNama(rs.getString("nama"));
			aProduk.setKuantitas(rs.getInt("kuantitas"));
			return aProduk;
		}

	}

	public class InsertProduk implements PreparedStatementSetter {

		final Produk produk;

		public InsertProduk(Produk produk) {
			this.produk = produk;
		}

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, produk.getId());
			ps.setString(2, produk.getNama());
			ps.setInt(3, produk.getKuantitas());
		}

	}
}

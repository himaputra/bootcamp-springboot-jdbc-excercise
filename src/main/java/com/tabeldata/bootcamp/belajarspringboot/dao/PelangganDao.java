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

import com.tabeldata.bootcamp.belajarspringboot.model.Pelanggan;

@Repository
@Transactional(readOnly = true)
public class PelangganDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Transactional
	public void save(Pelanggan pelanggan) {
		String SQL = "insert into pelanggan(id, nama, alamat) values (?, ?, ?)";
		this.jdbc.update(SQL, new InsertPelanggan(pelanggan));
	}
	
	public List<Pelanggan> listPelanggan() {
		String SQL = "select * from pelanggan";
		return this.jdbc.query(SQL, new PelangganRowMapper());
	}
	
	public Pelanggan findByName(String nama) {
		String SQL = "select * from pelanggan where nama = ?";
		return this.jdbc.queryForObject(SQL, new PelangganRowMapper(), new Object[] {nama});
	}
	
	@Transactional
	public void update(Pelanggan pelanggan) {
		String SQL = "update pelanggan set "
				+ "nama = ?, "
				+ "alamat = ? "
				+ "where id = ?";
		this.jdbc.update(SQL, new UpdatePelanggan(pelanggan));
	}
	
	@Transactional
	public void delete(String id) {
		String SQL = "delete from pelanggan where id = ?";
		this.jdbc.update(SQL, new Object[] {id});
	}

//	Class
	public class UpdatePelanggan implements PreparedStatementSetter {
		final Pelanggan pelanggan;
		
		public UpdatePelanggan(Pelanggan pelanggan) {
			this.pelanggan = pelanggan;
		}

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, pelanggan.getNama());
			ps.setString(2, pelanggan.getAlamat());
			ps.setString(3, pelanggan.getId());
		}
	}
	
	public class InsertPelanggan implements PreparedStatementSetter {

		final Pelanggan pelanggan;
		
		public InsertPelanggan(Pelanggan pelanggan) {
			this.pelanggan = pelanggan;
		}
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, pelanggan.getId());
			ps.setString(2, pelanggan.getNama());
			ps.setString(3, pelanggan.getAlamat());
		}

	}
	
	private class PelangganRowMapper implements RowMapper<Pelanggan> {

		@Override
		public Pelanggan mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Pelanggan aPelanggan = new Pelanggan();
			aPelanggan.setId(rs.getString("id"));
			aPelanggan.setNama(rs.getString("nama"));
			aPelanggan.setAlamat(rs.getString("alamat"));
			return aPelanggan;
		}
		
	}
}

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

import com.tabeldata.bootcamp.belajarspringboot.model.Toko;

@Repository
@Transactional(readOnly = true)
public class TokoDao {
	@Autowired
	private JdbcTemplate jdbc;

	public List<Toko> listToko() {
		String SQL = "select * from toko";
		return this.jdbc.query(SQL, new TokoRowMapper());
	}

	public Toko findByName(String nama) {
		String SQL = "select * from toko where nama = ?";
		return this.jdbc.queryForObject(SQL, new TokoRowMapper(), new Object[] { nama });
	}

	@Transactional
	public void save(Toko toko) {
		String SQL = "insert into toko(id, nama, alamat) values (?, ?, ?)";
		this.jdbc.update(SQL, new InsertToko(toko));
	}

	@Transactional
	public void update(Toko toko) {
		String SQL = "update toko set " + "nama = ?, " + "alamat = ? " + "where id = ?";
		this.jdbc.update(SQL, new UpdateToko(toko));
	}

	@Transactional
	public void delete(String id) {
		String SQL = "delete from toko where id = ?";
		this.jdbc.update(SQL, new Object[] { id });
	}

//	Class
	public class UpdateToko implements PreparedStatementSetter {

		final Toko toko;

		public UpdateToko(Toko toko) {
			this.toko = toko;
		}

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, toko.getNama());
			ps.setString(2, toko.getAlamat());
			ps.setString(3, toko.getId());
		}

	}

	public class InsertToko implements PreparedStatementSetter {
		final Toko toko;

		public InsertToko(Toko toko) {
			this.toko = toko;
		}

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, toko.getId());
			ps.setString(2, toko.getNama());
			ps.setString(3, toko.getAlamat());
		}
	}

	private class TokoRowMapper implements RowMapper<Toko> {

		@Override
		public Toko mapRow(ResultSet rs, int rowNum) throws SQLException {
			Toko aToko = new Toko();
			aToko.setId(rs.getString("id"));
			aToko.setNama(rs.getString("nama"));
			aToko.setAlamat(rs.getString("alamat"));
			return aToko;
		}

	}
}

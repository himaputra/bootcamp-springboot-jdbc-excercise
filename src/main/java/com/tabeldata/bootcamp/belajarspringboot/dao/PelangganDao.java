package com.tabeldata.bootcamp.belajarspringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tabeldata.bootcamp.belajarspringboot.model.Pelanggan;

@Repository
@Transactional(readOnly = true)
public class PelangganDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	public void save(Pelanggan pelanggan) {
		String SQL = "insert into pelanggan(id, nama, alamat) values (?, ?, ?)";
		this.jdbc.update(SQL, new PelangganRowMapper());
	}
}

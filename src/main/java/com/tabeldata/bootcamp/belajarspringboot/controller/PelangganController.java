package com.tabeldata.bootcamp.belajarspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tabeldata.bootcamp.belajarspringboot.dao.PelangganDao;
import com.tabeldata.bootcamp.belajarspringboot.model.Pelanggan;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {
	@Autowired
	private PelangganDao dao;
	
	@PostMapping("/")
	public ResponseEntity<Pelanggan> save(@RequestBody Pelanggan pelanggan) {
		dao.save(pelanggan);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/json/list")
	public List<Pelanggan> listJson() {
		return dao.listPelanggan();
	}
	
	@GetMapping("/{nama}")
	public ResponseEntity<Pelanggan> findByName(@PathVariable("nama") String nama) {
		Pelanggan pelanggan = dao.findByName(nama);
		return ResponseEntity.ok(pelanggan);
	}
	
	@PutMapping("/")
	public ResponseEntity<Pelanggan> update(@RequestBody Pelanggan pelanggan) {
		dao.update(pelanggan);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pelanggan> delete(@PathVariable("id") String id) {
		dao.delete(id);
		return ResponseEntity.ok().build();
	}
}

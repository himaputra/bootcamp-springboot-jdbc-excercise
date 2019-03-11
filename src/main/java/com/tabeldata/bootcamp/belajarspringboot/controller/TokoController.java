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

import com.tabeldata.bootcamp.belajarspringboot.dao.TokoDao;
import com.tabeldata.bootcamp.belajarspringboot.model.Toko;

@RestController
@RequestMapping("/api/toko")
public class TokoController {
	@Autowired
	private TokoDao dao;
	
	@PostMapping("/")
	public ResponseEntity<Toko> save(@RequestBody Toko toko) {
		dao.save(toko);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/json/list")
	public List<Toko> allJson() {
		return dao.listToko();
	}
	
	@GetMapping("/{nama}")
	public ResponseEntity<Toko> findByName(@PathVariable("nama") String nama) {
		Toko toko = dao.findByName(nama);
		return ResponseEntity.ok(toko);
	}
	
	@PutMapping("/")
	public ResponseEntity<Toko> update(@RequestBody Toko toko) {
		dao.update(toko);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Toko> delete(@PathVariable("id") String id) {
		dao.delete(id);
		return ResponseEntity.ok().build();
	}
}

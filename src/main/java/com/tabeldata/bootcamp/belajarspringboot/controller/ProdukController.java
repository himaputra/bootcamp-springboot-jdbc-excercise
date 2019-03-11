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

import com.tabeldata.bootcamp.belajarspringboot.dao.ProdukDao;
import com.tabeldata.bootcamp.belajarspringboot.model.Produk;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {
	@Autowired
	private ProdukDao dao;
	
	@PostMapping("/")
	public ResponseEntity<Produk> save(@RequestBody Produk produk) {
		dao.save(produk);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/json/list")
	public List<Produk> listJson() {
		return dao.listProduk();
	}
	
	@GetMapping("/{nama}")
	public ResponseEntity<Produk> findByName(@PathVariable("nama") String nama) {
		Produk produk = dao.findByName(nama);
		return ResponseEntity.ok(produk);
	}
	
	@PutMapping("/")
	public ResponseEntity<Produk> update(@RequestBody Produk produk) {
		dao.update(produk);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produk> delete(@PathVariable("id") String id) {
		dao.delete(id);
		return ResponseEntity.ok().build();
	}
}

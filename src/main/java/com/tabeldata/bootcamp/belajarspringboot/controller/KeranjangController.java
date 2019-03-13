package com.tabeldata.bootcamp.belajarspringboot.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tabeldata.bootcamp.belajarspringboot.dao.KeranjangDao;
import com.tabeldata.bootcamp.belajarspringboot.model.Keranjang;
import com.tabeldata.bootcamp.belajarspringboot.model.KeranjangDetail;

@RestController
@RequestMapping("/api/keranjang")
public class KeranjangController {
	@Autowired
	private KeranjangDao dao;
	
	@PostMapping("/")
	public ResponseEntity<Keranjang> save(@RequestBody Keranjang keranjang) {
		dao.save(keranjang);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/json/list")
	public List<Keranjang> listJson() {
		return dao.listKeranjang();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Keranjang> findById(@PathVariable("id") String id) {
		Keranjang keranjang = dao.findById(id);
		return ResponseEntity.ok(keranjang);
	}
	
	@GetMapping("/checkout/{id}")
	public ResponseEntity<Keranjang> checkout(@PathVariable("id") String id) {
		Keranjang keranjang = dao.checkout(id);
		keranjang.setTotal(BigDecimal.ZERO);
		for(KeranjangDetail k: keranjang.getDetail()) {
			k.setSubTotal(k.getIdProduk().getHarga().multiply(new BigDecimal(k.getKuantitas())));
			keranjang.setTotal(keranjang.getTotal().add(k.getSubTotal()));
		}
		return ResponseEntity.ok(keranjang);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Keranjang> delete(@PathVariable("id") String id) {
		dao.delete(id);
		return ResponseEntity.ok().build();
	}
}

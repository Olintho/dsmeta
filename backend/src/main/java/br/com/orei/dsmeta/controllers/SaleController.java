package br.com.orei.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orei.dsmeta.entities.Sale;
import br.com.orei.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public List<Sale> findSales() {
	
		return saleService.findSales();
	}
}

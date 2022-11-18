package br.com.orei.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.orei.dsmeta.entities.Sale;
import br.com.orei.dsmeta.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	public List<Sale> findSales() {
		
//		List<Sale> list = new List<Sale>(); 
		return saleRepository.findAll();
		
	}
}

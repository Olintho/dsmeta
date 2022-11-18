package br.com.orei.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.orei.dsmeta.entities.Sale;
import br.com.orei.dsmeta.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {

//		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		try {

			LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
			LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
			return saleRepository.findSales(min, max, pageable);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
}

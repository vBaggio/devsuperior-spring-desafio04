package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.utils.DateRangeUtil;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SummaryDTO> getSummary(String minDate, String maxDate) {
		LocalDate[] range = DateRangeUtil.resolveMinMaxDates(minDate, maxDate);
	    return repository.getSummary(range[0], range[1]);
	}
	
	public Page<ReportDTO> getReport(Pageable pageable, String minDate, String maxDate, String name) {
		LocalDate[] range = DateRangeUtil.resolveMinMaxDates(minDate, maxDate);
		return repository.getReport(range[0], range[1], name, pageable).map(x -> new ReportDTO(x));
	}
}

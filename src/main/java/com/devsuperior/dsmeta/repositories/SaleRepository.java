package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(" + "obj.seller.name, SUM(obj.amount)) " + "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate " + "GROUP BY obj.seller.name")
	List<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate);

	@Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj.date BETWEEN :minDate AND :maxDate AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')",
		countQuery = "SELECT count(obj) FROM Sale obj JOIN obj.seller WHERE obj.date BETWEEN :minDate AND :maxDate AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
	Page<Sale> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
}
//
//, 
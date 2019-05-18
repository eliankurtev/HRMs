package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Short> {
}

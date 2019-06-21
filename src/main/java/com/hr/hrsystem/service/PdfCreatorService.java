package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ApplicationForVacationFEDto;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface PdfCreatorService {
    void createApplication(Long id, HireEmployeeService hireEmployeeService, ApplicationForVacationFEDto applicationForVacationFEDto) throws FileNotFoundException, DocumentException;
    void createContract(Long id, HireEmployeeService hireEmployeeService) throws DocumentException, FileNotFoundException;
}

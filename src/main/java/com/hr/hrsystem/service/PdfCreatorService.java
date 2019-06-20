package com.hr.hrsystem.service;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface PdfCreatorService {
    void createApplication(Long id, HireEmployeeService hireEmployeeService) throws FileNotFoundException, DocumentException;
    void createContract(Long id, HireEmployeeService hireEmployeeService) throws DocumentException, FileNotFoundException;
}

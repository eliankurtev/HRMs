package com.hr.hrsystem.service;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface PdfCreatorService {
    void createApplication(Long id) throws FileNotFoundException, DocumentException;
    void createContract() throws DocumentException, FileNotFoundException;
}

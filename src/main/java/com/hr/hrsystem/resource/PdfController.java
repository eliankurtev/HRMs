package com.hr.hrsystem.resource;


import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.impl.PdfCreatorServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class PdfController {

    @Autowired
    private HireEmployeeService hireEmployeeService;

    @RequestMapping("/createApplication")
    @ResponseBody
    String homeApplication() {
        try {
            createApplication(1L, hireEmployeeService);
            return "PDF Created!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error in creating pdf: ";
        }
    }

    private void createApplication(Long id, HireEmployeeService hireEmployeeService) {
        PdfCreatorServiceImpl pdf = new PdfCreatorServiceImpl();
        try {
            pdf.createApplication(id, hireEmployeeService);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package com.hr.hrsystem.resource;


import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ApplicationForVacationFEDto;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.impl.PdfCreatorServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class PdfController {

    @Autowired
    private HireEmployeeService hireEmployeeService;

    @RequestMapping(value = "/createApplication",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    String homeApplication(@RequestBody ApplicationForVacationFEDto applicationDto) {
        try {
            createApplication(1L, hireEmployeeService, applicationDto);
            return "PDF Created!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error in creating pdf: ";
        }
    }

    private void createApplication(Long id, HireEmployeeService hireEmployeeService, ApplicationForVacationFEDto applicationDto) {
        PdfCreatorServiceImpl pdf = new PdfCreatorServiceImpl();
        try {
            pdf.createApplication(id, hireEmployeeService, applicationDto);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package com.hr.hrsystem.resource;


import com.hr.hrsystem.service.impl.PdfCreatorServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;

@Controller
public class PdfController {

    @RequestMapping("/createContract")
    @ResponseBody
    String homeContract() {
        try {
            createContract();
            return "PDF Created!";
        } catch (Exception ex) {
            return "Error in creating pdf: " + ex;
        }
    }

    @RequestMapping("/createApplication")
    @ResponseBody
    String homeApplication() {
        try {
            createApplication(1L);
            return "PDF Created!";
        } catch (Exception ex) {
            return "Error in creating pdf: " + ex;
        }
    }

    private void createContract() {
        PdfCreatorServiceImpl pdf = new PdfCreatorServiceImpl();
        try {
            pdf.createContract();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createApplication(Long id) {
        PdfCreatorServiceImpl pdf = new PdfCreatorServiceImpl();
        try {
            pdf.createApplication(id);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

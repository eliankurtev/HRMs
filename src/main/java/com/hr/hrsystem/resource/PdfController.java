package com.hr.hrsystem.resource;


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
            createApplication();
            return "PDF Created!";
        } catch (Exception ex) {
            return "Error in creating pdf: " + ex;
        }
    }

    private void createContract() {
        PdfCreator pdf = new PdfCreator();
        try {
            pdf.createContract();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createApplication() {
        PdfCreator pdf = new PdfCreator();
        try {
            pdf.createApplication();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

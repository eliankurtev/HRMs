package com.hr.hrsystem.resource;


import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ApplicationForVacationFEDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.TransformationService;
import com.hr.hrsystem.service.impl.PdfCreatorServiceImpl;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@RestController
public class PdfController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TransformationService transformationService;

    @Autowired
    private HireEmployeeService hireEmployeeService;

    @RequestMapping(value = "/createApplication", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> findHrEmployees() {
        log.info(transformationService.getHrEmployeeDtos().toString());
        return new ResponseEntity<>(transformationService.getHrEmployeeDtos(), HttpStatus.OK);
    }


    @RequestMapping(value = "/createApplication",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    String homeApplication(@RequestBody ApplicationForVacationFEDto applicationDto) {
        try {
            createApplication(1L, hireEmployeeService, applicationDto);
            sendEmail(applicationDto.getHrName());
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

    private void sendEmail(String name) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("cuwabaleba@emailate.com");
        helper.setText("Hello, " + name + "! <html><body><p>Here is my application for vacation! I hope you will confirm it as soon as possible!</p> <p>Greetings!</p><body></html>", true);
        helper.setSubject("Hi");
        helper.addAttachment("ApplicationForVacation.pdf", new File("src/main/resources/documents/ApplicationForVacation.pdf"));

        ClassPathResource file = new ClassPathResource("Human-Hands-Front-Back.jpg");
        helper.addInline("id101", file);

        sender.send(message);
    }

}

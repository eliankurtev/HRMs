package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmailDto;
import com.hr.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class SendEmailController {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/salaryEmail")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }

    @RequestMapping(value = "/sendInterviewInvite", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<String> sendInterviewInvite(@RequestBody EmailDto emailDto) {
        try {
            sendInterview(emailDto);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error in sending email: " + ex, HttpStatus.EXPECTATION_FAILED);
        }
    }

    private void sendEmail() {
        employeeService.findAll().forEach(e -> {
            MimeMessage message = sender.createMimeMessage();

            // Enable the multipart flag!
            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(message, true);
                helper.setTo(e.getPerson().getEmail());
                helper.setText("<html><body>Hi " + e.getPerson().getFirstName() +
                        ", <br/> You have been paid the amount of:" + e.getSecurityData().getSalary()
                        + "lv. <br/><br/>" +
                        "Kind regards, <br/> Java Team <body></html>", true);
                helper.setSubject("Salary Report on "+ LocalDateTime.now());

                sender.send(message);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void sendInterview(@RequestBody EmailDto emailDto) throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailDto.getEmail());
        String date = emailDto.getInterviewDate();
        String newDate = date.replaceAll("T", " ");
        String finalDate = newDate.replaceAll(".000Z", "");

        helper.setText("<html><body><i>Dear,</i> <b>" + emailDto.getName() + "</b><i>,</i><br/><i>We want to invite you on interview on </i><b>" + finalDate + "</b><i>.</br>" + "<br/> Our address is </i>" + "<b>Kyustendil, Dupnishsko shose 18, fl.5</b><i>." + "<br/><br/>Thank you,<br/>HRMs team!</i><br/><br/> This email is automatically generated! Please do not respond!", true);
        helper.setSubject("Invite for interview");

        sender.send(message);
    }
}


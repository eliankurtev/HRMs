package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.CompanyDto;
import com.hr.hrsystem.dto.EmailDto;
import com.hr.hrsystem.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

@Controller
public class SendEmailController {
    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/simpleemail3")
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

    private void sendEmail() throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("humu@email-24x7.com");
        helper.setText("<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>", true);
        helper.setSubject("Hi");

        ClassPathResource file = new ClassPathResource("Human-Hands-Front-Back.jpg");
        helper.addInline("id101", file);

        sender.send(message);
    }

//    private void sendInterview(@RequestBody EmailDto emailDto) throws Exception {
//        MimeMessage message = sender.createMimeMessage();
//
//        // Enable the multipart flag!
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(emailDto.getEmail());
//        helper.setText("<html><body>Hello " + emailDto.getName() + ",<br/><br/>We want ot invite you on interview on " + emailDto.getInterviewDate() + ". <br/><br/>Thank you,<br/>HRMS team", true);
//        helper.setSubject("Invite for interview");
//
//        sender.send(message);
//    }

    private void sendInterview(@RequestBody EmailDto emailDto) throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailDto.getEmail());
        String date =  emailDto.getInterviewDate();
        String newDate = date.replaceAll("T"," ");
        String finalDate= newDate.replaceAll(".000Z","");

        helper.setText("<html><body><i>Dear,</i> <b>" + emailDto.getName() + "</b><i>,</i><br/><i>We want to invite you on interview on </i><b>" + finalDate + "</b><i>.</br>"+"<br/> Our address is </i>"+"<b>Kyustendil, Dupnishsko shose 18, fl.5</b><i>."+"<br/><br/>Thank you,<br/>HRMs team!</i><br/><br/> This email is automatically generated! Please do not respond!", true);
        helper.setSubject("Invite for interview");

        sender.send(message);
    }
}


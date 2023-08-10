package com.vivatech.VivaTech.Service.ImplService;


import com.vivatech.VivaTech.Dto.UserDto;
import com.vivatech.VivaTech.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    public UserService userService;
    @Autowired
    private JavaMailSender javaMailSender;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendOtpMessage(String to, String subject,int otp) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(to);
        UserDto userDto=userService.getUserByEmail(to);
        String name=userDto.getName();

        helper.setSubject(subject);
        //Adding html template
        String content="<p>Hello "+ name + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + otp+ "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>"
                + "<p><b>Thanks & Regards </b></p>"
                +"<p><b>Pankaj Kumar </b></p>"
                + "<br>";

        helper.setText(content, true);

        javaMailSender.send(msg);
    }

    public void sendVerificationMessage(String to, String subject) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(to);
        UserDto userDto=userService.getUserByEmail(to);
        String name=userDto.getName();

        helper.setSubject(subject);
        //Adding html template
        String content="<p>Hello "+ name + "</p>"
                + "<p>Your Otp has been successfully verified </p>"
                + "<p><b>Thanks & Regards </b></p>"
                +"<p><b>Pankaj Kumar </b></p>"
                + "<br>";

        helper.setText(content, true);

        javaMailSender.send(msg);
    }
}


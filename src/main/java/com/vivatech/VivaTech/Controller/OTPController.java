package com.vivatech.VivaTech.Controller;

import com.vivatech.VivaTech.Service.ImplService.EmailService;
import com.vivatech.VivaTech.Service.ImplService.OTPService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class OTPController {


    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @GetMapping("/generateOtp")
    public String generateOTP() throws MessagingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);

        //This is for testing purpose only
        //logger.info("OTP : "+otp);
        //logger.info("username : "+username);

        emailService.sendOtpMessage(username, "OTP -Viva Tech",otp);

        return "Send otp on user mail id ";
    }


    @RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
    public @ResponseBody String validateOtp(@Valid @RequestParam(required = false,defaultValue = "0")Integer otpnum) throws MessagingException {

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if(otpnum >= 0){

            int serverOtp = otpService.getOtp(username);
            if(serverOtp > 0){
                if(otpnum == serverOtp){
                    otpService.clearOTP(username);
                    emailService.sendVerificationMessage(username, "Successfully Verified OTP");
                    return ("Entered Otp is valid");
                }
                else {
                    return SUCCESS;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }
}

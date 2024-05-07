package com.example.apiWithDb.controller;

import com.example.apiWithDb.dto.MailBody;
import com.example.apiWithDb.entities.ForgotPassword;
import com.example.apiWithDb.entities.User;
import com.example.apiWithDb.exception.AppException;
import com.example.apiWithDb.repository.ForgotPasswordRepository;
import com.example.apiWithDb.repository.UserRepository;
import com.example.apiWithDb.service.EmailService;
import com.example.apiWithDb.utils.ChangePassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;
    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Send mail for email verification
    @PostMapping("/auth/check-email/")
    public ResponseEntity<String> verifyEmail(@RequestBody String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("Ваш код для восстановления пароля : " + otp)
                .subject("Восстановление пароля")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sensSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("OTP send for verification!");
    }

    @PostMapping("/auth/confirm-email")
    public ResponseEntity<String> verifyOtp(@RequestBody String email, @RequestBody String code){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(code,user)
                .orElseThrow(() -> new AppException("Invalid OTP for email", HttpStatus.EXPECTATION_FAILED));
        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP verifed");
    }

    @PostMapping("/auth/change-password")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                        @RequestBody String email){
        if(!Objects.equals(changePassword.password(), changePassword.confirmPassword())) {
            return new ResponseEntity<>("Please enter the password again!",HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok("Password has been changed");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }
}

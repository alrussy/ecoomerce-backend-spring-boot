package com.alrussy.idantityservice.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alrussy.idantityservice.dto.OtpActivation;
import com.alrussy.idantityservice.entity.Otp;
import com.alrussy.idantityservice.repository.OtpRepository;
import com.alrussy.idantityservice.utils.EmailUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {

	private final  EmailUtil emailUtil;
	private final OtpRepository otpRepository;
	
	public Otp sendAndSaveOtp(String email) {
		String otp=genarateOtp();
		log.info(otp);
		//emailUtil.send(email,otp);
		Otp newOtp= Otp.builder().otpCode(otp).email(email).createdDate(LocalDateTime.now()).expireDate(LocalDateTime.now().plusMinutes(30)).isVerifiy(false).build();
		return otpRepository.save(newOtp);
		
	}
	
	public String activateAccount(OtpActivation otpActivation) {
		Optional<Otp> otpFind= otpRepository.findByOtpCode(otpActivation.otp());
		if(otpFind.isPresent()) {
			Otp otpSaved=otpFind.get();
			if(LocalDateTime.now().isBefore(otpSaved.getExpireDate()) && otpActivation.email().equals(otpSaved.getEmail())) {
				otpSaved.setIsVerifiy(true);
				otpRepository.save(otpSaved);
				return "verifiy is successfuly...!";
			}
			else if(LocalDateTime.now().isBefore(otpSaved.getExpireDate())){
				String otpCode=genarateOtp();
				
				log.info(otpCode);
				//emailUtil.send(email,otpCode);
				throw new RuntimeException(" No Activate : OTP Code has expired ");
			}
			else {
				throw new RuntimeException(" No Activate : OTP Code Uncorrect");				
			}
				
		}
		else
			throw new RuntimeException("No Regsitertion");
		
	}

	private String genarateOtp() {
		int otpRandum=new SecureRandom().nextInt(100000,999999);
		return String.valueOf(otpRandum);
	}
	
	

}

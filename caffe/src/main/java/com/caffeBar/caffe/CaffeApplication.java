package com.caffeBar.caffe;

import com.caffeBar.caffe.model.CaffeBarAdmin;
import com.caffeBar.caffe.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaffeApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CaffeApplication.class, args);
	}

	@PostConstruct
	public void init() {
		CaffeBarAdmin admin = new CaffeBarAdmin();
		admin.setUsername("admin");
		admin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
		userRepository.save(admin);
	}
}

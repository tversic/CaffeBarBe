package com.caffeBar.caffe;

import com.caffeBar.caffe.model.CaffeBarAdmin;
import com.caffeBar.caffe.model.Post;
import com.caffeBar.caffe.repo.PostRepository;
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
	@Autowired
	PostRepository postRepo;

	public static void main(String[] args) {
		SpringApplication.run(CaffeApplication.class, args);
	}

	@PostConstruct
	public void init() {
		CaffeBarAdmin admin = new CaffeBarAdmin();
		admin.setUsername("admin");
		admin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
		userRepository.save(admin);

		Post post = new Post();
		post.setPostTitle("Article 2");
		post.setPostContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.");
		postRepo.save(post);
	}
}

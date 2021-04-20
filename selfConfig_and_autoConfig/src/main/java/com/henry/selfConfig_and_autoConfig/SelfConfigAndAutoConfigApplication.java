package com.henry.selfConfig_and_autoConfig;

import com.henry.selfConfig_and_autoConfig.config.AuthorSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SelfConfigAndAutoConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfConfigAndAutoConfigApplication.class, args);

		// 关闭启动时的banner图
//		SpringApplication app = new SpringApplication(SelfConfigAndAutoConfigApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//
//		app.run(args);
	}

	@Value("${book.author}")
	private String bookAuthor;

	@Value("${book.name}")
	private String bookName;



	@GetMapping("/")
	public String hello() {
		return "book name is: " + bookName + ", and book author is: " + bookAuthor;
	}

	@Autowired
	private AuthorSetting authorSetting;

	@GetMapping("/authorBrief")
	public String author() {
		return "author name is: " + authorSetting.getName() + ", author's age is: " + authorSetting.getAge();
	}

}

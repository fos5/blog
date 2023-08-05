package dev.festus.blog;

import dev.festus.blog.appUser.UserRole;
import dev.festus.blog.security.auth.AuthenticationRequest;
import dev.festus.blog.security.auth.AuthenticationService;
import dev.festus.blog.security.auth.registration.RegistrationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//	@Bean
	//	CommandLineRunner commandLineRunner(AuthenticationService service){
	//		return args ->{
	//			RegistrationRequest user = RegistrationRequest.builder()
	//					.firstName("Jaaja")
	//					.email("jaajaq@gmail.com")
	//					.lastName("Oshin")
	//					.role(UserRole.USER)
	//					.password("we")
	//					.build();
	//			service.register(user);
	//			AuthenticationRequest request = AuthenticationRequest.builder()
	//					.email("jaajaq@gmail.com")
	//					.password("we")
	//					.build();
	//			service.authenticate(request);
	//
	//		};
	//	}

//	@Bean
//	CommandLineRunner commandLineRunner1(BlogPostServiceImpl service){
//		return args -> {
//			BlogPostRequest request = BlogPostRequest.builder()
//					.title("Akoko")
//					.blog("ipsom lorem ajfua jhaefj ajfan're")
//					.date("today")
//					.build();
//			service.createBlogPost(request);
//		};
//	}

}

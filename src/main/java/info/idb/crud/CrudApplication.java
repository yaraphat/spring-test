package info.idb.crud;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.idb.crud.entity.Student;
import info.idb.crud.repository.StudentRepository;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CrudApplication.class, args);
		// StudentRepository studentRepository = context.getBean(StudentRepository.class);

		// for (int i = 0; i < Integer.MAX_VALUE; i++) {
		// 	try {
		// 		Student student = new Student("Student " + i, i + "", "address " + i, "5", null, new ArrayList<>(),
		// 				null);
		// 		studentRepository.save(student);
		// 	} catch (Exception e) {
		// 	}
		// }

	}

	@Bean
	public ObjectMapper ObjectMapper() {
		return new ObjectMapper();
	}

}

package com.springbootfirebase.application;

import com.springbootfirebase.configuration.FirabaseConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)  {
		SpringApplication.run(DemoApplication.class, args);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(FirabaseConnect.class);
		ctx.refresh();
		ctx.close();
	}

}

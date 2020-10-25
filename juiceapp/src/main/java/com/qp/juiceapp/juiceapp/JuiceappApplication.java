package com.qp.juiceapp.juiceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JuiceappApplication implements CommandLineRunner {
	
	@Autowired
	private JuiceBar juiceBar;

	public static void main(String[] args) {
		SpringApplication.run(JuiceappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		juiceBar.start(args[0], args[1]);
		log.info("Juicer Done with work");
	}

}

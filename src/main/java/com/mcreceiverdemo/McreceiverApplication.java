package com.mcreceiverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.mcreceiverdemo.exceptions"})
public class McreceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(McreceiverApplication.class, args);
	}
}

package com.hxd.fsystemback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.hxd.fsystemback.entity")

public class FsystemBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsystemBackApplication.class, args);
	}

}

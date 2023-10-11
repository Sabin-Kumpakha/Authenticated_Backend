package com.reyzotech;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reyzotech.models.ApplicationUser;
import com.reyzotech.models.Role;
import com.reyzotech.repository.RoleRepository;
import com.reyzotech.repository.UserRepository;

@SpringBootApplication
public class AuthenticatedBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticatedBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {

		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent())
				return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role doctorRole = roleRepository.save(new Role("DOCTOR"));
			roleRepository.save(new Role("USER"));

			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(adminRole);

			Set<Role> doctorRoles = new HashSet<>();
			doctorRoles.add(doctorRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), adminRoles);
			ApplicationUser doctor = new ApplicationUser(2, "doctor",
					passwordEncoder.encode("password"), doctorRoles);

			userRepository.save(admin);
			userRepository.save(doctor);
		};
	}
}

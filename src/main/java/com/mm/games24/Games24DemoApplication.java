package com.mm.games24;

import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mm.games24.config.AppConstants;
import com.mm.games24.entities.Role;
import com.mm.games24.repository.RoleRepo;

@SpringBootApplication
public class Games24DemoApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(Games24DemoApplication.class, args);
	}
	
	///Model Mapper
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	////
	@Override
	public void run(String... args) throws Exception {
		//
		
		try {
			///Set ADMIN Role
			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");
			
			///Set NORMAL Role
			Role role2 = new Role();
			role2.setRoleId(AppConstants.NORMAL_USER);
			role2.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role1, role2);
			
			List<Role> result = roleRepo.saveAll(roles);
			
			for (int i = 0; i < result.size(); i++) {
				
				System.out.println(result.get(i).getName());
			}
			
//			result.forEach(r->{
//				System.out.println(r.getName());
//			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

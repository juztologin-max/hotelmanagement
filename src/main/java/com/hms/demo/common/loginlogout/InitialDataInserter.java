package com.hms.demo.common.loginlogout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataInserter {
	@Bean
	CommandLineRunner fillRolesDatabase(UserDetailsRepo urepo, UserRolesRepo rrepo, PasswordEncoder enc)
			throws Exception {
		return (args) -> {
			if (!rrepo.existsByRole(UserRolesEnum.ROLE_ADMIN)) {
				rrepo.save(new UserRoles(UserRolesEnum.ROLE_ADMIN));
				rrepo.save(new UserRoles(UserRolesEnum.ROLE_CUSTOMER));
				rrepo.save(new UserRoles(UserRolesEnum.ROLE_STAFF));
			}
			if (!urepo.existsByUsername("admin")) {
				LoginUser admin = new LoginUser();
				admin.setUsername("admin");
				admin.setPassword(enc.encode("pass"));
				admin.setEnabled(true);
				admin.getRoles().add(rrepo.getByRole(UserRolesEnum.ROLE_ADMIN)
						.orElseThrow(() -> new Exception("ROLE_ADMIN missing")));
				urepo.save(admin);

			}
		};
	}

}

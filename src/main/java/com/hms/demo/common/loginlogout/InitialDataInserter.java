package com.hms.demo.common.loginlogout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataInserter {
	@Value("${initialdata.insertdefaults}")
	private boolean insertDefaults;

	@Bean
	CommandLineRunner fillRolesDatabase(UserDetailsRepo urepo, UserRolesRepo rrepo, PasswordEncoder enc)
			throws Exception {
		return (args) -> {
			if (insertDefaults) {
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
					LoginUser cust = new LoginUser();
					cust.setUsername("customer");
					cust.setPassword(enc.encode("pass"));
					cust.setEnabled(true);
					cust.getRoles().add(rrepo.getByRole(UserRolesEnum.ROLE_CUSTOMER)
							.orElseThrow(() -> new Exception("ROLE_CUSTOMER missing")));
					urepo.save(cust);
					LoginUser staff = new LoginUser();
					staff.setUsername("staff");
					staff.setPassword(enc.encode("pass"));
					staff.setEnabled(true);
					staff.getRoles().add(rrepo.getByRole(UserRolesEnum.ROLE_STAFF)
							.orElseThrow(() -> new Exception("ROLE_STAFF missing")));
					urepo.save(staff);

				}
			}

		};
	}

}

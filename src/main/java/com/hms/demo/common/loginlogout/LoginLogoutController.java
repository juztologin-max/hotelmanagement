package com.hms.demo.common.loginlogout;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {
	@GetMapping("/login")
	String getLoginHandler() {
		return "login";
	}

	@GetMapping("/frontpage")
	String getFrontPageHandler(@AuthenticationPrincipal LoginUser pri) throws Exception {
		List<String> auths = pri.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		if (auths.contains(UserRolesEnum.ROLE_ADMIN.name())) {
			return "admin/frontpage";
		}

		throw new AccessDeniedException(getLoginHandler());

	}
	
	

}

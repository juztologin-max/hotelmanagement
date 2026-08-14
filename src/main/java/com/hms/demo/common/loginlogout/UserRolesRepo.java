package com.hms.demo.common.loginlogout;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepo extends JpaRepository<UserRoles, Long> {
	public Optional<UserRoles> findById(Long id);

	public boolean existsByRole(UserRolesEnum role);

	public Optional<UserRoles> getByRole(UserRolesEnum role);
}

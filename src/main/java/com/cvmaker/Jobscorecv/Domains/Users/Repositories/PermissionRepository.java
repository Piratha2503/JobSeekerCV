
package com.cvmaker.Jobscorecv.Domains.Users.Repositories;

import com.cvmaker.Jobscorecv.Domains.Users.Entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}

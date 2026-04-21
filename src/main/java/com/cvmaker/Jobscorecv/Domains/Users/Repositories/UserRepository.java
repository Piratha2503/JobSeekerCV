
package com.cvmaker.Jobscorecv.Domains.Users.Repositories;

import com.cvmaker.Jobscorecv.Domains.Users.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

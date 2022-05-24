package com.pifrans.multidatabasedynamic.repositories.users;

import com.pifrans.multidatabasedynamic.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

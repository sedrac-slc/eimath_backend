package com.ei.math.repository;

import com.ei.math.entity.UserPeople;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPeople, UUID>{
    UserPeople findByUsername(String username);
    UserPeople findByEmail(String email);
}

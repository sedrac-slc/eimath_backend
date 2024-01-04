package com.ei.math.repository;
        
import com.ei.math.entity.Group;
import com.ei.math.entity.UserPeople;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,UUID>{
    Page<Group> findAllByUserPeople(Pageable var1, UserPeople people);
}

package com.ei.math.repository;
        
import com.ei.math.entity.Convit;
import com.ei.math.entity.UserPeople;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvitRepository extends JpaRepository<Convit,UUID>{
    
    @Query("SELECT c FROM Convit c WHERE c.userPeople = :person")
    Page<Convit> findConvitsByPerson(Pageable pageable,UserPeople person);
}

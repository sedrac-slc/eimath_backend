package com.ei.math.repository;
        
import com.ei.math.entity.EmailHistory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistory,UUID>{
}

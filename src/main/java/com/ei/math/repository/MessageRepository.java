package com.ei.math.repository;
        
import com.ei.math.entity.Group;
import com.ei.math.entity.Message;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,UUID>{
    @Query("SELECT m FROM Member m WHERE m.group = :group")
    Page<Message> findMessagesByGroup(Pageable pageable,Group group);
}

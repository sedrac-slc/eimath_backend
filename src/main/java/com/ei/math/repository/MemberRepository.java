package com.ei.math.repository;
        
import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,UUID>{
    @Query("SELECT m FROM Member m WHERE m.group = :group")
    Page<Member> findMembersByGroup(Pageable pageable,Group group);
}

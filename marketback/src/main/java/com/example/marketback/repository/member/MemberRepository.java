package com.example.marketback.repository.member;

import com.example.marketback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id = :username")
    Member findByMemberName(String username);
}

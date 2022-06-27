package com.example.marketback.repository.member;

import com.example.marketback.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id = :id")
    Member findByMemberId(@Param("id") String id);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> checkDubById(@Param("id") String id);

    @Query("select m from Member m where m.name = :name and m.phoneNumber = :phoneNumber")
    Optional<Member> checkMember(@Param("name")String name, @Param("phoneNumber") String phoneNumber);

    @Query("select m from Member m where m.id = :id and m.name = :name and m.phoneNumber = :phoneNumber")
    Optional<Member> findPwById(@Param("id")String id, @Param("name")String name, @Param("phoneNumber")String phoneNumber);

    @Query("select m from Member m where m.id = :email")
    Optional<Member> findByEmail(@Param("email")String email);

}

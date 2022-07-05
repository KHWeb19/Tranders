package com.example.marketback.repository.manager;

import com.example.marketback.entity.manager.BanMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanMemberRepository extends JpaRepository<BanMember, Long> {
}

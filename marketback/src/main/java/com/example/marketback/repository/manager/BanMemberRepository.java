package com.example.marketback.repository.manager;

import com.example.marketback.entity.manager.BanMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BanMemberRepository extends JpaRepository<BanMember, Long> {

}

package com.example.marketback.service.member;

public interface BossService {
    boolean checkBossMember(String id);

    void register(String fileSrc, String id, String name, String region, String category);
}

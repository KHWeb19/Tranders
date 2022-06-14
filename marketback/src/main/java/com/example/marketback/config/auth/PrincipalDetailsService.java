package com.example.marketback.config.auth;

import com.example.marketback.entity.Member;
import com.example.marketback.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(id);

        if(member == null){
            log.error("NONE MEMBER");
            throw new UsernameNotFoundException("NONE");
        } else {
            log.info("MEMBER : {}", id);
        }

        return new PrincipalDetails(member);
    }

}

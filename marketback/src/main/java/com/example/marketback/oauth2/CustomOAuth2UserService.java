package com.example.marketback.oauth2;

import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.member.MemberRole;
import com.example.marketback.entity.member.ProviderType;
import com.example.marketback.oauth2.UserInfo.OAuth2UserInfo;
import com.example.marketback.oauth2.UserInfo.OAuth2UserInfoFactory;
import com.example.marketback.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("attributes" + super.loadUser(userRequest).getAttributes());
        OAuth2User member = super.loadUser(userRequest);
        //여기서 attriutes를 찍어보면 모두 각기 다른 이름으로 데이터가 들어오는 것을 확인할 수 있다.
        try{
            return process(userRequest, member);
        } catch (Exception ex){
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());

        Member savedUser = memberRepository.findByMemberId(userInfo.getEmail());

        if (savedUser != null) {

            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return new PrincipalDetails(savedUser, user.getAttributes());
    }

    private Member createUser(OAuth2UserInfo userInfo, ProviderType providerType) {

        Member member = Member.builder()
                            .name(userInfo.getName())
                            .id(userInfo.getEmail())
                            .password("")
                            .roles(MemberRole.ROLE_USER)
                            .phoneNumber(providerType.name())
                            .region(providerType.name())
                            .providerType(providerType).build();

        return memberRepository.save(member);
    }

    private void updateUser(Member member, OAuth2UserInfo userInfo) {
        if (userInfo.getLastName() != null && !member.getName().equals(userInfo.getName())) {
            member.setName(userInfo.getName());
        }
    }
}

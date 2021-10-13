package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.Repository.MemberRepository;
import com.project.drops_musicmarket.DTO.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean signup(MemberDto user){

        String id = user.getMemberId();
        String pw = user.getMemberPassword();
        String nickname = user.getMemberNickname();
        String email = user.getMemberEmail();

        if (id == null || id.length() == 0) {
            return false;
        } else if (pw == null || pw.length() == 0) {
            return false;
        } else if (email == null || email.length() == 0) {
            return false;
        } else {
            pw = bCryptPasswordEncoder.encode(pw);
            user.setMemberPassword(pw);

            memberRepository.save(user.toEntity());

            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public MemberEntity loginUser(MemberDto user){
        String id = user.getMemberId();
        String pw = user.getMemberPassword();

        MemberEntity checkUser = memberRepository.getById(id);
        if (checkUser != null && bCryptPasswordEncoder.matches(pw, checkUser.getMemberPassword())){
            return checkUser;
        } else{
            return null;
        }

    }



}

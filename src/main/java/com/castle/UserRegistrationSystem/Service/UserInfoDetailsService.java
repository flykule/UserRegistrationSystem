package com.castle.UserRegistrationSystem.Service;

import com.castle.UserRegistrationSystem.dto.UserInfo;
import com.castle.UserRegistrationSystem.repository.UserInfoJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UserInfoJpaRepository mUserInfoJpaRepository;

    @Autowired
    public UserInfoDetailsService(UserInfoJpaRepository mUserInfoJpaRepository) {
        this.mUserInfoJpaRepository = mUserInfoJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfo user = mUserInfoJpaRepository.findByUserName(name);
        if (user==null) {
            throw new UsernameNotFoundException(
                    "Oops! user not found with user-name: "+name);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new User(
                user.getUserName(),
                passwordEncoder.encode(user.getPassword()),
                getAuthorities(user)
        );
    }

    private Collection<GrantedAuthority> getAuthorities(UserInfo userInfo){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList = AuthorityUtils.createAuthorityList(userInfo.getRole());
        return authorityList;
    }
}

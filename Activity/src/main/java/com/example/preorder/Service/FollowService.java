package com.example.preorder.Service;

import com.example.preorder.Entity.Follow;
import com.example.preorder.Entity.Member;
import com.example.preorder.JWT.JwtTokenProvider;
import com.example.preorder.Repository.FollowRepository;
import com.example.preorder.Repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberLoginRepository memberLoginRepository;

    @Transactional
    public void follow(String token, String followingEmail){
        String email = jwtTokenProvider.getAuthentication(token).getName();

        Member follower = memberLoginRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("cannot find user"));


        Member following = memberLoginRepository.findByEmail(followingEmail)
                .orElseThrow(() -> new IllegalArgumentException("not exist following user"));

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        followRepository.save(follow);



    }

    public void unfollow(Long followId) {
        followRepository.deleteById(followId);
    }




}
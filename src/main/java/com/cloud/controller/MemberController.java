package com.cloud.controller;

import com.cloud.repository.MemberRepository;
import com.cloud.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }


    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀원이 없습니다."));
    }
}
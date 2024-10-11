package com.example.nestco.controller;


import com.example.nestco.config.local.LocalUserDetails;
import com.example.nestco.dao.repository.MemberRepository;
import com.example.nestco.models.dto.LikeResponseDTO;
import com.example.nestco.models.entity.Member;
import com.example.nestco.services.BaseService;
import com.example.nestco.services.ItemLikeService;
import com.example.nestco.services.ItemThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class myPageController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BaseService baseService;

    @Autowired
    private ItemThumbnailService itemThumbnailService;

    @Autowired
    private ItemLikeService itemLikeService;

    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal LocalUserDetails localUserDetails, Model model) {
        String userId = null;
        baseService.headerAttributes(model);

        // 로그인 사용자 처리
        if (localUserDetails != null) {
            userId = localUserDetails.getMemberEntity().getUserId(); // 로컬 로그인 사용자 ID
        } else {
            return "redirect:/loginForm"; // 로그인하지 않은 경우
        }
//        System.out.println("마이페이지 뷰 표시");
        // 사용자 정보를 데이터베이스에서 조회
        Member member = memberRepository.findByUserId(userId);
        model.addAttribute("member", member);

        List<LikeResponseDTO> likeItems = itemLikeService.getUserLikes(userId);
        model.addAttribute("likeItems", likeItems);

        System.out.println("get userDetails userId : " + userId);
        System.out.println("get userDetails info : " + localUserDetails);
        System.out.println("printing member info : " + member);
        return "myPages/myPage"; // 마이 페이지 뷰 반환
    }

    @GetMapping("/updateMyPage")
    public String updateMyPage(@AuthenticationPrincipal LocalUserDetails localUserDetails, Model model) {
        String userId = null;

        // 로그인 사용자 처리
        if (localUserDetails != null) {
            userId = localUserDetails.getMemberEntity().getUserId(); // 로컬 로그인 사용자 ID
        } else {
            return "redirect:/loginForm"; // 로그인하지 않은 경우
        }
        Member member = memberRepository.findByUserId(userId);
        model.addAttribute("member", member);

        return "myPages/updateMyPage";
    }
}

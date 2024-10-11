package com.example.nestco.controller.admin;

import com.example.nestco.dao.repository.NoticeBoardRepository;
import com.example.nestco.models.entity.Board;
import com.example.nestco.models.entity.NoticeBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/notices")
public class AdminNoticeBoardController {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    // 조회
    @GetMapping
    public String notices(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable newPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());

        Page<NoticeBoard> noticeBoardList = noticeBoardRepository.findAll(newPageable);

        int totalPages = noticeBoardList.getTotalPages();
        int currentPage = newPageable.getPageNumber() + 1;

        int start = Math.max(1, currentPage - 2);
        int end = Math.min(totalPages, currentPage + 2);

        List<Integer> pageNumbers = new ArrayList<>();
        for(int i = start; i <= end; i ++) {
            pageNumbers.add(i);
        }

        int firstPage = 1;
        int lastPage = totalPages;

        model.addAttribute("notices", noticeBoardList);
        model.addAttribute("prev", Math.max(1, currentPage-1));
        model.addAttribute("next", Math.min(totalPages, currentPage + 1));
        model.addAttribute("hasNext", noticeBoardList.hasNext());
        model.addAttribute("hasPrev", noticeBoardList.hasPrevious());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);


        return "admin/noticesManagement";
    }

    // 삭제
    @PostMapping("/delete/{id}")
    public String noticesDelete(@PathVariable("id") Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).orElse(null);

        noticeBoardRepository.delete(noticeBoard);

        return "redirect:/admin/notices";
    }
}

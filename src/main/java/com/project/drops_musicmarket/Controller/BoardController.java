package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Entity.CommunityEntity;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.CommunityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    private final CommunityService communityService;

    public BoardController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/community")
    public String community(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
//        List<CommunityDto> boardList = communityService.getBoardList();
        List<CommunityDto> boardList = communityService.getBoardList(pageNum);
        Integer[] pageList = communityService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "pages/board/list";
    }

    @GetMapping("/community/search")
    public String searchArticle(@RequestParam("searchValue") String searchValue, Model model) {


        return "pages/board/list";
    }

    @GetMapping("/community/read")
    public String readArticle(@RequestParam("communityNum") long communityNum, Model model) {
        CommunityDto readArticle = communityService.getArticle(communityNum);

        if (readArticle != null) {
            model.addAttribute("article", readArticle);
//            List<CommunityEntity> commentList = communityService.findComment(readArticle.getCommunity_oriNum());
//
//            if (commentList != null) {
//                model.addAttribute("comment", commentList);
//            }
            return "pages/board/readContent";
        } else {
            return "redirect:/community";
        }
    }

    @GetMapping("/community/write")
    public String writeArticle(HttpServletRequest request) {
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");

        if (user != null) {
            return "pages/board/write";
        } else {
            return "redirect:/community";
        }
    }

    @PostMapping("/community/write")
    public String writeArticle(HttpServletRequest request, CommunityDto article) {
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        article.setCommunityMemberId(user.getMemberId());
        article.setCommunityNickname(user.getMemberNickname());

        boolean articleCheck = communityService.writeArticle(article);

        if (articleCheck == true) {
            return "redirect:/community";
        } else {
            return "redirect:/community/write";
        }
    }

    @GetMapping("/community/edit")
    public String editArticle(@RequestParam("communityNum") long communityNum, Model model, HttpServletRequest request) {
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        String userId = user.getMemberId();

        if (user == null) {
            return "redirect:/community/read?communityNum=" + communityNum;
        }

        CommunityDto article = communityService.getArticle(communityNum);
        model.addAttribute("article", article);

        String writerId = article.getCommunityMemberId();

        if (userId.equals(writerId)) {
            return "pages/board/edit";
        } else {
            return "redirect:/community/read?communityNum=" + communityNum;
        }
    }

    @PostMapping("/community/edit")
    public String editArticle(@RequestParam("communityNum") long communityNum, HttpServletRequest request, CommunityDto editedArticle) {
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        String userId = user.getMemberId();

        if (user == null) {
            return "redirect:/community/read?communityNum=" + communityNum;
        }

        editedArticle.setCommunityMemberId(user.getMemberId());
        editedArticle.setCommunityNickname(user.getMemberNickname());
        boolean article = communityService.editArticle(communityNum, editedArticle);

        return "redirect:/community";
    }

    @GetMapping("/community/delete")
    public String deleteArticle(@RequestParam("communityNum") long communityNum, HttpServletRequest request){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        String userId = user.getMemberId();

        if (user == null) {
            return "redirect:/community/read?communityNum=" + communityNum;
        }

        boolean deleteArticle = communityService.deleteArticle(communityNum, userId);
        return "redirect:/community";

    }

}

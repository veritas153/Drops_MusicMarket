package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.CommunityDto;
//import com.project.drops_musicmarket.service.CommunityServiceImpl;
import com.project.drops_musicmarket.Entity.CommunityEntity;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    private final CommunityService communityService;

    public BoardController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/community")
    public String community(Model model) {
        List<CommunityEntity> boardList = communityService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "pages/board/list";
    }

    @GetMapping("/community/search")
    public String searchArticle(@RequestParam("searchValue") String searchValue, Model model){


        return "pages/board/list";
    }


    @GetMapping("/community/read")
    public String readArticle(@RequestParam("community_oriNum") long community_oriNum, Model model) {
        CommunityEntity readArticle = communityService.getArticle(community_oriNum);

        if (readArticle != null){
            model.addAttribute("article", readArticle);
            List<CommunityEntity> commentList = communityService.findComment(readArticle.getCommunity_oriNum());

            if (commentList != null){
                model.addAttribute("comment", commentList);
            }
            return "pages/board/readContent";
        } else {
            return "redirect:/community";
        }

    }

    @GetMapping("/community/write")
    public String writeArticle(HttpServletRequest request){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");

        if (user != null) {
            return "pages/board/write";
        } else {
            return "redirect:/community";
        }
    }

    @PostMapping("/community/write")
    public String writeArticle(HttpServletRequest request, CommunityDto article){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        article.setCommunity_member_id(user.getMember_id());

        System.out.println(article);

        boolean articleCheck = communityService.writeArticle(article);

        if (articleCheck == true) {
            return "pages/board/list";
        }else {
            return "redirect:/community/write";
        }
    }

    @GetMapping("/community/edit")
    public String editArticle(@PathVariable("community_oriNum") long commnunity_oriNum, Model model,HttpServletRequest request){
//        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
//
//        if (user == null) {
//            return "pages/board/list";
//        }
//

        CommunityEntity article = communityService.getArticle(commnunity_oriNum);
        model.addAttribute("article", article);

        return "pages/board/write";
    }

    @PostMapping("/community/edit")
    public String editArticle(){

        return "page/board/list";

    }
}

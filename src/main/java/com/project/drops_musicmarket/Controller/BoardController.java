package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.DTO.MemberDto;
//import com.project.drops_musicmarket.service.CommunityServiceImpl;
import com.project.drops_musicmarket.service.CommunityService;
import com.project.drops_musicmarket.service.CommunityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private final CommunityService communityService;

    public BoardController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/community")
    public String community() {
//        ArrayList<CommunityDto> boardList = CommunityRepository.findAll().stream().map(CommunityDto::new).collect(Collectors.toList());



        return "pages/board/list";
    }

    @GetMapping("/community/read")
    public String readArticle(CommunityDto article) {

        return "pages/board/readContent";
    }

    @GetMapping("/community/write")
    public String writeArticle(){
        return "pages/board/write";
    }

    @PostMapping("/community/write")
    public String writeArticle(MemberDto user, CommunityDto article){

        article.setCommunity_member_id(user.getMember_id());
        communityService.writeArticle(article);

        return "pages/board/write";
    }

}

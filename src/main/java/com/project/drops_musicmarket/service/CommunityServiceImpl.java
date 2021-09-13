package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Entity.CommunityEntity;
import com.project.drops_musicmarket.Repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    public List<CommunityEntity> getBoardList() {

        List<CommunityEntity> boardList = communityRepository.findAll();

        return boardList;
    }

    @Override
    public boolean writeArticle(CommunityDto article) {

        CommunityDto communityDto = new CommunityDto();

        String id = article.getCommunity_member_id();
        String category = article.getCommunity_category();
        String title = article.getCommunity_title();
        String content = article.getCommunity_content();
//        File track = article.getCommunity_track(); I will work on file upload issue after finishing posting article.

        if (id == null || id.length() == 0){
            return false;
        }
        else if (category == null || category == "#"){
            return false;
        }
        else if (title == null || title.length() <= 4){
            return false;
        }
        else if (content == null || content.length() <= 4){
            return false;
        }
//        if (category != "Song" && (track == null || track.value == '')) {
//            return false;
//        }
        else {
            CommunityEntity newPost = new CommunityEntity(id, category, title, content);
            System.out.println(newPost);

            System.out.println(newPost);
            communityRepository.save(newPost);
            return true;
        }
    }

}

package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    public ArrayList<CommunityDto> getBoardList() {
        return null;
    }

    @Override
    public void writeArticle(CommunityDto article) {

    }

}

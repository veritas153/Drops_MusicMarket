package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;

import java.util.ArrayList;

public interface CommunityService {

    public ArrayList<CommunityDto> getBoardList();

    void writeArticle(CommunityDto article);
}

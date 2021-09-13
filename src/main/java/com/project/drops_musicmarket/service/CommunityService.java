package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Entity.CommunityEntity;

import java.util.List;

public interface CommunityService {

    public List<CommunityEntity> getBoardList();

    boolean writeArticle(CommunityDto article);
}

package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;

import java.util.List;

public interface CommunityService {

    List<CommunityDto> getBoardList(Integer pageNum);

    boolean writeArticle(CommunityDto article);

    CommunityDto getArticle(long community_oriNum);

    boolean editArticle(long community_oriNum, CommunityDto editedArticle);

    boolean deleteArticle(long community_oriNum, String userId);

    Integer[] getPageList(Integer pageNum);

}

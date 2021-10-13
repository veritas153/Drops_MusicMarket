package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Entity.CommunityEntity;
import com.project.drops_musicmarket.Repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 10; // Page numbers inside of blocks
    private static final int PAGE_POST_COUNT = 5; // Article numbers per page.

    @Override
    public List<CommunityDto> getBoardList(Integer pageNum) {
        // Follow camelCase convention because it is JPA standard. If you use snakeCase for sort, the sort doesn't recognize properties name unless fixing default option!
        Page<CommunityEntity> board = communityRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "communityDate")));

        List<CommunityEntity> boardResult = board.getContent();
        List<CommunityDto> boardList = new ArrayList<>();

        for (CommunityEntity boardInfo : boardResult) {
            CommunityDto communityDto = CommunityDto.builder()
                    .communityNum(boardInfo.getCommunityNum())
                    .communityTitle(boardInfo.getCommunityTitle())
                    .communityCategory(boardInfo.getCommunityCategory())
                    .communityNickname(boardInfo.getCommunityNickname())
                    .communityContent((boardInfo.getCommunityContent()))
                    .communityLike((int) boardInfo.getCommunityLike())
                    .communityTrack(boardInfo.getCommunityTrack())
                    .communityDate(boardInfo.getCommunityDate())
                    .communityMemberId(boardInfo.getCommunityMemberId())
                    .build();

            boardList.add(communityDto);

        }

        return boardList;
    }

    @Transactional
    public Long getBoardCount(){
        return communityRepository.count();
    }

    @Override
    public Integer[] getPageList(Integer curPageNum) {
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        Integer totalLastPageNum = (int)(Math.ceil(postsTotalCount/PAGE_POST_COUNT));

        Integer[] pageList = new Integer[totalLastPageNum];

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum <= 3) ? 1 : curPageNum-2;

        for(int val = curPageNum, i = 0 ; val <= blockLastPageNum ; val++ , i++){
            pageList[i] = val;
        }

        return pageList;
    }

    @Override
    public boolean writeArticle(CommunityDto article) {
        String id = article.getCommunityMemberId();
        String category = article.getCommunityCategory();
        String title = article.getCommunityTitle();
        String content = article.getCommunityContent();
        File track = article.getCommunityTrack();

        if (id == null || id.length() == 0) {
            return false;
        } else if (category == null || category.equals("#")|| category.equals("")) {
            return false;
        } else if (title == null || title.length() <= 4) {
            return false;
        } else if (content == null || content.length() <= 4) {
            return false;
        }
        if (category != "Song" && track == null) {
            return false;
        } else {
            if (track != null){

            }

            communityRepository.save(article.toEntity());

            return true;
        }
    }

    @Override
    public CommunityDto getArticle(long communityNum) {
        Optional<CommunityEntity> findArticle = communityRepository.findById(communityNum);
        CommunityEntity findResult = findArticle.get();

        CommunityDto readPosting = CommunityDto.builder()
                .communityNum(findResult.getCommunityNum())
                .communityTitle(findResult.getCommunityTitle())
                .communityCategory(findResult.getCommunityCategory())
                .communityNickname(findResult.getCommunityNickname())
                .communityContent((findResult.getCommunityContent()))
                .communityLike((int) findResult.getCommunityLike())
                .communityTrack(findResult.getCommunityTrack())
                .communityDate(findResult.getCommunityDate())
                .communityMemberId(findResult.getCommunityMemberId())
                .build();

        if (readPosting != null) {
            return readPosting;
        } else {
            return null;
        }

    }

    @Override
    public boolean editArticle(long communityNum, CommunityDto editedArticle) {
        String id = editedArticle.getCommunityMemberId();
        String category = editedArticle.getCommunityCategory();
        String title = editedArticle.getCommunityTitle();
        String content = editedArticle.getCommunityContent();
        File track = editedArticle.getCommunityTrack();

        if (id == null || id.length() == 0) {
            return false;
        } else if (category == null || category.equals("#")|| category.equals("")) {
            return false;
        } else if (title == null || title.length() <= 4) {
            return false;
        } else if (content == null || content.length() <= 4) {
            return false;
        }
        if (category != "Song" && track == null) {
            return false;
        } else {
            Optional<CommunityEntity> findArticle = communityRepository.findById(communityNum);
            CommunityEntity foundInfo = findArticle.get();

            CommunityDto updateInfo = CommunityDto.builder()
                    .communityNum(foundInfo.getCommunityNum())
                    .communityTitle(foundInfo.getCommunityTitle())
                    .communityCategory(foundInfo.getCommunityCategory())
                    .communityNickname(foundInfo.getCommunityNickname())
                    .communityContent((foundInfo.getCommunityContent()))
                    .communityLike((int) foundInfo.getCommunityLike())
                    .communityTrack(foundInfo.getCommunityTrack())
                    .communityDate(foundInfo.getCommunityDate())
                    .communityMemberId(foundInfo.getCommunityMemberId())
                    .build();

            updateInfo.setCommunityCategory(category);
            updateInfo.setCommunityTitle(title);
            updateInfo.setCommunityContent(content);
            updateInfo.setCommunityTrack(track);

            communityRepository.save(updateInfo.toEntity());

            return true;
        }
    }

    @Override
    public boolean deleteArticle(long communityNum, String userId) {
        Optional<CommunityEntity> findArticle = communityRepository.findById(communityNum);
        CommunityEntity findResult = findArticle.get();

        CommunityDto readPosting = CommunityDto.builder()
                .communityNum(findResult.getCommunityNum())
                .communityTitle(findResult.getCommunityTitle())
                .communityCategory(findResult.getCommunityCategory())
                .communityNickname(findResult.getCommunityNickname())
                .communityContent((findResult.getCommunityContent()))
                .communityLike((int) findResult.getCommunityLike())
                .communityTrack(findResult.getCommunityTrack())
                .communityDate(findResult.getCommunityDate())
                .communityMemberId(findResult.getCommunityMemberId())
                .build();

        if (userId == readPosting.getCommunityMemberId() || !userId.isEmpty()){
            communityRepository.deleteById(communityNum);

            return true;
        } else {
            return false;
        }

    }

}

package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.CommunityEntity;
import lombok.*;

import java.io.File;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityDto {

    private long communityNum;
    private String communityTitle;
    private String communityNickname;
    private String communityCategory;
    private String communityContent;
    private File communityTrack;
    private int communityLike;
    private Date communityDate;
    private String communityMemberId;

    public CommunityEntity toEntity() {
        CommunityEntity communityEntity = CommunityEntity.builder()
                .communityTitle(communityTitle)
                .communityCategory(communityCategory)
                .communityNickname(communityNickname)
                .communityContent(communityContent)
                .communityTrack(communityTrack)
                .communityLike(communityLike)
//                .communityDate(communityDate)
                .communityMemberId(communityMemberId)
                .build();

        return communityEntity;
    }

    @Builder
    public CommunityDto(long communityNum, String communityCategory,
                        String communityNickname, String communityTitle, String communityContent, int communityLike,
                        File communityTrack, Date communityDate, String communityMemberId){

        this.communityNum = communityNum;
        this.communityCategory = communityCategory;
        this.communityMemberId = communityMemberId;
        this.communityNickname = communityNickname;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLike = communityLike;
        this.communityTrack = communityTrack;
        this.communityDate = communityDate;
    }


}

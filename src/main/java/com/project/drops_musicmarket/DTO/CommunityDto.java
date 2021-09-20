package com.project.drops_musicmarket.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityDto {

    private long community_oriNum;
    private Integer community_commentNum;
    private String community_title;
    private String community_category;
    private String community_content;
    private File community_track;
    private int community_like;
    private Date community_date;
    private String community_member_id;

    public CommunityDto(long community_oriNum, String community_member_id, String community_category, String community_title, int community_like, Integer community_commentNum, Date community_date){
        this.community_oriNum = community_oriNum;
        this.community_category = community_category;
        this.community_member_id = community_member_id;
        this.community_title = community_title;
        this.community_like = community_like;
        this.community_commentNum = community_commentNum;
        this.community_date = community_date;
    }

}

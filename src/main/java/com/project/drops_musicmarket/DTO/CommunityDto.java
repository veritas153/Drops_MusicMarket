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
    private int community_num;
    private int community_commentNum;
    private String community_title;
    private String community_category;
    private String community_content;
    private File community_track;
    private int community_like;
    private Date community_date;
    private String community_member_id;


}

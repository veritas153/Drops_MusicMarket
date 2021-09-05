package com.project.drops_musicmarket.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Getter
@Entity(name="community")
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long community_oriNum;

    @Column
    private int community_num;

    @Column(nullable = true)
    private int community_commentNum;

    @Column
    private String community_title;

    @Column
    private String community_category;

    @Column
    private String community_content;

    @Column
    private File community_track;

    @Column
    private int community_like;

    @Column
    private Date community_date;

    @JoinColumn(name="member_id")
    private String community_member_id;

}

package com.project.drops_musicmarket.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Getter
@Entity(name="community")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long community_oriNum;

    @Column
    private Integer community_commentNum; // int is primitive type so int or float type can't have null value.

    @Column(nullable = false)
    private String community_title;

    @Column(nullable = false)
    private String community_category;

    @Column(nullable = false)
    private String community_content;

    @Column
    private File community_track;

    @Column(nullable = false)
    private long community_like;

    @Column
    private Date community_date;

    @JoinColumn(name="member_id")
    private String community_member_id;

    @PreUpdate // This is for Date setting when someone posts something on board.
    void community_date(){
        this.community_date = new Date();
    }

    public CommunityEntity(String community_member_id, String community_category, String community_title, String community_content) {
        this.community_member_id = community_member_id;
        this.community_category = community_category;
        this.community_title = community_title;
        this.community_content = community_content;
    }




}

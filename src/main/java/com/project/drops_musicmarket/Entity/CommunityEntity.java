package com.project.drops_musicmarket.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Getter
@DynamicUpdate
@Entity(name="community")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityEntity {

    @Id
    @Column(name = "communityNum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long communityNum;

    @Column(name = "communityTitle", nullable = false)
    private String communityTitle;

    @Column(name = "communityCategory", nullable = false)
    private String communityCategory;

    @Column(name = "communityNickname", nullable = false)
    private String communityNickname;

    @Column(name = "communityContent", nullable = false)
    private String communityContent;

    @Column(name = "communityTrack")
    private File communityTrack;

    @Column(name = "communityLike", nullable = false)
    private long communityLike;

    @Column(name = "communityDate", updatable = false)
    private Date communityDate;

    @JoinColumn(name="communityMemberId")
    private String communityMemberId;

    @PrePersist // This is for Date setting when someone posts something on board.
    void communityDate(){
        this.communityDate = new Date();
    }

    @Builder
    public CommunityEntity(long communityNum, String communityCategory, String communityTitle,
                           String communityNickname, String communityContent, File communityTrack,
                           long communityLike, String communityMemberId) {

        this.communityNum = communityNum;
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityNickname =communityNickname;
        this.communityContent = communityContent;
//        this.communityTrack = communityTrack;
        this.communityDate = communityDate;
        this.communityMemberId = communityMemberId;

    }

}

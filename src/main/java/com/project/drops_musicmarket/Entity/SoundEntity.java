package com.project.drops_musicmarket.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity(name = "sound")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SoundEntity {

    @Id
    @Column(length=30)
    private String soundId;

    @Column(nullable = false)
    private String soundName;

    @Column(nullable = false)
    private String soundSeller;

    @Column(nullable = false)
    private String soundType;

    @Column(nullable = false)
    private String soundGenre;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String soundDescription;

    @Column(nullable = false)
    private String soundImage;

    @Column(nullable = false)
    private String soundFile;

    @Column(nullable = false)
    private BigDecimal soundPrice;

    @Column(nullable = false)
    private Date soundDate;

    @PrePersist
    void soundDate() {this.soundDate = new Date();}

    @Builder
    public SoundEntity(String soundId, String soundName, String soundSeller,
                       String soundType, String soundGenre, String soundDescription,
                       String soundImage, String soundFile, BigDecimal soundPrice,
                       Date soundDate){

        this.soundId = soundId;
        this.soundName = soundName;
        this.soundSeller = soundSeller;
        this.soundType = soundType;
        this.soundGenre = soundGenre;
        this.soundDescription = soundDescription;
        this.soundImage = soundImage;
        this.soundFile = soundFile;
        this.soundPrice = soundPrice;
        this.soundDate = soundDate;

    }

}

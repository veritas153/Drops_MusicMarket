package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.SoundEntity;
import lombok.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SoundDto {

    private String soundId;
    private String soundName;
    private String soundSeller;
    private String soundType;
    private String soundGenre;
    private String soundDescription;
    private String soundImage;
    private String soundFile;
    private BigDecimal soundPrice;
    private Date soundDate;

    public SoundEntity toEntity(){
        SoundEntity soundEntity = SoundEntity.builder()
                .soundId(soundId)
                .soundName(soundName)
                .soundSeller(soundSeller)
                .soundType(soundType)
                .soundGenre(soundGenre)
                .soundDescription(soundDescription)
                .soundImage(soundImage)
                .soundFile(soundFile)
                .soundPrice(soundPrice)
                .build();

        return soundEntity;
    }

    @Builder
    public SoundDto(String soundId, String soundName, String soundSeller,
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

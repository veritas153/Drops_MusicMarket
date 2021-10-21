package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.PluginEntity;
import com.project.drops_musicmarket.Entity.SoundEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PluginDto {

    private long pluginId;
    private String pluginName;
    private String pluginSeller;
    private String pluginType;
    private String pluginDescription;
    private String pluginImage;
    private String pluginFile;
    private BigDecimal pluginPrice;
    private Date pluginDate;

    public PluginEntity toEntity(){
        PluginEntity pluginEntity = PluginEntity.builder()
                .pluginName(pluginName)
                .pluginSeller(pluginSeller)
                .pluginType(pluginType)
                .pluginDescription(pluginDescription)
                .pluginImage(pluginImage)
                .pluginFile(pluginFile)
                .pluginPrice(pluginPrice)
                .build();

        return pluginEntity;
    }

    @Builder
    public PluginDto(long pluginId, String pluginName, String pluginSeller,
                    String pluginType, String pluginDescription, String pluginImage,
                     String pluginFile, BigDecimal pluginPrice, Date pluginDate){

        this.pluginId = pluginId;
        this.pluginName = pluginName;
        this.pluginSeller = pluginSeller;
        this.pluginType = pluginType;
        this.pluginDescription = pluginDescription;
        this.pluginImage = pluginImage;
        this.pluginFile = pluginFile;
        this.pluginPrice = pluginPrice;
        this.pluginDate = pluginDate;
    }

}

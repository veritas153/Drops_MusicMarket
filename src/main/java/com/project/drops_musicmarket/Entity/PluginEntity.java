package com.project.drops_musicmarket.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity(name = "plugin")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PluginEntity {

    @Id
    private long pluginId;

    @Column(nullable = false)
    private String pluginName;

    @Column(nullable = false)
    private String pluginSeller;

    @Column(nullable = false)
    private String pluginType;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String pluginDescription;

    @Column(nullable = false)
    private String pluginImage;

    @Column(nullable = false)
    private String pluginFile;

    @Column(nullable = false)
    private BigDecimal pluginPrice;

    @Column(nullable = false)
    private LocalDate pluginDate;

    @PrePersist
    void pluginDate() {
        this.pluginDate = LocalDate.now();
    }

    @Builder
    public PluginEntity(long pluginId, String pluginName, String pluginSeller,
                        String pluginType, String pluginDescription, String pluginImage,
                        String pluginFile, BigDecimal pluginPrice, LocalDate pluginDate) {

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
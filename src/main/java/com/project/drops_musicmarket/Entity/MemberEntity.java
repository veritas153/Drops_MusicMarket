package com.project.drops_musicmarket.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member") // The real name of table in MySQL
public class MemberEntity {

    @Id
    private String member_id;

    @Column(nullable = false, length = 255)
    private String member_password;

    @Column(nullable = false, length=50)
    private String member_name;

    @Column(nullable = false, length=20)
    private String member_nickname;

    @Column(nullable = false, unique = true, length = 50)
    private String member_email;

    @ColumnDefault("USER")
    private String member_level;

    @Column(nullable = true, length = 255)
    private String member_image;

    public MemberEntity(String member_id, String member_password, String member_name, String member_nickname, String member_email){
        this.member_id = member_id;
        this.member_password = member_password;
        this.member_name = member_name;
        this.member_nickname = member_nickname;
        this.member_email = member_email;

    }

}

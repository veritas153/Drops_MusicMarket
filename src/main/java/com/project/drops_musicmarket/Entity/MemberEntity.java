package com.project.drops_musicmarket.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.lang.reflect.Member;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member") // The real name of table in MySQL
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;

    @Column(nullable = false, length = 255)
    private String memberPassword;

    @Column(nullable = false, length=20)
    private String memberNickname;

    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;

    @Column(columnDefinition = "varchar(10) default 'USER'")
    private String memberLevel;

    @Column(nullable = true, length = 255)
    private String memberImage;


    @Builder
    public MemberEntity(String memberId, String memberPassword, String memberNickname,
                        String memberEmail, String memberLevel, String memberImage){
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.memberLevel = memberLevel;
        this.memberImage = memberImage;

    }

}

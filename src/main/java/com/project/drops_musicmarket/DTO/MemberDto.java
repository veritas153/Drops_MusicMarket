package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

   private String member_id;
   private String member_password;
   private String member_nickname;
   private String member_email;
   private String member_level;
   private String member_image;

   public MemberEntity toEntity(){
      MemberEntity memberEntity = MemberEntity.builder()
              .member_id(member_id)
              .member_password(member_password)
              .member_nickname(member_nickname)
              .member_email(member_email)
              .member_image(member_image)
              .build();

      return memberEntity;
   }

   @Builder
   public MemberDto(String member_id, String member_password, String member_nickname,
                    String member_email, String member_image){
         this.member_id = member_id;
         this.member_password = member_password;
         this.member_nickname = member_nickname;
         this.member_email = member_email;
         this.member_image = member_image;
   }

}

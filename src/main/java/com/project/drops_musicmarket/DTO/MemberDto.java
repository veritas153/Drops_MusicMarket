package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

   private String memberId;
   private String memberPassword;
   private String memberNickname;
   private String memberEmail;
   private String memberLevel;
   private String memberImage;

   public MemberEntity toEntity(){
      MemberEntity memberEntity = MemberEntity.builder()
              .memberId(memberId)
              .memberPassword(memberPassword)
              .memberNickname(memberNickname)
              .memberEmail(memberEmail)
              .memberImage(memberImage)
              .build();

      return memberEntity;
   }

   @Builder
   public MemberDto(String memberId, String memberPassword, String memberNickname,
                    String memberEmail, String memberImage){
         this.memberId = memberId;
         this.memberPassword = memberPassword;
         this.memberNickname = memberNickname;
         this.memberEmail = memberEmail;
         this.memberImage = memberImage;
   }

}

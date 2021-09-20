package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

   private String member_id;
   private String member_password;
   private String member_nickname;
   private String member_name;
   private String member_email;
   private String member_level;
   private String member_image;


}

package com.project.drops_musicmarket.DTO;

import com.project.drops_musicmarket.Entity.MemberEntity;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }

    public String getMember_nickname() {
        return member_nickname;
    }

    public void setMember_nickname(String member_nickname) {
        this.member_nickname = member_nickname;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_level() {
        return member_level;
    }

    public void setMember_level(String member_level) {
        this.member_level = member_level;
    }

    public String getMember_image() {
        return member_image;
    }

    public void setMember_image(String member_image) {
        this.member_image = member_image;
    }

}

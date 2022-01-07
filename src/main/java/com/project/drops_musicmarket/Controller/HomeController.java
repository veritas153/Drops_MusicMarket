package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.MemberDto;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final MemberService memberService; // I won't use @Autowired due to field injection issue.

    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){return "/pages/member/login";}

    @PostMapping("/login")
    public String loginPage(HttpServletRequest request, MemberDto user){
        HttpSession session = request.getSession();
        MemberEntity verifyUser = memberService.loginUser(user);

        if (verifyUser != null){
            session.setAttribute("user", verifyUser);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String signup(){
        return "/pages/member/signup";
    }

    @PostMapping("/signup")
    public String newMember(MemberDto user){

        if (!memberService.signup(user)){
            return "redirect:/signup";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/signout")
    public String signout(HttpServletRequest request){

        request.getSession().removeAttribute("user");
        return "redirect:/";

    }


}

package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.CartDto;
import com.project.drops_musicmarket.DTO.SoundDto;
import com.project.drops_musicmarket.Entity.CartEntity;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.CartService;
import com.project.drops_musicmarket.service.MemberService;
import com.project.drops_musicmarket.service.PluginService;
import com.project.drops_musicmarket.service.SoundService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CartController {

    private final MemberService memberService;
    private final CartService cartService;
    private final SoundService soundService;
    private final PluginService pluginService;

    public CartController(MemberService memberService, CartService cartService, SoundService soundService,
                          PluginService pluginService){
        this.memberService = memberService;
        this.cartService = cartService;
        this.soundService = soundService;
        this.pluginService = pluginService;

    }

    @GetMapping("/cart")
    public String openList(Model model, HttpServletRequest request, @RequestParam(value="page", defaultValue = "1") Integer pageNum){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String userId = user.getMemberId();
        List<CartDto> cartList = cartService.getCartList(userId, pageNum);
        model.addAttribute("cartList", cartList);

        return "pages/cart/cartList";

    }

}

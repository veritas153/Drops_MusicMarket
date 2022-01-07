package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.SoundDto;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.CartService;
import com.project.drops_musicmarket.service.SoundService;
import com.project.drops_musicmarket.service.SoundServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SoundController {

    public final SoundService soundService;
    public final CartService cartService;

    public SoundController(SoundService soundService, CartService cartService){
        this.soundService = soundService;
        this.cartService = cartService;
    }

    @GetMapping("/sounds")
    public String soundList(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum){

        List<SoundDto> sampleList = soundService.getSampleList(pageNum);
        Integer[] pageList = soundService.getPageList(pageNum);

        model.addAttribute("sampleList", sampleList);
        model.addAttribute("pageList", pageList);

        return "/pages/sound/list";
    }

    @GetMapping("/sounds/add")
    public String addSample(HttpServletRequest request){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        String checkAuth = user.getMemberLevel();

        if (checkAuth.equals("ADMIN")){
            return "/pages/sound/add";
        }

        return "redirect:/sounds";

    }

    @PostMapping("/sounds/add")
    public String addSample(HttpServletRequest request, SoundDto sampleInfo,
                            @RequestParam("sampleValue") MultipartFile sampleValue, @RequestParam("imageValue") MultipartFile imageValue) throws Exception{
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");
        String checkAuth = user.getMemberLevel();


        if (!checkAuth.equals("ADMIN")){
            return "redirect:/sounds";
        }

        boolean checkSample = soundService.submitSample(sampleInfo, sampleValue, imageValue);

        if (checkSample == true){
            return "redirect:/sounds";
        } else {
            return "redirect:/sounds/add";
        }

    }

    @GetMapping("/sounds/info")
    public String sampleInfo(@RequestParam("soundId") String soundId, Model model){

        SoundDto soundInfo = soundService.getSoundInfo(soundId);
        model.addAttribute("soundInfo", soundInfo);


        return "/pages/sound/sampleInfo";
    }

    @GetMapping("/store/sounds")
    public String addCart(@RequestParam("soundId") String productId, HttpServletRequest request){
        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String userId = user.getMemberId();

        SoundDto detectSound = soundService.getSoundInfo(productId);
        String checkSoundId = detectSound.getSoundId();

        if (!checkSoundId.isEmpty()){
            cartService.addProduct(detectSound, userId);
            return "/sounds/info?" + productId;
        }

        return "";
    }







}

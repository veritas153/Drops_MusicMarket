package com.project.drops_musicmarket.Controller;

import com.project.drops_musicmarket.DTO.PluginDto;
import com.project.drops_musicmarket.Entity.MemberEntity;
import com.project.drops_musicmarket.service.PluginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PluginController {

    private final PluginService pluginService;

    public PluginController(PluginService pluginService) {
        this.pluginService = pluginService;
    }

    @GetMapping("/plugins")
    public String pluginList(@RequestParam(value="page", defaultValue = "1") Integer pageNum, Model model){

        List<PluginDto> listInfo = pluginService.getList(pageNum);
        Integer[] pageList = pluginService.getPageList(pageNum);

        model.addAttribute("pluginInfo", listInfo);
        model.addAttribute("pageNum", pageList);

        return "/pages/plugin/list";
    }

    @GetMapping("/plugins/add")
    public String addPlugin(HttpServletRequest request){

        MemberEntity user = (MemberEntity) request.getSession().getAttribute("user");

        if (user != null && user.getMemberLevel() == "ADMIN") {
            return "/pages/plugin/add";
        } else {
            return "redirect:/plugins";
        }

    }



}

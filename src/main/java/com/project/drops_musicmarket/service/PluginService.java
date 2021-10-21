package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.PluginDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PluginService {
    List<PluginDto> getList(Integer pageNum);

    Integer[] getPageList(Integer pageNum);
}

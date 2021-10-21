package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.PluginDto;
import com.project.drops_musicmarket.Entity.PluginEntity;
import com.project.drops_musicmarket.Repository.PluginRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PluginServiceImpl implements PluginService{

    private final PluginRepository pluginRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 10; // Page numbers inside of blocks
    private static final int PAGE_POST_COUNT = 12; // Article numbers per page.

    @Override
    public List<PluginDto> getList(Integer pageNum) {
        Page<PluginEntity> pluginInfo = pluginRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "pluginDate")));
        List<PluginDto> getList = new ArrayList<>();

        for (PluginEntity listInfo : pluginInfo) {
            PluginDto pluginDto = PluginDto.builder()
                    .pluginId(listInfo.getPluginId())
                    .pluginSeller(listInfo.getPluginSeller())
                    .build();

            getList.add(pluginDto);

        }
        return getList;
    }

    @Transactional
    public Long getBoardCount(){
        return pluginRepository.count();
    }

    @Override
    public Integer[] getPageList(Integer curPageNum) {
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        Integer totalLastPageNum = (int)(Math.ceil(postsTotalCount/PAGE_POST_COUNT));

        Integer[] pageList = new Integer[totalLastPageNum];

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum <= 3) ? 1 : curPageNum-2;

        for(int val = curPageNum, i = 0 ; val <= blockLastPageNum ; val++ , i++){
            pageList[i] = val;
        }

        return pageList;
    }


}

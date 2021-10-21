package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.SoundDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SoundService {

    List<SoundDto> getSampleList(Integer pageNum);

    boolean submitSample(SoundDto sampleInfo, MultipartFile sampleValue, MultipartFile imageValue) throws IOException;

    Integer[] getPageList(Integer pageNum);

    SoundDto getSoundInfo(String soundId);

}

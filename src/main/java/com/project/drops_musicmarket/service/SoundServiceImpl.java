package com.project.drops_musicmarket.service;

import com.project.drops_musicmarket.DTO.SoundDto;
import com.project.drops_musicmarket.Entity.SoundEntity;
import com.project.drops_musicmarket.Repository.SoundRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@AllArgsConstructor
public class SoundServiceImpl implements SoundService {

    public final MultipartHttpServletRequest multipartHttpServletRequest;
    public final SoundRepository soundRepository;


    private static final int BLOCK_PAGE_NUM_COUNT = 10; // Page numbers inside of blocks
    private static final int PAGE_POST_COUNT = 12; // Article numbers per page.

    public List<SoundDto> getSampleList(Integer pageNum) {

        Page<SoundEntity> sampleData = soundRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "soundDate")));
        List<SoundDto> sampleBoard = new ArrayList<>();

        for (SoundEntity dataInfo : sampleData){
            SoundDto soundDto = SoundDto.builder()
                    .soundId(dataInfo.getSoundId())
                    .soundName(dataInfo.getSoundName())
                    .soundSeller(dataInfo.getSoundSeller())
                    .soundType(dataInfo.getSoundType())
                    .soundGenre(dataInfo.getSoundGenre())
                    .soundImage(dataInfo.getSoundImage())
                    .soundPrice(dataInfo.getSoundPrice())
                    .soundDate(dataInfo.getSoundDate())
                    .build();

            sampleBoard.add(soundDto);

        }

        return sampleBoard;
    }

    @Transactional
    public Long getBoardCount(){
        return soundRepository.count();
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


    public boolean submitSample(SoundDto sampleInfo, MultipartFile sampleValue, MultipartFile imageValue) throws IOException {

        String name = sampleInfo.getSoundName();
        String seller = sampleInfo.getSoundSeller();
        String type = sampleInfo.getSoundType();
        String genre = sampleInfo.getSoundGenre();
        String description = sampleInfo.getSoundDescription();
        BigDecimal price = sampleInfo.getSoundPrice();

        if (name.isEmpty() || name.equals("")){
            return false;
        } else if (seller.isEmpty() || seller.equals("")){
            return false;
        }

        String productCode = "";
        String checkSoundId = null;

        do {
            try {
                productCode = SoundServiceImpl.generateProductCode();
                Optional<SoundEntity> sampleList = soundRepository.findById(productCode);
                SoundEntity tempInfo = sampleList.get();

                checkSoundId = tempInfo.getSoundId();
            } catch (NoSuchElementException e){
                e.printStackTrace();
                checkSoundId = null;
            }

        } while(checkSoundId != null);

        String fileLocation = uploadFile(sampleValue, productCode);
        String imageLocation = uploadFile(imageValue, productCode);

        sampleInfo.setSoundId(productCode);
        sampleInfo.setSoundFile(fileLocation);
        sampleInfo.setSoundImage(imageLocation);

        soundRepository.save(sampleInfo.toEntity());

        return true;

    }

    public static String generateProductCode(){
        StringBuffer tmp = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 30; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    tmp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    tmp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    tmp.append((rnd.nextInt(10)));
                    break;
            }
        }

        String result = tmp.substring(0);
        return result;

    }

    public static String uploadFile(MultipartFile uploadFile, String folderName) throws IOException {
        String localDir = System.getProperty("user.dir") + "/src/main/resources/static";
        String sampleDir = "/file/sound/" + folderName;
        String imgDir = "/image/uploaded/sound/" + folderName;

        String fileName = uploadFile.getOriginalFilename();
        String resourceSrc = "";
        String returnLocation = "";


        if (fileName.endsWith(".zip") || fileName.endsWith(".rar") || fileName.endsWith(".7z")) {
            new File(localDir + sampleDir).mkdir();
            returnLocation =  sampleDir + "/" + fileName;
            resourceSrc = localDir + returnLocation;
        } else if (fileName.endsWith(".img") || fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            new File(localDir + imgDir).mkdir();
            returnLocation =  imgDir + "/" + fileName;
            resourceSrc = localDir + returnLocation;
        }

        Path destination = Paths.get(resourceSrc);
        FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(destination.toFile(), true));

        return returnLocation;

    }

    @Override
    public SoundDto getSoundInfo(String soundId) {
        Optional<SoundEntity> checkInfo = soundRepository.findById(soundId);
        SoundEntity getInfo = checkInfo.get();

        SoundDto infoResult = SoundDto.builder()
                .soundName(getInfo.getSoundName())
                .soundImage(getInfo.getSoundImage())
                .soundPrice(getInfo.getSoundPrice())
                .soundGenre(getInfo.getSoundGenre())
                .soundType(getInfo.getSoundType())
                .soundSeller(getInfo.getSoundSeller())
                .soundDescription(getInfo.getSoundDescription())
                .build();

        if (infoResult != null){
            return infoResult;
        } else {
            return null;
        }

    }
}

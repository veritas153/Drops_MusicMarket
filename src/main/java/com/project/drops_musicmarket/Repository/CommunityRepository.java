package com.project.drops_musicmarket.Repository;

import com.project.drops_musicmarket.DTO.CommunityDto;
import com.project.drops_musicmarket.Entity.CommunityEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {

    @Query(value = "SELECT * FROM community where community_commentNum = :community_oriNum", nativeQuery = true)
    List<CommunityEntity> findComment(@Param("community_oriNum") long community_oriNum);

}

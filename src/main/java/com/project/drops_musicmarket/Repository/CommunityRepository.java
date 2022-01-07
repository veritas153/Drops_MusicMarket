package com.project.drops_musicmarket.Repository;

import com.project.drops_musicmarket.Entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {

    Page<CommunityEntity> findAll(Pageable pageable);

}

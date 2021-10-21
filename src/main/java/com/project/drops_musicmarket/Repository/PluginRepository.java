package com.project.drops_musicmarket.Repository;

import com.project.drops_musicmarket.Entity.PluginEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginRepository extends JpaRepository<PluginEntity, Long> {

    Page<PluginEntity> findAll(Pageable pageable);

}

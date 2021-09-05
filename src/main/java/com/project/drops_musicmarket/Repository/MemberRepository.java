package com.project.drops_musicmarket.Repository;

import com.project.drops_musicmarket.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}

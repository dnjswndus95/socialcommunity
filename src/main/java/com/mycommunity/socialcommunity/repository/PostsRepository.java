package com.mycommunity.socialcommunity.repository;

import com.mycommunity.socialcommunity.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // IgnoreCase로 영어 대소문자 구분없이 쿼리가 나가게
    Page<Posts> findByTitleIgnoreCaseContaining(String keyword, Pageable pageable);

    /**
     * Modifying : @Query 어노테이션으로 Select 이 아닌 Update, Insert, Delete 쿼리를 사용할 때 필요하다.
     *
     * flushAutomatically, clearAutomatically
     * - @Modifying 을 사용하게 되면 Entity Life Cycle 을 무시하고 쿼리가 나가기 때문에
     *   영속성 컨텍스트와 실질적 값이 다른 경우가 발생한다.
     *
     *   1) flushAutomatically 는 @Modifying 이 붙은 쿼리를 실행 하기 전 영속성 컨텍스트의 내용을 DB에 flush 할 것인지 결정한다. (default 는 false)
     *   2) clearAutomatically 는 @Modifying 이 붙은 쿼리를 실행 하기 전 영속성 컨텍스트의 내용을 clear 할 것인지 결정한다. (default 는 false)
     */

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(@Param("id") Long id);
}

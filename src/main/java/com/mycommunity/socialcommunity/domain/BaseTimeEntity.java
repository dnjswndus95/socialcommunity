package com.mycommunity.socialcommunity.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass  // Entity 공통속성을 매핑하기 위한 어노테이션
@Getter
@EntityListeners(AuditingEntityListener.class)  // EntityListeners : JPA Entity에 이벤트가 발생하면 콜백을 처리하고 코드를 실행하는 어노테이션
                                                // Auditing 목적으로 사용할거라면 인자에 AuditingEntityListener.class를 인자로 넘겨준다.
public abstract class BaseTimeEntity {

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate
    private String modifiedDate;

    // @EntityListeners 가 지원하는 콜백 메서드
    /**
     * 1. PrePersist / PostPersist : 엔티티를 저장하기 전
     * 2. PreRemove / PostRemove : 엔티티를 삭제하기 전 / 후
     * 3. PreUpdate / PostUpdate : 엔티티를 업데이트 하기 전 / 후
     * 4. PostLoad : 해당 엔티티를 새로 불러오거나 refresh 한 후
     **/

    @PrePersist
    public void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate = this.createdDate;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}

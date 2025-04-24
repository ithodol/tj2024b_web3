package web.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 해당 클래스는 일반 엔티티가 아닌 상속(공통부문) 엔티티 용도
@EntityListeners(AuditingEntityListener.class) // 해당 클래스를 JPA 감사 활성화
@Getter
public class BaseTime {
    // 생성 날짜시간
    @CreatedDate
    private LocalDateTime createDateTime;

    // 수정 날짜시간
    @LastModifiedDate
    private LocalDateTime updateDateTime;
}

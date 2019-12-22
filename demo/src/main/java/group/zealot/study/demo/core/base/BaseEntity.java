package group.zealot.study.demo.core.base;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, length = 20)
    protected Long id;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime insertTime;
    @Column
    protected LocalDateTime updateTime;
    @Column(nullable = false, insertable = false, columnDefinition = "bit default 0")
    protected Boolean isDelete;

    @PrePersist
    protected void prePersist() {
        this.insertTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}

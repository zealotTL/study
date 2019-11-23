package group.zealot.study.datasource.jpa;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected Integer id;

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

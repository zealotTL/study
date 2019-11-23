package group.zealot.study.datasource.jpa;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "本表数据ID，自增")
    protected Integer id;

    @Column(nullable = false, updatable = false, columnDefinition = "本条数据插入时间")
    protected LocalDateTime insertTime;
    @Column(columnDefinition = "本条数据最近更新时间")
    protected LocalDateTime updateTime;
    @Column(insertable = false, columnDefinition = "true 表示已逻辑删除")
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

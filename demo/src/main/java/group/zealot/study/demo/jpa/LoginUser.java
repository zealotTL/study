package group.zealot.study.demo.jpa;

import group.zealot.study.demo.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_login_user")
public class LoginUser extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(length = 100)
    private String email;
}

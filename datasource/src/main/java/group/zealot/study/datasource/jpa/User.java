package group.zealot.study.datasource.jpa;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(length = 100)
    private String email;
}

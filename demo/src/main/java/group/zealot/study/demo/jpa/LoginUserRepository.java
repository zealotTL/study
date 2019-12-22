package group.zealot.study.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zealot
 * @date 2019/11/23 11:52
 */
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
}

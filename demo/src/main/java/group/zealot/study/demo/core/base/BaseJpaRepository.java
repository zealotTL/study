package group.zealot.study.demo.core.base;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zealot
 * @date 2019/12/21 22:13
 */
public interface BaseJpaRepository<E> extends JpaRepository<E, Long> {
}

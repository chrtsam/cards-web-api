package com.chrtsam.cards.api.repository;

import com.chrtsam.cards.api.model.Task;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chris
 */
public interface TaskRepository extends CrudRepository<Task, Integer>, JpaRepository<Task, Integer>, PagingAndSortingRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

    @Query("select c from Task c where c.id = :id and (c.owner = ?#{ principal?.id } or 'Admin' = ?#{ principal?.userRole.toString() })")
    @Override
    public Optional<Task> findById(Integer id);

    @Transactional
    @Modifying
    @Query("delete from Task c where c.id = :id and (c.owner = ?#{ principal?.id } or 'Admin' = ?#{ principal?.userRole.toString() })")
    public Integer deleteAndReturnById(Integer id);
}

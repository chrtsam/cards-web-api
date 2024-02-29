package com.chrtsam.cards.api.services;

import com.chrtsam.cards.api.exception.EntityNotFoundException;
import com.chrtsam.cards.api.model.Task;
import com.chrtsam.cards.api.model.Status;
import com.chrtsam.cards.api.model.helper.Condition;
import com.chrtsam.cards.api.model.helper.ConditionBuilder;
import com.chrtsam.cards.api.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chris
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository cardRepository;

    public Task saveTask(Task card, int owner) throws IllegalArgumentException {
        card.setStatus(Status.TODO);
        card.setOwner(owner);
        return cardRepository.save(card);
    }

    public Task findById(int id) throws EntityNotFoundException {
        Optional<Task> optional = cardRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        return optional.get();
    }

    public Task updateTask(Task card) throws IllegalArgumentException {
        return cardRepository.save(card);
    }

    public void changeTaskStatus(int id, Status status) {
        Task card = findById(id);
        card.setStatus(status);
        updateTask(card);
    }

    public boolean deleteTask(int id) {
        return cardRepository.deleteAndReturnById(id) > 0;
    }

    public Page<Task> searchTasks(List<Condition> conditions, Pageable page) {
        Specification criteria = null;
        for (Condition condition : conditions) {
            ConditionBuilder conditionSpec = new ConditionBuilder(condition);
            if (criteria != null) {
                criteria = criteria.and(conditionSpec);
            } else {
                criteria = Specification.where(conditionSpec);
            }
        }
        return cardRepository.findAll(criteria, page);
    }

}

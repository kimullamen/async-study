package com.example.async.services.common;

import com.example.async.models.Task;
import com.example.async.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class Executor {
    TaskRepository taskRepository;
    private final int LATENCY_TIME_IN_SECONDS = 5;

    /**
     * 一定時間待ったあとにタスクを1つ進める
     *
     * @param task
     */
    @Transactional
    public Task execute(Task task) {

        try {
            TimeUnit.SECONDS.sleep(LATENCY_TIME_IN_SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.setDone(task.getDone() + 1);
        Task done = taskRepository.save(task);
        log.info("progress: " + done.toString());
        return done;
    }

}

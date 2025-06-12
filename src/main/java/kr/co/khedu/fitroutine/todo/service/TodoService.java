package kr.co.khedu.fitroutine.todo.service;

import kr.co.khedu.fitroutine.todo.dto.RoutineMvpTOP3;
import kr.co.khedu.fitroutine.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper todoMapper;
    public TodoService (TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<? extends RoutineMvpTOP3> getRoutineMvpTOP3() {
        return todoMapper.getRoutineMvpTOP3();
    }
}

package kr.co.khedu.fitroutine.todo.model.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public final class MenuTodoListResponse {
    private final long todoId;
    private final String category;
    private final Date startedAt;
    private final Date endedAt;
    private @Nullable List<? extends Menu> todos;

    @Builder
    private MenuTodoListResponse(long todoId, String category, Date startedAt, Date endedAt) {
        this.todoId = todoId;
        this.category = category;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}

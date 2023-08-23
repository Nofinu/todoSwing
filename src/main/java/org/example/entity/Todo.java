package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private String title;
    private boolean isComplete;

    public Todo(String title) {
        this.title = title;
        isComplete = false;
    }

    @Override
    public String toString() {
        return isComplete ? "<html><Strike>"+title+"</Strike></html>": title;
    }
}

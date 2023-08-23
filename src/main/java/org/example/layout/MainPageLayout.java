package org.example.layout;

import lombok.Data;
import org.example.entity.Todo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class MainPageLayout {

    private JPanel mainPanel;
    private JTextField todoInput;
    private JList todoList;
    private TodoListModel todoListModel;

    public MainPageLayout() {
        mainPanel = new JPanel();
        mainPanel.setLayout( new BorderLayout());

        todoInput = new JTextField();
        Border border = new LineBorder(Color.black);
        todoInput.setBorder(border);
        mainPanel.add(todoInput,BorderLayout.NORTH);

        Container tableContainer = new Container();
        todoList = new JList();
        todoListModel = new TodoListModel();
        todoListModel.addTodo(new Todo("todo"));
        todoList.setModel(todoListModel);
        tableContainer.add(todoList);
        mainPanel.add(todoList,BorderLayout.CENTER);


        Container buttonContainer = new Container();
        buttonContainer.setLayout(new FlowLayout());
//        ImageIcon img = new ImageIcon("C:\\Users\\Administrateur\\Desktop\\TodoListSwing\\src\\icon\\plus-circle.svg");
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletAction();
            }
        });
        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeAction();
            }
        });
        buttonContainer.add(addButton);
        buttonContainer.add(deleteButton);
        buttonContainer.add(completeButton);

        mainPanel.add(buttonContainer,BorderLayout.SOUTH);

    }

    private void addAction (){
        todoListModel.addTodo(new Todo(todoInput.getText()));
        todoList.setListData(todoListModel.getlist().toArray());

    }
    private void deletAction (){
        int indexSelected = todoList.getSelectedIndex();
        todoListModel.remove(indexSelected);
        todoList.setListData(todoListModel.getlist().toArray());

    }

    private void completeAction (){
        int indexSelected = todoList.getSelectedIndex();
        Todo todo = (Todo) todoListModel.getElementAt(indexSelected);
        todo.setComplete(!todo.isComplete());
        todoList.setListData(todoListModel.getlist().toArray());
    }


    public static class TodoListModel extends AbstractListModel {

        private List<Todo> todoList;

        public TodoListModel() {
            this.todoList = new ArrayList<>();
        }

        public void addTodo(Todo todo) {
            todoList.add(todo);
            System.out.println(todoList);
        }

        public List<Todo> getlist (){
            return this.todoList;
        }

        public void remove (int index){
            this.todoList.remove(index);
        }

        @Override
        public int getSize() {
            return todoList.size();
        }
        @Override
        public Object getElementAt(int index) {
            Todo result = null;
            if (index < getSize()) {
                result = todoList.get(index);
            }
            return result;
        }
    }
}

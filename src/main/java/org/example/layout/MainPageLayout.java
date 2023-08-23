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

        Container c = new Container();
        c.setLayout(new FlowLayout());
        todoInput = new JTextField(40);
        Border border = new LineBorder(Color.black);
        todoInput.setBorder(border);
        c.add(todoInput);
        mainPanel.add(c,BorderLayout.NORTH);

        Container tableContainer = new Container();
        todoList = new JList();
        todoListModel = new TodoListModel();
        todoListModel.addTodo(new Todo("todo"));
        todoList.setModel(todoListModel);
        tableContainer.add(todoList);
        mainPanel.add(todoList,BorderLayout.CENTER);


        Container buttonContainer = new Container();
        buttonContainer.setLayout(new FlowLayout());
        ImageIcon imgPlus = new ImageIcon("C:\\Users\\Administrateur\\Desktop\\TodoListSwing\\src\\icon\\img.png");
        ImageIcon imgCheck = new ImageIcon("C:\\Users\\Administrateur\\Desktop\\TodoListSwing\\src\\icon\\check_icon.png");
        ImageIcon imgMinus = new ImageIcon("C:\\Users\\Administrateur\\Desktop\\TodoListSwing\\src\\icon\\minus_icon.png");
        JButton addButton = new JButton("Add",imgPlus);
        addButton.setBackground(Color.white);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });
        JButton deleteButton = new JButton("Delete",imgMinus);
        deleteButton.setBackground(Color.white);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletAction();
            }
        });
        JButton completeButton = new JButton("Complete",imgCheck);
        completeButton.setBackground(Color.white);
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
        todoInput.setText("");
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

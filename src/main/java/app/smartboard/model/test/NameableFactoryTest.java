package app.smartboard.model.test;

import app.smartboard.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameableFactoryTest {

    private NameableFactory nameableFactory;
    private Project project;
    private Column column;
    private Task task;

    private Project projectNull;
    private Column columnNull;
    private Task taskNull;

    @Before
    public void setUp() {
        nameableFactory = new NameableFactory();
        project = new Project("Test");
        column = new Column("Test");
        task = new Task("Test");

        projectNull = null;
        columnNull = null;
        taskNull = null;

    }

    @After
    public void tearDown() {
        nameableFactory = null;
        project = null;
        column = null;
        task = null;

        projectNull = null;
        columnNull = null;
        taskNull = null;
    }

    @Test
    public void createNameableProject() {
        assertEquals(this.project.getClass(), nameableFactory.createNameable("Project", "Test").getClass());
    }

    @Test
    public void createNameableColumn() {
        assertEquals(this.column.getClass(), nameableFactory.createNameable("Column", "Test").getClass());
    }

    @Test
    public void createNameableTask() {
        assertEquals(this.task.getClass(), nameableFactory.createNameable("Task", "Test").getClass());
    }

    @Test
    public void createNameableProjectNull() {
        assertEquals(this.projectNull, nameableFactory.createNameable(null, ""));
    }

    @Test
    public void createNameableColumnNull() {
        assertEquals(this.columnNull, nameableFactory.createNameable(null, ""));
    }

    @Test
    public void createNameableTaskNull() {
        assertEquals(this.taskNull, nameableFactory.createNameable(null, ""));
    }

    @Test
    public void createNameableProjectNullNoName() {
        assertEquals(this.projectNull, nameableFactory.createNameable("Project", ""));
    }

    @Test
    public void createNameableColumnNullNoName() {
        assertEquals(this.columnNull, nameableFactory.createNameable("Column", ""));
    }

    @Test
    public void createNameableTaskNullNoName() {
        assertEquals(this.taskNull, nameableFactory.createNameable("Task", ""));
    }
}
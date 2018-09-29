package by.gsu.epamlab.service.section;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.service.exception.DAOException;

import java.time.LocalDate;

public class TodaySection extends SomeDaySection {

    public TodaySection(String getTaskQuery, String deleteTaskQuery, String changeQuery, String addQuery) {
        super(getTaskQuery, deleteTaskQuery, changeQuery, addQuery);
    }

    @Override
    public int addTask(Task task) throws DAOException {
        task.setDate(LocalDate.now());
        return super.addTask(task);
    }
}

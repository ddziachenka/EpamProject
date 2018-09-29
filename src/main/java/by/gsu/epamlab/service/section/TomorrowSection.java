package by.gsu.epamlab.service.section;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.service.exception.DAOException;

import java.time.LocalDate;

public class TomorrowSection extends SomeDaySection {

    public TomorrowSection(String getTaskQuery, String deleteTaskQuery, String changeQuery, String addQuery) {
        super(getTaskQuery, deleteTaskQuery, changeQuery, addQuery);
    }

    @Override
    public int addTask(Task task) throws DAOException {
        task.setDate(LocalDate.now().plusDays(1));
        return super.addTask(task);
    }

}

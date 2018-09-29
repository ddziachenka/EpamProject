package by.gsu.epamlab.service.section;

import by.gsu.epamlab.service.exception.DAOException;

public class BucketSection extends FixSection {
    protected final String changeQuery;

    public BucketSection(String getTaskQuery, String deleteTaskQuery, String changeQuery) {
        super(getTaskQuery, deleteTaskQuery);
        this.changeQuery = changeQuery;
    }

    @Override
    public void changeTask(int... tasks) throws DAOException {
        runQuery(changeQuery, tasks);
    }
}

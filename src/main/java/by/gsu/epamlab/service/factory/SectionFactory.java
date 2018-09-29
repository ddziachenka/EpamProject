package by.gsu.epamlab.service.factory;

import by.gsu.epamlab.service.section.*;

public class SectionFactory {

    private final static String FIX_GET_TASKS_QUERY = "SELECT id, date, description, file FROM tasks WHERE login =? AND bin=0 AND fixed=1";
    private final static String FIX_DELETE_QUERY = "UPDATE tasks SET bin=1 WHERE id=? AND fixed = 1 AND bin = 0";

    private final static String BUCKET_GET_TASKS_QUERY = "SELECT id, date, description, file FROM tasks WHERE login =? AND bin=1";
    private final static String BUCKET_RESTORE_TASKS_QUERY = "UPDATE tasks SET bin=0 WHERE id=? AND bin = 1";
    private final static String BUCKET_DELETE_QUERY = "DELETE FROM tasks WHERE id=?  AND bin = 1";

    private final static String TODAY_GET_TASKS_QUERY = "SELECT id, date, description, file FROM tasks WHERE login = ? AND date <= date(NOW()) AND bin = 0 AND fixed = 0";

    private final static String TOMORROW_GET_TASKS_QUERY = "SELECT id, date, description, file FROM tasks WHERE login = ? AND date = date(NOW() + INTERVAL 1 DAY) AND bin = 0 AND fixed = 0";

    private final static String DAY_NOTE_FIXED_TASK_QUERY = "UPDATE tasks SET fixed=1 WHERE id=? AND bin = 0 AND fixed = 0";
    private final static String DAY_ADD_QUERY = "INSERT INTO tasks(login, description, file, date) VALUES(?,?,?,?)";
    private final static String DAY_DELETE_QUERY = "UPDATE tasks SET bin=1 WHERE id=? AND bin = 0 AND fixed = 0";
    private final static String DAY_GET_TASKS_QUERY = "SELECT id, date, description, file FROM tasks WHERE login = ? AND bin = 0 AND fixed = 0";


    public static AbstractSection getFixSection() {
        return new FixSection(FIX_GET_TASKS_QUERY, FIX_DELETE_QUERY);
    }

    public static AbstractSection getBucketSection() {
        return new BucketSection(BUCKET_GET_TASKS_QUERY, BUCKET_DELETE_QUERY, BUCKET_RESTORE_TASKS_QUERY);
    }

    public static AbstractSection getSomeDaySection() {
        return new SomeDaySection(DAY_GET_TASKS_QUERY, DAY_DELETE_QUERY, DAY_NOTE_FIXED_TASK_QUERY, DAY_ADD_QUERY);
    }

    public static AbstractSection getTodaySection() {
        return new TodaySection(TODAY_GET_TASKS_QUERY, DAY_DELETE_QUERY, DAY_NOTE_FIXED_TASK_QUERY, DAY_ADD_QUERY);
    }

    public static AbstractSection getTomorrowSection() {
        return new TomorrowSection(TOMORROW_GET_TASKS_QUERY, DAY_DELETE_QUERY, DAY_NOTE_FIXED_TASK_QUERY, DAY_ADD_QUERY);
    }

}

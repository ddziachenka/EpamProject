package by.gsu.epamlab.service.file;

import by.gsu.epamlab.service.dao.impl.UserDBImpl;
import by.gsu.epamlab.service.database.DBManager;
import by.gsu.epamlab.service.exception.DAOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;

public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    private final static String DELETE_FILE = "UPDATE tasks SET file = 0 WHERE id = ?";
    private final String path;

    public FileManager(String path) {
        this.path = path + SAVED_FILES + File.separator;
        new File(this.path).mkdir();
    }

    public void saveFile(Part file, String id) {
        String fileName = getFileName(file);
        try (FileOutputStream out = new FileOutputStream(new File(path + id + DOLLAR_SIGN + fileName));
             InputStream fileContent = file.getInputStream()) {
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void getFile(String id, ServletContext context, HttpServletResponse response) {
        try {
            File file = findFile(id);
            try (FileInputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                String mimeType = context.getMimeType(path + file.getName());
                if (mimeType == null) {
                    mimeType = APPLICATION_OCTET_STREAM;
                }
                response.setContentType(mimeType);
                response.setContentLength((int) file.length());
                String headerKey = CONTENT_DISPOSITION;
                String headerValue = String.format("attachment; =%s", file.getName().replaceAll("^\\d+\\$", EMPTY));
                response.setHeader(headerKey, headerValue);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void deleteFile(String id) throws IOException, DAOException {
        File file = findFile(id);
        if (file != null) {
            if (!file.delete()) {
                throw new IOException();
            }
            deleteFileInDB(id);
        }
    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (content.trim().startsWith(FILENAME)) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private File findFile(String id) {
        File folder = new File(path);
        File[] matchingFiles = folder.listFiles((dir, name) -> name.startsWith(id + "$"));
        if (matchingFiles == null || matchingFiles.length < 1) {
            return null;
        }
        return matchingFiles[0];
    }

    private void deleteFileInDB(String id) throws DAOException {
        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILE)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}


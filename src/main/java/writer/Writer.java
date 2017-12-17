package writer;

import repository.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public Writer() {}

    public void writeReportToFile (Repository repository) throws IOException {
        String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + repository.cityName + ".txt";
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(repository.report);
        writer.close();
    }

    public void writeReportsToOutputFile(List<Repository> weatherRepositories, List<Repository> forecastRepositories) throws IOException {
        for (Repository repository : weatherRepositories) {
            this.writeReportToFile(repository);
        }
        for (Repository forecastRepository : forecastRepositories) {
            this.writeReportToFile(forecastRepository);
        }
    }

    private static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    public void cleanDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
        }
    }
}

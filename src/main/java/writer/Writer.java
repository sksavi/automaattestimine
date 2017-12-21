package writer;

import repository.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Writer {

    public Writer() {}

    public void writeReportToFile (Repository repository) throws Exception {
        if (repository.getCityName() != null) {
            String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + repository.getCityName() + ".txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(repository.getReport());
            writer.close();
        } else {
            throw new Exception("No city name found!");
        }
    }

    public void writeReportsToOutputFile(List<Repository> weatherRepositories, List<Repository> forecastRepositories) throws Exception {
        for (Repository repository : weatherRepositories) {
            this.writeReportToFile(repository);
        }
        for (Repository forecastRepository : forecastRepositories) {
            this.writeReportToFile(forecastRepository);
        }
    }

    private void removeDirectory(File dir) {
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

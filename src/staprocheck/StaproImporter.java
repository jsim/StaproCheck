package staprocheck;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

/**
 *
 * @author jsim
 */
public class StaproImporter {

    private static final Logger logger = new Logger();
    private final StringBuilder message = new StringBuilder();

    private String sourcePath;
    private final Date now = new Date();
    private final List<PersonDiet> results = new LinkedList<PersonDiet>();

    public void init(String filesPath) {
        sourcePath = filesPath;

        if (!sourcePath.endsWith(File.separator)) {
            sourcePath = sourcePath + File.separator;
        }
    }

    public void run() {

        try {
            processArchives();
        } catch (Exception ex) {
            logger.info(ex);
            addMessage("Error [processArchive] : " + ex.getMessage());
        }

    }

    private void processArchives() throws Exception {

        final File[] files = new File(sourcePath).listFiles();

        if (files == null) {
            final String msg = "Cannot read directory: [" + sourcePath + "]";
            addMessage(msg);
            throw new RuntimeException(msg);
        }

        for (File file : files) {
            if (file.isFile() && hasValidExtension(file, "tgz")) {
                logger.info("Processing file: %s", file.getName());

                extractArchive(file);
            }
        }

    }

    private void extractArchive(File file) throws Exception {

        final int BUFFER = 4096;

        final FileInputStream fin = new FileInputStream(file);
        final BufferedInputStream in = new BufferedInputStream(fin);
        final GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
        final TarArchiveInputStream tarIn = new TarArchiveInputStream(gzIn);

        TarArchiveEntry entry;

        while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {

            if (!entry.isDirectory()) {

                final byte data[] = new byte[BUFFER];

                if (entry.getName().contains("/ZAL/")) {
                    continue;
                }

                final String[] arr = entry.getName().split("/");

                final String filename = arr[arr.length - 1];
                final String ward = arr[arr.length - 2];

                final int month = Integer.parseInt(filename.substring(3, 5));
                final int day = Integer.parseInt(filename.substring(5, 7));
                final int mt = Integer.parseInt(filename.substring(7, 8));

                final Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.set(cal.get(Calendar.YEAR), month - 1, day, 0, 0, 0);

                final Date targetDate = cal.getTime();

                while ((tarIn.read(data, 0, BUFFER)) != -1) {

                    final String fullFile = new String(data, "windows-1250");

                    final String[] lines = fullFile.split("\r\n");
                    for (String line : lines) {
                        try {

                            line = line.trim();

                            if (line.isEmpty()) {
                                continue;
                            }

                            final PersonDiet pd = new PersonDiet();

                            pd.setSource(file.getAbsolutePath());
                            pd.setCreated(now);
                            pd.setTargetDate(targetDate);
                            pd.setWard(ward);

                            final List<String> list = split(line);

                            final String patient = getStr(list, 2) + " " + getStr(list, 3);
                            pd.setPatient(patient.trim());
                            pd.setIdent(getStr(list, 0));  //jejich id
                            pd.setNote2(getStr(list, 1));   //RC

                            pd.setMealType(Integer.toString(mt));
                            pd.setDiet(getStr(list, 4));
                            pd.setNote1(getStr(list, 5));
                            pd.setAd1(getStr(list, 6));
                            pd.setAd2(getStr(list, 7));
                            pd.setAd3(getStr(list, 8));

                            results.add(pd);

                        } catch (Exception ex) {
                            logger.info(ex);
                            addMessage(ex.getMessage());
                        }
                    }
                }

            }
        }

        tarIn.close();
    }

    private List<String> split(String line) {
        final List<String> list = new LinkedList<String>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            final char c = line.charAt(i);
            final int x = (int) c;

            if (x == 355) {
                list.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }

        }

        return list;
    }

    private String getStr(List<String> list, int index) {
        if (index < list.size() && index >= 0) {
            return list.get(index);
        } else {
            return "";
        }
    }

    private boolean hasValidExtension(File file, String ext) {
        final String filename = file.getName().toLowerCase();
        return filename.endsWith("." + ext);
    }

    public String getResultMessage() {
        return message.toString();
    }

    private void addMessage(String msg) {
        message.append(msg);
        message.append("\n");
    }

    public List<PersonDiet> getResults() {
        return results;
    }

}

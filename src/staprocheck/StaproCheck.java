package staprocheck;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import staprocheck.grouping.KeyGenerator;
import staprocheck.grouping.KeyGeneratorFactory;

/**
 *
 * @author jsim
 */
public class StaproCheck {

    public static void main(String[] args) throws IOException {

        if (args == null || args.length == 0) {
            System.out.println("Missing WorkPath command line argument!");
            return;
        }

        System.out.println("Vyberte typ souctu:");
        System.out.println("  1. Den, Oddeleni, Typ, Dieta/Pridavek");
        System.out.println("  2. Den, Oddeleni, Typ");
        System.out.println("  3. Den, Oddeleni");
        System.out.println("  4. Bez souctu (cisty seznam)");

        final char c = (char) System.in.read();

        final int type = Integer.parseInt(c + "");

        final KeyGenerator kg = KeyGeneratorFactory.getGenerator(type);

        final StaproImporter imp = new StaproImporter();
        imp.init(args[0]);
        imp.run();

        final Map<String, Integer> results = new HashMap<String, Integer>();

        results.put(kg.getHeader(), 0);

        for (PersonDiet pd : imp.getResults()) {

            final String key = kg.getDietKey(pd);

            final Integer count = results.get(key);
            if (count == null) {
                results.put(key, 1);
            } else {
                results.put(key, count + 1);
            }

            add(kg.getAdditionKey(pd, pd.getAd1()), results);
            add(kg.getAdditionKey(pd, pd.getAd2()), results);
            add(kg.getAdditionKey(pd, pd.getAd3()), results);
            add(kg.getAdditionKey(pd, pd.getAd4()), results);
            add(kg.getAdditionKey(pd, pd.getAd5()), results);

        }

        print(results, type);

    }

    private static void add(String key, Map<String, Integer> additions) {
        if (key != null) {
            final Integer count = additions.get(key);
            if (count == null) {
                additions.put(key, 1);
            } else {
                additions.put(key, count + 1);
            }
        }
    }

    private static void print(Map<String, Integer> map, int type) {
        final List<String> keys = new LinkedList<String>(map.keySet());
        Collections.sort(keys);

        try {

            final File logFile = new File("result-" + type + ".txt");

            if (logFile.exists()) {
                logFile.delete();
            }

            final BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));

            for (String key : keys) {
                writer.write(key + " = " + map.get(key) + "\n");
            }

            writer.close();
        } catch (Exception e) {
        }

    }
}

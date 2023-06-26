package hr.fer.oop.zi.z1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class PovertyLoader {
    public static List<PovertyDatum> load(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PovertyDatum> list = new LinkedList<>();

        for (String line : lines) {
            String[] args = line.split(",");
            list.add(new PovertyDatum(
                    args[0],
                    Integer.parseInt(args[1]),
                    Double.parseDouble(args[2])
            ));
        }

        return list;
    }
}

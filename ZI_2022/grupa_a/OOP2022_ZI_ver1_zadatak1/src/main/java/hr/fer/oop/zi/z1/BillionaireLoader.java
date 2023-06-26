package hr.fer.oop.zi.z1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class BillionaireLoader {
    public static List<BillionaireDatum> load(String path) {
        List<String> lines = new LinkedList<>();
        try {
            lines = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BillionaireDatum> list = new LinkedList<>();
        for (String line : lines) {
            String[] args = line.split("\t");
            list.add(new BillionaireDatum(
                    Integer.parseInt(args[0]),
                    args[1],
                    args[2].equals("") ? null : Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]),
                    args[4]));
        }
        return list;
    }
}

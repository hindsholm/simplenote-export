package dk.hindsholm.dendron;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "sn2dendron", description = "Generates a set of markdown files from a SimpleNote export json file")
public class Main implements Callable<Integer> {

    @Option(names = "-f", description = "JSON input file", required = true)
    File jsonFile;

    @Option(names = "-o", description = "Output directory", required = true)
    Path outDir;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            if (Files.notExists(outDir)) {
                throw new IOException("Output directory %s does not exist".formatted(outDir));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Export export = objectMapper.readValue(jsonFile, Export.class);
            for (Note note: export.activeNotes()) {
                File outFile = outDir.resolve(note.getFileName()).toFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
                writer.write(note.getFrontmatter());
                writer.newLine();
                writer.write(note.content());
                writer.close();
                if (!outFile.setLastModified(note.lastModified().getTime())) {
                    System.err.printf("Could not set last modified time of '%s'%n", outFile.getName());
                }
            }
            System.out.printf("%d notes loaded from %s%n", export.activeNotes().length, jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

}

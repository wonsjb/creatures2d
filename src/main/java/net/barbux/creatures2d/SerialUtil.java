package net.barbux.creatures2d;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import net.barbux.creatures2d.proto.Creatures;

import java.io.*;
import java.util.function.Consumer;

public class SerialUtil {

    static void readFile(String fileName, Consumer<Creatures.Generation> generationConsumer, Consumer<Creatures.Results> resultsConsumer) {
        try (FileInputStream stream = new FileInputStream(fileName)) {
            CodedInputStream file = CodedInputStream.newInstance(stream);
            while (!file.isAtEnd()) {
                Creatures.Generation generation = file.readMessage(Creatures.Generation.parser(), ExtensionRegistryLite.getEmptyRegistry());

                generationConsumer.accept(generation);

                if (!file.isAtEnd()) {
                    Creatures.Results results = file.readMessage(Creatures.Results.parser(), ExtensionRegistryLite.getEmptyRegistry());
                    resultsConsumer.accept(results);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

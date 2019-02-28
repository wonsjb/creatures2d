package net.barbux.creatures2d;

import java.io.RandomAccessFile;

public class TestFile {
    public static void main(String[] args) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/home/wons/testFile", "rw");

        file.writeUTF("hello");
        file.writeUTF("world");

        file.seek(0);
        file.writeUTF("hallo2");
        file.close();

    }
}

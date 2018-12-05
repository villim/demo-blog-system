package io.github.villim.blog.infrastructure.gzip;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

public class GzipTool {

    private GzipTool() {
    }

    public static void compress(File inputFile, File outputFile) {
        try (GzipCompressorOutputStream gzipOut = new GzipCompressorOutputStream(new FileOutputStream(outputFile))) {
            IOUtils.copy(new FileInputStream(inputFile), gzipOut);
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    public static void decompress(File inputFile, File outputFile) {
        try (GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(new FileInputStream(inputFile))) {
            IOUtils.copy(gzipIn, new FileOutputStream(outputFile));
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    public static byte[] compress(String inputString) {
        try {
            return compressWithUnHandledException(inputString);
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    public static String decompress(byte[] inputBytes) {
        try (ByteArrayInputStream baInput = new ByteArrayInputStream(inputBytes);
             GzipCompressorInputStream gzipInput = new GzipCompressorInputStream(baInput);
             ObjectInputStream objectInput = new ObjectInputStream(gzipInput)) {
            return (String) objectInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    private static byte[] compressWithUnHandledException(Object inputString) throws IOException {
        ByteArrayOutputStream baOutput = new ByteArrayOutputStream();
        GzipCompressorOutputStream gzipOutput = new GzipCompressorOutputStream(baOutput);
        ObjectOutputStream objectOutput = new ObjectOutputStream(gzipOutput);
        try {
            objectOutput.writeObject(inputString);
        } finally {
            objectOutput.close();
            gzipOutput.close();
            baOutput.close();
        }
        return baOutput.toByteArray();
    }

    public static byte[] compressObj(Object inputString) {
        try {
            return compressWithUnHandledException(inputString);
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    public static Object decompressObj(byte[] inputBytes) {
        try (ByteArrayInputStream baInput = new ByteArrayInputStream(inputBytes);
             GzipCompressorInputStream gzipInput = new GzipCompressorInputStream(baInput);
             ObjectInputStream objectInput = new ObjectInputStream(gzipInput)) {
            return objectInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException("encounter exception:", e);
        }
    }

    // public static void main(String[] args) throws IOException, ClassNotFoundException {
    //
    // String test = "ABCDEFGHIJKLMNOPQRSTUVWXWZ";
    // byte[] compressedBytes = GzipTool.compress(test);
    //
    // String decompressedString = GzipTool.decompress(compressedBytes);
    // System.out.println(decompressedString);
    //
    // String s = "ABCDEFGHIJKLMNOPQRSTUVWXWZ";
    // }

}

package com.github.yungfasty.logger.utils;

import com.github.yungfasty.logger.LoggerPlugin;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WritterUtils {

    public static String FORMAT = LoggerPlugin.INSTANCE.getConfig().getString("messages.log-format");
    private static int BYTE_LIMIT = LoggerPlugin.INSTANCE.getConfig().getInt("settings.zip-size");
    private static SimpleDateFormat DATE_FOMART = new SimpleDateFormat("yyyy-MM-dd-sh");

    @SneakyThrows
    public static void write(String user, String toWrite) {

        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(String.format("logs/users/%s/latest.log", user));
        new File("logs/users/" + user).mkdirs();

        if (file.exists()) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) { bufferedReader.lines().forEach(line -> stringBuilder.append(line).append("\n")); }
            catch (Exception exception) { exception.printStackTrace(); }

        }

        else file.createNewFile();

        if (stringBuilder.toString().getBytes().length >= BYTE_LIMIT) {

            FileOutputStream fos = new FileOutputStream(String.format("logs/users/%s/%s-log.gz", user, DATE_FOMART.format(new Date(System.currentTimeMillis()))));
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            FileInputStream fis = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            byte[] bytes = new byte[1024];
            int length;

            zipOut.putNextEntry(zipEntry);
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.close();
            fis.close();
            fos.close();

            stringBuilder.delete(0, stringBuilder.length());

        }

        stringBuilder.append(StringUtils.replaceEach(FORMAT,
                new String[]{"{time}", "{action}"},
                new String[]{LoggerPlugin.DATE_FORMAT.format(new Date(System.currentTimeMillis())), toWrite}));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) { bufferedWriter.write(stringBuilder.toString()); }
        catch (Exception exception) { exception.printStackTrace(); }

    }

}

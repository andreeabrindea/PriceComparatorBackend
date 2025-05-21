package com.PriceComparatorBackend.PriceComparatorBackend.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    public static void createDataset() throws Exception {
        FileWriter fileWriter = new FileWriter("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/dataset.csv");
		final File folder = new File("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/data");
        if (!folder.exists())
        {
            folder.mkdir();
            fileWriter.close();
            return;
        }
        List<String> listOfFiles = listFilesFromGivenFolder(folder);
        if (listOfFiles == null || listOfFiles.size() == 0)
        {
            fileWriter.close();
            return;
        }

        for (int filesCount = 0; filesCount < listOfFiles.size(); filesCount++)
        {
            String file = listOfFiles.get(filesCount);
            String[] info = file.split("_");
            String storeName = info[0];
            String date = info[info.length - 1].replace(".csv", "");
            try (BufferedReader br = new BufferedReader(new FileReader(folder + "/" + file))) {
                String line;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {
                    if (filesCount > 0 && lineCount == 0)
                    {
                        lineCount++;
                        continue;
                    }
                    fileWriter.write(filesCount == 0 && lineCount == 0 ? line + ";store_name;date;\n" : line + ";" + storeName + ";" + date + ";" + "\n");
                    lineCount++;
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        fileWriter.close();
    }

    public static List<String> listFilesFromGivenFolder(final File folder) {
        List<String> filenames = new LinkedList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesFromGivenFolder(fileEntry);
            } else {
                if(fileEntry.getName().contains(".csv"))
                    filenames.add(fileEntry.getName());
            }
        }

        return filenames;
    }
}

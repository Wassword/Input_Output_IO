package org.example;
import java.io.*;
import java.util.*;

public class MergeAndFindCommonIntegers {
    public static void main(String[] args) {
        String inputFile1 = "file1.text";
        String inputFile2 = "file2.text";
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        try {
            //Read integers from input1.txt & input2.txt
            List<Integer> list1 = readIntegersFromFile(inputFile1);
            List<Integer> list2 = readIntegersFromFile(inputFile2);

            //Merge the two lists, maintains order
            List<Integer> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);
            writeIntegersToFile(mergedFile, mergedList);

            //Find common integers
            Set<Integer> set1 = new HashSet<>(list1);
            Set<Integer> set2 = new HashSet<>(list2);
            set1.retainAll(set2); //Set Intersection
            List<Integer> commonList = new ArrayList<>(set1);
            writeIntegersToFile(commonFile, commonList);

            //print

            System.out.println("Merging and finding common Integers completed successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file:" + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in on of the input files: " + e.getMessage());
        }
    }

    private static List<Integer> readIntegersFromFile(String fileName) throws IOException {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                integers.add(Integer.parseInt(line.trim()));
            }
        }
        return integers;
    }

    private static void writeIntegersToFile(String fileName, List<Integer> integers) throws IOException {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for( Integer integer: integers){
                bw.write(integer.toString());
                bw.newLine();
            }
        }
    }
}


package bjs.task14;

import java.io.*;

/**
 * Created by U-1 on 18.11.2015.
 */
public class FileManager {
    //Stream to read data
    private Reader fileInputStream;

    //Stream to write data
    private Writer fileOutputStream;

    private boolean isBuffered;

    FileManager(boolean buffered) {
        isBuffered = buffered;
    }

    //Finalize method it is  not the same as destructor in C++ because garbage collector does not guarantee
    //immediate call of the finalize()
    protected void finalize() {
        closeFileManager();
    }

    /**
     * Release all involved resources
     */
    void closeFileManager() {
        try {
            if (fileOutputStream != null)
                fileOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (fileInputStream != null)
                fileInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates output file
     * @param filePath Path the output file
     */
    void createOutputFile(String filePath) {
        try {
           if (isBuffered)
               fileOutputStream = new PrintWriter(new FileWriter(filePath));
           else
               fileOutputStream = new FileWriter(filePath);

            System.out.println("Output file '" + filePath + "'" + " created successfully");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills output file with bytes from the fileData
     * @param fileData
     */
    void writeOutputFile(String fileData) {
        try {
            if (isBuffered)
                ((PrintWriter)fileOutputStream).print(fileData);
            else
                ((FileWriter)fileOutputStream).write(fileData);

            System.out.println("Data written successfully");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens input file for reading
     * @param filePath
     */
    void createInputFile(String filePath) {
        try {
            if (isBuffered)
                fileInputStream = new BufferedReader(new FileReader(filePath));
            else
                fileInputStream = new FileReader(filePath);

            System.out.println("Input file '" + filePath + "'" + " created successfully");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void copyInputFile(String outFilePath) {
        createOutputFile(outFilePath);

        try {
            if (isBuffered) {
                String line;

                //Read by line
                while ((line = ((BufferedReader)fileInputStream).readLine()) != null) {
                    ((PrintWriter)fileOutputStream).println(line);
                }
            }
            else {
                final int blockSize = 1024;
                char[] blockBuffer = new char[blockSize];

                //the total number of bytes read into the buffer
                int readLength = 0;

                //Read file by blocks
                while ((readLength = ((FileReader)fileInputStream).read(blockBuffer)) > 0) {
                    ((FileWriter)fileOutputStream).write(blockBuffer, 0, readLength);
                }
            }

            System.out.println("Data copied successfully");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

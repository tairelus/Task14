package bjs.task14;

public class Main {

    public static void main(String[] args) {
        //true is a buffered input/output
        FileManager fileManager = new FileManager(true);

        fileManager.createOutputFile("first.txt");
        fileManager.writeOutputFile("This the content of the first.txt");
        fileManager.closeFileManager();

        fileManager.createInputFile("first.txt");
        fileManager.copyInputFile("second.txt");
        fileManager.closeFileManager();
    }
}

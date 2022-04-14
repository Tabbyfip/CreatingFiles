package TestingPackage.TheGoods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileCreator {

    //<editor-fold desc="*DATA MEMBERS*">
    private static final String MAIN_MENU_OPTION_MAKE_FOLDER = "Makes a folder named:";
    private static final String MAIN_MENU_OPTION_MAKE_FILE = "Makes a file named:";
    private static final String EXIT_MENU_OPTION = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_MAKE_FOLDER, MAIN_MENU_OPTION_MAKE_FILE, EXIT_MENU_OPTION};
    private final Menu menu;
    private final Scanner scanner = new Scanner(System.in);
    //</editor-fold>


    public FileCreator(Menu menu) {
        this.menu = menu;
    }


    public void run() {
        LoopMenu:
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            switch (choice) {
                case MAIN_MENU_OPTION_MAKE_FOLDER:
                    System.out.println("Please enter folder name!");
                    if (scanner.hasNext()) {
                        String userInput = scanner.nextLine();
                        Path pathToBeMade = Path.of(userInput);
                        folderCreator(pathToBeMade);
                    }

                    break;
                case MAIN_MENU_OPTION_MAKE_FILE:
                    System.out.println("Please enter file name!");
                    if (scanner.hasNext()) {
                        String userInput = scanner.nextLine();
                        Path fileToBeMade = Path.of(userInput);
                        fileCreator(fileToBeMade);
                    }

                    break;
                case EXIT_MENU_OPTION:
                    break LoopMenu;
            }
        }
    }

    public void folderCreator(Path createTheFolderPath) {
        if (!(Files.exists(createTheFolderPath))) {
            try {
                Files.createDirectories(createTheFolderPath);
                System.out.println(createTheFolderPath + " -- got created!");
            } catch (IOException e) {
                System.err.println("Folder was not created!");
            }
        } else {
            System.out.printf(createTheFolderPath + " -- Exists Already!");
        }
    }

    public void fileCreator(Path createTheFile) {
        if (!createTheFile.toFile().isFile()) {
            try {
                Files.createFile(createTheFile);
                System.out.println(createTheFile + "-- has been made!");
            } catch (IOException e) {
                System.err.println(createTheFile + " exploded!");
            }
        } else {
            System.out.println(createTheFile + " -- Exists already!");
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        FileCreator doTheThing = new FileCreator(menu);
        doTheThing.run();
    }
}


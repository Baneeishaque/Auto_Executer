package ndk.auto_executer.deprecation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author Srf
 */
public class Prompt_Execute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Prompt_Execute find_File_v2 = new Prompt_Execute();

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the directory where to search\nExamples\n/Volumes/Data_Drive/DK-HP-PA-2000AR/Laboratory/ : ");
        String directory = scan.next();

        System.out.println("Enter the file name to check for existense\nExamples\nbuild.gradle : ");
        String file = scan.next();

        System.out.println("Enter the command to execute,\nExamples\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle installDebug (Unix)\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle build (Unix)\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle tasks (Unix)\nls (Unix)\ngradle installDebug\ngradle build\ngradle tasks\ndir : ");
        scan = new Scanner(System.in);
        String command = scan.nextLine();

        System.out.println("Any Files to delete before execution (Y/N) : ");
        scan = new Scanner(System.in);
        String answer = scan.next();

        String file_to_delete = "";

        if (answer.equals("Y")) {
            System.out.println("Enter File name of File to delete from project folder before execution\nExamples\nlocal.properties : ");
            file_to_delete = scan.next();
        }

        System.out.println("Auto Mode (Y/N) : ");
        String mode = scan.next();

        System.out.println("Continue On Error (Y/N) : ");
        String continue_mode = scan.next();
        
        System.out.println("Confirmation Mode (Y/N) : ");
        String confirmation_mode = scan.next();
        
        find_File_v2.find_File(file, new File(directory), command, answer.equals("Y"), file_to_delete, mode.equals("Y"),continue_mode.equals("Y"),confirmation_mode.equals("Y"));

    }

    public void find_File(String name, File folder, String command, boolean file_delete, String file_to_delete, boolean auto_mode,boolean continue_on_error,boolean confirmation_mode) {

        File[] folder_contents = folder.listFiles();

        if (folder_contents != null) {

            boolean file_found = false;

            for (File current_file : folder_contents) {

                if ((current_file.isFile()) && (name.equalsIgnoreCase(current_file.getName()))) {

                    System.out.println("Project Folder : " + current_file.getParentFile());
                    file_found = true;

                    execute_command(current_file.getParentFile(), command, file_delete, file_to_delete, auto_mode,continue_on_error,confirmation_mode);

                    break;
                }
            }

            if (!file_found) {

                for (File current_file : folder_contents) {

                    if (current_file.isDirectory()) {
                        find_File(name, current_file, command, file_delete, file_to_delete, auto_mode,continue_on_error,confirmation_mode);
                    }
                }
            }
        }
    }

    private void execute_command(File parentFile, String command, boolean file_delete, String file_to_delete, boolean auto_mode, boolean continue_on_error, boolean confirmation_mode) {

        if (confirmation_mode) {

            System.out.println("Do you want to execute " + command + " on " + parentFile + " (Y/N) : ");
            Scanner scan = new Scanner(System.in);
            String answer = scan.next();

            if (answer.equals("Y")) {

                after_folder_selection(parentFile, command, file_delete, file_to_delete, auto_mode, continue_on_error);
            }

        } else {
            after_folder_selection(parentFile, command, file_delete, file_to_delete, auto_mode, continue_on_error);
        }
    }

    private void after_folder_selection(File parentFile, String command, boolean file_delete, String file_to_delete, boolean auto_mode, boolean continue_on_error) {

        try {

            if (file_delete) {
                delete_file(parentFile, file_to_delete);
            }

            ProcessBuilder builder;
            if (SystemUtils.IS_OS_WINDOWS) {

                builder = new ProcessBuilder("cmd.exe", "/c", command);

            } else {

                builder = new ProcessBuilder("sh", "-c", command);

            }

            builder.directory(parentFile);
            builder.environment().put("ANDROID_HOME", "/Volumes/Data_Drive/Programs/Android_SDK_Minimum_Mac/");
            builder.redirectErrorStream(true);
            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

            while (true) {
                if (!p.isAlive()) {
                    break;
                }
            }

            System.out.println("Process Exit Value : " + p.exitValue());

            if (p.exitValue() != 0) {

                if (continue_on_error) {

                    if (!auto_mode) {

                        System.out.println("Process Failed, Do you want to continue (Y/N) : ");
                        Scanner scan = new Scanner(System.in);
                        String answer = scan.next();

                        if (answer.equals("Y")) {
                            System.out.println("\n\n");
                        } else {

                            exit(0);
                        }

                    } else {
                        System.out.println("Process Failed, Please take a look at errors.\n\n");
                    }

                } else {
                    System.out.println("Process Failed, Please take a look at errors.");
                    exit(0);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Prompt_Execute.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete_file(File parentFile, String file_to_delete) {

        File file = new File(parentFile.getPath() + "//" + file_to_delete);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File " + file_to_delete + " deleted successfully");
            } else {
                System.out.println("Failed to delete " + file_to_delete);
            }
        } else {
            System.out.println("File " + file_to_delete + " not exist");

        }
    }

}

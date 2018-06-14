/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer;

import ndk.auto_executer.deprecation_v2.Prompt_Execute_Base;
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
 * @author manec
 */
public interface Prompt_Execute_Interface {

    default void execute() {

        find_File(configure_search_file(), new File(configure_search_directory()), configure_command(), configure_delete_mode(), configure_delete_file(), configure_auto_mode(), configure_continue_mode(), configure_confirmation_mode());

    }

    String configure_search_file();

    String configure_search_directory();

    String configure_command();

    boolean configure_delete_mode();

    String configure_delete_file();

    boolean configure_auto_mode();

    boolean configure_continue_mode();

    boolean configure_confirmation_mode();

    default void find_File(String name, File folder, String command, boolean file_delete, String file_to_delete, boolean auto_mode, boolean continue_on_error, boolean confirmation_mode) {

        File[] folder_contents = folder.listFiles();

        if (folder_contents != null) {

            boolean file_found = false;

            for (File current_file : folder_contents) {

                if ((current_file.isFile()) && (name.equalsIgnoreCase(current_file.getName()))) {

                    System.out.println("Project Folder : " + current_file.getParentFile());
                    file_found = true;

                    execute_command(current_file.getParentFile(), command, file_delete, file_to_delete, auto_mode, continue_on_error, confirmation_mode);

                    break;
                }
            }

            if (!file_found) {

                for (File current_file : folder_contents) {

                    if (current_file.isDirectory()) {
                        find_File(name, current_file, command, file_delete, file_to_delete, auto_mode, continue_on_error, confirmation_mode);
                    }
                }
            }
        }
    }

    default void execute_command(File parentFile, String command, boolean file_delete, String file_to_delete, boolean auto_mode, boolean continue_on_error, boolean confirmation_mode) {

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

    default void after_folder_selection(File parentFile, String command, boolean file_delete, String file_to_delete, boolean auto_mode, boolean continue_on_error) {

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
            Logger.getLogger(Prompt_Execute_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    default void delete_file(File parentFile, String file_to_delete) {

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

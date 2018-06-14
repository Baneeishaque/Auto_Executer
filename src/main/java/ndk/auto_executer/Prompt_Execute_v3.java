/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer;

import java.util.Scanner;
import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author manec
 */
public class Prompt_Execute_v3 implements Prompt_Execute_Interface{

    Scanner scan = new Scanner(System.in);

    @Override
    public String configure_search_file() {

        System.out.println("Enter the file name to check for existense\nExamples\nbuild.gradle : ");
        return scan.next();

    }

    @Override
    public String configure_search_directory() {
        System.out.println("Enter the directory where to search\nExamples\n/Volumes/Data_Drive/DK-HP-PA-2000AR/Laboratory/ : ");
        return scan.next();

    }

    @Override
    public String configure_command() {
        System.out.println("Enter the command to execute,\nExamples");
        if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("dir : ");
        } else {
            System.out.println("ls : ");
        }
        scan = new Scanner(System.in);
        return scan.nextLine();

    }

    boolean delete_mode;
    
    @Override
    public boolean configure_delete_mode() {

        System.out.println("Any Files to delete before execution (Y/N) : ");
        scan = new Scanner(System.in);

        delete_mode=scan.next().equals("Y");
        return delete_mode;

    }

    @Override
    public String configure_delete_file() {

        if (delete_mode) {

            System.out.println("Enter File name of File to delete from project folder before execution\nExamples\nlocal.properties : ");
            return scan.next();

        } else {
            return "";
        }

    }

    @Override
    public boolean configure_auto_mode() {

        System.out.println("Auto Mode (Y/N) : ");
        return scan.next().equals("Y");

    }

    @Override
    public boolean configure_continue_mode() {

        System.out.println("Continue On Error (Y/N) : ");
        return scan.next().equals("Y");
    }

    @Override
    public boolean configure_confirmation_mode() {

        System.out.println("Confirmation Mode (Y/N) : ");
        return scan.next().equals("Y");

    }

    public static void main(String[] args) {
        Prompt_Execute_v3 prompt_Execute_v3 = new Prompt_Execute_v3();
        prompt_Execute_v3.execute();
    }
}

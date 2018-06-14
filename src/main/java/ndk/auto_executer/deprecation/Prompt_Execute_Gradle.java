/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer.deprecation;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author manec
 */
public class Prompt_Execute_Gradle {

    public static void main(String[] args) {

        Prompt_Execute prompt_Execute = new Prompt_Execute();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the directory where to search\nExamples\n/Volumes/Data_Drive/DK-HP-PA-2000AR/Laboratory/ : ");
        String directory = scan.next();
        
        System.out.println("Enter the command to execute,\nExamples\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle installDebug (Unix)\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle build (Unix)\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle tasks (Unix)\nls (Unix)\ngradle installDebug\ngradle build\ngradle tasks\ndir : ");
        scan = new Scanner(System.in);
        String command = scan.nextLine();

        prompt_Execute.find_File("build.gradle", new File(directory), command, true, "local.properties", false, true, false);
    }
}

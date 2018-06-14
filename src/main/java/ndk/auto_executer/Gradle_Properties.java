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
public interface Gradle_Properties {

    String SEARCH_FILE = "build.gradle";
    boolean DELETE_FILE=true;
    String FILE_TO_DELETE="local.properties";

    static String configure_gradle_command() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the command to execute,\nExamples");

//        if (SystemUtils.IS_OS_WINDOWS) {
//
//            int option;
//            do {
//                System.out.println("\nGlobal Gradle Examples"
//                        + "\n1 - gradle installDebug"
//                        + "\n2 - gradle build"
//                        + "\n3 - gradle tasks"
//                        + "\n\nProject Gradle Examples"
//                        + "\n4 - gradlew installDebug"
//                        + "\n5 - gradlew build"
//                        + "\n6 - gradlew tasks"
//                        + "\n7 - Another Global Gradle Task"
//                        + "\n8 - Another Project Gradle Task"
//                        + "\n\nYour Option : ");
//                option = scan.nextInt();
//            } while ((option > 0) && (option < 9));
//
//        } else {
//
////            System.out.println("Enter Value for GRADLE_HOME"
////                    + "\nExamples"
////                    + "\n/Volumes/Data_Drive/Programs/gradle-4.4/ : ");
////            String gradle_home=scan.nextLine();
//            int option;
//            do {
//                System.out.println("\nGlobal Gradle Examples"
//                        + "\n1 - $GRADLE_HOME/bin/gradle installDebug"
//                        + "\n2 - $GRADLE_HOME/bin/gradle build"
//                        + "\n3 - $GRADLE_HOME/bin/gradle tasks"
//                        + "\n\nProject Gradle Examples"
//                        + "\n4 - gradlew installDebug"
//                        + "\n5 - gradlew build"
//                        + "\n6 - gradlew tasks"
//                        + "\n7 - Another Global Gradle Task"
//                        + "\n8 - Another Project Gradle Task"
//                        + "\n\nYour Option : ");
//                option = scan.nextInt();
//            } while ((option > 0) && (option < 9));

            System.out.println("\nGlobal Gradle Examples\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle installDebug\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle build\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle tasks\n\nProject Gradle Examples\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradlew installDebug\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradlew build\n/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradlew tasks : ");
//        }

        return scan.nextLine();
    }

}

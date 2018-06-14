/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer.deprecation;

import java.io.File;

/**
 *
 * @author manec
 */
public class Auto_Execute_Gradle {
    public static void main(String[] args) {
        Prompt_Execute prompt_Execute=new Prompt_Execute();
        prompt_Execute.find_File("build.gradle", new File("/Volumes/Data_Drive/DK-HP-PA-2000AR/Laboratory/"), "/Volumes/Data_Drive/Programs/gradle-4.4/bin/gradle installDebug", true, "local.properties", false, true, false);
    }
}

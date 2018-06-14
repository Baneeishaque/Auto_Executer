/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer;

import java.io.File;
import static ndk.auto_executer.Gradle_Properties.configure_gradle_command;

/**
 *
 * @author manec
 */
public class Prompt_Execute_v3_Auto_Gradle extends Prompt_Execute_v3_Auto implements Gradle_Properties {

    @Override
    public void execute() {
        Prompt_Execute_Interface.find_File(SEARCH_FILE, new File(super.configure_search_directory()), configure_gradle_command(), DELETE_FILE, FILE_TO_DELETE, super.configure_auto_mode(), super.configure_continue_mode(), super.configure_confirmation_mode());
    }
    
    public static void main(String[] args) {
        Prompt_Execute_v3_Auto_Gradle prompt_Execute_v3_Auto_Gradle = new Prompt_Execute_v3_Auto_Gradle();
        prompt_Execute_v3_Auto_Gradle.execute();
    }

}

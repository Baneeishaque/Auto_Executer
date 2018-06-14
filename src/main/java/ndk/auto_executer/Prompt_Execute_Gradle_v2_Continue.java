/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndk.auto_executer;

/**
 *
 * @author manec
 */
public class Prompt_Execute_Gradle_v2_Continue extends Prompt_Execute_Gradle_v2_Confirmation{

    @Override
    boolean configure_confirmation_mode() {
        return false;
    }
    
    public static void main(String[] args) {
        Prompt_Execute_Gradle_v2_Continue prompt_Execute_Gradle_v2_Continue = new Prompt_Execute_Gradle_v2_Continue();
        prompt_Execute_Gradle_v2_Continue.execute();
    }

}

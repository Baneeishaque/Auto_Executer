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
public class Prompt_Execute_v2_Confirmation extends Prompt_Execute_v2 {

    @Override
    boolean configure_auto_mode() {
        return false;
    }

    @Override
    boolean configure_confirmation_mode() {
        return true;
    }

    @Override
    boolean configure_continue_mode() {
        return true;
    }

    public static void main(String[] args) {
        Prompt_Execute_v2_Confirmation prompt_Execute_v2_Confirmation = new Prompt_Execute_v2_Confirmation();
        prompt_Execute_v2_Confirmation.execute();
    }

}

package gitlet;

import java.io.File;
import java.io.IOException;

import static gitlet.Utils.*;
import static gitlet.Utils.join;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                File CWD = new File(System.getProperty("user.dir"));
                File GITLET_DIR = join(CWD, ".gitlet");
                if (GITLET_DIR.exists()) {
                    System.out.println("A Gitlet version-control system already exists in the current directory.");
                    break;
                }
                GITLET_DIR.mkdir();

                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            case "commit":
        }
    }
}

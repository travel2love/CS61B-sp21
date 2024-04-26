package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import static gitlet.Utils.*;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {
    private static CommitTree commitTree;
    private static StageArea stageArea;
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
                File Object_File = join(GITLET_DIR, "Objects");
                Object_File.mkdir();
                commitTree = new CommitTree();
                commitTree.AddCommit("initial commit", new Date(0), "master");
                File commit_tree3 = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                writeObject(commit_tree3, commitTree);
                stageArea = new StageArea();
                stageArea.Write();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                File f = new File(args[1]);
                //if file not exist, print something.
                if (!f.exists()) {
                    System.out.println("File does not exist.");
                    System.exit(0);
                }
                //find stageArea and the map.
                File stage_area = join(new File(System.getProperty("user.dir")), ".gitlet", "StagingArea", "stage_area");
                stageArea = readObject(stage_area, StageArea.class);
                Map<File, File> map = stageArea.map;
                File new_file = Repository.Copy_To_StageArea(f);
                writeObject(stage_area, stageArea);
                if (map.containsKey(f)) {
                } else {
                    stageArea.put_new(f, new_file);
                    stageArea.Write();
                }
                break;
            // TODO: FILL THE REST IN
            case "commit":
                //when it doesn't have commit message.
                if (args.length == 1) {
                    System.out.println("Please enter a commit message.");
                }
                //get commitTree and map.
                File commit_tree = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                commitTree = readObject(commit_tree, CommitTree.class);
                Commit current = commitTree.getCurrentCommit();
                Map<String, File> current_map = current.map;
                //get stage_area's map, and iterate the map from stage_area to Objects_Dir.
                File stage = join(new File(System.getProperty("user.dir")), ".gitlet", "StagingArea", "stage_area");
                Map<File, File> stage_map = readObject(stage, StageArea.class).map;
                for (File key : stage_map.keySet()) {
                    File new_file1 = Repository.Copy_File(stage_map.get(key));
                    current_map.put(key.getName(), new_file1);
                }
                //iterate the map from current_commit to Objects_Dir.
                //create a new commit.
//                if (current_map == null) {
//                } else {
//                    for (String key : current_map.keySet()) {
//                        File new_file1 = Repository.Copy_File(current_map.get(key));
//                        current_map.put(key, new_file1);
//                    }
//                }
                commitTree.AddCommit(args[1], new Date(), "master");
                commitTree.addCurrentMap(current_map);
                writeObject(commit_tree, commitTree);
                //clear the stage area.
                stage.delete();
                StageArea new_stage = new StageArea();
                new_stage.Write();
                break;
            case "rm":
                File stage1 = join(new File(System.getProperty("user.dir")), ".gitlet", "StagingArea", "stage_area");
                StageArea sa = readObject(stage1, StageArea.class);
                File commit_tree1 = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                CommitTree tree = readObject(commit_tree1, CommitTree.class);
                File file = new File(args[1]);
                if (sa.map.containsKey(file)) {
                    sa.map.remove(file);
                } else {
                    if (tree.CurrentCommit.map.containsKey(file.getName())) {
                        tree.CurrentCommit.map.remove(file.getName());
                    }
                    System.out.println("No reason to remove the file.");
                }
                break;
            case "log":
                File commit_tree2 = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                CommitTree tree2 = readObject(commit_tree2, CommitTree.class);
                Commit commit = tree2.CurrentCommit;
                if (commit.parentCommit == null) {
                    System.out.println("===");
                    System.out.println("commit " + commit.commitID);
                    System.out.println("Date: " + commit.date);
                    System.out.println(commit.message);
                    System.out.println();
                } else {
                    while (commit.parentCommit != null) {
                        System.out.println("===");
                        System.out.println("commit " + commit.commitID);
                        System.out.println("Date: " + commit.date);
                        System.out.println(commit.message);
                        System.out.println();
                        commit = commit.parentCommit;
                    }
                }
                break;
            case "global-log":
                break;

            case "find":
                break;

            case "status":
                break;

            case "checkout":
                if (args[1].equals("--")) {
                    File f1 = new File(args[2]);
                    File commit_tree_file = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                    CommitTree commit_tree4 = readObject(commit_tree_file, CommitTree.class);
                    Commit current_commit = commit_tree4.CurrentCommit;
                    Map<String, File> map2 = current_commit.map;
                    File new_file1 = map2.get(f1.getName());
                    f1.delete();
                    try {
                        f1.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String contents = null;
                    try {
                        contents = Files.readString(new_file1.toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    writeContents(f1, contents);
                } else if (args[2].equals("--")) {
                    File f2 = new File(args[3]);
                    File commit_tree_file = join(new File(System.getProperty("user.dir")), ".gitlet", "CommitTree", "Tree");
                    CommitTree commit_tree5 = readObject(commit_tree_file, CommitTree.class);
                    Commit current_commit = commit_tree5.CurrentCommit;
                    while (!current_commit.commitID.equals(args[1])) {
                        current_commit = current_commit.parentCommit;
                    }
                    Map<String, File> map2 = current_commit.map;
                    File new_file1 = map2.get(f2.getName());
                    f2.delete();
                    try {
                        f2.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String contents = null;
                    try {
                        contents = Files.readString(new_file1.toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    writeContents(f2, contents);
                }
                break;

            case "branch":
                break;

            case "rm-branch":
                break;

            case "reset":
                break;

            case "merge":
                break;
        }
    }
}

package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");


    /* TODO: fill in the rest of this class. */
    public static File Copy_File(File odd_file) {
        String content = null;
        try {
            content = Files.readString(odd_file.toPath(), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file_hash = sha1(content);
        File new_file_dir = join(GITLET_DIR, "Objects", file_hash.substring(0, 2));
        new_file_dir.mkdir();
        File new_file = join(new_file_dir, file_hash.substring(2));
        try {
            new_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeContents(new_file, content);
        return new_file;
    }
    public static File Copy_To_StageArea(File file) {
        String context = null;
        try {
            context = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file_hash = sha1(context);
        File new_file_dir = join(GITLET_DIR, "StagingArea", file_hash.substring(0, 2));
        new_file_dir.mkdir();
        File new_file = join(new_file_dir, file_hash.substring(2));
        try {
            new_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeContents(new_file, context);
       return new_file;
    }



}

package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;
import java.util.Map;

import static gitlet.Utils.join;
import static gitlet.Utils.sha1;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    public String message;
    public String commitMessage;
    public Date date;
    private String branchID;
    public Map<String, File> map;
    public Commit parentCommit;
    public String commitID;
    public Commit(String message, Date date, String branchID) {
        this.message = message;
        this.date = date;
        this.branchID = branchID;
        this.map = new HashMap<>();
    }
    public void Write() {
        File temp = join(new File(System.getProperty("user.dir")), ".gitlet", "Objects", "temp");
        try {
            temp.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Utils.writeObject(temp, this);
        File newfile = Repository.Copy_File(temp);
        commitID = (newfile.getParent() + newfile.getName()).split("Objects/")[1];
        temp.delete();
    }
    /* TODO: fill in the rest of this class. */

}

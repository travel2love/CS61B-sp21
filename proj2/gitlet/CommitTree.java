package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import static gitlet.Utils.*;

public class CommitTree implements Serializable {
    private Commit parent;
    private Commit initial_commit;
    public Commit CurrentCommit;
    public CommitTree() {
        initial_commit = new Commit("initial commit", new Date(0), "master");
        File CWD = new File(System.getProperty("user.dir"));
        File GITLET_DIR = join(CWD, ".gitlet");
        File Commit_File = join(GITLET_DIR,"CommitTree");
        Commit_File.mkdir();
        File commit_file = join(Commit_File, "Tree");
        try {
            commit_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CurrentCommit = initial_commit;
        writeObject(commit_file, this);
        initial_commit.Write();
        initial_commit.parentCommit = null;
    }

    public void AddCommit(String CommitMessage, Date date, String branchID) {
        Commit NewCommit = new Commit(CommitMessage, date, branchID);
        NewCommit.Write();
        if (branchID.equals("master")) {
            NewCommit.parentCommit = CurrentCommit;
            CurrentCommit = NewCommit;
        }
    }
    public Commit getCurrentCommit() {
        return CurrentCommit;
    }
    public void addCurrentMap(Map<String, File> map) {
        CurrentCommit.map = map;
    }

}

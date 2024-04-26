package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static gitlet.Utils.join;

public class StageArea implements Serializable {
    Map<File, File> map = new HashMap<>();
    File stage_area;
    File stage_area_directory;
    public StageArea() {
        File stage_area_dir_dir = join(new File(System.getProperty("user.dir")), ".gitlet");
        stage_area_dir_dir.mkdir();
        stage_area_directory = join(stage_area_dir_dir, "StagingArea");
        stage_area_directory.mkdir();
        stage_area = join(stage_area_directory, "stage_area");
        try {
            stage_area.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public StageArea getStageArea() {
        return null;
    }

    public void put_new(File old_file, File new_file) {
        map.put(old_file, new_file);
    }

    public void Write() {
        Utils.writeObject(stage_area, this);
    }
    public StageArea Read() {
        return Utils.readObject(stage_area, StageArea.class);
    }
}

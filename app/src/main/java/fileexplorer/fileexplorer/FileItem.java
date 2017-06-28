package fileexplorer.fileexplorer;

/**
 * Created by rtb on 21/6/17.
 */

public class FileItem {

    public int imgIconId;
    public String name_file;
    public String last_mdfd_file;
    public String size_file;

    public FileItem(int imgIconId, String name_file, String last_mdfd_file, String size_file) {
        this.imgIconId = imgIconId;
        this.name_file = name_file;
        this.last_mdfd_file = last_mdfd_file;
        this.size_file = size_file;
    }
}

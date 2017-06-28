package fileexplorer.fileexplorer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragDirList extends Fragment {

    int i=0;
    private static final String TAGG = "@codekul";
    private List<FileItem> fileItems = new ArrayList<>();

    public FragDirList() {
        // Required empty public constructor
    }

    public static FragDirList getInstance(){

        FragDirList fradDirList = new FragDirList();

        return fradDirList;
    }

    @Override //here we will pass rootView to different methods
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String tempFileName=""; //this is to load dir-list for the first time without giving and dir's name
        View rootView = inflater.inflate(R.layout.fragment_frag_dir_list, container, false);
        loadDirList(rootView,tempFileName);

        return rootView;
    }

    //displaying directory list on the fragment
    private void loadDirList(View rootView,String fileName) {

        Log.i("pg",""+fileName);

        if(i == 0){
            File file = Environment.getExternalStoragePublicDirectory(fileName);
            File []files = file.listFiles();
            forLoop(files);
            i++;
        }else {
            File file = new File(fileName);
            Log.i("div",""+file);
            File []files = file.listFiles();
            forLoop(files);
        }

        //setting adapter to list
        ListView listView = (ListView)rootView.findViewById(R.id.lstMainDirList);
        listView.setAdapter(new FileAdapter(getActivity(),fileItems));

        //Action on clicking single directory
        onDirClick(rootView,fileItems);
    }

    private void forLoop(File[] files) {
        for (File fl: files){

            Date date = new Date(fl.lastModified());
            String size = convertSizeLong(fl.length());

            fileItems.add(new FileItem(fl.isDirectory()?R.drawable.ic_folders:R.mipmap.ic_launcher_round, fl.getName(),""+(new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(date)),""+size));
        }
    }

    private void onDirClick(final View rootView, final List<FileItem> fileItems) {

        ((ListView)rootView.findViewById(R.id.lstMainDirList)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FileItem file = fileItems.get(position);

                File dir = Environment.getExternalStoragePublicDirectory("");

                String path = dir.toString();

                String clickedDirName = file.name_file;
                File directory = new File(path+dir.separator+clickedDirName);

                File []contents = directory.listFiles();
                if(directory.isDirectory()) { //clicked item is a folder

                    if (contents == null) { //clicked item is an Empty folder

                        Toast.makeText(getContext(), "Empty Folder", Toast.LENGTH_SHORT).show();

                    } else { //clicked item is not an empty folder

                        Toast.makeText(getContext(), "Folder", Toast.LENGTH_SHORT).show();

                        loadDirList(rootView, ""+directory);
                    }

                }
                else {//clicked item is a file, not a folder
                        Toast.makeText(getContext(), "not a folder", Toast.LENGTH_SHORT).show();

                        //To check which file it is...through extension
                    //and providing the appropriate service
                }

                //Toast.makeText(getContext(), " " + clickedDirName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertSizeLong(long length) {

        String size = null;

        double bt =length;
        double kb = length/1024.0;
        double mb = ((length/1024.0)/1024.0);
        double gb = (((length/1024.0)/1024.0)/1024.0);
        double tb = ((((length/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat decFormat = new DecimalFormat("0.00");

        if(tb>1) {
            size = decFormat.format(tb).concat(" TB");
        } else if(gb>1) {
            size = decFormat.format(gb).concat(" GB");
        } else if(mb>1) {
            size = decFormat.format(mb).concat(" MB");
        } else if(kb>1) {
            size = decFormat.format(kb).concat(" kB");
        } else {
            size = decFormat.format(bt).concat(" Bytes");
        }

        return size;
    }
}

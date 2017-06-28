package fileexplorer.fileexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rtb on 21/6/17.
 */

public class FileAdapter extends BaseAdapter {

    private final Context context;
    private final List<FileItem> filedataset;

    public LayoutInflater inflater;

    public FileAdapter(Context context, List<FileItem> filedataset) {
        this.context = context;
        this.filedataset = filedataset;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return filedataset.size();
    }

    @Override
    public Object getItem(int position) {
        return filedataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView =null;

        if(convertView == null){
            rootView = inflater.inflate(R.layout.file_item_view,parent,false);
        }else {
            rootView = convertView;
        }

        ((ImageView)rootView.findViewById(R.id.imgFileIcon)).setImageResource(filedataset.get(position).imgIconId);
        ((TextView)rootView.findViewById(R.id.txtFileName)).setText(filedataset.get(position).name_file);
        ((TextView)rootView.findViewById(R.id.txtFileLstMdfd)).setText(filedataset.get(position).last_mdfd_file);
        ((TextView)rootView.findViewById(R.id.txtFileSize)).setText(filedataset.get(position).size_file);

        return rootView;
    }
}

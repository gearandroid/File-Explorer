package fileexplorer.fileexplorer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDirList();

    }

    //Loading list on the first time with the root
    public void loadDirList() {
        findViewById(R.id.imgMainFilrExplore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFrag(R.id.frag_container,FragDirList.getInstance());
            }
        });
    }

    //Loading Fragment
    public void loadFrag(int frag_container, FragDirList instance) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction txn = manager.beginTransaction();

        txn.replace(frag_container,instance);
        txn.commit();
    }

    public void onLoadList(View view) {
        loadDirList();
    }

}

package ltd.nickolay.listclick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ltd.nickolay.listclick.Jv.DataAdapter;
import ltd.nickolay.listclick.Jv.DataItem;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemListClick {

    private static final String TAG = "myLOG";
    private static final String ARG_TITLE = "ltd.nickolay.listclick.ARG_TITLE";
    private static final String ARG_LIST = "ltd.nickolay.listclick.ARG_LIST";

    private ArrayList<DataItem> mList;
    DataAdapter dataAdapter;// = new DataAdapter();

    //Variables for List
    int var_Title = 0;
    String[] var_SubText = new String[] {"Статически", "типизированный", "язык", "программирования",
            "работающий", "поверх JVM", "разрабатываемый", "компанией",
            "JetBrains", "Также"};
    int[] var_Image = new int[] {R.drawable.ic_0, R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3,
            R.drawable.ic_4, R.drawable.ic_5, R.drawable.ic_6, R.drawable.ic_7, R.drawable.ic_8,
            R.drawable.ic_9};
    final Random random = new Random();

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey(ARG_LIST)) {
            mList = new ArrayList<>();
            setupListView();
            AddItem(5);
        } else {
            mList = savedInstanceState.getParcelableArrayList(ARG_LIST);
            var_Title = savedInstanceState.getInt(ARG_TITLE, 0);
            setupListView();
        }
        dataAdapter.setOnItemListClickListener(this);
    }

    void setupListView(){
        dataAdapter = new DataAdapter(mList);
        RecyclerView recyclerView = findViewById(R.id.rv_List);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);
    }

    void AddItem(int count){
        for (int i = 0; i < count; i++) {
            dataAdapter.addItem(
                    var_Image[random.nextInt(10)],
                    "Title " + var_Title++,
                    var_SubText[random.nextInt(10)]);
        }
    }

    @Override
    public void onSelectItem(int position, String title) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, "Clicked " + title + " at position " + position, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean beforeDeleteItem(DataItem dataItem) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, "Delete item " + dataItem.getTitle(), Toast.LENGTH_LONG);
        toast.show();
        Log.d(TAG, "beforeDeleteItem: " + dataItem.toString());
        //TODO add dialog for delete
        return true;
    }

    public void AddFun(View view) {
        switch (view.getId()){
            case R.id.b_add_1: AddItem(1);
                break;
            case R.id.b_add_10: AddItem(10);
                break;
            default:
                Log.d(TAG, "AddFun: Error Add Click");

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ARG_TITLE, var_Title);
        outState.putParcelableArrayList(ARG_LIST, mList);

        super.onSaveInstanceState(outState);
    }
}

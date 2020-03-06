package ltd.nickolay.listclick

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ltd.nickolay.listclick.Kt.DataAdapterK
import ltd.nickolay.listclick.Kt.DataItemK
import kotlin.random.Random

private const val ARG_TITLE = "ltd.nickolay.listclick.ARG_TITLE_K"
private const val ARG_LIST = "ltd.nickolay.listclick.ARG_LIST_K"

class MainActivityK: AppCompatActivity(), DataAdapterK.OnItemListClick {

    lateinit var dataAdapter: DataAdapterK// = DataAdapterK()
    private lateinit var mList: ArrayList<DataItemK>

    //Variables for List
    var var_Title: Int = 0
    val var_SubText = arrayOf("Статически", "типизированный", "язык", "программирования",
            "работающий", "поверх JVM", "разрабатываемый", "компанией",
            "JetBrains", "Также")
    val var_Image = intArrayOf(R.drawable.ic_0, R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3,
            R.drawable.ic_4, R.drawable.ic_5, R.drawable.ic_6, R.drawable.ic_7, R.drawable.ic_8,
            R.drawable.ic_9)

    var toast: Toast? = null

    companion object{
        private const val TAG = "myLOG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null || !savedInstanceState.containsKey(ARG_LIST)) {
            mList = ArrayList()
            setupListView()
            AddItem(5)
        } else {
            mList = savedInstanceState.getParcelableArrayList(ARG_LIST)
            var_Title = savedInstanceState.getInt(ARG_TITLE)
            setupListView()

        }
        dataAdapter.onItemListClickListener  = this
    }

    fun setupListView(){
        dataAdapter = DataAdapterK(mList)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_List)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dataAdapter
    }

    fun AddItem(count: Int) {
        for (i in 0 until count) {
            dataAdapter.addItem(
                    var_Image[Random.nextInt(10)],
                    "Title ${var_Title++}",
                    var_SubText[Random.nextInt(10)])
        }
    }

    override fun onSelectItem(position: Int, title: String) {
        toast?.cancel()
        toast = Toast.makeText(this, "Clicked $title at position $position", Toast.LENGTH_LONG)
        toast!!.show()
    }

    override fun beforeDeleteItem(dataItem: DataItemK): Boolean {
        toast?.cancel()
        toast = Toast.makeText(this, "Delete item ${dataItem.title}", Toast.LENGTH_LONG)
        toast!!.show()
        Log.d(TAG, "beforeDeleteItem: $dataItem")
        //TODO add dialog for delete
        return true
    }

    fun AddFun(view: View) {
        when (view.id) {
            R.id.b_add_1 -> AddItem(1)
            R.id.b_add_10 -> AddItem(10)
            else -> Log.d(TAG, "AddFun: Error Add Click")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putInt(ARG_TITLE,var_Title)
            putParcelableArrayList(ARG_LIST, mList)
        }
        super.onSaveInstanceState(outState)
    }
}
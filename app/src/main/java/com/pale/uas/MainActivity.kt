package com.pale.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pale.uas.adapter.DataAdapter
import com.pale.uas.model.DataItem
import com.pale.uas.presenter.CrudView
import com.pale.uas.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    private lateinit var adapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)
        presenter.getData()

        btnTambah.setOnClickListener{
            startActivity<UpdateAddActivity>()
            finish()
        }

        txtSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText.toString())
                return false;
            }
        })
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        adapter = DataAdapter(data,object :
            DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                startActivity<UpdateAddActivity>("dataItem" to item)
            }
            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                startActivity<MainActivity>()
                finish()
            }
        })
        rvCategory.adapter = adapter
    }

    override fun onFailedGet(msg: String) {

    }

    override fun onSuccessDelete(msg: String) {
       presenter.getData()
    }

    override fun onErrorDelete(msg: String) {
        alert {
            title = "error Delete Data"
        }.show()
    }

    override fun successAdd(msg: String) {

    }

    override fun errorAdd(msg: String) {

    }

    override fun onSuccessUpdate(msg: String) {

    }

    override fun onErrorUpdate(msg: String) {

    }
}
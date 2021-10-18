package com.development.mychiplayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.development.mychiplayout.databinding.ActivityMainBinding
import com.libaml.android.view.chip.ChipLayout
import kotlinx.android.synthetic.main.activity_main.*

/*Todo Reference : https://github.com/AmaldevTA/ChipLayout*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val friendList= ArrayList<FriendsData>()
    var friends = ArrayList<String>()
    var nwfriends = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.fragment = this

        getFriendList()

        bt_save.setOnClickListener {
           /* nwfriends = binding.etFriends.text as ArrayList<String>
            Log.e("MainActivity","List : $nwfriends")*/
        }
    }

    private fun getFriendList() {
        friendList.add(
            FriendsData(
                "Dipanwita","0"
            )
        )
        friendList.add(
            FriendsData(
                "Nilam","1"
            )
        )
        friendList.add(
            FriendsData(
                "Tapasree","2"
            )
        )
        friendList.add(
            FriendsData(
                "Poulami","3"
            )
        )

        setKidsChipsAdapter()
    }

    private fun setKidsChipsAdapter(){
        val strFriendList = ArrayList<String>()
        for( i in 0 until friendList.size){
            strFriendList.add(friendList.get(i).name)
        }
        if(friends.size>0)
            binding.etFriends.text = getFriendNameList(friends)
        val spadapter =  ArrayAdapter(this,android.R.layout.simple_list_item_1,strFriendList)
        binding.etFriends.adapter = spadapter

        binding.etFriends.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                friends.clear()
                friends.add(friendList[position].id)
            }

        binding.etFriends.onChipItemChangeListener = object : ChipLayout.ChipItemChangeListener {
            override fun onChipAdded(pos: Int, txt: String) {
              /*  nwfriends.add(friendList[pos].name)
                Log.e("MainActivity","List : $nwfriends")*/
            }

            override fun onChipRemoved(pos: Int, txt: String) {
               // binding.etFriends.removeAllChips()
            }
        }
    }

    private fun getFriendNameList(mList:ArrayList<String>):ArrayList<String>{
        val names = ArrayList<String>()
        for(i in 0 until mList.size){
            for(j in 0 until friendList.size){
                if(mList.get(i)==friendList[j].id){
                    names.add(friendList[j].name)
                }
            }
        }
        return names
    }
}
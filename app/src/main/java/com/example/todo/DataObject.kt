package com.example.todo

object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(task: String) {
        listdata.add(CardInfo(task))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,task: String)
    {
        listdata[pos].task=task

    }
}
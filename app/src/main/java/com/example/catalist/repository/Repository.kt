package com.example.catalist.repository

import com.example.catalist.model.CatData

object Repository {
    private var mutableData = SampleData.toMutableList()

    fun allData() : List<CatData> = mutableData

    fun getById(id: String) : CatData? {
        return mutableData.find { it.id == id }
    }
    fun search(search: String) : List<CatData> {
        if(search.isEmpty())
            return mutableData
        return mutableData.filter { it.name.contains(search, ignoreCase = true) }
    }
    fun randomThreeTemeperaments(name : String) : List<String>
    {
        val cat = mutableData.find { it.name == name }
        val list = mutableListOf<String>()
        if(cat != null)
        {
            val tempList = cat.temperament.split(", ")
            val random = (0 until tempList.size).shuffled().take(3)
            for(i in random)
            {
                list.add(tempList[i])
            }
        }
        return list
    }

}
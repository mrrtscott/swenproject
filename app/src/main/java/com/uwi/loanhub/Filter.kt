package com.uwi.loanhub

class Filter (inputName: String){
    private var name: String = inputName;

    fun getFilterName(): String {
        return name;
    }

}
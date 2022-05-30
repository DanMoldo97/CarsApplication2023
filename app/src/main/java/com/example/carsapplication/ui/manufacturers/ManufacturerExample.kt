package com.example.carsapplication.ui.manufacturers

class ManufacturerExample constructor() {
    lateinit var title : String;
    lateinit var country : String;
    var imageLogo : Int = 0;

     constructor (title: String?, country: String?, imageLogo: Int) : this() {
        this.title = title!!
        this.country = country!!
        this.imageLogo = imageLogo
    }
}
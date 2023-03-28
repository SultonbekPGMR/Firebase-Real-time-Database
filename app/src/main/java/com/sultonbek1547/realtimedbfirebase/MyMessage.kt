package com.sultonbek1547.realtimedbfirebase

class MyMessage {
    var id:String? = null
    var text:String? = null
    var date:String? = null

    constructor()
    constructor(id: String?, text: String?, date: String?) {
        this.id = id
        this.text = text
        this.date = date
    }

}
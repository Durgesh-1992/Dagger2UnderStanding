package com.dsrise.mynote.data.model

data class Note(val id:Float,val title:String,val content:String,val contentImage:ByteArray?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (!contentImage.contentEquals(other.contentImage)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + contentImage.contentHashCode()
        return result
    }
}
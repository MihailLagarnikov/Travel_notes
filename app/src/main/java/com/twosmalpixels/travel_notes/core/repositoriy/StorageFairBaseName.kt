package com.twosmalpixels.travel_notes.core.repositoriy

import java.lang.StringBuilder

 val DEAFULT = "deafult"
private val SVG = "xml"


private const val SLASH = "/"
private const val POINT = "."

enum class FileFairBase(val reference: String, val extension: String, val fileName: String){

}

private fun getReferenceFile(extension: String, vararg path: String): String{
    val rez: StringBuilder = StringBuilder()
    for (name in path){
        rez.append(name)
        rez.append(SLASH)
    }
    rez.deleteCharAt(rez.length - 1)
    rez.append(POINT)
    rez.append(extension)
    return rez.toString()
}
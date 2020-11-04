package com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.twosmalpixels.travel_notes.core.repositoriy.ExpenceDataCollection
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.*
import com.twosmalpixels.travel_notes.core.repositoriy.TravelsCollection
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class LocalRepositoriy(val sharedPrefHelper: ISharedPrefHelper) : ILocalRepositoriy {

    private val DEF_INT = 0
    private val DEF_STRING = ""
    private val DEF_LONG = 0L
    private val DEF_DOUBLE = 0.0

    override fun saveNewTravel(travelsItem: TravelsItem) {
        val nameTravel = sharedPrefHelper.loadInt(TRAVELS_NUMBER, DEF_INT) + 1
        sharedPrefHelper.saveInt(TRAVELS_NUMBER, nameTravel)

        sharedPrefHelper.saveText(
            "${TravelsCollection.TITLE.name} $nameTravel",
            travelsItem.title
        )
        sharedPrefHelper.saveText(
            "${TravelsCollection.DATA_STRING.name} $nameTravel",
            travelsItem.data
        )
        sharedPrefHelper.saveLong(
            "${TravelsCollection.DATA_START.name} $nameTravel",
            travelsItem.dataStart
        )
        sharedPrefHelper.saveLong(
            "${TravelsCollection.DATA_END.name} $nameTravel",
            travelsItem.dataEnd
        )
        sharedPrefHelper.saveText(
            "${TravelsCollection.PERSON.name} $nameTravel",
            travelsItem.person
        )
        sharedPrefHelper.saveText(
            "${TravelsCollection.IMAGE.name} $nameTravel",
            travelsItem.imageUrl
        )
        sharedPrefHelper.saveText(
            "${TravelsCollection.MAIN_CURRENCY.name} $nameTravel",
            travelsItem.mainCurrencyIso
        )
        sharedPrefHelper.saveText(
            "${TravelsCollection.ADDITIONAL_CURRENCY.name} $nameTravel",
            travelsItem.additionalCurrencyIso
        )
        sharedPrefHelper.saveInt(
            "${TravelsCollection.RATES_CURRENCY.name} $nameTravel",
            travelsItem.rates
        )
    }

    override fun getAllTravels(): ArrayList<TravelsItem> {
        val nameTravel = sharedPrefHelper.loadInt(TRAVELS_NUMBER, DEF_INT)
        val listTravels = ArrayList<TravelsItem>()
        if (nameTravel > 0) {
            for (i in 1..nameTravel) {

                val localyDocName =  sharedPrefHelper.loadText(
                    "${TravelsCollection.TITLE.name} $i",
                    DEF_STRING
                ) + sharedPrefHelper.loadText(
                    "${TravelsCollection.DATA_STRING.name} $i",
                    DEF_STRING
                )

                listTravels.add(
                    TravelsItem(
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.TITLE.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.DATA_STRING.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadLong(
                            "${TravelsCollection.DATA_START.name} $i",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadLong(
                            "${TravelsCollection.DATA_END.name} $i",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.PERSON.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.IMAGE.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.MAIN_CURRENCY.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${TravelsCollection.ADDITIONAL_CURRENCY.name} $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadInt(
                            "${TravelsCollection.RATES_CURRENCY.name} $i",
                            DEF_INT
                        ),
                       localyDocName
                    )
                )
            }
        }
        return listTravels
    }

    override fun saveExpenceData(expenceData: ExpenceData, travelsName: String) {
        val nameExpence = sharedPrefHelper.loadInt(EXPENCE_NUMBER + travelsName, DEF_INT) + 1
        sharedPrefHelper.saveInt(EXPENCE_NUMBER + travelsName, nameExpence)

        sharedPrefHelper.saveLong(
            "${ExpenceDataCollection.AMOUNT.name} $travelsName $nameExpence",
            expenceData.amount
        )
        sharedPrefHelper.saveText(
            "${ExpenceDataCollection.CURRENCY.name} $travelsName $nameExpence",
            expenceData.currencyIso
        )
        sharedPrefHelper.saveLong(
            "${ExpenceDataCollection.CATEGORY.name} $travelsName $nameExpence",
            expenceData.category
        )
        sharedPrefHelper.saveText(
            "${ExpenceDataCollection.COMMENT.name} $travelsName $nameExpence",
            expenceData.comment
        )
        sharedPrefHelper.saveText(
            "${ExpenceDataCollection.DATA.name} $travelsName $nameExpence",
            expenceData.data
        )
        sharedPrefHelper.saveLong(
            "${ExpenceDataCollection.DATA_LONG.name} $travelsName $nameExpence",
            expenceData.dataLong
        )
        sharedPrefHelper.saveText(
            "${ExpenceDataCollection.IMAGEURL.name} $travelsName $nameExpence",
            expenceData.imageUrl
        )
        sharedPrefHelper.saveDouble(
            "${ExpenceDataCollection.LAT.name} $travelsName $nameExpence",
            expenceData.lat
        )
        sharedPrefHelper.saveDouble(
            "${ExpenceDataCollection.LON.name} $travelsName $nameExpence",
            expenceData.lon
        )
        sharedPrefHelper.saveBoolean(HASE_SAVE_EXPENCE_DATA, true)
        sharedPrefHelper.saveText(TRAVEL_NAME_WHAT_WE_SAVED_EXPENCE, travelsName)
    }

    override fun loadExpenceDataList(travelName: String): ArrayList<ExpenceData> {
        val nameExpence = sharedPrefHelper.loadInt(EXPENCE_NUMBER + travelName, DEF_INT)
        val listExpence = ArrayList<ExpenceData>()
        if (nameExpence > 0) {
            for (i in 1..nameExpence) {
                listExpence.add(
                    ExpenceData(
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.AMOUNT.name} $travelName $i",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.CURRENCY.name} $travelName $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.CATEGORY.name} $travelName $i",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.COMMENT.name} $travelName $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.DATA.name} $travelName $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.DATA_LONG.name} $travelName $i",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.IMAGEURL.name} $travelName $i",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadDouble(
                            "${ExpenceDataCollection.LAT.name} $travelName $i",
                            DEF_DOUBLE
                        ),
                        sharedPrefHelper.loadDouble(
                            "${ExpenceDataCollection.LON.name} $travelName $i",
                            DEF_DOUBLE
                        )
                    )
                )
            }
        }
        return listExpence
    }

    override fun saveBitmap(bitmap: Bitmap, name: String, file: File): File {

        var outStream: OutputStream? = null
        val filePng = File(file, name + ".png")
        try {
            outStream = FileOutputStream(filePng)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
        }
        return filePng
    }

    override fun loadBitmap(name: String, file: File): Bitmap {
        val filePng = File(file, name + ".png")
        return BitmapFactory.decodeFile(filePng.absolutePath)
    }
}
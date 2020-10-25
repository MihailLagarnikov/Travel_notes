package com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy

import com.twosmalpixels.travel_notes.core.repositoriy.ExpenceDataCollection
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.EXPENCE_NUMBER
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefHelper
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.TRAVELS_NUMBER
import com.twosmalpixels.travel_notes.core.repositoriy.TravelsCollection
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData

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
                        )
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


    }

    override fun loadExpenceDataList(travelName: String): ArrayList<ExpenceData> {
        val nameExpence = sharedPrefHelper.loadInt(EXPENCE_NUMBER, DEF_INT)
        val listExpence = ArrayList<ExpenceData>()
        if (nameExpence > 0) {
            for (i in 1..nameExpence) {
                listExpence.add(
                    ExpenceData(
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.AMOUNT.name} $travelName $nameExpence",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.CURRENCY.name} $travelName $nameExpence",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.CATEGORY.name} $travelName $nameExpence",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.COMMENT.name} $travelName $nameExpence",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.DATA.name} $travelName $nameExpence",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadLong(
                            "${ExpenceDataCollection.DATA_LONG.name} $travelName $nameExpence",
                            DEF_LONG
                        ),
                        sharedPrefHelper.loadText(
                            "${ExpenceDataCollection.IMAGEURL.name} $travelName $nameExpence",
                            DEF_STRING
                        ),
                        sharedPrefHelper.loadDouble(
                            "${ExpenceDataCollection.LAT.name} $travelName $nameExpence",
                            DEF_DOUBLE
                        ),
                        sharedPrefHelper.loadDouble(
                            "${ExpenceDataCollection.LON.name} $travelName $nameExpence",
                            DEF_DOUBLE
                        )
                    )
                )
            }
        }
        return listExpence
    }
}
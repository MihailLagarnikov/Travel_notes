package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.views.shedule.CategoryExpenceData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import kotlin.collections.ArrayList

class ExpenceAllAllUseCase(val iExpenceAllRepositoriy: IExpenceAllRepositoriy) :
    IExpenceAllUseCase {
    private val SUM_NUL = 0


    override fun getDefaultShdulelistSpiner(travelsItem: TravelsItem): ArrayList<SheduleSpinerData> {
        return arrayListOf(
            SheduleSpinerData(SUM_NUL, travelsItem.mainCurrencyIso),
            SheduleSpinerData(SUM_NUL, travelsItem.additionalCurrencyIso)
        )
    }

    override fun getDefaultExpenseList(): ArrayList<ExpenceData> {
        return arrayListOf(ExpenceData.getEmptyData())
    }

    override fun getExpenceList(
        db: FirebaseFirestore,
        docName: String
    ): MutableLiveData<ArrayList<ExpenceData>> {
        return iExpenceAllRepositoriy.getExpenceList(db, docName)
    }

    override fun getTotalExpenceWithCurrency(
        allExpenceList: ArrayList<ExpenceData>,
        travelsItem: TravelsItem
    ): ArrayList<SheduleSpinerData> {
        var mainCurrAmount = 0
        var additinalCurrAmount = 0
        for (expence in allExpenceList) {
            if (expence.currencyIso.equals(travelsItem.mainCurrencyIso)) {
                mainCurrAmount += expence.amount.toInt()
                additinalCurrAmount += expence.amount.toInt() / travelsItem.rates
            } else {
                additinalCurrAmount += expence.amount.toInt()
                mainCurrAmount += expence.amount.toInt() * travelsItem.rates
            }
        }
        return arrayListOf(
            SheduleSpinerData(mainCurrAmount, travelsItem.mainCurrencyIso),
            SheduleSpinerData(additinalCurrAmount, travelsItem.additionalCurrencyIso)
        )
    }

    override fun getCategoryExpenceList(
        allExpenceList: ArrayList<ExpenceData>,
        travelsItem: TravelsItem
    ): ArrayList<CategoryExpenceData> {
        if (allExpenceList.size > 0) {
            return getCatExpList(allExpenceList, travelsItem)
        } else {
            return getStartDataExpenceList()
        }
    }

    private fun getCatExpList(
        allExpenceList: ArrayList<ExpenceData>,
        travelsItem: TravelsItem
    ): ArrayList<CategoryExpenceData> {
        var transportAmount = 0
        var homeAmount = 0
        var cafeAmount = 0
        var supermarketAmount = 0
        var tourAmount = 0
        var entertainmentAmount = 0
        var giftsAmount = 0
        var shoppingAmount = 0
        var safetyAmount = 0
        var visaAmount = 0
        var othersAmount = 0

        var transportAmountAdditional = 0
        var homeAmountAdditional = 0
        var cafeAmountAdditional = 0
        var supermarketAmountAdditional = 0
        var tourAmountAdditional = 0
        var entertainmentAmountAdditional = 0
        var giftsAmountAdditional = 0
        var shoppingAmountAdditional = 0
        var safetyAmountAdditional = 0
        var visaAmountAdditional = 0
        var othersAmountAdditional = 0

        var transportHeight = 0
        var homeHeight = 0
        var cafeHeight = 0
        var supermarketHeight = 0
        var tourHeight = 0
        var entertainmentHeight = 0
        var giftsHeight = 0
        var shoppingHeight = 0
        var safetyHeight = 0
        var visaHeight = 0
        var othersHeight = 0
        for (expence in allExpenceList) {
            val mainCurrAmount =
                if (expence.currencyIso.equals(travelsItem.mainCurrencyIso)) {
                    expence.amount.toInt()
                } else {
                    expence.amount.toInt() * travelsItem.rates
                }
            val additionalCurrAmount =
                if (expence.currencyIso.equals(travelsItem.additionalCurrencyIso)) {
                    expence.amount.toInt()
                } else {
                    expence.amount.toInt() / travelsItem.rates
                }
            when (expence.category.toInt()) {
                ExpenceCategory.TRANSPORT.number -> {
                    transportAmount += mainCurrAmount
                    transportAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.HOME.number -> {
                    homeAmount += mainCurrAmount
                    homeAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.CAFE.number -> {
                    cafeAmount += mainCurrAmount
                    cafeAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.SUPERMARKET.number -> {
                    supermarketAmount += mainCurrAmount
                    supermarketAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.TOUR.number -> {
                    tourAmount += mainCurrAmount
                    tourAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.ENTETAIMENT.number -> {
                    entertainmentAmount += mainCurrAmount
                    entertainmentAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.GIFT.number -> {
                    giftsAmount += mainCurrAmount
                    giftsAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.SHOPING.number -> {
                    shoppingAmount += mainCurrAmount
                    shoppingAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.INSURANCE.number -> {
                    safetyAmount += mainCurrAmount
                    safetyAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.VISA.number -> {
                    visaAmount += mainCurrAmount
                    visaAmountAdditional += additionalCurrAmount
                }
                ExpenceCategory.PTHER.number -> {
                    othersAmount += mainCurrAmount
                    othersAmountAdditional += additionalCurrAmount
                }
            }
        }


        val rezultList = ArrayList<CategoryExpenceData>()
        if (transportAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.TRANSPORT.logoWhite,
                    0F,
                    ExpenceCategory.TRANSPORT.text,
                    transportAmount,
                    transportAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (homeAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.HOME.logoWhite,
                    0F,
                    ExpenceCategory.HOME.text,
                    homeAmount,
                    homeAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (cafeAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.CAFE.logoWhite,
                    0F,
                    ExpenceCategory.CAFE.text,
                    cafeAmount,
                    cafeAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (supermarketAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.SUPERMARKET.logoWhite,
                    0F,
                    ExpenceCategory.SUPERMARKET.text,
                    supermarketAmount,
                    supermarketAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (tourAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.TOUR.logoWhite,
                    0F,
                    ExpenceCategory.TOUR.text,
                    tourAmount,
                    tourAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (entertainmentAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.ENTETAIMENT.logoWhite,
                    0F,
                    ExpenceCategory.ENTETAIMENT.text,
                    entertainmentAmount,
                    entertainmentAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (giftsAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.GIFT.logoWhite,
                    0F,
                    ExpenceCategory.GIFT.text,
                    giftsAmount,
                    giftsAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (shoppingAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.SHOPING.logoWhite,
                    0F,
                    ExpenceCategory.SHOPING.text,
                    shoppingAmount,
                    shoppingAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (safetyAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.INSURANCE.logoWhite,
                    0F,
                    ExpenceCategory.INSURANCE.text,
                    safetyAmount,
                    safetyAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (visaAmount != 0) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.VISA.logoWhite,
                    0F,
                    ExpenceCategory.VISA.text,
                    visaAmount,
                    visaAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        rezultList.sortByDescending { it.mainCurrAmount }
        if (rezultList.get(0).mainCurrAmount != 0) {
            rezultList.get(0).height = 1F
            for (category in rezultList) {
                if (category.height == 0F && category.mainCurrAmount != 0) {
                    category.height =
                        (category.mainCurrAmount.toFloat() / rezultList.get(0).mainCurrAmount.toFloat())
                }

            }
        }
        return rezultList
    }

    private fun getStartDataExpenceList(): ArrayList<CategoryExpenceData> {
        return arrayListOf(
            CategoryExpenceData(
                R.drawable.ic_icon_transport,
                1F, ,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_home,
                DEF_HEIGHT,
                R.string.booking,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_cafe,
                DEF_HEIGHT,
                R.string.cafe_and_restorans,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_supermarket,
                DEF_HEIGHT,
                R.string.supermarket,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_tour,
                DEF_HEIGHT,
                R.string.tour,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_entertainment,
                DEF_HEIGHT,
                R.string.entertainment,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_gifts,
                DEF_HEIGHT,
                R.string.gifts,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_shopping,
                DEF_HEIGHT,
                R.string.shopping,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_safety,
                DEF_HEIGHT,
                R.string.safety,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_visa,
                DEF_HEIGHT,
                R.string.visa,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            ),
            SheduleData(
                R.drawable.ic_icon_others,
                DEF_HEIGHT,
                R.string.others,
                DEF_LIST_AMOUNT,
                DEF_LIST_CURENCY
            )
        )
    }
}
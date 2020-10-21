package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.DEF_EMPTY_STRING
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.views.shedule.CategoryExpenceData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import kotlin.collections.ArrayList

class ExpenceAllAllUseCase(val iExpenceAllRepositoriy: IExpenceAllRepositoriy) :
    IExpenceAllUseCase {
    private val SUM_NUL = 0
    private val MAX_HEIGHT = 1F
    private val EMPTY_AMOUNT = 0F


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
        var transportAmount = EMPTY_AMOUNT
        var homeAmount = EMPTY_AMOUNT
        var cafeAmount = EMPTY_AMOUNT
        var supermarketAmount = EMPTY_AMOUNT
        var tourAmount = EMPTY_AMOUNT
        var entertainmentAmount = EMPTY_AMOUNT
        var giftsAmount = EMPTY_AMOUNT
        var shoppingAmount = EMPTY_AMOUNT
        var safetyAmount = EMPTY_AMOUNT
        var visaAmount = EMPTY_AMOUNT
        var othersAmount = EMPTY_AMOUNT

        var transportAmountAdditional = EMPTY_AMOUNT
        var homeAmountAdditional = EMPTY_AMOUNT
        var cafeAmountAdditional = EMPTY_AMOUNT
        var supermarketAmountAdditional = EMPTY_AMOUNT
        var tourAmountAdditional = EMPTY_AMOUNT
        var entertainmentAmountAdditional = EMPTY_AMOUNT
        var giftsAmountAdditional = EMPTY_AMOUNT
        var shoppingAmountAdditional = EMPTY_AMOUNT
        var safetyAmountAdditional = EMPTY_AMOUNT
        var visaAmountAdditional = EMPTY_AMOUNT
        var othersAmountAdditional = EMPTY_AMOUNT

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
        if (transportAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.TRANSPORT.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.TRANSPORT.text,
                    transportAmount,
                    transportAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (homeAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.HOME.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.HOME.text,
                    homeAmount,
                    homeAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (cafeAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.CAFE.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.CAFE.text,
                    cafeAmount,
                    cafeAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (supermarketAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.SUPERMARKET.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.SUPERMARKET.text,
                    supermarketAmount,
                    supermarketAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (tourAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.TOUR.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.TOUR.text,
                    tourAmount,
                    tourAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (entertainmentAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.ENTETAIMENT.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.ENTETAIMENT.text,
                    entertainmentAmount,
                    entertainmentAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (giftsAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.GIFT.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.GIFT.text,
                    giftsAmount,
                    giftsAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (shoppingAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.SHOPING.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.SHOPING.text,
                    shoppingAmount,
                    shoppingAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (safetyAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.INSURANCE.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.INSURANCE.text,
                    safetyAmount,
                    safetyAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        if (visaAmount != EMPTY_AMOUNT) {
            rezultList.add(
                CategoryExpenceData(
                    ExpenceCategory.VISA.logoWhite,
                    EMPTY_AMOUNT,
                    ExpenceCategory.VISA.text,
                    visaAmount,
                    visaAmountAdditional,
                    travelsItem.mainCurrencyIso,
                    travelsItem.additionalCurrencyIso
                )
            )
        }
        rezultList.sortByDescending { it.mainCurrAmount }
        if (!rezultList.isEmpty() && rezultList.get(0).mainCurrAmount != EMPTY_AMOUNT) {
            rezultList.get(0).height = MAX_HEIGHT
            for (category in rezultList) {
                if (category.height == 0F && category.mainCurrAmount != EMPTY_AMOUNT) {
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
                MAX_HEIGHT,
                R.string.transport,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_home,
                MAX_HEIGHT,
                R.string.booking,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_cafe,
                MAX_HEIGHT,
                R.string.cafe_and_restorans,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_supermarket,
                MAX_HEIGHT,
                R.string.supermarket,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_tour,
                MAX_HEIGHT,
                R.string.tour,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_entertainment,
                MAX_HEIGHT,
                R.string.entertainment,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_gifts,
                MAX_HEIGHT,
                R.string.gifts,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_shopping,
                MAX_HEIGHT,
                R.string.shopping,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_safety,
                MAX_HEIGHT,
                R.string.safety,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_visa,
                MAX_HEIGHT,
                R.string.visa,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            ),
            CategoryExpenceData(
                R.drawable.ic_icon_others,
                MAX_HEIGHT,
                R.string.others,
                EMPTY_AMOUNT,
                EMPTY_AMOUNT,
                DEF_EMPTY_STRING,
                DEF_EMPTY_STRING
            )
        )
    }
}
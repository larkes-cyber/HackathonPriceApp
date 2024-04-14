package com.larkes.hackathonpriceapp.android.viewmodels.main.models

import android.graphics.Bitmap

sealed class MainAction {
    data object OpenEditPriceSheet:MainAction()
    data object OpenCompletedSheet:MainAction()
    data object ClosePriceSheet:MainAction()
    data object BackInPriceSheet:MainAction()


}


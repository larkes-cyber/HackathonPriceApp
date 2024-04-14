//
//  MainViewModel.swift
//  iosApp
//
//  Created by MacBook on 14.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

class MainViewModel:ObservableObject{
    
    @Published var isEditSheetPresented = false
    @Published var error = ""
    @Published var scannedPrice:ScannedPrice? = nil
    @Published var stores:[Store] = []
    @Published var selectedStore:Store?
    @Published var isLoading:Bool = false
    @Published var isSuccessPresented:Bool = false

    init() {
        InjectUseCase().useFetchStores.execute(completionHandler: {res, err in
            self.stores = res?.data as! [Store] as [Store]
        })
    }

    
    func sendPhoto(bytes:[UInt8]){
        DispatchQueue.main.async {
            self.isLoading = true
            InjectUseCase().useSendPricePhoto.execute(
                byteArray: KotlinByteArray.from(data: Data(bytes)),
                completionHandler: {res, err in
                    print(res?.data)
                    print(res?.message)

                    self.error = res?.message ?? ""
                    self.scannedPrice = res?.data
                    self.isEditSheetPresented = true
                    self.isLoading = false
                }
            )
        }
    }
    
    func onScannedPhotoChanged(price:ScannedPrice){
        scannedPrice = price
    }
    
    func selectStore(store:Store){
        selectedStore = store
    }
    
    func done(){
        self.isLoading = true

        InjectUseCase().usePerformPrice.execute(performedPrice: PerformedPrice(price: scannedPrice!.price, category: scannedPrice!.category, store: selectedStore?.location ?? "", name: scannedPrice!.name), completionHandler: {res, err in
            self.isEditSheetPresented = false
            self.isSuccessPresented = true
            self.isLoading = false
        })
    }
    
}

extension KotlinByteArray {
    static func from(data: Data) -> KotlinByteArray {
        let swiftByteArray = [UInt8](data)
        return swiftByteArray
            .map(Int8.init(bitPattern:))
            .enumerated()
            .reduce(into: KotlinByteArray(size: Int32(swiftByteArray.count))) { result, row in
                result.set(index: Int32(row.offset), value: row.element)
            }
    }
}

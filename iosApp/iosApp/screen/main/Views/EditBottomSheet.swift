//
//  EditBottomSheet.swift
//  iosApp
//
//  Created by MacBook on 14.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct EditBottomSheet: View {
    
    let scannedPrice:ScannedPrice?
    let isLoading:Bool
    let error:String
    let onPriceChange:(ScannedPrice) -> Void
    let stores:[Store]
    let onStoreSelected:(Store) -> Void
    let confirm:()->Void

    @State var selection:String
    init(scannedPrice: ScannedPrice?, isLoading: Bool, error: String, onPriceChange: @escaping (ScannedPrice) -> Void, stores: [Store], onStoreSelected: @escaping (Store) -> Void, confirm: @escaping () -> Void, selection: String) {
        self.scannedPrice = scannedPrice
        self.isLoading = isLoading
        self.error = error
        self.onPriceChange = onPriceChange
        self.stores = stores
        self.onStoreSelected = onStoreSelected
        self.confirm = confirm
        self.selection = selection
    }
 
    
    var body: some View {
        if(scannedPrice != nil){
            VStack{
                
                Picker("Выберете магазин", selection: $selection) {
                    ForEach(stores, id: \.self.id) {
                        Text($0.location)
                   }
                }
               .pickerStyle(.menu)
               .onChange(of: selection){tag in
                   //onStoreSelected(tag)
               }
                
                let name = scannedPrice?.name ?? ""
                
                CommonTextField(hint: String(name.isEmpty ? "Имя не распознано" : name ), onValueChanged: {name in
                    onPriceChange(ScannedPrice(id: scannedPrice!.id, name: name, category: scannedPrice!.category, price: scannedPrice!.price))
                })
                CommonTextField(hint: String(scannedPrice?.price ?? 0), onValueChanged: {price in
                    onPriceChange(ScannedPrice(id: scannedPrice!.id, name: scannedPrice!.name, category: scannedPrice!.category, price: Float(price)!))
                })
                CommonTextField(hint: String(scannedPrice?.category ?? ""), onValueChanged: {category in
                    onPriceChange(ScannedPrice(id: scannedPrice!.id, name: scannedPrice!.name, category: category, price: scannedPrice!.price))
                })
                
                Spacer()
                    .frame(height: 40)
                
                
                ActionButton(title: "Подтвердить", action: {
                    confirm()
                })
                .frame(height: 42)
                
            
            }
            .padding(.horizontal, 20)
        }
    }
}




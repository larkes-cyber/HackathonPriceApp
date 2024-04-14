//
//  MainScreen.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    @StateObject private var handler = ContentHandler()
    @ObservedObject private var viewModel = MainViewModel()

    
    
    
    var body: some View {
        
        
        VStack{
            FrameView(image: handler.frame){bytes in
                viewModel.sendPhoto(bytes: bytes)
            }
            .edgesIgnoringSafeArea(.all)
            .sheet(isPresented: $viewModel.isEditSheetPresented){
            EditBottomSheet(
                scannedPrice: viewModel.scannedPrice,
                isLoading: viewModel.isLoading, error: viewModel.error,
                onPriceChange: {viewModel.onScannedPhotoChanged(price: $0)},
                stores: viewModel.stores,
                onStoreSelected: {viewModel.selectStore(store: $0)},
                confirm: {viewModel.done()}, selection: viewModel.stores[0].location
                )
            }
        }
        .alert("Ваша заявка была успешно отправлена!", isPresented: $viewModel.isSuccessPresented) {
            
        }
        
    
    }
   
    
}

#Preview {
    MainScreen()
}

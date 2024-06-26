//
//  SplashScreen.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SplashScreen: View {
    
    @ObservedObject private var viewModel = SplashViewModel()
    
    var body: some View {
        ZStack{
            ProgressView()
                .frame(width: 50, height: 50)
        }
        .fullScreenCover(isPresented: $viewModel.isAuthPresented){
            let _ = print("ddddddd")
            AuthScreen()
        }
        .fullScreenCover(isPresented: $viewModel.isMainPresented){
            MainScreen()
        }
    }
}

#Preview {
    SplashScreen()
}

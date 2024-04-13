//
//  SplashViewModel.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

class SplashViewModel:ObservableObject{
    
    @Published var isAuthPresented = false
    @Published var isMainPresented = false
    
    
    init() {
        InjectUseCase().useCheckAuth.execute(completionHandler: {data, err in
            if(data?.data == nil){
                self.isAuthPresented = true
            }else{
                self.isMainPresented = true
            }
        })
       }
    
}

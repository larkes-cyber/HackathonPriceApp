//
//  MainScreen.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    @State private var isCameraPresented = true
    
    var body: some View {
        
        CameraView()
    }
        
}

#Preview {
    MainScreen()
}

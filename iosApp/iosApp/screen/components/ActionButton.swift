//
//  AuthPrimaryButton.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ActionButton: View {
    
    let title:LocalizedStringKey
       var enabled:Bool = true
       let action:() -> Void
       
       var body: some View {
           ZStack{
               RoundedRectangle(cornerRadius: 10)
                   .foregroundColor(.blue)
                   .opacity(enabled ? 1.0 : 0.5)
               Text(title)
                   .foregroundColor(.white)
           }
           .frame(maxWidth: .infinity, minHeight: 56)
           .onTapGesture {
               action()
           }
       }
    
}

//
//  AuthTabButton.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AuthTabButton: View {
    
    let title:LocalizedStringKey
    let isSelected:Bool
    let onClick:()->Void
    
    var body: some View {
        ZStack{
            RoundedRectangle(cornerRadius: 10)
                .foregroundColor(isSelected ? .blue : .white)
            
            Text(title)
                .foregroundColor(isSelected ? .white : .black)
        }
        .frame(maxWidth: .infinity, minHeight: 40)
        .overlay(
              RoundedRectangle(cornerRadius: 10)
                  .stroke(Color.blue, lineWidth: 2)
          )
        .onTapGesture {
            if(!isSelected){onClick()}
        }
    }
}

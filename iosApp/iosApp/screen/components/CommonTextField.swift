//
//  CommonTextField.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CommonTextField: View {
    
    
    @State private var value = ""
    private let hint:String
    private let enabled:Bool
    private let isSecure:Bool
    private let onValueChanged:(String) -> Void

    init(hint: String, enabled:Bool = true, isSecure:Bool = false, onValueChanged:@escaping (String) -> Void) {
          self.hint = hint
          self.enabled = enabled
          self.isSecure = isSecure
          self.onValueChanged = onValueChanged
      }
    
    var body: some View {
        ZStack(alignment:.leading){
                   RoundedRectangle(cornerRadius: 10.0)
                .foregroundColor(.white)
                   
                   if(value.isEmpty){
                       Text(hint)
                           .foregroundColor(.gray.opacity(0.5))
                           .padding(EdgeInsets(top: 20, leading: 0, bottom: 20, trailing: 0))
                           .font(.system(size: 16))
                   }
                   
                   if(isSecure){
                       SecureField("", text: $value)
                           .foregroundColor(.gray)
                           .font(.system(size: 16))
                           .autocapitalization(.none)
                           .disableAutocorrection(true)
                           .disabled(!self.enabled)
                           .onChange(of: value){ newValue in
                               onValueChanged(newValue)
                           }
                   }else{
                       TextField("", text: $value)
                           .foregroundColor(.gray)
                           .font(.system(size: 16))
                           .autocapitalization(.none)
                           .disableAutocorrection(true)
                           .disabled(!self.enabled)
                           .onChange(of: value){ newValue in
                               onValueChanged(newValue)
                           }
                   }
                   
               }
               .frame(height: 56)
    }
}

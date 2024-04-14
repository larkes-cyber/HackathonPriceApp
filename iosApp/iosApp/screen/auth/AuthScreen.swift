//
//  AuthScreen.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AuthScreen: View {
    
    @ObservedObject private var viewModel = AuthViewModel()
    
    var body: some View {
        VStack{
            HStack{
                Text(viewModel.isRegistration ? "Регистрация" : "Вход")
                    .font(.largeTitle)
                Spacer()
            }
            Spacer().frame(height: 30)
            if(!viewModel.isRegistration){
                HStack{
                    AuthTabButton(title: "Почта", isSelected: viewModel.isEmailMethod, onClick: {
                        viewModel.obtainEmailMethodChanged()
                    })
                    Spacer()
                    AuthTabButton(title: "Телефон", isSelected: !viewModel.isEmailMethod, onClick: {
                        viewModel.obtainEmailMethodChanged()
                    })
                }
                .frame(height: 45)
            }
            Spacer().frame(height: 20)
            if(viewModel.isRegistration){
                CommonTextField(hint: "Введите почту", onValueChanged: {email in
                    viewModel.obtainEmailChanged(email: email)
                }, initValue: "")
                Spacer().frame(height: 10)
                CommonTextField(hint: "Введите номер", onValueChanged: {number in
                    viewModel.obtainNumberChanged(number: number)
                }, initValue: "")
                Spacer().frame(height: 10)
            }else{
                CommonTextField(hint: viewModel.isEmailMethod ? "Введите почту" : "Введите номер", onValueChanged: {login in
                    if(viewModel.isEmailMethod){
                        viewModel.obtainEmailChanged(email: login)
                    }else{
                        viewModel.obtainNumberChanged(number: login)
                    }
                }, initValue: "")
            }
            
            ZStack{
                CommonTextField(hint: "Введите пароль", isSecure: viewModel.passwordHidden, onValueChanged: {password in
                    viewModel.obtainPasswordChanged(password: password)
                }, initValue: "")
                HStack{
                    Spacer()
                    Button(action: {
                        viewModel.obtainPasswordHiddenChanged()
                    }, label: {
                        Image(systemName: viewModel.passwordHidden ? "eye.slash" : "eye")
                    })
                   
                }
              
            }
            
            Spacer().frame(height: 40)

            ActionButton(title: viewModel.isRegistration ? "Регистрация" : "Войти",enabled: !viewModel.isLoading, action: {
                viewModel.obtainDone()
            })
            .frame(height: 45)
            
            Spacer().frame(height: 30)

            Button(action: {
                viewModel.obtainRegistrationModeChanged()
            }, label: {
                Text(viewModel.isRegistration ? "Войти" : "Регистрация")
            })
            
            Spacer().frame(height: 30)
            Text(viewModel.error)
                .foregroundColor(Color.red)
            
            
        }
        .padding(.horizontal, 20)
        .fullScreenCover(isPresented: $viewModel.isSplashPresented){
            SplashScreen()
        }
    }
}

#Preview {
    AuthScreen()
}

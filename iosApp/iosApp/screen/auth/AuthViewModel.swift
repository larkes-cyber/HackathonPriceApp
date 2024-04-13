//
//  AuthViewModel.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class AuthViewModel:ObservableObject{
    
    @Published var isRegistration = false
    @Published var isEmailMethod = true
    @Published var email = ""
    @Published var password = ""
    @Published var number = ""
    @Published var passwordHidden = false
    @Published var error = ""
    @Published var isLoading = false
    @Published var isSplashPresented = false
    
    
    func obtainRegistrationModeChanged(){
        self.isRegistration = !isRegistration
        email = ""
        password = ""
        number = ""
    }
    
    func obtainEmailMethodChanged(){
        self.isEmailMethod = !isEmailMethod
    }
    
    func obtainNumberChanged(number:String){
        self.number = number
    }
    
    func obtainEmailChanged(email:String){
        self.email = email
    }
    
    func obtainPasswordChanged(password:String){
        self.password = password
    }
    
    func obtainPasswordHiddenChanged(){
        self.passwordHidden = !passwordHidden
    }
    
    func obtainDone(){
        isLoading = true
        if(isRegistration){
            let authData = AuthData(number: number, email: email, password: password)
            InjectUseCase().useRegistrationUser.execute(authData: authData, completionHandler: {res, err in
                if(res?.data == nil){
                    self.error = "Неверный логин или пароль"
                }else{
                    InjectUseCase().useLoginUser.execute(authData: authData, completionHandler: {res, err in
                            if(res?.data != nil){
                                self.isSplashPresented = true
                            }else{
                                self.error = "Неверный логин или пароль"
                            }
                            self.isLoading = false
                    })
                }
            })
        }else{
            InjectUseCase().useLoginUser.execute(authData: AuthData(number: number, email: email, password: password), completionHandler: {res, err in
                    if(res?.data != nil){
                        self.isSplashPresented = true
                    }else{
                        self.error = "Неверный логин или пароль"
                    }
                    self.isLoading = false
            })
        }
    }
    
}

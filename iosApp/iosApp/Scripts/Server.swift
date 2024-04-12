//
//  Server.swift
//  iosApp
//
//  Created by Alexander Skorokhodov on 12.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation


struct Server {
    //App Constants
    static let API = "https://example.com/upload";
}

func sendDataToServer(data: Data) {
    // URL сервера, куда будут отправляться данные
    guard let url = URL(string: Server.API) else {
        print("Invalid URL")
        return
    }
    
    // Создание запроса
    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    
    // Добавление данных к запросу
    request.httpBody = data
    
    // Создание сессии URLSession
    let session = URLSession.shared
    
    // Создание задачи для отправки данных
    let task = session.dataTask(with: request) { data, response, error in
        // Проверка наличия ошибки
        if let error = error {
            print("Error: \(error.localizedDescription)")
            return
        }
        
        // Проверка полученного ответа от сервера
        guard let httpResponse = response as? HTTPURLResponse else {
            print("Invalid response")
            return
        }
        
        // Проверка статус кода ответа
        if (200...299).contains(httpResponse.statusCode) {
            print("Data sent successfully")
        } else {
            print("Error response: \(httpResponse.statusCode)")
        }
    }
    
    // Запуск задачи
    task.resume()
}

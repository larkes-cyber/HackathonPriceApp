//
//  FrameView.swift
//  iosApp
//
//  Created by Alexander Skorokhodov on 12.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI




struct FrameView: View {
    var image: CGImage?
    private let label = Text("Camera feed")
    
    func takePhoto() async {
        var taken_photo = UIImage(cgImage: image!)
        var _bytes = getArrayOfBytesFromImage(imageData:  taken_photo.pngData()! as NSData)
        print("Bytes fetched")
        sendDataToServer(data: Data(_bytes))
    }
    

    var body: some View {
        if let image = image {
          // 2
          GeometryReader { geometry in
            // 3
              ZStack{
                  Image(image, scale: 1.0, orientation: .upMirrored, label: label)
                      .resizable()
                      .scaledToFill()
                      .frame(
                        width: geometry.size.width,
                        height: geometry.size.height,
                        alignment: .center)
                      .clipped()
                  VStack {
                                      Spacer()
                                      Button(action: { Task{
                                        await takePhoto()}
                                      }) {
                                          Image(systemName: "camera.circle.fill")
                                              .resizable()
                                              .aspectRatio(contentMode: .fit)
                                              .frame(width: 64, height: 64)
                                              .foregroundColor(.white)
                                      }
                                      .padding(.bottom, 20)
                                  }

              }
          }
        } else {
          // 4
          Color.black
        }
    }
}

#Preview {
    FrameView()
}

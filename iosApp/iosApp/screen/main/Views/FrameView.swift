//
//  FrameView.swift
//  iosApp
//
//  Created by MacBook on 13.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct FrameView: View {
    var image: CGImage?
    private let label = Text("Camera feed")
    
    func takePhoto() async {
        var taken_photo = UIImage(cgImage: image!)
        var _bytes = getArrayOfBytesFromImage(imageData:  taken_photo.pngData()! as NSData)
        print(_bytes)
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

func getArrayOfBytesFromImage(imageData:NSData) -> Array<UInt8>
{

  // the number of elements:
  let count = imageData.length / MemoryLayout<Int8>.size

  // create array of appropriate length:
  var bytes = [UInt8](repeating: 0, count: count)

  // copy bytes into array
  imageData.getBytes(&bytes, length:count * MemoryLayout<Int8>.size)

  var byteArray:Array = Array<UInt8>()

  for i in 0 ..< count {
    byteArray.append(bytes[i])
  }

  return byteArray


}

#Preview {
    FrameView()
}

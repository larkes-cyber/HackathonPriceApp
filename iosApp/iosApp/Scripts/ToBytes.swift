//
//  ToBytes.swift
//  iosApp
//
//  Created by Alexander Skorokhodov on 12.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

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

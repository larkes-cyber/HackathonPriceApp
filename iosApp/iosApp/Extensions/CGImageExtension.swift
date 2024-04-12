//
//  CGImageExtension.swift
//  iosApp
//
//  Created by Alexander Skorokhodov on 12.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import CoreGraphics
import VideoToolbox

extension CGImage {
  static func create(from cvPixelBuffer: CVPixelBuffer?) -> CGImage? {
    guard let pixelBuffer = cvPixelBuffer else {
      return nil
    }

    var image: CGImage?
    VTCreateCGImageFromCVPixelBuffer(
      pixelBuffer,
      options: nil,
      imageOut: &image)
    return image
  }
}

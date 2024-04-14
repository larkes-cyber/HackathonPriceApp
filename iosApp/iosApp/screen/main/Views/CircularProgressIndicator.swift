//
//  CircularProgressIndicator.swift
//  iosApp
//
//  Created by MacBook on 14.04.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CircularProgressIndicator: View {
    @State private var isAnimating = false

        
        var body: some View {
            Circle()
                .trim(from: 0, to: 0.7)
                .stroke(style: StrokeStyle(lineWidth: 5, lineCap: .round, lineJoin: .round))
                .foregroundColor(.blue)
                .rotationEffect(Angle(degrees: isAnimating ? 360 : 0))
                .animation(Animation.linear(duration: 1.0).repeatForever(autoreverses: false))
                .onAppear {
                    self.isAnimating = true
                }

        }
}

#Preview {
    CircularProgressIndicator()
}

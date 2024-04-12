////
////  CameraView.swift
////  iosApp
////
////  Created by Alexander Skorokhodov on 12.04.2024.
////  Copyright © 2024 orgName. All rights reserved.
////
//
//import SwiftUI
//import AVFoundation
//
//struct CameraView: View {
//    @State private var showCaptureButton = true
//    @State private var image: UIImage?
//    let captureSession = AVCaptureSession()
//    let photoOutput = AVCapturePhotoOutput()
//    
//    var body: some View {
//        ZStack {
//            CameraPreview(session: captureSession)
//                .edgesIgnoringSafeArea(.all)
//                .onAppear {
//                    setupCamera()
//                }
//            
//            if showCaptureButton {
//                VStack {
//                    Spacer()
//                    Button(action: {
//                        takePhoto()
//                    }) {
//                        Image(systemName: "camera.circle.fill")
//                            .resizable()
//                            .aspectRatio(contentMode: .fit)
//                            .frame(width: 64, height: 64)
//                            .foregroundColor(.white)
//                    }
//                    .padding(.bottom, 20)
//                }
//            }
//        }
//    }
//    
//    func setupCamera() {
//        guard let device = AVCaptureDevice.default(for: .video) else { return }
//        
//        do {
//            let input = try AVCaptureDeviceInput(device: device)
//            if captureSession.canAddInput(input) {
//                captureSession.addInput(input)
//                if captureSession.canAddOutput(photoOutput) {
//                    captureSession.addOutput(photoOutput)
//                    captureSession.startRunning()
//                }
//            }
//        } catch {
//            print(error.localizedDescription)
//        }
//    }
//    
//    func takePhoto() {
//        photoOutput.capturePhoto(with: AVCapturePhotoSettings(), delegate: PhotoCaptureDelegate { image in
//            self.image = image
//            self.showCaptureButton = false
//        })
//    }
//}
//
//struct CameraPreview: UIViewRepresentable {
//    let session: AVCaptureSession
//    
//    func makeUIView(context: Context) -> some UIView {
//        let previewLayer = AVCaptureVideoPreviewLayer(session: session)
//        previewLayer.videoGravity = .resizeAspectFill
//        return UIView(frame: UIScreen.main.bounds)
//    }
//    
//    func updateUIView(_ uiView: UIViewType, context: Context) {
//        guard let layer = uiView.layer as? AVCaptureVideoPreviewLayer else { return }
//        layer.session = session
//    }
//}
//
///*struct*/ PhotoCaptureDelegate: AVCapturePhotoCaptureDelegate {
//    let completionHandler: (UIImage?) -> Void
//    
//    func photoOutput(_ output: AVCapturePhotoOutput, didFinishProcessingPhoto photo: AVCapturePhoto, error: Error?) {
//        if let imageData = photo.fileDataRepresentation(), let image = UIImage(data: imageData) {
//            completionHandler(image)
//        } else {
//            completionHandler(nil)
//        }
//    }
//}
//
//struct CameraView_Previews: PreviewProvider {
//    static var previews: some View {
//        CameraView()
//    }
//}

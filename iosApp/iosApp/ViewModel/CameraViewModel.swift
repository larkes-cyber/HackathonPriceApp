//
//  CameraViewModel.swift
//  Golf
//
//  Created by Alexander Skorokhodov on 26.08.2023.
//

import SwiftUI
import AVFoundation

class CameraViewModel: NSObject, ObservableObject, AVCaptureFileOutputRecordingDelegate{
    
    @Published var session = AVCaptureSession()
    
    @Published var alert = false
    
    // read pic data
    @Published var output = AVCaptureMovieFileOutput()
    
    // preview
    @Published var preview : AVCaptureVideoPreviewLayer!
    
    @Published var isRecording: Bool = false
    @Published var recordedURLs: [URL] = []
    @Published var previewURL: URL?
    @Published var showPreview: Bool = false
    
    @Published var isRecordingExists: Bool = false
    
    
    func checkPermission() {
        
        // checking camera model has got permission
        switch AVCaptureDevice.authorizationStatus(for: .video) {
        case .authorized:
            setUp()
            return
            // setting up session
        case .notDetermined:
            AVCaptureDevice.requestAccess(for: .video) {
                (status) in if status {
                    self.setUp()
                }
            }
        case .denied:
            self.alert.toggle()
            return
        
        
        default:
            return
        }
    }
    
    func setUp() {
        
        
        do {
            self.session.beginConfiguration()
            
            let cameraDevice = AVCaptureDevice.default(.builtInDualCamera, for: .video, position: .back)
            let videoInput = try AVCaptureDeviceInput(device: cameraDevice!)
            
            let audioDevice = AVCaptureDevice.default(for: .audio)
            let audioInput = try AVCaptureDeviceInput(device: audioDevice!)
            
            if self.session.canAddInput(videoInput) && self.session.canAddInput(audioInput) {
                self.session.addInput(videoInput)
                self.session.addInput(audioInput)
            }
            
            if self.session.canAddOutput(self.output) {
                self.session.addOutput(self.output)
            }
            
            self.session.commitConfiguration()
        } catch {
            print(error.localizedDescription)
            
        }
        
    }
    
    func startRecording() {
        let tempURL = NSTemporaryDirectory() + "\(Date()).mp4"
        output.startRecording(to: URL(fileURLWithPath: tempURL), recordingDelegate: self)
        isRecording = true
    }
    
    func stopRecording() {
        output.stopRecording()
        isRecording = false
        isRecordingExists = true
    }
    
    func fileOutput(_ output: AVCaptureFileOutput, didFinishRecordingTo outputFileURL: URL, from connections: [AVCaptureConnection], error: Error?) {
        if let error = error {
            print(error.localizedDescription)
            return
        }
        print(outputFileURL)
        self.previewURL = outputFileURL
    }
}

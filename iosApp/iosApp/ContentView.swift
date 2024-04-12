import SwiftUI
import shared



struct ContentView: View {

    let rep = InjectRepositories().testRepository.test()
    @StateObject private var model = ContentViewModel()

	var body: some View {
//        Text(rep)
        FrameView(image: model.frame)
          .edgesIgnoringSafeArea(.all)
        
//        CameraView()
         

	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

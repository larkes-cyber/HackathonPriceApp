import SwiftUI
import shared


struct ContentView: View {

    let rep = InjectRepositories().testRepository.test()
    
	var body: some View {
        Text(rep)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

//
//  ContentView.swift
//  BlogApp
//
//  Created by RPS on 06/06/24.
//
import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack {
                Spacer()
                
                Text("Welcome to Euro 2024!")
                    .font(.largeTitle)
                    .foregroundColor(.white)
                    .padding()
                
                Spacer()
                
                NavigationLink(destination: DetailsView()) {
                    Text("Learn More")
                        .font(.title2)
                        .foregroundColor(.white)
                        .padding()
                        .background(Color.black)
                        .cornerRadius(8)
                }
                .padding()
                
                Spacer()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(
                LinearGradient(
                    gradient: Gradient(colors: [.purple, .blue]),
                    startPoint: .top,
                    endPoint: .bottom
                )
                .edgesIgnoringSafeArea(.all)
            )
            .navigationTitle("Euro 2024")
        }
    }
}

struct DetailsView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 20) {
                TitleView()
                IntroView()
                HostNationView()
                TeamsToWatchView()
                EmergingStarsView()
                FanExperienceView()
                SustainabilityInitiativesView()
            }
            .padding()
        }
        .navigationTitle("Euro 2024 Details")
    }
}

struct TitleView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Excitement Builds for UEFA Euro 2024")
                .font(.title)
            
            Text("As football fans around the globe eagerly await the start of the UEFA Euro 2024, the excitement is palpable. This prestigious tournament, which brings together the best national teams in Europe, promises thrilling matches, unforgettable moments, and a celebration of the beautiful game.")
        }
    }
}

struct IntroView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Introduction")
                .font(.title)
            
            Text("Let's delve into what we can expect from Euro 2024, from the host nation to the teams to watch.")
        }
    }
}

struct HostNationView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("The Host Nation: Germany")
                .font(.title)
            
            Text("Germany, a country with a rich footballing history, is set to host Euro 2024. Known for its passionate fans and state-of-the-art stadiums, Germany provides an ideal backdrop for this major tournament. The matches will be played across several iconic cities, including Berlin, Munich, and Frankfurt, offering fans a chance to experience the nation's vibrant culture alongside top-tier football.")
        }
    }
}

struct TeamsToWatchView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Teams to Watch")
                .font(.title)
            
            Text("""
1. France: The defending World Cup champions have a squad brimming with talent. With players like Kylian Mbappe and N'Golo Kante, France is a strong contender for the title.
2. Italy: The winners of Euro 2020, Italy boasts a blend of experienced players and exciting young talents. Their disciplined approach and tactical acumen make them a formidable opponent.
3. England: Always a team to watch, England's squad features a mix of youth and experience. With stars like Harry Kane and emerging talents such as Phil Foden, they aim to end their long wait for a major trophy.
4. Germany: As hosts, Germany will have the home advantage. With a new generation of players coming through, they will be keen to impress on their own turf.
""")
        }
    }
}

struct EmergingStarsView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Emerging Stars")
                .font(.title)
            
            Text("Every major tournament sees the emergence of new stars who captivate fans with their performances. Keep an eye on players like Pedri (Spain), Jude Bellingham (England), and Jamal Musiala (Germany) – young talents who could make a significant impact.")
        }
    }
}

struct FanExperienceView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Fan Experience")
                .font(.title)
            
            Text("Euro 2024 promises to be more than just a football tournament; it's set to be a festival of culture and sport. Fans can look forward to fan zones, live music, and cultural events in the host cities, making it a complete experience for visitors.")
        }
    }
}

struct SustainabilityInitiativesView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Text("Sustainability Initiatives")
                .font(.title)
            
            Text("In line with global efforts to promote sustainability, Euro 2024 will incorporate several green initiatives. From eco-friendly stadiums to sustainable transportation options, the tournament aims to minimize its environmental impact.")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

//
//  DemoSwift2App.swift
//  DemoSwift2
//
//  Created by RPS on 07/06/24.
//

import SwiftUI

@main
struct demoswiftApp: App {
    var body: some Scene {
        WindowGroup {
            TabView {
                ContentView().tabItem{Label("Blog", systemImage: "list.dash")}
                HomeView().tabItem{Label("Home", systemImage: "house")}
                PaymentView().tabItem{Label("Payment", systemImage: "square.and.pencil")}
            }
            
        }
    }
}

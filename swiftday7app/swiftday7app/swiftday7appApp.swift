//
//  swiftday7appApp.swift
//  swiftday7app
//
//  Created by RPS on 12/06/24.
//

import SwiftUI

@main
struct AdvancedSwiftUIAppApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}

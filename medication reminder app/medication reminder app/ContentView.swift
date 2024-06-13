//
//  ContentView.swift
//  medication reminder app
//
//  Created by RPS on 04/06/24.
//


import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Welcome to MedReminder")
                    .font(.largeTitle)
                    .padding()
                
                NavigationLink(destination: ReminderListView()) {
                    Text("View Reminders")
                        .font(.title2)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
                .padding()
                
                NavigationLink(destination: AddReminderView()) {
                    Text("Add New Reminder")
                        .font(.title2)
                        .padding()
                        .background(Color.green)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
                .padding()
            }
            .navigationTitle("MedReminder")
        }
    }
}

struct ReminderListView: View {
    var body: some View {
        Text("Reminder List View")
            .navigationTitle("Reminders")
    }
}

struct AddReminderView: View {
    var body: some View {
        Text("Add Reminder View")
            .navigationTitle("Add Reminder")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

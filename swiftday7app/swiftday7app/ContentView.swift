//
//  ContentView.swift
//  swiftday7app
//
//  Created by RPS on 12/06/24.
//
import SwiftUI

struct ContentView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @FetchRequest(sortDescriptors: [NSSortDescriptor(keyPath: \Medication.time, ascending: true)], animation: .default)
    private var medications: FetchedResults<Medication>
    
    @ObservedObject var currencyViewModel = CurrencyViewModel()
    
    var body: some View {
        TabView {
            NavigationView {
                VStack {
                    List(currencyViewModel.currencies) { currency in
                        HStack {
                            Text(currency.code)
                            Spacer()
                            Text("\(currency.rate, specifier: "%.2f")")
                        }
                    }
                    .navigationTitle("Currency Rates")
                    .onAppear {
                        currencyViewModel.fetchCurrencies()
                    }
                }
            }
            .tabItem {
                Label("Currencies", systemImage: "dollarsign.circle")
            }
            
            NavigationView {
                VStack {
                    List {
                        ForEach(medications) { medication in
                            HStack {
                                VStack(alignment: .leading) {
                                    Text(medication.name ?? "")
                                        .font(.headline)
                                    Text(medication.dosage ?? "")
                                }
                                Spacer()
                                Text(medication.time ?? Date(), style: .time)
                            }
                        }
                    }
                    .navigationTitle("Medications")
                }
            }
            .tabItem {
                Label("Medications", systemImage: "pills")
            }
        }
        .preferredColorScheme(.dark) // For Dark Mode support
        .environment(\.dynamicTypeSize, .accessibility3) // Dynamic text sizing
    }
}

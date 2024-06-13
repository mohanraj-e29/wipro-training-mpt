//
//  ContentView.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//
import SwiftUI

struct ContentView: View {
    @StateObject var transactionManager = TransactionManager()
    
    var body: some View {
        TabView {
            DashboardView(transactionManager: transactionManager)
                .tabItem {
                    Image(systemName: "chart.pie.fill")
                    Text("Dashboard")
                }
            
            TransactionListView(transactionManager: transactionManager)
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("Transactions")
                }
            
            AddTransactionView(transactionManager: transactionManager)
                .tabItem {
                    Image(systemName: "plus.circle.fill")
                    Text("Add Transaction")
                }
        }
    }
}

// Preview
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

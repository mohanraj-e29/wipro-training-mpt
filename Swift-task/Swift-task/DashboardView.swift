//
//  DashboardView.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//

import SwiftUI

struct DashboardView: View {
    @ObservedObject var transactionManager: TransactionManager
    
    var body: some View {
        VStack {
            Text("Current Balance: $\(transactionManager.currentBalance, specifier: "%.2f")")
                .font(.title)
                .padding()
            
            List(transactionManager.spendingTrends, id: \.self) { trend in
                Text("$\(trend, specifier: "%.2f")")
            }
        }
        .navigationBarTitle("Dashboard")
    }
}

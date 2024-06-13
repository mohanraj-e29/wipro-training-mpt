//
//  TransactionRow.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//
import SwiftUI

struct TransactionRow: View {
    let transaction: Transaction
    var onDelete: () -> Void // Closure to handle delete action
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(transaction.title)
                .font(.headline)
            Text("$\(transaction.amount, specifier: "%.2f")")
                .foregroundColor(transaction.amount >= 0 ? .green : .red)
        }
        .gesture(
            DragGesture()
                .onEnded { _ in
                    self.onDelete() // Call onDelete closure
                }
        )
        .animation(.default) // Add animation
    }
}

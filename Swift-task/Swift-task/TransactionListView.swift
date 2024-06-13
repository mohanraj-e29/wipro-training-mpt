//
//  TransactionListView.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//

import SwiftUI

struct TransactionListView: View {
    @ObservedObject var transactionManager: TransactionManager
    
    var body: some View {
        NavigationView {
            List {
                ForEach(transactionManager.transactions) { transaction in
                    TransactionRow(transaction: transaction) {
                        if let index = transactionManager.transactions.firstIndex(where: { $0.id == transaction.id }) {
                            withAnimation {
                                transactionManager.deleteTransaction(at: IndexSet(integer: index))
                            }
                        }
                    }
                }
                .onDelete(perform: deleteTransaction)
            }
            .navigationBarTitle("Transactions")
            .navigationBarItems(trailing: NavigationLink(destination: AddTransactionView(transactionManager: transactionManager)) {
                Image(systemName: "plus")
            })
        }
    }
    
    // Method to delete transaction
    private func deleteTransaction(at offsets: IndexSet) {
        withAnimation {
            transactionManager.deleteTransaction(at: offsets)
        }
    }
}

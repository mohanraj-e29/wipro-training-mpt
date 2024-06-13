//
//  AddTransactionView.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//
import SwiftUI

struct AddTransactionView: View {
    @ObservedObject var transactionManager: TransactionManager
    @State private var title = ""
    @State private var amount = ""
    @State private var isIncome = true
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        NavigationView {
            Form {
                Section(header: Text("Transaction Details")) {
                    TextField("Title", text: $title)
                    TextField("Amount", text: $amount)
                        .keyboardType(.decimalPad)
                }
                
                Section(header: Text("Category")) {
                    Toggle("Income", isOn: $isIncome)
                }
                
                Section {
                    Button("Save") {
                        guard let amount = Double(self.amount), !self.title.isEmpty else { return }
                        let transaction = Transaction(title: self.title, amount: self.isIncome ? amount : -amount)
                        withAnimation {
                            self.transactionManager.saveTransaction(transaction)
                        }
                        self.presentationMode.wrappedValue.dismiss() // Dismiss the view after saving
                    }
                }
            }
            .navigationBarTitle("Add Transaction")
        }
    }
}

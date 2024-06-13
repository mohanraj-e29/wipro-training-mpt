//
//  TransactionManager.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//

import Foundation
import Combine

class TransactionManager: ObservableObject {
    @Published var transactions: [Transaction] = []
    private let transactionService = TransactionService()
    private var cancellables = Set<AnyCancellable>()
    
    init() {
        loadTransactions()
    }
    
    // Method to load transactions
    func loadTransactions() {
        transactionService.fetchTransactions()
            .sink(receiveCompletion: { _ in }, receiveValue: { [weak self] transactions in
                self?.transactions = transactions
            })
            .store(in: &cancellables)
    }
    
    // Method to save transaction
    func saveTransaction(_ transaction: Transaction) {
        transactionService.saveTransaction(transaction)
            .sink(receiveCompletion: { _ in }, receiveValue: { [weak self] _ in
                self?.transactions.append(transaction) // Update the transactions list
            })
            .store(in: &cancellables)
    }
    
    // Method to delete transaction
    func deleteTransaction(at indexSet: IndexSet) {
        transactions.remove(atOffsets: indexSet)
    }
    
    // Method to calculate current balance
    var currentBalance: Double {
        transactions.map { $0.amount }.reduce(0, +)
    }
    
    // Method to get spending trends
    var spendingTrends: [Double] {
        transactions.map { $0.amount }
    }
}

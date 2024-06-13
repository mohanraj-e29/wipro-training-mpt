//
//  TransactionService.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//

import Foundation
import Combine

class TransactionService {
    func fetchTransactions() -> AnyPublisher<[Transaction], Never> {
        // Simulate fetching transactions asynchronously
        return Just([
            Transaction(title: "Salary", amount: 3000),
            Transaction(title: "Rent", amount: -1000),
            Transaction(title: "Groceries", amount: -200),
            Transaction(title: "Bonus", amount: 500)
        ])
        .delay(for: 1, scheduler: DispatchQueue.main) // Simulate delay
        .eraseToAnyPublisher()
    }
    
    func saveTransaction(_ transaction: Transaction) -> AnyPublisher<Void, Error> {
        // Simulate saving transaction asynchronously
        return Just(())
            .delay(for: 1, scheduler: DispatchQueue.main) // Simulate delay
            .setFailureType(to: Error.self)
            .eraseToAnyPublisher()
    }
}

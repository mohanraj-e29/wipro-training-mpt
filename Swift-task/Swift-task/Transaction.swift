//
//  Transaction.swift
//  Swift-task
//
//  Created by RPS on 11/06/24.
//
import Foundation

struct Transaction: Identifiable {
    var id = UUID()
    var title: String
    var amount: Double
}

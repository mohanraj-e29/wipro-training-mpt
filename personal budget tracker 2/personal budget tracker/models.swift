//
//  models.swift
//  personal budget tracker
//
//  Created by RPS on 04/06/24.
//

import Foundation

struct Expense: Identifiable {
    var id: Int
    let date: Date
    let category: String
    let amount: Double
    let description: String?
}

struct Income: Identifiable {
    var id: Int
    let date: Date
    let source: String
    let amount: Double
    let description: String?
}

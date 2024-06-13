//
//  ExchangeRate.swift
//  CurrencyExchange
//
//  Created by RPS on 12/06/24.
//

import Foundation

struct ExchangeRateResponse: Codable {
    let rates: [String: Double]
    let base: String
    let date: String
}

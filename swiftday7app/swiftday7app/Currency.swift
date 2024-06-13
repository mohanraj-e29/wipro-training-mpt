//
//  Currency.swift
//  swiftday7app
//
//  Created by RPS on 12/06/24.
//

import Foundation

struct Currency: Identifiable, Codable {
    let id = UUID()
    let code: String
    let rate: Double

    enum CodingKeys: String, CodingKey {
        case code
        case rate
    }
}

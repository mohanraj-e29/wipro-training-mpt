//
//  CurrencyViewModel.swift
//  swiftday7app
//
//  Created by RPS on 12/06/24.
//

import Foundation

class CurrencyViewModel: ObservableObject {
    @Published var currencies: [Currency] = []

    func fetchCurrencies() {
        guard let url = URL(string: "https://api.exchangerate-api.com/v4/latest/USD") else {
            return
        }

        URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data else { return }

            do {
                let decodedResponse = try JSONDecoder().decode(CurrencyResponse.self, from: data)
                DispatchQueue.main.async {
                    self.currencies = decodedResponse.rates.map { Currency(code: $0.key, rate: $0.value) }
                }
            } catch {
                print("Error decoding JSON:", error)
            }
        }.resume()
    }
}

struct CurrencyResponse: Codable {
    let rates: [String: Double]
}

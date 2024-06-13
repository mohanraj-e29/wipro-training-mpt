//
//  NetworkManager.swift
//  CurrencyExchange
//
//  Created by RPS on 12/06/24.
//

import Foundation

class NetworkManager: ObservableObject {
    @Published var exchangeRates: [String: Double] = [:]

    private let apiKey = "YOUR_API_KEY"
    private let baseURL = "https://api.exchangerate-api.com/v4/latest/USD"

    func fetchExchangeRates() {
        guard let url = URL(string: baseURL) else { return }
        
        URLSession.shared.dataTask(with: url) { data, response, error in
            if let data = data {
                do {
                    let decodedResponse = try JSONDecoder().decode(ExchangeRateResponse.self, from: data)
                    DispatchQueue.main.async {
                        self.exchangeRates = decodedResponse.rates
                    }
                } catch {
                    print("Error decoding data: \(error)")
                }
            }
        }.resume()
    }
}

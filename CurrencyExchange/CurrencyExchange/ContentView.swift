//
//  ContentView.swift
//  CurrencyExchange
//
//  Created by RPS on 12/06/24.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var networkManager = NetworkManager()
    @State private var amount: String = ""
    @State private var selectedCurrency = "EUR"
    @Environment(\.sizeCategory) var sizeCategory
    
    var convertedAmount: Double {
        let amountInUSD = Double(amount) ?? 0
        let rate = networkManager.exchangeRates[selectedCurrency] ?? 1
        return amountInUSD * rate
    }
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "dollarsign.circle")
                    .resizable()
                    .frame(width: 30, height: 30)
                    .padding(.trailing, 10)
                Text("Currency Converter")
                    .font(.largeTitle)
                    
                    .foregroundColor(.white)
            }
            .padding()
            
            VStack(alignment: .leading) {
                Text("Enter Amount in USD:")
                    .foregroundColor(.white)
                TextField("Amount in USD", text: $amount)
                    .keyboardType(.decimalPad)
                    .padding()
                    .background(Color.white)
                    .cornerRadius(8)
                    .accessibility(label: Text("Amount in USD"))
                    .accessibility(hint: Text("Enter the amount in US dollars"))
                    .font(.system(size: sizeCategory == .extraExtraExtraLarge ? 24 : 16))
            }
            .padding()
            
            VStack(alignment: .leading) {
                Text("Select Currency:")
                    .foregroundColor(.white)
                Picker("Currency", selection: $selectedCurrency) {
                    ForEach(networkManager.exchangeRates.keys.sorted(), id: \.self) { currency in
                        Text(currency)
                            .font(.system(size: sizeCategory == .extraExtraExtraLarge ? 24 : 16))
                    }
                }
                .pickerStyle(WheelPickerStyle())
                .background(Color.white)
                .cornerRadius(8)
                .accessibility(label: Text("Currency"))
                .accessibility(hint: Text("Select the target currency"))
            }
            .padding()
            
            VStack {
                Text("Converted Amount:")
                    .foregroundColor(.white)
                Text("\(convertedAmount, specifier: "%.2f") \(selectedCurrency)")
                    .font(.title)
                    .fontWeight(.bold)
                    .foregroundColor(.white)
                    .accessibility(label: Text("Converted Amount"))
                    .accessibility(hint: Text("Displays the converted amount in the selected currency"))
            }
            .padding()
            
            Spacer()
        }
        .background(
            LinearGradient(gradient: Gradient(colors: [.blue, .purple]), startPoint: .top, endPoint: .bottom)
                .edgesIgnoringSafeArea(.all)
        )
        .onAppear {
            networkManager.fetchExchangeRates()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

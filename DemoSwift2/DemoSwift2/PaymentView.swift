
//
//  PaymentView.swift
//  demoswift
//
//
//

import SwiftUI

struct Subscriptions: Identifiable {
    var id: String
    var price: Double
    var duration: Date
    var name: String
    var isSubscribed: Bool
}

struct PaymentView: View {
    @State var list = [
        Subscriptions(id: "1", price: 30, duration: Date(), name: "BGMI Royal Pass", isSubscribed: true),
        Subscriptions(id: "2", price: 46, duration: Date(), name: "Netflix", isSubscribed: true),
        Subscriptions(id: "3", price: 10, duration: Date(), name: "Spotify", isSubscribed: true)
    ]
    
    @State private var subscribeToAll = true

    var body: some View {
       /* Form {
            Section {
                List {
                    ForEach($list) { $subscription in
                        HStack {
                            Text(subscription.name)
                            Spacer()
                            Toggle("", isOn: $subscription.isSubscribed)
                                .labelsHidden()
                        }
                    }
                    .onDelete(perform: deleteSubs)
                }
            }*/
        Form {
                    Section {
                        List {
                            ForEach($list) { $list in
                                Text(list.name)
                                    
                            }.onDelete(perform: deleteSubs)
                        }
                        
                    }
            Section {
                Toggle("Subscribe to all", isOn: $subscribeToAll)
                    .onChange(of: subscribeToAll) { newValue in
                        for i in list.indices {
                            list[i].isSubscribed = newValue
                        }
                    }
            }
        }
    }
    
    func deleteSubs(at offsets: IndexSet) {
        list.remove(atOffsets: offsets)
    }
}

struct PaymentView_Previews: PreviewProvider {
    static var previews: some View {
        PaymentView()
    }
}

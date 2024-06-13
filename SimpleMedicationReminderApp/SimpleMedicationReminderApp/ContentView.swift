//
//  ContentView.swift
//  SimpleMedicationReminderApp
//
//  Created by RPS on 12/06/24.
//

import SwiftUI

struct Medication: Identifiable {
    var id = UUID()
    var name: String
    var dosage: String
    var schedule: Date
    var isCompleted: Bool = false
}

class MedicationViewModel: ObservableObject {
    @Published var medications: [Medication] = []
    
    func addMedication(name: String, dosage: String, schedule: Date) {
        let newMedication = Medication(name: name, dosage: dosage, schedule: schedule)
        medications.append(newMedication)
    }
    
    func deleteMedication(at offsets: IndexSet) {
        medications.remove(atOffsets: offsets)
    }
    
    func toggleCompletion(for medication: Medication) {
        if let index = medications.firstIndex(where: { $0.id == medication.id }) {
            medications[index].isCompleted.toggle()
        }
    }
}

struct ContentView: View {
    @State private var newMedicationName: String = ""
    @State private var newDosage: String = ""
    @State private var newSchedule: Date = Date()
    @ObservedObject private var viewModel = MedicationViewModel()

    var body: some View {
        VStack {
            Text("Medication List")
                .font(.largeTitle)
                .padding()

            List {
                ForEach(viewModel.medications) { medication in
                    HStack {
                        VStack(alignment: .leading) {
                            Text(medication.name)
                                .font(.headline)
                            Text("Dosage: \(medication.dosage)")
                            Text("Schedule: \(medication.schedule, style: .date)")
                        }
                        Spacer()
                        Button(action: {
                            viewModel.toggleCompletion(for: medication)
                        }) {
                            Image(systemName: medication.isCompleted ? "checkmark.square" : "square")
                        }
                    }
                }
                .onDelete(perform: viewModel.deleteMedication)
            }

            HStack {
                VStack {
                    TextField("Medication Name", text: $newMedicationName)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding()

                    TextField("Dosage", text: $newDosage)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding()

                    DatePicker("Schedule", selection: $newSchedule, displayedComponents: .date)
                        .padding()
                }

                Button(action: {
                    viewModel.addMedication(name: newMedicationName, dosage: newDosage, schedule: newSchedule)
                    newMedicationName = ""
                    newDosage = ""
                    newSchedule = Date()
                }) {
                    Text("Add")
                }
                .padding()
            }
        }
        .padding()
        .background(Color(UIColor.systemBackground))
        .navigationBarTitle("Medications", displayMode: .inline)
        .navigationBarItems(trailing: EditButton())
    }
}

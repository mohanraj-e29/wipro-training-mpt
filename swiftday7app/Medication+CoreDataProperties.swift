//
//  Medication+CoreDataProperties.swift
//  swiftday7app
//
//  Created by RPS on 12/06/24.
//
import Foundation
import CoreData

extension Medication {
    @nonobjc public class func fetchRequest() -> NSFetchRequest<Medication> {
        return NSFetchRequest<Medication>(entityName: "Medication")
    }

    @NSManaged public var name: String?
    @NSManaged public var dosage: String?
    @NSManaged public var time: Date?
}

extension Medication : Identifiable {
}

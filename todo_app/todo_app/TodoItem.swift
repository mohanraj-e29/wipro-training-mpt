//
//  TodoItem.swift
//  todo_app
//
//  Created by RPS on 10/06/24.
//
import Foundation

struct TodoItem: Identifiable, Codable {
    let id: String
    let title: String
    var completed: Bool
}

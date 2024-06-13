//
//  TodoListViewModel.swift
//  todo_app
//
//  Created by RPS on 08/06/24.
//
// TodoListViewModel.swift


import Foundation

class TodoListViewModel: ObservableObject {
    @Published var todoItems: [TodoItem] = []

    // Function to fetch todo items from the server
    func fetchTodoItems() {
        guard let url = URL(string: "https://todo-app-zaj2.onrender.com/todos") else {
            return
        }

        URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data else {
                return
            }

            do {
                let fetchedItems = try JSONDecoder().decode([TodoItem].self, from: data)
                DispatchQueue.main.async {
                    self.todoItems = fetchedItems
                }
            } catch {
                print("Error decoding JSON:", error)
            }
        }.resume()
    }

    // Function to add a new todo item to the server
    func addTodoItem(title: String) {
        guard let url = URL(string: "https://todo-app-zaj2.onrender.com/todos") else {
            return
        }

        let todoItem = TodoItem(id: UUID().uuidString, title: title, completed: false)
        guard let encoded = try? JSONEncoder().encode(todoItem) else {
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = encoded

        URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data else {
                return
            }
            do {
                let savedItem = try JSONDecoder().decode(TodoItem.self, from: data)
                DispatchQueue.main.async {
                    self.todoItems.append(savedItem)
                }
            } catch {
                print("Error decoding JSON:", error)
            }
        }.resume()
    }

    // Function to delete a todo item from the server
    func deleteTodoItem(at index: Int) {
        let todoItem = todoItems[index]
        guard let url = URL(string: "https://todo-app-zaj2.onrender.com/todos/\(todoItem.id)") else {
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"

        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                print("Error deleting item:", error)
                return
            }

            DispatchQueue.main.async {
                self.todoItems.remove(at: index)
            }
        }.resume()
    }

    // Function to toggle the completion status of a todo item
    func toggleTodoItemCompletion(_ todoItem: TodoItem) {
        guard let url = URL(string: "https://todo-app-zaj2.onrender.com/todos/\(todoItem.id)") else {
            return
        }

        var updatedTodoItem = todoItem
        updatedTodoItem.completed.toggle()
        guard let encoded = try? JSONEncoder().encode(updatedTodoItem) else {
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = "PATCH"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = encoded

        URLSession.shared.dataTask(with: request) { data, response, error in
            guard let _ = data else {
                return
            }

            DispatchQueue.main.async {
                if let index = self.todoItems.firstIndex(where: { $0.id == todoItem.id }) {
                    self.todoItems[index] = updatedTodoItem
                }
            }
        }.resume()
    }
}

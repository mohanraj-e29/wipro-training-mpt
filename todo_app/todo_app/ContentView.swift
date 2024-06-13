
// Import SwiftUI framework to create UI elements

// ContentView.swift

import SwiftUI

struct ContentView: View {
    @State private var newTodoItem = ""
    @ObservedObject private var viewModel = TodoListViewModel()
    @State private var isLoggedIn = false
    @State private var username = ""
    @State private var password = ""
    @State private var loginError = false
    @State private var showLoginSuccess = false
    
    var body: some View {
        if isLoggedIn {
            VStack {
                if showLoginSuccess {
                    Text("Login Successful!")
                        .foregroundColor(.green)
                        .padding()
                        .background(Color.white)
                        .cornerRadius(10)
                        .shadow(radius: 10)
                        .transition(.opacity)
                        .onAppear {
                            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                                withAnimation {
                                    showLoginSuccess = false
                                }
                            }
                        }
                }
                
                Text("Todo List 📝")
                    .font(.largeTitle)
                    .padding()
                
                List {
                    ForEach(viewModel.todoItems.indices, id: \.self) { index in
                        HStack {
                            Button(action: {
                                viewModel.toggleTodoItemCompletion(viewModel.todoItems[index])
                            }) {
                                Image(systemName: viewModel.todoItems[index].completed ? "checkmark.circle.fill" : "circle")
                                    .foregroundColor(viewModel.todoItems[index].completed ? .green : .gray)
                            }
                            
                            Text(viewModel.todoItems[index].title)
                                .strikethrough(viewModel.todoItems[index].completed)
                                .foregroundColor(viewModel.todoItems[index].completed ? .gray : .primary)
                            Spacer()
                            Button(action: {
                                viewModel.deleteTodoItem(at: index)
                            }) {
                                Image(systemName: "trash")
                                    .foregroundColor(.red)
                            }
                        }
                    }
                }
                .cornerRadius(10)
                
                HStack {
                    TextField("Enter new Todo item", text: $newTodoItem)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding()
                    
                    Button(action: {
                        viewModel.addTodoItem(title: newTodoItem)
                        newTodoItem = ""
                    }) {
                        Text("Add")
                    }
                    .padding()
                }
            }
            .padding()
            .onAppear {
                viewModel.fetchTodoItems()
            }
        } else {
            LoginView(isLoggedIn: $isLoggedIn, username: $username, password: $password, loginError: $loginError, showLoginSuccess: $showLoginSuccess)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

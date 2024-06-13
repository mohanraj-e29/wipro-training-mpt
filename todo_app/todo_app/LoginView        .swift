//
//  LoginView        .swift
//  todo_app
//
//  Created by RPS on 10/06/24.
//

import SwiftUI

struct LoginView: View {
    @Binding var isLoggedIn: Bool
    @Binding var username: String
    @Binding var password: String
    @Binding var loginError: Bool
    @Binding var showLoginSuccess: Bool
    
    var body: some View {
        ZStack {
            LinearGradient(gradient: Gradient(colors: [Color.blue, Color.purple]), startPoint: .topLeading, endPoint: .bottomTrailing)
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                Text("Welcome to Todo App")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .foregroundColor(.white)
                    .padding(.bottom, 40)
                
                VStack(spacing: 20) {
                    TextField("Username", text: $username)
                        .padding()
                        .background(Color.white)
                        .cornerRadius(10)
                        .shadow(radius: 10)
                    
                    SecureField("Password", text: $password)
                        .padding()
                        .background(Color.white)
                        .cornerRadius(10)
                        .shadow(radius: 10)
                }
                .padding([.leading, .trailing], 27.5)
                
                Button(action: {
                    authenticateUser()
                }) {
                    Text("Login")
                        .frame(width: 200, height: 50)
                        .background(Color.white)
                        .cornerRadius(10)
                        .foregroundColor(.blue)
                        .shadow(radius: 10)
                }
                .padding(.top, 50)
                
                if loginError {
                    Text("Invalid username or password")
                        .foregroundColor(.red)
                        .padding()
                }
            }
        }
    }
    
    private func authenticateUser() {
        // For demonstration purposes, we'll assume any username and password combination is valid
        // In a real application, you would send a request to your backend server to validate the credentials
        if !username.isEmpty && !password.isEmpty {
            DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                isLoggedIn = true
                loginError = false
                showLoginSuccess = true
            }
        } else {
            loginError = true
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(isLoggedIn: .constant(false), username: .constant(""), password: .constant(""), loginError: .constant(false), showLoginSuccess: .constant(false))
    }
}

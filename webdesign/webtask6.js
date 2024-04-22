let user = {
    name: "",
    email: "",
    age: ""
  };
  
  function changeName() {
    let newName = prompt("Enter the new name:");
    if (newName !== null && newName.trim() !== "") {
      user.name = newName;
      displayUserInfo();
    }
  }
  
  function updateEmail() {
    let newEmail = prompt("Enter the new email:");
    if (newEmail !== null && newEmail.trim() !== "") {
      user.email = newEmail;
      displayUserInfo();
    }
  }
  
  function calculateBirthYear() {
    let currentYear = new Date().getFullYear();
    return currentYear - user.age;
  }
  
  function displayUserInfo() {
    document.getElementById("name").value = user.name;
    document.getElementById("email").value = user.email;
    document.getElementById("age").value = user.age;
  }
  
  function displayBirthYear() {
    let currentYear = new Date().getFullYear(); // Get the current year
    let birthYear = calculateBirthYear();
    if (!isNaN(birthYear)) {
      alert("User's birth year: " + birthYear);
    } else {
      alert("Please enter a valid age to calculate birth year.");
    }
  }
  
  
  displayUserInfo();
  